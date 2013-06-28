package com.mtnsat.testcode.ErrorLog;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: evanreynolds
 */
public final class ErrorLog {

    //Hide the constructor.
    private ErrorLog(){};

    public static final int MAX_ERRORS = 100;

    private static List<String> errors = new ArrayList<String>();

    public static int getErrorCount() {
        return errors.size();
    }

    public static synchronized void clear() {
        errors.clear();
    }

    public static synchronized void storeEvent(String message) {

        while (errors.size() >= MAX_ERRORS) {
            //Don't let the list get too big. Clip off a message before adding a message.
            //This shouldn't happen, but just disallows a storm of errors taking up too much memory.
            errors.remove(0);
        }

        final String separator = " --- ";
        DateTime dateTime = new DateTime().withZone(DateTimeZone.UTC);
        StringBuilder sb = new StringBuilder();
        sb.append(dateTime.toString("MM/dd/yyyy HH:mm:ss"));
        sb.append(separator);
        sb.append(message);
        errors.add(sb.toString());
    }

    public static List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }

}
