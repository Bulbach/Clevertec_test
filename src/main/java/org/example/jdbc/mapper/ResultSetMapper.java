package org.example.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetMapper<T> {
    T processResultSet(ResultSet rs) throws SQLException;
}
