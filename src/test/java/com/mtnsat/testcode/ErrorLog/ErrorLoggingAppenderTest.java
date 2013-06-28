package com.mtnsat.testcode.ErrorLog;

import com.mtnsat.testcode.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: evanreynolds
 */
public class ErrorLoggingAppenderTest extends TestBase {

    private Logger logger = LoggerFactory.getLogger(ErrorLoggingAppenderTest.class);

    ErrorLoggingAppender ela = new ErrorLoggingAppender();

    @Before
    public void Setup() {
        MockitoAnnotations.initMocks(this);
        ErrorLog.clear();
        ela.startErrorLogAppender();
    }

    @Test
    public void testAppendNotErrors() throws Exception {
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.info("warn");
        Assert.assertEquals(0, ErrorLog.getErrorCount());
    }

    @Test
    public void testAppendErrors() throws Exception {
        logger.error("error");
        Assert.assertEquals(1, ErrorLog.getErrorCount());
    }

    @Test
    public void testAppendMessages() throws Exception {
        Assert.assertEquals(0, ErrorLog.getErrorCount());
        logger.error("error");
        Assert.assertEquals(1, ErrorLog.getErrorCount());
        logger.trace("trace");
        logger.debug("debug");
        logger.error("error2");
        Assert.assertEquals(2, ErrorLog.getErrorCount());
        logger.info("info");
        logger.info("warn");
        logger.error("error3");
        Assert.assertEquals(3, ErrorLog.getErrorCount());
    }

    @Test
    public void testGetFullList() throws Exception {
        logger.error("1");
        logger.error("2");
        logger.error("3");
        logger.error("4");
        logger.error("5");
        logger.error("6");
        logger.error("7");
        logger.error("8");
        logger.error("9");
        logger.error("10");

        List<String> testList = ErrorLog.getErrors();
        Assert.assertEquals(10, testList.size());
        Assert.assertTrue(testList.get(0).endsWith("1"));
        Assert.assertTrue(testList.get(1).endsWith("2"));
        Assert.assertTrue(testList.get(2).endsWith("3"));
        Assert.assertTrue(testList.get(3).endsWith("4"));
        Assert.assertTrue(testList.get(4).endsWith("5"));
        Assert.assertTrue(testList.get(5).endsWith("6"));
        Assert.assertTrue(testList.get(6).endsWith("7"));
        Assert.assertTrue(testList.get(7).endsWith("8"));
        Assert.assertTrue(testList.get(8).endsWith("9"));
        Assert.assertTrue(testList.get(9).endsWith("10"));
    }

    @Test
    public void testGetFullListEmpty() throws Exception {
        List<String> testList = ErrorLog.getErrors();
        Assert.assertEquals(0, testList.size());
    }

    @Test
    public void testMaxErrors() throws Exception {
        for (Integer i = 0; i < ErrorLog.MAX_ERRORS * 2; i++) {
            logger.error(i.toString());
        }

        Assert.assertEquals(ErrorLog.MAX_ERRORS, ErrorLog.getErrorCount());
        List<String> testList = ErrorLog.getErrors();

        Assert.assertTrue(testList.get(0).endsWith("100"));
        Assert.assertTrue(testList.get(99).endsWith("199"));
    }

}
