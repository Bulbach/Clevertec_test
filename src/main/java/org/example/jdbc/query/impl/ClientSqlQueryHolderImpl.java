package org.example.jdbc.query.impl;

import org.example.jdbc.query.CrudJdbcSqlQueryHolder;

public class ClientSqlQueryHolderImpl implements CrudJdbcSqlQueryHolder {
    @Override
    public String getByIdSql() {
        return "select id, personalIdentifier, first_name, surname from clients where id = ?";
    }

    @Override
    public String getAllSql() {
        return "select id, personalIdentifier, first_name, surname from clients";
    }

    @Override
    public String updateSql() {
        return "update clients set  personalIdentifier = ?, first_name = ?, surname = ? where id = ?";
    }

    @Override
    public String createSql() {
        return "insert into clients (personalIdentifier, first_name, surname) values (?,?,?)";
    }

    @Override
    public String deleteSql() {
        return "delete from clients where id = ?";
    }

    @Override
    public String createWithIdSql() {
        return "insert into clients (id, personalIdentifier, first_name, surname) values (?,?,?,?)";
    }
}
