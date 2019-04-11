package com.qthegamep.jersey.grizzly.example.handler;

import com.qthegamep.jersey.grizzly.example.util.LogUtil;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BaseExceptionHandler implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable throwable) {
        return errorResponse(throwable);
    }

    private Response errorResponse(Throwable exception) {
        LogUtil.logError("ERROR", exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .build();
    }
}
