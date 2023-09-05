package org.example.jdbc.mapper.impl;

import org.example.jdbc.mapper.ResultSetMapper;
import org.example.model.BankTransaction;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionResultSetMapper implements ResultSetMapper<BankTransaction> {
    @Override
    public BankTransaction processResultSet(ResultSet rs) throws SQLException {
        return BankTransaction.builder()
                .id(rs.getLong("id"))
                .timeOperation(rs.getTimestamp("time_operation").toLocalDateTime())
                .typeTransaction(rs.getString("type_transaction"))
                .senderAccount(rs.getString("sender_account"))
                .senderBalanceBefore(rs.getLong("sender_balance_before"))
                .senderBank(rs.getString("sender_bank"))
                .recipientAccount(rs.getString("recipient_account"))
                .recipientBalanceBefore(rs.getLong("recipient_balance_before"))
                .recipientBank(rs.getString("recipient_bank"))
                .amountTransaction(rs.getLong("amount"))
                .build();
    }
}
