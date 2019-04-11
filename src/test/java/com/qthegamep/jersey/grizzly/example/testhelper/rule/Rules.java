package com.qthegamep.jersey.grizzly.example.testhelper.rule;

import com.qthegamep.jersey.grizzly.example.testhelper.util.IOUtil;

import lombok.experimental.UtilityClass;
import lombok.val;

import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

@UtilityClass
public class Rules {

    /**
     * The constant is used as a rule for calculating of the time spent by a test.
     * It is used with {@link org.junit.Rule} JUnit annotation.
     */
    public final Stopwatch STOPWATCH_RULE = new Stopwatch() {

        @Override
        protected void finished(long nanos, Description description) {
            val result = formatResultForStopwatchRule(nanos, description);
            setClassName(description);
            formatResults(result);
            logResultOfStopwatchRule(result);
        }
    };

    /**
     * The constant is used as a rule for outputting results of the class tests.
     * It is used with {@link org.junit.ClassRule} JUnit annotation.
     */
    public final ExternalResource SUMMARY_RULE = new ExternalResource() {

        @Override
        protected void before() {
            clearResults();
        }

        @Override
        protected void after() {
            val infoLine = formatInfoLineForSummaryRule();
            logResultOfSummaryRule(infoLine);
        }
    };

    /**
     * This constant is used as a rule for configure input and output on the console.
     * It is used with {@link org.junit.Rule} JUnit annotation.
     */
    public final ExternalResource INPUT_OUTPUT_SETUP_RULE = new ExternalResource() {

        @Override
        protected void before() {
            IOUtil.setInputOutputStreamToConsole();
        }

        @Override
        protected void after() {
            IOUtil.setInputOutputStreamToConsole();
        }
    };

    private final Logger log = LoggerFactory.getLogger("TEST_RESULT_LOGGER");

    private final StringBuilder RESULTS = new StringBuilder();

    private String className;

    private String formatResultForStopwatchRule(long nanos, Description description) {
        return String.format("%-164s %7d",
                description.getDisplayName(),
                TimeUnit.NANOSECONDS.toMillis(nanos)
        );
    }

    private void setClassName(Description description) {
        if (isThisClass(description)) {
            className = description.getClassName();
        }
    }

    private boolean isThisClass(Description description) {
        return !description.getClassName().equals(className);
    }

    private void formatResults(String result) {
        RESULTS.append(result).append(System.lineSeparator());
    }

    private void logResultOfStopwatchRule(String result) {
        log.info(result);
    }

    private void clearResults() {
        RESULTS.setLength(0);
    }

    private String formatInfoLineForSummaryRule() {
        return String.format("Test of %-151s %12s",
                className,
                "Duration, ms"
        );
    }

    private void logResultOfSummaryRule(String infoLine) {
        log.info("--------------------------------------------------------------------------------------"
                + "--------------------------------------------------------------------------------------"
                + System.lineSeparator()
                + infoLine
                + System.lineSeparator()
                + "--------------------------------------------------------------------------------------"
                + "--------------------------------------------------------------------------------------"
                + System.lineSeparator()
                + RESULTS
                + "--------------------------------------------------------------------------------------"
                + "--------------------------------------------------------------------------------------"
                + System.lineSeparator()
        );
    }
}
