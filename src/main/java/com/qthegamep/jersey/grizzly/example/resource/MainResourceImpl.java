package com.qthegamep.jersey.grizzly.example.resource;

import com.qthegamep.jersey.grizzly.example.service.MainService;
import com.qthegamep.jersey.grizzly.example.service.MainServiceImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.val;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.Response;

@Path("/")
public class MainResourceImpl implements MainResource {

    @Getter
    @Setter
    private MainService mainService = new MainServiceImpl();

    @Override
    @GET
    @Produces("text/plain; charset=UTF-8")
    public Response getMain() {
        val entity = mainService.getMain();
        return Response.status(Response.Status.OK)
                .entity(entity)
                .build();
    }

    @Override
    @GET
    @Path("/info")
    @Produces("text/plain; charset=UTF-8")
    public Response getInfo() {
        val entity = mainService.getInfo();
        return Response.status(Response.Status.OK)
                .entity(entity)
                .build();
    }
}
