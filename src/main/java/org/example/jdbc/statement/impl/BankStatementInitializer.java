package org.example.jdbc.statement.impl;

import org.example.jdbc.statement.StatementInitializer;
import org.example.model.Bank;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankStatementInitializer implements StatementInitializer<Bank> {
    @Override
    public void createQueryStatement(PreparedStatement ps, Bank bank) throws SQLException {
        ps.setString(1, bank.getBrand());
    }
    @Override
    public void updateQueryStatement(PreparedStatement ps, Bank bank) throws SQLException {
        ps.setString(1, bank.getBrand());
        ps.setLong(2, bank.getId());
    }
    @Override
    public void setByIdQueryStatement(PreparedStatement ps, Long id) throws SQLException {
        ps.setLong(1, id);
    }

    @Override
    public void createWithIdQueryStatement(PreparedStatement ps, Bank bank) throws SQLException {
        ps.setLong(1, bank.getId());
        ps.setString(2, bank.getBrand());
    }
}
