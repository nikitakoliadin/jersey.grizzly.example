package com.qthegamep.jersey.grizzly.example.resource;

import lombok.val;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.Response;

@Path("/")
public class MainResourceImpl implements MainResource {

    @Override
    @GET
    @Produces("text/plain; charset=UTF-8")
    public Response getMain() {
        val entity = "jersey.grizzly.example";
        return Response.status(Response.Status.OK)
                .entity(entity)
                .build();
    }

    @Override
    @GET
    @Path("/info")
    @Produces("text/plain; charset=UTF-8")
    public Response getInfo() {
        val entity = "Created by qThegamEp";
        return Response.status(Response.Status.OK)
                .entity(entity)
                .build();
    }
}
