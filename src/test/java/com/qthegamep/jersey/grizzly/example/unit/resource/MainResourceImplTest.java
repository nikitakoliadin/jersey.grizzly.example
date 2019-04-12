package com.qthegamep.jersey.grizzly.example.unit.resource;

import com.qthegamep.jersey.grizzly.example.config.Config;
import com.qthegamep.jersey.grizzly.example.resource.MainResource;
import com.qthegamep.jersey.grizzly.example.resource.MainResourceImpl;
import com.qthegamep.jersey.grizzly.example.service.MainService;
import com.qthegamep.jersey.grizzly.example.service.MainServiceImpl;

import com.qthegamep.jersey.grizzly.example.testhelper.rule.Rules;
import com.qthegamep.jersey.grizzly.example.testhelper.util.ConfigUtil;

import lombok.val;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainResourceImplTest {

    @ClassRule
    public static ExternalResource summaryRule = Rules.SUMMARY_RULE;

    @Rule
    public Stopwatch stopwatchRule = Rules.STOPWATCH_RULE;
    @Rule
    public ExternalResource inputOutputSetup = Rules.INPUT_OUTPUT_SETUP_RULE;

    private MainResource mainResource;
    private MainResource mainResourceMock;

    private MainService mainService;
    @Mock
    private MainService mainServiceMock;

    @Before
    public void setUp() {
        ConfigUtil.setPathToTestProperties();

        Config.getInstance();

        mainService = new MainServiceImpl();

        mainResource = new MainResourceImpl();
        ((MainResourceImpl) mainResource).setMainService(mainService);

        mainResourceMock = new MainResourceImpl();
        ((MainResourceImpl) mainResourceMock).setMainService(mainServiceMock);
    }

    @Test
    public void shouldCreateMainResource() {
        assertThat(mainResource).isNotNull();
    }

    @Test
    public void shouldImplementsMainResourceInterface() {
        assertThat(mainResource).isInstanceOf(MainResource.class);
    }

    @Test
    public void shouldGetAndSetMainService() {
        mainService = new MainServiceImpl();

        ((MainResourceImpl) mainResource).setMainService(mainService);

        assertThat(((MainResourceImpl) mainResource).getMainService())
                .isNotNull()
                .isEqualTo(mainService);
    }

    @Test
    public void shouldCreateNewMainService() {
        mainResource = new MainResourceImpl();

        val newMainService = ((MainResourceImpl) mainResource).getMainService();

        assertThat(newMainService)
                .isNotNull()
                .isNotEqualTo(mainService);
    }

    @Test
    public void shouldReturnNotNullResponseWhenGetMain() {
        val response = mainResource.getMain();

        assertThat(response).isNotNull();
    }

    @Test
    public void shouldCallGetMainServiceWhenGetMain() {
        when(mainServiceMock.getMain()).thenReturn("");

        mainResourceMock.getMain();

        verify(mainServiceMock, times(1)).getMain();
        verifyNoMoreInteractions(mainServiceMock);
    }

    @Test
    public void shouldReturnNotNullResponseWhenGetInfo() {
        val response = mainResource.getInfo();

        assertThat(response).isNotNull();
    }

    @Test
    public void shouldCallGetInfoServiceWhenGetInfo() {
        when(mainServiceMock.getInfo()).thenReturn("");

        mainResourceMock.getInfo();

        verify(mainServiceMock, times(1)).getInfo();
        verifyNoMoreInteractions(mainServiceMock);
    }
}
