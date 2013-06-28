package com.mtnsat.testcode.batsReport;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: evanreynolds
 */

/**
 * This file exists for only one reason - to enable the proper marshalling and unmarshalling of
 * this list as a JSON object by Jersey in the web interface.
 */

public class BatsReportList {

    public List<BatsReport> getBatsReportList() {
        return batsReportList;
    }

    public void setBatsReportList(List<BatsReport> batsReportList) {
        this.batsReportList = batsReportList;
    }

    private List<BatsReport> batsReportList;

}
