package com.github.russ4stall.dw.jdbi;

import com.github.russ4stall.dw.core.Name;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Russ Forstall
 */
public class NameMapper implements ResultSetMapper<Name> {
    @Override
    public Name map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Name(resultSet.getInt("id"), resultSet.getString("name"));
    }
}
