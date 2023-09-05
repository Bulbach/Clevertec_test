package org.example.jdbc.query.impl;

import org.example.jdbc.query.CrudJdbcSqlQueryHolder;

public class BankAccountSqlQueryHolderImpl implements CrudJdbcSqlQueryHolder {
    @Override
    public String getByIdSql() {
        return "select c.id cid, c.personalIdentifier, c.first_name, c.surname, " +
                "b.id bid, b.brand, " +
                "ba.id, ba.account_number, ba.client_id, ba.bank_id, ba.currency, ba.open_date, ba.account_balance from bank_accounts ba " +
                "left join clients c on c.id = ba.client_id " +
                "left join banks b on b.id = ba.bank_id " +
                "where ba.id = ?";
    }
    @Override
    public String getAllSql() {
        return "select c.id cid, c.personalIdentifier, c.first_name, c.surname, " +
                "b.id bid, b.brand, " +
                "ba.id, ba.account_number, ba.client_id, ba.bank_id, ba.currency, ba.open_date, ba.account_balance from bank_accounts ba " +
                "left join clients c on c.id = ba.client_id " +
                "left join banks b on b.id = ba.bank_id ";
    }

    @Override
    public String updateSql() {
        return "update bank_accounts set  account_number = ?, client_id = ?, bank_id = ?, currency = ?, open_date = ?, account_balance = ? where id = ?";
    }

    @Override
    public String createSql() {
        return "insert into bank_accounts (account_number, client_id, bank_id, currency, open_date, account_balance) values (?,?,?,?,?,?)";
    }

    @Override
    public String deleteSql() {
        return "delete from bank_accounts where id = ?";
    }

    @Override
    public String createWithIdSql() {
        return "insert into bank_accounts (id, account_number, client_id, bank_id, currency, open_date, account_balance) values (?,?,?,?,?,?,?)";
    }
    public String refillSql(){
        return "UPDATE bank_accounts SET account_balance = account_balance + ? WHERE account_number = ?";
    }

    public String checkBalanceSql(){
        return "SELECT account_balance FROM bank_accounts WHERE account_number = ?";
    }
}
