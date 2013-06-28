package com.mtnsat.testcode.ErrorLog;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * Catch all error level messages so that we can easily create an error report.
 * They are stored in memory, this is a list of errors for this execution of the program,
 * not a log - the log file holds the persisted events.
 *
 * User: evanreynolds
 */
public class ErrorLoggingAppender extends AppenderBase<ILoggingEvent> {

    public ErrorLoggingAppender() {
    }

    public void append(ILoggingEvent event) {
        if (event.getLevel() == Level.ERROR) {
            ErrorLog.storeEvent(event.getFormattedMessage());
        }
    }

    public void startErrorLogAppender() {

        final String errorLogName = "errorLogger";
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);

        //Make sure the error log doesn't get in here twice
        if (rootLogger.getAppender(errorLogName) == null) {
            setContext(lc);
            setName(errorLogName);
            rootLogger.addAppender(this);
            start();
        }
    }

}

