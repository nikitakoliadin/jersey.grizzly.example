package com.qthegamep.jersey.grizzly.example.config;

import com.qthegamep.jersey.grizzly.example.exception.CouldNotStartServerException;
import com.qthegamep.jersey.grizzly.example.util.LogUtil;

import lombok.val;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Config {

    private static Config instance;

    private Config() {
        init();
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    private static void init() throws CouldNotStartServerException {
        try {
            logInfo();
            val properties = loadProperties();
            loadToSystemProperties(properties);
        } catch (Exception e) {
            LogUtil.logError("ERROR", e);
            throw new CouldNotStartServerException(e.getMessage(), e);
        }
    }

    private static void logInfo() {
        LogUtil.logWarn("log.path = {}", System.getProperty("log.path"));
        LogUtil.logWarn("config.path = {}", System.getProperty("config.path"));
    }

    private static Properties loadProperties() throws IOException {
        val properties = new Properties();
        properties.load(new InputStreamReader(new FileInputStream(
                System.getProperty("config.path")),
                StandardCharsets.UTF_8
        ));
        return properties;
    }

    private static void loadToSystemProperties(Properties properties) {
        properties.forEach((key, value) -> System.setProperty((String) key, (String) value));
    }
}
