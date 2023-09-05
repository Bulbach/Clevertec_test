package org.example.repository.jdbc;

import org.example.jdbc.mapper.ResultSetMapper;
import org.example.jdbc.mapper.impl.ClientResultSetMapper;
import org.example.jdbc.query.CrudJdbcSqlQueryHolder;
import org.example.jdbc.query.impl.ClientSqlQueryHolderImpl;
import org.example.jdbc.statement.StatementInitializer;
import org.example.jdbc.statement.impl.ClientStatementInitializer;
import org.example.model.Client;
import org.example.repository.ClientRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientDaoJdbc extends AbstractCrudDao<Client> implements ClientRepo {
    private static final Logger logger = LoggerFactory.getLogger(ClientDaoJdbc.class);
       @Override
    protected Logger getLogger() {
        return logger;
    }

    @Override
    protected CrudJdbcSqlQueryHolder getSqlHolder() {
        return new ClientSqlQueryHolderImpl();
    }

    @Override
    protected StatementInitializer<Client> queryTranslate() {
        return new ClientStatementInitializer();
    }

    @Override
    protected ResultSetMapper<Client> abstractResultSet() {
        return new ClientResultSetMapper();
    }
}
