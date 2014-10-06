package com.github.russ4stall.dw.jdbi;

import com.github.russ4stall.dw.core.Name;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.Iterator;
import java.util.List;

/**
 * @author Russ Forstall
 */
@RegisterMapper(NameMapper.class)
public interface NameDao {
    @SqlUpdate("insert into name (name) values (:name)")
    void insert(@Bind("name") String name);

    @SqlQuery("select id, name from name")
    List<Name> getAll();
}
