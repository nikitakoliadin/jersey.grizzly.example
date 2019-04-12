package com.qthegamep.jersey.grizzly.example.integration.resource;

import com.qthegamep.jersey.grizzly.example.config.Config;
import com.qthegamep.jersey.grizzly.example.filter.LogFilter;
import com.qthegamep.jersey.grizzly.example.handler.BaseExceptionHandler;
import com.qthegamep.jersey.grizzly.example.resource.MainResourceImpl;

import com.qthegamep.jersey.grizzly.example.testhelper.rule.Rules;
import com.qthegamep.jersey.grizzly.example.testhelper.util.ConfigUtil;

import lombok.val;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.*;

public class MainResourceImplIntegrationTest extends JerseyTest {

    @ClassRule
    public static ExternalResource summaryRule = Rules.SUMMARY_RULE;

    @Rule
    public Stopwatch stopwatchRule = Rules.STOPWATCH_RULE;

    @Override
    protected Application configure() {
        ConfigUtil.setPathToTestProperties();
        return new ResourceConfig(
                LogFilter.class,
                BaseExceptionHandler.class,
                MainResourceImpl.class
        );
    }

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        Config.getInstance();
    }

    @Test
    public void givenGetMainWhenCorrectRequestThenResponseInOk() {
        val response = target("/")
                .request()
                .get();

        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
    }

    @Test
    public void givenGetMainWhenCorrectRequestThenMediaTypeIsTextPlainWithCharsetUtf8() {
        val response = target("/")
                .request()
                .get();

        assertThat(response.getMediaType())
                .isEqualTo(MediaType.TEXT_PLAIN_TYPE
                        .withCharset(StandardCharsets.UTF_8.displayName()));
    }

    @Test
    public void givenGetMainWhenCorrectRequestThenEntityIsCorrect() {
        val response = target("/")
                .request()
                .get();

        val actualEntity = response.readEntity(String.class);
        val expectedEntity = "jersey.grizzly.example";

        assertThat(actualEntity)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(expectedEntity);
    }

    @Test
    public void givenGetInfoWhenCorrectRequestThenResponseInOk() {
        val response = target("/info")
                .request()
                .get();

        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
    }

    @Test
    public void givenGetInfoWhenCorrectRequestThenMediaTypeIsTextPlainWithCharsetUtf8() {
        val response = target("/info")
                .request()
                .get();

        assertThat(response.getMediaType())
                .isEqualTo(MediaType.TEXT_PLAIN_TYPE
                        .withCharset(StandardCharsets.UTF_8.displayName()));
    }

    @Test
    public void givenGetInfoWhenCorrectRequestThenEntityIsCorrect() {
        val response = target("/info")
                .request()
                .get();

        val actualEntity = response.readEntity(String.class);
        val expectedEntity = "Created by qThegamEp";

        assertThat(actualEntity)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(expectedEntity);
    }

    @Test
    public void givenGetRequestWhenIncorrectRequestThenResponseIsInternalServerError() {
        val response = target("/error")
                .request()
                .get();

        assertThat(response.getStatus()).isEqualTo(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
    }
}
