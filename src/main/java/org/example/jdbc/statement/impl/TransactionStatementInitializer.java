package org.example.jdbc.statement.impl;

import org.example.jdbc.statement.StatementInitializer;
import org.example.model.BankTransaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TransactionStatementInitializer implements StatementInitializer<BankTransaction> {

    public void createTransferQueryStatement(PreparedStatement ps, BankTransaction bankTransactionFrom,BankTransaction bankTransactionTo,String typeTransaction) throws SQLException {
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        ps.setTimestamp(1, timestamp);
        ps.setString(2,typeTransaction);
        ps.setString(3,bankTransactionFrom.getSenderAccount());
        ps.setLong(4,bankTransactionFrom.getSenderBalanceBefore());
        ps.setString(5, bankTransactionFrom.getSenderBank());
        ps.setString(6, bankTransactionTo.getRecipientAccount());
        ps.setLong(7,bankTransactionTo.getRecipientBalanceBefore());
        ps.setString(8, bankTransactionTo.getRecipientBank());
        ps.setLong(9,bankTransactionFrom.getAmountTransaction());
    }

    @Override
    public void createQueryStatement(PreparedStatement ps, BankTransaction bankTransaction) throws SQLException {
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        ps.setTimestamp(1, timestamp);
        ps.setString(2, bankTransaction.getTypeTransaction());
        ps.setString(3,bankTransaction.getSenderAccount());
        ps.setLong(4,bankTransaction.getSenderBalanceBefore());
        ps.setString(5, bankTransaction.getSenderBank());
        ps.setString(6, bankTransaction.getRecipientAccount());
        ps.setLong(7,bankTransaction.getRecipientBalanceBefore());
        ps.setString(8, bankTransaction.getRecipientBank());
        ps.setLong(9,bankTransaction.getAmountTransaction());
    }

    @Override
    public void updateQueryStatement(PreparedStatement ps, BankTransaction bankTransaction) throws SQLException {

    }

    @Override
    public void setByIdQueryStatement(PreparedStatement ps, Long id) throws SQLException {
        ps.setLong(1, id);
    }

    @Override
    public void createWithIdQueryStatement(PreparedStatement ps, BankTransaction bankTransaction) throws SQLException {

    }
    public void setBySenderAccountQueryStatement(PreparedStatement ps, BankTransaction bankTransaction) throws SQLException {
        ps.setString(1, bankTransaction.getSenderAccount());
    }
}
