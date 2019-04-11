package com.qthegamep.jersey.grizzly.example.starter;

import com.qthegamep.jersey.grizzly.example.config.Config;
import com.qthegamep.jersey.grizzly.example.exception.CouldNotStartServerException;
import com.qthegamep.jersey.grizzly.example.util.LogUtil;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import lombok.val;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

@UtilityClass
public class ApplicationStarter {

    @Getter
    private HttpServer httpServer;

    public void startApplication() throws CouldNotStartServerException {
        initConfig();
        val applicationUrl = getApplicationUrl();
        startServer(applicationUrl);
        logApplicationUrl(applicationUrl);
    }

    private void initConfig() {
        Config.getInstance();
    }

    private String getApplicationUrl() {
        return "http://"
                + System.getProperty("application.host", "0.0.0.0")
                + ":"
                + System.getProperty("application.port", "8081")
                + System.getProperty("application.context", "");
    }

    private void startServer(String connectionUrl) throws CouldNotStartServerException {
        try {
            val uri = getUriFromString(connectionUrl);
            val resourceConfig = new ResourceConfig();
            resourceConfig.packages("com.qthegamep.jersey.grizzly.example");
            httpServer = GrizzlyHttpServerFactory.createHttpServer(
                    uri,
                    resourceConfig,
                    true
            );
        } catch (Exception e) {
            LogUtil.logError("Error while starting server by url {}", connectionUrl, e);
            throw new CouldNotStartServerException(e.getMessage(), e);
        }
    }

    private URI getUriFromString(String connectionUrl) {
        return URI.create(connectionUrl);
    }

    private void logApplicationUrl(String applicationUrl) {
        LogUtil.logWarn("Jersey app started application: {}", applicationUrl);
    }
}
