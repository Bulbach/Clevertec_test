package org.example.repository.jdbc;

import org.example.jdbc.mapper.ResultSetMapper;
import org.example.jdbc.mapper.impl.BankResultSetMapper;
import org.example.jdbc.query.CrudJdbcSqlQueryHolder;
import org.example.jdbc.query.impl.BankSqlQueryHolderImpl;
import org.example.jdbc.statement.StatementInitializer;
import org.example.jdbc.statement.impl.BankStatementInitializer;
import org.example.model.Bank;
import org.example.repository.BankRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankDaoJdbc extends AbstractCrudDao<Bank>  implements BankRepo {
    private static final Logger logger = LoggerFactory.getLogger(BankDaoJdbc.class);

    @Override
    protected Logger getLogger() {
        return logger;
    }
    @Override
    protected CrudJdbcSqlQueryHolder getSqlHolder() {
        return new BankSqlQueryHolderImpl();
    }


    @Override
    protected StatementInitializer<Bank> queryTranslate() {
        return new BankStatementInitializer();
    }

    @Override
    protected ResultSetMapper<Bank> abstractResultSet() {
        return new BankResultSetMapper();
    }

}