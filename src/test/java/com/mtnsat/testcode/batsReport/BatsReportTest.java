package com.mtnsat.testcode.batsReport;

import com.mtnsat.testcode.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: evanreynolds
 */
public class BatsReportTest extends TestBase {

    BatsReport batsReport;
    final String batsName = "172.16.4.200";

    final String statusString = "show status\r\nSystem Status:\r\nSoftware Version        = 1.9.14\r\nStatus                  = Waiting\r\n\r\nUbiquitiM5 Status:\r\nConnected               = Yes\r\nLink Status             = Disconnected\r\nRSSI                    = -96.00\r\nSNR                     = 0.00\r\nLink Speed              = 0.00\r\nLink Distance (km)      = 0.000\r\n\r\nPositioner Status:\r\nConnected               = No\r\nAzimuth (absolute)      = 0.00\r\nElevation (absolute)    = 0.00\r\n\r\nGPS Status:\r\nFix Status              = GHU Disconnected\r\nLocation                = 0 0'0.00\\\" N 0 0'0.00\\\" E\r\nAltitude (m)            = 0.00\r\nHeading Status          = GHU Disconnected\r\nHeading                 = 0.00\r\nCalculated Speed (kmh)  = 0.00\r\nGPS Timestamp           = 0.00\r\nPitch                   = 0.00\r\nRoll                    = 0.00\r\nadmin@172.16.4.200# \r\nadmin@172.16.4.200# ";
    final String cleanedStatusString = "System Status:\r\nSoftware Version        = 1.9.14\r\nStatus                  = Waiting\r\n\r\nUbiquitiM5 Status:\r\nConnected               = Yes\r\nLink Status             = Disconnected\r\nRSSI                    = -96.00\r\nSNR                     = 0.00\r\nLink Speed              = 0.00\r\nLink Distance (km)      = 0.000\r\n\r\nPositioner Status:\r\nConnected               = No\r\nAzimuth (absolute)      = 0.00\r\nElevation (absolute)    = 0.00\r\n\r\nGPS Status:\r\nFix Status              = GHU Disconnected\r\nLocation                = 0 0'0.00\\\" N 0 0'0.00\\\" E\r\nAltitude (m)            = 0.00\r\nHeading Status          = GHU Disconnected\r\nHeading                 = 0.00\r\nCalculated Speed (kmh)  = 0.00\r\nGPS Timestamp           = 0.00\r\nPitch                   = 0.00\r\nRoll                    = 0.00";


    final String statusStringFromSky = "show status\r\nSystem Status:\r\nSoftware Version        = 1.9.7\r\nStatus                  = Tracking Target MiamiNWT\r\n\r\nUbiquitiM5 Status:\r\nConnected               = Yes\r\nLink Status             = Connected\r\n" +
            "RSSI                    = -63.00\r\nSNR                     = 92.10\r\nLink Speed              = 78.00\r\nLink Distance (miles)      = 1.517\r\n\r\nPositioner Status:\r\nConnected               = Yes\r\nAzimuth (absolute)      = -86.40\r\n" +
            "Elevation (absolute)    = -1.14\r\n\r\nGPS Status:\r\nFix Status              = Differential\r\nLocation                = 25 46'28.78\" N 80 9'51.53\" W\r\nAltitude (m)            = 149.61\r\nHeading Status          = GPS\r\n" +
            "Heading                 = 105.00\r\nCalculated Speed (kmh)  = 0.00\r\nGPS Timestamp           = 204211\r\nPitch                   = 1.27\r\nRoll                    = -1.36\r\nadmin@172.16.4.200# \r\nadmin@172.16.4.200# ";
    final String cleanedStatusStringFromSky =  "System Status:\r\nSoftware Version        = 1.9.7\r\nStatus                  = Tracking Target MiamiNWT\r\n\r\nUbiquitiM5 Status:\r\nConnected               = Yes\r\nLink Status             = Connected\r\n" +
            "RSSI                    = -63.00\r\nSNR                     = 92.10\r\nLink Speed              = 78.00\r\nLink Distance (miles)      = 1.517\r\n\r\nPositioner Status:\r\nConnected               = Yes\r\nAzimuth (absolute)      = -86.40\r\n" +
            "Elevation (absolute)    = -1.14\r\n\r\nGPS Status:\r\nFix Status              = Differential\r\nLocation                = 25 46'28.78\" N 80 9'51.53\" W\r\nAltitude (m)            = 149.61\r\nHeading Status          = GPS\r\n" +
            "Heading                 = 105.00\r\nCalculated Speed (kmh)  = 0.00\r\nGPS Timestamp           = 204211\r\nPitch                   = 1.27\r\nRoll                    = -1.36";

    @Before
    public void Setup() {
        batsReport = new BatsReport(batsName);
    }

    @Test
    public void testGetOriginalStatusString() throws Exception {
        batsReport.setAndParseOriginalStatusString(statusString);
        Assert.assertEquals(cleanedStatusString, batsReport.getOriginalStatusString());
    }

    @Test
    public void testGetOriginalStatusStringFromSky() throws Exception {
        batsReport.setAndParseOriginalStatusString(statusStringFromSky);
        Assert.assertEquals(cleanedStatusStringFromSky, batsReport.getOriginalStatusString());
    }

    //Pass in the cleaned string instead of the original string
    @Test
    public void testRobustnessOfGetOriginalStatusString() throws Exception {
        batsReport.setAndParseOriginalStatusString(cleanedStatusString);
        Assert.assertEquals(cleanedStatusString, batsReport.getOriginalStatusString());
    }

    @Test
    public void testGetBatsName() throws Exception {
        Assert.assertEquals(batsName, batsReport.getBatsName());
    }

    @Test
    public void testBatsUp() throws Exception {
        batsReport.setBatsUp(true);
        Assert.assertTrue(batsReport.isBatsUp());

        batsReport.setBatsUp(false);
        Assert.assertFalse(batsReport.isBatsUp());
    }

    @Test
    public void testParseStatusString() throws Exception {
        batsReport.setAndParseOriginalStatusString(statusString);
        Assert.assertEquals("1.9.14", batsReport.getBatsVersion());
        Assert.assertEquals("Waiting", batsReport.getBatsStatus());
        Assert.assertEquals(-96.00, batsReport.getRssi(), 0.01);
        Assert.assertEquals("0 0'0.00\\\" N 0 0'0.00\\\" E", batsReport.getGpsLocation());
        Assert.assertEquals(0.00, batsReport.getShipSpeed(), 0.01);
        Assert.assertEquals(0.00, batsReport.getSignalToNoise(), 0.01);
        Assert.assertEquals("Disconnected", batsReport.getLinkStatus());

    }

    @Test
    public void testParseStatusStringFromSky() throws Exception {
        batsReport.setAndParseOriginalStatusString(statusStringFromSky);
        Assert.assertEquals("Tracking Target MiamiNWT", batsReport.getBatsStatus());
        Assert.assertEquals("1.9.7", batsReport.getBatsVersion());
        Assert.assertEquals(-63.00, batsReport.getRssi(), 0.01);
        Assert.assertEquals("25 46'28.78\" N 80 9'51.53\" W", batsReport.getGpsLocation());
        Assert.assertEquals(0.00, batsReport.getShipSpeed(), 0.01);
        Assert.assertEquals(92.10, batsReport.getSignalToNoise(), 0.01);
        Assert.assertEquals("Connected", batsReport.getLinkStatus());

    }
}
