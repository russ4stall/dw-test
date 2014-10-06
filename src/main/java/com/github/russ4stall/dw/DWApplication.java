package com.github.russ4stall.dw;

import com.github.russ4stall.dw.core.FoodItem;
import com.github.russ4stall.dw.core.Name;
import com.github.russ4stall.dw.jdbi.FoodItemDao;
import com.github.russ4stall.dw.jdbi.NameDao;
import com.github.russ4stall.dw.resources.FoodItemResource;
import com.github.russ4stall.dw.resources.HelloWorldResource;
import com.github.russ4stall.dw.resources.NameResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

/**
 * @author Russ Forstall
 * @since (version)
 */
public class DWApplication extends Application<DWConfiguration> {

    public static void main(String[] args) throws Exception {
        new DWApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<DWConfiguration> dwConfigurationBootstrap) {

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
    }
}
