package com.qthegamep.jersey.grizzly.example.filter;

import com.qthegamep.jersey.grizzly.example.util.LogUtil;

import lombok.val;

import javax.ws.rs.container.*;
import javax.ws.rs.ext.Provider;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Provider
@PreMatching
public class LogFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        addStartTimeToHeaders(containerRequestContext);
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        logRequestInfo(containerRequestContext);
    }

    private void addStartTimeToHeaders(ContainerRequestContext containerRequestContext) {
        val headers = containerRequestContext.getHeaders();
        headers.add("startTime", String.valueOf(System.currentTimeMillis()));
    }

    private void logRequestInfo(ContainerRequestContext containerRequestContext) throws MalformedURLException {
        val url = getRequestUrl(containerRequestContext);
        val params = getQueryParameters(containerRequestContext);
        val totalTime = getTotalTime(containerRequestContext);
        LogUtil.logInfo("Request URL::{} Parameters::{} Time Taken::{}",
                url,
                params,
                totalTime
        );
    }

    private URL getRequestUrl(ContainerRequestContext containerRequestContext) throws MalformedURLException {
        val uriInfo = containerRequestContext.getUriInfo();
        val requestUri = uriInfo.getRequestUri();
        return requestUri.toURL();
    }

    private String getQueryParameters(ContainerRequestContext containerRequestContext) {
        val uriInfo = containerRequestContext.getUriInfo();
        val queryParameters = uriInfo.getQueryParameters();
        return queryParameters.toString();
    }

    private Long getTotalTime(ContainerRequestContext containerRequestContext) {
        val startTimeProperty = containerRequestContext.getHeaderString("startTime");
        val startTime = Long.parseLong(startTimeProperty);
        return (System.currentTimeMillis() - startTime);
    }
}
