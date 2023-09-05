package org.example.jdbc.mapper.impl;

import org.example.jdbc.mapper.ResultSetMapper;
import org.example.model.Bank;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankResultSetMapper implements ResultSetMapper<Bank> {
    @Override
    public Bank processResultSet(ResultSet rs) throws SQLException {
        return Bank.builder()
                .id(rs.getLong("id"))
                .brand(rs.getString("brand"))
                .build();
    }
}
