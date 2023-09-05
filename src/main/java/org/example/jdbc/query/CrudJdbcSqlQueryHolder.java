package org.example.jdbc.query;

public interface CrudJdbcSqlQueryHolder {
    String getByIdSql();

    String getAllSql();

    String updateSql();

    String createSql();

    String deleteSql();

    String createWithIdSql();

   }
