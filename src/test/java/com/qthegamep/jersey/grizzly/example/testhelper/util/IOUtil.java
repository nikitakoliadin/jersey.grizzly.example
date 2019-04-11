package com.qthegamep.jersey.grizzly.example.testhelper.util;

import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.io.PrintStream;

@UtilityClass
public class IOUtil {

    private final InputStream CONSOLE_INPUT_STREAM = System.in;
    private final PrintStream CONSOLE_PRINT_STREAM = System.out;

    public void setInputOutputStreamToConsole() {
        System.setIn(CONSOLE_INPUT_STREAM);
        System.setOut(CONSOLE_PRINT_STREAM);
    }
}
