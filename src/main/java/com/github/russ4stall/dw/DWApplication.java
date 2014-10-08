package com.github.russ4stall.dw;

import com.github.russ4stall.dw.api.Name;
import com.github.russ4stall.dw.auth.AuthService;
import com.github.russ4stall.dw.auth.TokenFilter;
import com.github.russ4stall.dw.jdbi.FoodItemDao;
import com.github.russ4stall.dw.jdbi.NameDao;
import com.github.russ4stall.dw.resources.FoodItemResource;
import com.github.russ4stall.dw.resources.NameResource;
import com.github.russ4stall.dw.resources.SimpleCacheResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Russ Forstall
 * @since (version)
 */
public class DWApplication extends Application<DWConfiguration> {
    public Map<String, String> simpleCache;
    public AuthService authService;

    public static void main(String[] args) throws Exception {
        new DWApplication().run(args);
    }

    @Override
    public String getName() {
        return "DW Test App";
    }

    @Override
    public void initialize(Bootstrap<DWConfiguration> dwConfigurationBootstrap) {
        simpleCache = new HashMap<>();
        authService = new AuthService();
        authService.put("oingo", new Name(1, "Russ"));

    }

    @Override
    public void run(DWConfiguration dwConfiguration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, dwConfiguration.getDataSourceFactory(), "mysql");

        final FoodItemDao foodItemDao = jdbi.onDemand(FoodItemDao.class);
        final FoodItemResource foodItemResource = new FoodItemResource(foodItemDao);
        environment.jersey().register(foodItemResource);

        final NameDao nameDao = jdbi.onDemand(NameDao.class);
        environment.jersey().register(new NameResource(nameDao));

        environment.jersey().register(new SimpleCacheResource(simpleCache));

        //FILTERS
        environment.servlets().addFilter("TokenFilter", new TokenFilter(authService))
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

    }
}
