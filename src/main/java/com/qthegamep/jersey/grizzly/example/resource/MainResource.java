package com.qthegamep.jersey.grizzly.example.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import javax.ws.rs.core.Response;

public interface MainResource {

    @GET
    @Produces("text/plain; charset=UTF-8")
    Response getMain();
}
