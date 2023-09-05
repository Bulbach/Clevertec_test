package org.example.jdbc.statement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementInitializer <T>{
    void createQueryStatement(PreparedStatement ps, T t) throws SQLException;

    void updateQueryStatement(PreparedStatement ps, T t) throws SQLException;

    void setByIdQueryStatement(PreparedStatement ps, Long id) throws SQLException;

    void createWithIdQueryStatement(PreparedStatement ps, T t) throws SQLException;
}
