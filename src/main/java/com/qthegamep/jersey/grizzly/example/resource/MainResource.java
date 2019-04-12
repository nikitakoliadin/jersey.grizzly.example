package com.qthegamep.jersey.grizzly.example.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.Response;

public interface MainResource {

    /**
     * This is an example of the root resource.
     *
     * @return name of the project.
     */
    @GET
    @Produces("text/plain; charset=UTF-8")
    Response getMain();

    /**
     * This is an example of the child resource.
     *
     * @return author.
     */
    @GET
    @Path("/info")
    @Produces("text/plain; charset=UTF-8")
    Response getInfo();
}
