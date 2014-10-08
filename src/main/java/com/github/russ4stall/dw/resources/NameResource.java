package com.github.russ4stall.dw.resources;

import com.codahale.metrics.annotation.Timed;
import com.github.russ4stall.dw.core.Name;
import com.github.russ4stall.dw.core.User;
import com.github.russ4stall.dw.jdbi.NameDao;
import io.dropwizard.auth.Auth;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * @author Russ Forstall
 */
@Path("/names")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NameResource {
    private NameDao nameDao;

    public NameResource(NameDao nameDao) {
        this.nameDao = nameDao;
    }

    @GET
    @Timed
    public List<Name> getAll(@Auth User user) {
        return nameDao.getAll();
    }

    @POST
    @Timed
    public void addNew(Name name) {
        nameDao.insert(name.getName());
    }
}
