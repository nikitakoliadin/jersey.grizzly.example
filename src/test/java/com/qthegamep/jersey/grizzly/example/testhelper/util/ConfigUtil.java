package com.qthegamep.jersey.grizzly.example.testhelper.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConfigUtil {

    public void setPathToTestProperties() {
        System.setProperty("config.path", "src/test/resources/properties/config.properties");
    }
}
