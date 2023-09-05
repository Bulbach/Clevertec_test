package org.example.jdbc.mapper.impl;

import org.example.jdbc.mapper.ResultSetMapper;
import org.example.model.Bank;
import org.example.model.BankAccount;
import org.example.model.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountResultSetMapper implements ResultSetMapper<BankAccount> {
    @Override
    public BankAccount processResultSet(ResultSet rs) throws SQLException {
        Client client = Client.builder()
                .id(rs.getLong("cid"))
                .personalIdentifier(rs.getString("personalIdentifier"))
                .first_name(rs.getString("first_name"))
                .surname(rs.getString("surname"))
                .build();
        Bank bank = Bank.builder()
                .id(rs.getLong("bid"))
                .brand(rs.getString("brand"))
                .build();

        return BankAccount.builder()
                .id(rs.getLong("id"))
                .accountNumber(rs.getString("account_number"))
                .accountOwner(client)
                .bankOwner(bank)
                .currency(rs.getString("currency"))
                .openDate(rs.getDate("open_date").toLocalDate())
                .accountBalance(rs.getLong("account_balance"))
                .build();
    }
}
