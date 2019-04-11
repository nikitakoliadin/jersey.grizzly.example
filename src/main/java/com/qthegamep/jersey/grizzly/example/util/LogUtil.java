package com.qthegamep.jersey.grizzly.example.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class LogUtil {

    public void logInfo(String message, Object... values) {
        log.info(message, values);
    }

    public void logWarn(String message, Object... values) {
        log.warn(message, values);
    }

    public void logError(String message, Object... values) {
        log.error(message, values);
    }
}
