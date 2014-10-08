package com.github.russ4stall.dw.jdbi;

import com.github.russ4stall.dw.api.FoodItem;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Russ Forstall
 */
public class FoodItemMapper implements ResultSetMapper<FoodItem> {
    @Override
    public FoodItem map(int i, ResultSet r, StatementContext statementContext) throws SQLException {
        return new FoodItem(r.getInt("id"), r.getString("title"), r.getInt("calories"), r.getTimestamp("timestamp"));
    }
}
