package com.github.russ4stall.dw.jdbi;

import com.github.russ4stall.dw.api.FoodItem;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * @author Russ Forstall
 */
@RegisterMapper(FoodItemMapper.class)
public interface FoodItemDao {
    @SqlUpdate("insert into fooditem (title, calories) values (:title, :calories)")
    void insert(@Bind("title") String title, @Bind("calories") int calories);

    @SqlQuery("select * from fooditem")
    List<FoodItem> getAll();
}
