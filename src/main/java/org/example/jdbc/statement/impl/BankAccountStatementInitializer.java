package org.example.jdbc.statement.impl;

import org.example.jdbc.statement.StatementInitializer;
import org.example.model.BankAccount;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankAccountStatementInitializer implements StatementInitializer<BankAccount> {
    @Override
    public void createQueryStatement(PreparedStatement ps, BankAccount bankAccount) throws SQLException {
        ps.setString(1, bankAccount.getAccountNumber());
        ps.setLong(2, bankAccount.getAccountOwner().getId());
        ps.setLong(3, bankAccount.getBankOwner().getId());
        ps.setString(4, bankAccount.getCurrency());
        ps.setDate(5, Date.valueOf(bankAccount.getOpenDate()));
        ps.setLong(6, bankAccount.getAccountBalance());
    }

    @Override
    public void updateQueryStatement(PreparedStatement ps, BankAccount bankAccount) throws SQLException {
        ps.setString(1, bankAccount.getAccountNumber());
        ps.setLong(2, bankAccount.getAccountOwner().getId());
        ps.setLong(3, bankAccount.getBankOwner().getId());
        ps.setString(4, bankAccount.getCurrency());
        ps.setDate(5, Date.valueOf(bankAccount.getOpenDate()));
        ps.setLong(6, bankAccount.getAccountBalance());
        ps.setLong(7, bankAccount.getId());
    }

    @Override
    public void setByIdQueryStatement(PreparedStatement ps, Long id) throws SQLException {
        ps.setLong(1, id);
    }

    @Override
    public void createWithIdQueryStatement(PreparedStatement ps, BankAccount bankAccount) throws SQLException {
        ps.setLong(1, bankAccount.getId());
        ps.setString(2, bankAccount.getAccountNumber());
        ps.setLong(3, bankAccount.getAccountOwner().getId());
        ps.setLong(4, bankAccount.getBankOwner().getId());
        ps.setString(5, bankAccount.getCurrency());
        ps.setDate(6, Date.valueOf(bankAccount.getOpenDate()));
        ps.setLong(7, bankAccount.getAccountBalance());
    }
    public void depositQueryStatement(PreparedStatement ps, BankAccount bankAccount) throws SQLException {
        ps.setString(1, bankAccount.getAccountNumber());
    }

    public void updateAccountBalanceQueryStatement(PreparedStatement ps, BankAccount bankAccount) throws SQLException {
        ps.setLong(1, bankAccount.getAccountBalance());
        ps.setString(2, bankAccount.getAccountNumber());
    }
}
