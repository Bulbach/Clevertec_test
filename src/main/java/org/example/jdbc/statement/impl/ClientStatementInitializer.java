package org.example.jdbc.statement.impl;

import org.example.jdbc.statement.StatementInitializer;
import org.example.model.Client;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientStatementInitializer implements StatementInitializer<Client> {
    @Override
    public void createQueryStatement(PreparedStatement ps, Client client) throws SQLException {
            ps.setString(1, client.getPersonalIdentifier());
            ps.setString(2, client.getFirst_name());
            ps.setString(3, client.getSurname());
    }

    @Override
    public void updateQueryStatement(PreparedStatement ps, Client client) throws SQLException {
            ps.setString(1, client.getPersonalIdentifier());
            ps.setString(2, client.getFirst_name());
            ps.setString(3, client.getSurname());
            ps.setLong(4,client.getId());
    }

    @Override
    public void setByIdQueryStatement(PreparedStatement ps, Long id) throws SQLException {
        ps.setLong(1,id);
    }

    @Override
    public void createWithIdQueryStatement(PreparedStatement ps, Client client) throws SQLException {
        ps.setLong(1,client.getId());
        ps.setString(2, client.getPersonalIdentifier());
        ps.setString(3, client.getFirst_name());
        ps.setString(4, client.getSurname());
    }
}
