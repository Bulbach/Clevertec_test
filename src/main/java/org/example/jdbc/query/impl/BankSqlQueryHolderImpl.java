package org.example.jdbc.query.impl;

import org.example.jdbc.query.CrudJdbcSqlQueryHolder;

public class BankSqlQueryHolderImpl implements CrudJdbcSqlQueryHolder {
    @Override
    public String getByIdSql() {
        return "select id, brand from banks where id = ?";
    }

    @Override
    public String getAllSql() {
        return "select id, brand from banks";
    }

    @Override
    public String updateSql() {
        return "update banks set brand = ? where id = ?";
    }

    @Override
    public String createSql() {
        return "insert into banks (brand) values (?)";
    }

    @Override
    public String deleteSql() {
        return "delete from banks where id = ?";
    }

    @Override
    public String createWithIdSql() {
        return "insert into banks (id, brand) values (?, ?)";
    }
}
