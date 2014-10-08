package com.github.russ4stall.dw;

import com.github.russ4stall.dw.auth.DWAuthenticator;
import com.github.russ4stall.dw.core.FoodItem;
import com.github.russ4stall.dw.core.Name;
import com.github.russ4stall.dw.core.User;
import com.github.russ4stall.dw.jdbi.FoodItemDao;
import com.github.russ4stall.dw.jdbi.NameDao;
import com.github.russ4stall.dw.resources.FoodItemResource;
import com.github.russ4stall.dw.resources.HelloWorldResource;
import com.github.russ4stall.dw.resources.NameResource;
import com.github.russ4stall.dw.resources.SimpleCacheResource;
import io.dropwizard.Application;
import io.dropwizard.auth.CachingAuthenticator;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.auth.oauth.OAuthProvider;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Russ Forstall
 * @since (version)
 */
public class DWApplication extends Application<DWConfiguration> {
    public Map<String, String> simpleCache;

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

    }

    @Override
    public void run(DWConfiguration dwConfiguration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, dwConfiguration.getDataSourceFactory(), "mysql");


        final HelloWorldResource resource = new HelloWorldResource(
                dwConfiguration.getTemplate(),
                dwConfiguration.getDefaultName()
        );
        environment.jersey().register(resource);

        final FoodItemDao foodItemDao = jdbi.onDemand(FoodItemDao.class);
        final FoodItemResource foodItemResource = new FoodItemResource(foodItemDao);
        environment.jersey().register(foodItemResource);

        final NameDao nameDao = jdbi.onDemand(NameDao.class);
        environment.jersey().register(new NameResource(nameDao));

        environment.jersey().register(new SimpleCacheResource(simpleCache));


        environment.jersey().register(new OAuthProvider<>(new DWAuthenticator(), "names"));

    }
}
