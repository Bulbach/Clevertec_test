package org.example.repository.jdbc;

import org.example.connector.DataBaseConnector;

public abstract class BaseAbstractJdbcDao {
    private static final DataBaseConnector CONNECTOR = DataBaseConnector.getInstance();

    public DataBaseConnector getConnector() {
        return CONNECTOR;
    }


}
