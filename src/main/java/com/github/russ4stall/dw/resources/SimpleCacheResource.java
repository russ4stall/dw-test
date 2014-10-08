package com.github.russ4stall.dw.resources;

import com.google.common.base.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * @author Russ Forstall
 */
@Path("/cache")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SimpleCacheResource {
    private Map<String, String> simpleCache;

    public SimpleCacheResource(Map<String, String> simpleCache) {
        this.simpleCache = simpleCache;
    }

    @GET
    public Map<String, String> getAllInCache() {
        return simpleCache;
    }

    @GET @Path("/{key}")
    public String insertInCache(@PathParam("key") String key, @QueryParam("message") String message) {
        if (message == null) {
            return simpleCache.get(key);
        }
        simpleCache.put(key, message);
        return simpleCache.get(key);
    }
}
