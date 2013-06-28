package com.mtnsat.testcode.json;

/**
 * Created with IntelliJ IDEA.
 * User: evanreynolds
 */
public class CiscoRouteJson {

    private boolean wifiPingable = false;

    private String currentCiscoInterface;

    private StringBuilder details = new StringBuilder();

    public CiscoRouteJson() {
    }

    public CiscoRouteJson(boolean wifiPingable, String currentCiscoInterface, String details) {
        this.wifiPingable = wifiPingable;
        this.currentCiscoInterface = currentCiscoInterface;
        setDetails(details);
    }

    public boolean getWifiPingable() {
        return wifiPingable;
    }

    public void setWifiPingable(boolean wifiPingable) {
        this.wifiPingable = wifiPingable;
    }

    public String getCurrentCiscoInterface() {
        return currentCiscoInterface;
    }

    public void setCurrentCiscoInterface(String currentCiscoInterface) {
        this.currentCiscoInterface = currentCiscoInterface;
    }

    public String getDetails() {
        return details.toString();
    }

    public void clearDetails() {
        details = new StringBuilder();
    }

    public void setDetails(String newDetails) {
        details = new StringBuilder(newDetails);
    }

    public void addDetail(String detail) {
        this.details.append(detail);
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();

        info.append("Value: ");
        if (wifiPingable) {
            info.append("true, Interface: ");
        } else {
            info.append("false, Interface: ");
        }

        info.append(currentCiscoInterface);

        return info.toString();
    }
}
