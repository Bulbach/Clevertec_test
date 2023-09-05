package org.example.repository.jdbc;

import lombok.Data;
import org.example.connector.DataBaseConnector;
import org.example.exceptions.AppBankException;
import org.example.jdbc.mapper.ResultSetMapper;
import org.example.jdbc.mapper.impl.BankAccountResultSetMapper;
import org.example.jdbc.mapper.impl.TransactionResultSetMapper;
import org.example.jdbc.query.CrudJdbcSqlQueryHolder;
import org.example.jdbc.query.impl.BankAccountSqlQueryHolderImpl;
import org.example.jdbc.query.impl.TransactionSqlQueryHolderImpl;
import org.example.jdbc.statement.StatementInitializer;
import org.example.jdbc.statement.impl.BankAccountStatementInitializer;
import org.example.jdbc.statement.impl.TransactionStatementInitializer;
import org.example.model.BankAccount;
import org.example.model.BankTransaction;
import org.example.repository.BankAccountRepo;
import org.example.util.CheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class BankAccountDaoJdbc extends AbstractCrudDao<BankAccount> implements BankAccountRepo {
    private static final Logger logger = LoggerFactory.getLogger(BankAccountDaoJdbc.class);


    private final BankAccountSqlQueryHolderImpl bankAccountSqlQueryHolder;
    private final BankAccountStatementInitializer bankAccountStatementInitializer;
    private final BankAccountResultSetMapper bankAccountResultSetMapper;
    private final TransactionSqlQueryHolderImpl transactionSqlQueryHolder;
    private final TransactionResultSetMapper transactionResultSetMapper;
    private final TransactionStatementInitializer transactionStatementInitializer;


    @Override
    protected Logger getLogger() {
        return logger;
    }

    @Override
    protected CrudJdbcSqlQueryHolder getSqlHolder() {
        return new BankAccountSqlQueryHolderImpl();
    }

    @Override
    protected StatementInitializer<BankAccount> queryTranslate() {
        return new BankAccountStatementInitializer();
    }

    @Override
    protected ResultSetMapper<BankAccount> abstractResultSet() {
        return new BankAccountResultSetMapper();
    }


    public void updateDepositAmount(BankAccount bankAccount, Long transferAmount) {

        try (Connection connection = DataBaseConnector.getInstance().getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(bankAccountSqlQueryHolder.checkBalanceSql())) {
            PreparedStatement updateStatement = connection.prepareStatement(bankAccountSqlQueryHolder.refillSql());
            PreparedStatement createTransaction = connection.prepareStatement(transactionSqlQueryHolder.createSql());

            bankAccountStatementInitializer.depositQueryStatement(selectStatement, bankAccount);

            connection.setAutoCommit(false);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getLong("account_balance") == bankAccount.getAccountBalance()) {
                    BankTransaction bankTransaction = BankTransaction.builder()
                            .typeTransaction(transferAmount > 0 ? "Пополнение" : "Списание")
                            .senderAccount(bankAccount.getAccountNumber())
                            .senderBalanceBefore(bankAccount.getAccountBalance())
                            .senderBank(bankAccount.getBankOwner().getBrand())
                            .recipientAccount(bankAccount.getAccountNumber())
                            .recipientBalanceBefore(bankAccount.getAccountBalance())
                            .recipientBank(bankAccount.getBankOwner().getBrand())
                            .amountTransaction(bankAccount.getAccountBalance())
                            .build();
                    CheckService.saveCheck(bankTransaction.toString());
                    bankAccount.updateBalance(transferAmount);

                    bankAccountStatementInitializer.updateAccountBalanceQueryStatement(updateStatement, bankAccount);
                    updateStatement.executeUpdate();
                    transactionStatementInitializer.createQueryStatement(createTransaction, bankTransaction);
                    createTransaction.executeUpdate();
                    connection.commit(); // Фиксация транзакции

                } else {

                    throw new AppBankException("Данные счета при обновлении были повреждены");
                }

            } catch (Exception e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true); // Возврат к автоматическому управлению транзакцией
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void transferAmount(BankAccount fromBankAccount, BankAccount toBankAccount, Long transferAmount) {

        try (Connection connection = DataBaseConnector.getInstance().getConnection();
             PreparedStatement selectStatementFrom = connection.prepareStatement(bankAccountSqlQueryHolder.checkBalanceSql());
             PreparedStatement selectStatementTo = connection.prepareStatement(bankAccountSqlQueryHolder.checkBalanceSql());
             PreparedStatement updateStatementFrom = connection.prepareStatement(bankAccountSqlQueryHolder.updateSql());
             PreparedStatement updateStatementTo = connection.prepareStatement(bankAccountSqlQueryHolder.updateSql());
             PreparedStatement createTransaction = connection.prepareStatement(transactionSqlQueryHolder.createSql())) {

            bankAccountStatementInitializer.depositQueryStatement(selectStatementFrom, fromBankAccount);
            bankAccountStatementInitializer.depositQueryStatement(selectStatementTo, toBankAccount);

            connection.setAutoCommit(false);
            try (ResultSet resultSetFrom = selectStatementFrom.executeQuery();
                 ResultSet resultSetTo = selectStatementTo.executeQuery()) {
                if ((resultSetFrom.next() && resultSetFrom.getLong("account_balance") == fromBankAccount.getAccountBalance())
                        && ((resultSetTo.next() && resultSetTo.getLong("account_balance") == toBankAccount.getAccountBalance()))) {
                    BankTransaction bankTransaction = BankTransaction.builder()
                            .typeTransaction("Перевод")
                            .senderAccount(fromBankAccount.getAccountNumber())
                            .senderBalanceBefore(fromBankAccount.getAccountBalance())
                            .senderBank(fromBankAccount.getBankOwner().getBrand())
                            .recipientAccount(toBankAccount.getAccountNumber())
                            .recipientBalanceBefore(toBankAccount.getAccountBalance())
                            .recipientBank(toBankAccount.getBankOwner().getBrand())
                            .amountTransaction(transferAmount)
                            .build();
                    CheckService.saveCheck(bankTransaction.toString());
                    fromBankAccount.updateBalance(-transferAmount);
                    toBankAccount.updateBalance(transferAmount);

                    bankAccountStatementInitializer.updateQueryStatement(updateStatementFrom, fromBankAccount);
                    bankAccountStatementInitializer.updateQueryStatement(updateStatementTo, toBankAccount);
                    transactionStatementInitializer.createQueryStatement(createTransaction, bankTransaction);
                    updateStatementFrom.executeUpdate();
                    updateStatementTo.executeUpdate();
                    createTransaction.executeUpdate();
                    connection.commit();

                }
            } catch (Exception e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true); // Возврат к автоматическому управлению транзакцией
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
