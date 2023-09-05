package org.example.jdbc.query.impl;

import org.example.jdbc.query.CrudJdbcSqlQueryHolder;

public class TransactionSqlQueryHolderImpl implements CrudJdbcSqlQueryHolder {
    @Override
    public String getByIdSql() {
        return "select id, time_operation, type_transaction, sender_account, sender_balance_before, sender_bank, " +
                "recipient_account, recipient_balance_before, recipient_bank,  amount from bank_transactions where id = ?";
    }

    @Override
    public String getAllSql() {
        return "select id, time_operation, type_transaction, sender_account, sender_balance_before, sender_bank, " +
                "recipient_account, recipient_balance_before, recipient_bank,  amount from bank_transactions";
    }

    public String getAllSqlByAccountNumber() {
        return "select id, time_operation, type_transaction, sender_account, sender_balance_before, sender_bank, " +
                "recipient_account, recipient_balance_before, recipient_bank,  amount from bank_transactions where sender_account = ?";
    }

    @Override
    public String updateSql() {
        return "null";
    }

    @Override
    public String createSql() {
        return "insert into bank_transactions (time_operation, type_transaction, sender_account, sender_balance_before, sender_bank, " +
                "recipient_account, recipient_balance_before, recipient_bank,  amount) values (?,?,?,?,?,?,?,?,?)";
    }

    @Override
    public String deleteSql() {
        return "null";
    }

    @Override
    public String createWithIdSql() {
        return "null";
    }
}
