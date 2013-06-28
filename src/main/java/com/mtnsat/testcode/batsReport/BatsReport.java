package com.mtnsat.testcode.batsReport;

import com.mtnsat.testcode.Helpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: evanreynolds
 */
public class BatsReport {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String batsName;
    private String originalStatusString;

    private String batsVersion = "";
    private String batsStatus = "";
    private Double rssi = 0.0;
    private String gpsLocation = "";
    private Double shipSpeed = 0.0;
    private Double signalToNoise = 0.0;
    private String linkStatus = "";
    private boolean batsUp = true;
    private String associatedShoresideMacAddress = "";

    public String getBatsVersion() {
        return batsVersion;
    }

    public String getBatsName() {
        return batsName;
    }

    public void setBatsName(String batsName) {
        this.batsName = batsName;
    }

    public String getOriginalStatusString() {
        return originalStatusString;
    }

    public void setOriginalStatusString(String originalStatusString) {
        this.originalStatusString = originalStatusString;
    }

    public void setBatsVersion(String batsVersion) {
        this.batsVersion = batsVersion;
    }

    public String getBatsStatus() {
        return batsStatus;
    }

    public void setBatsStatus(String batsStatus) {
        this.batsStatus = batsStatus;
    }

    public Double getRssi() {
        return rssi;
    }

    public void setRssi(Double rssi) {
        this.rssi = rssi;
    }

    public void setRssiFromString(String rssi) {
        this.rssi = Helpers.stringToDouble(rssi, 0.00);;
    }

    public String getGpsLocation() {
        return gpsLocation;
    }

    public void setGpsLocation(String gpsLocation) {
        this.gpsLocation = gpsLocation;
    }

    public Double getShipSpeed() {
        return shipSpeed;
    }

    public void setShipSpeedFromString(String shipSpeed) {
        this.shipSpeed = Helpers.stringToDouble(shipSpeed, 0.00);
    }

    public void setShipSpeed(Double shipSpeed) {
        this.shipSpeed = shipSpeed;
    }

    public String getLinkStatus() {
        return linkStatus;
    }

    public void setLinkStatus(String linkStatus) {
        this.linkStatus = linkStatus;
    }

    public boolean isBatsUp() {
        return batsUp;
    }

    public Double getSignalToNoise() {
        return signalToNoise;
    }

    public void setSignalToNoise(Double signalToNoise) {
        this.signalToNoise = signalToNoise;
    }

    public void setSignalToNoiseFromString(String signalToNoise) {
        this.signalToNoise = Helpers.stringToDouble(signalToNoise, 0.00);
    }

    public void setBatsUp(boolean batsUp) {
        this.batsUp = batsUp;
    }

    public String getAssociatedShoresideMacAddress() {
        return associatedShoresideMacAddress;
    }

    public void setAssociatedShoresideMacAddress(String associatedShoresideMacAddress) {
        this.associatedShoresideMacAddress = associatedShoresideMacAddress;
    }

    public void setAndParseOriginalStatusString(String originalStatusString) {
        logger.debug("BATS {} has status {}", batsName, originalStatusString);
        this.originalStatusString = originalStatusString;
        cleanStatusString();
        parseStatusString();
    }

    public BatsReport()  {
    }

    public BatsReport(String name)  {
        this.batsName = name;
    }

    private String getValue(String statusLine) {
        //Find the = sign, get the text after that, and trim it.
        int loc = statusLine.indexOf('=');
        if (loc == -1) {
            logger.error("No value was found in the BATS status {}", statusLine);
            return "";
        }
        String value = statusLine.substring(loc + 1);
        return value.trim();
    }

    protected void cleanStatusString() {

        originalStatusString = originalStatusString.trim();
        //If it starts with the show status command, remove that
        final String opener = "show status\r\n";
        if (originalStatusString.startsWith(opener)) {
            originalStatusString = originalStatusString.substring(opener.length()).trim();
        }

        //If it ends with the prompt, remove that too
        final String closer = batsName + "#";
        while (originalStatusString.endsWith(closer)) {
            //Find the entire last line and clip it.
            int loc = originalStatusString.lastIndexOf('\n');
            originalStatusString = originalStatusString.substring(0,loc).trim();
        }
    }

    protected void parseStatusString() {

        String [] status = originalStatusString.split("\\n");

        for (String statusLine : status) {
            if (statusLine.startsWith("Software Version")) {
                batsVersion = getValue(statusLine);
            } else if (statusLine.startsWith("Status")) {
                setBatsStatus(getValue(statusLine));
            } else if (statusLine.startsWith("RSSI")) {
                setRssiFromString(getValue(statusLine));
            } else if (statusLine.startsWith("Location")) {
                gpsLocation = getValue(statusLine);
            } else if (statusLine.startsWith("Calculated Speed")) {
                setShipSpeedFromString(getValue(statusLine));
            } else if (statusLine.startsWith("SNR")) {
                setSignalToNoiseFromString(getValue(statusLine));
            } else if (statusLine.startsWith("Link Status")) {
                linkStatus = getValue(statusLine);
            }
        }  //end the for loop
    }
}
