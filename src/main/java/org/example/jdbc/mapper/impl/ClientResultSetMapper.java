package org.example.jdbc.mapper.impl;

import org.example.jdbc.mapper.ResultSetMapper;
import org.example.model.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientResultSetMapper implements ResultSetMapper<Client> {
    @Override
    public Client processResultSet(ResultSet rs) throws SQLException {
        return Client.builder()
                .id(rs.getLong("id"))
                .personalIdentifier(rs.getString("personalIdentifier"))
                .first_name(rs.getString("first_name"))
                .surname(rs.getString("surname"))
                .build();
    }
}
