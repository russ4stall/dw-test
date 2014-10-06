package com.github.russ4stall.dw.resources;

import com.codahale.metrics.annotation.Timed;
import com.github.russ4stall.dw.core.FoodItem;
import com.github.russ4stall.dw.jdbi.FoodItemDao;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

/**
 * @author Russ Forstall
 */
@Path("/food-items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FoodItemResource {
    private FoodItemDao foodItemDao;

    public FoodItemResource(FoodItemDao foodItemDao) {
        this.foodItemDao = foodItemDao;
    }

    @GET
    @Timed
    public List<FoodItem> getList() {
        return foodItemDao.getAll();
    }

    @POST
    @Timed
    public void addNew(FoodItem foodItem) {
        foodItemDao.insert(foodItem.getTitle(), foodItem.getCalories());
    }

}
