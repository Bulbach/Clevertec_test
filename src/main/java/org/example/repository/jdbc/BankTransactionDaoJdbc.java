package org.example.repository.jdbc;

import org.example.jdbc.mapper.ResultSetMapper;
import org.example.jdbc.mapper.impl.TransactionResultSetMapper;
import org.example.jdbc.query.CrudJdbcSqlQueryHolder;
import org.example.jdbc.query.impl.TransactionSqlQueryHolderImpl;
import org.example.jdbc.statement.StatementInitializer;
import org.example.jdbc.statement.impl.TransactionStatementInitializer;
import org.example.model.BankTransaction;
import org.example.repository.TransactionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BankTransactionDaoJdbc extends AbstractCrudDao<BankTransaction> implements TransactionRepo {
    private static final Logger logger = LoggerFactory.getLogger(ClientDaoJdbc.class);
    @Override
    protected Logger getLogger() {
        return logger;
    }

    @Override
    protected CrudJdbcSqlQueryHolder getSqlHolder() {
        return new TransactionSqlQueryHolderImpl();
    }

    @Override
    protected StatementInitializer<BankTransaction> queryTranslate() {
        return new TransactionStatementInitializer();
    }

    @Override
    protected ResultSetMapper<BankTransaction> abstractResultSet() {
        return new TransactionResultSetMapper();
    }
}