package com.mtnsat.testcode;

import org.apache.commons.scxml.env.AbstractStateMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: evanreynolds
 */
public class TestShip extends AbstractStateMachine {

    private Logger logger = LoggerFactory.getLogger(TestShip.class);

    /** The events for the stop watch. */
    public static final String SHIP_MOVING = "ship.moving",
            SHIP_STOPPED = "ship.stopped", WIFI_CONNECTED = "wifi.connected",
            WIFI_DISCONNECTED = "wifi.disconnected";


    public TestShip() {
        super(StopWatch.class.getClassLoader().getResource("com/mtnsat/testcode/TestShip.xml"));
    }


    public void Moving() {
        System.out.println("Moving!");
    }

    public void ConnectedMoving() {
        System.out.println("ConnectedMoving!");
    }

    public void DisconnectedMoving () {
        System.out.println("DisconnectedMoving!");
    }

    public void Stopped() {
        System.out.println("Stopped!");
    }

    public void DisconnectedStopped() {
        System.out.println("DisconnectedStopped!");
    }

    public void ConnectedStopped() {
        System.out.println("ConnectedStopped!");
    }

    public void MoveToWiFi() {
        System.out.println("Move To WiFi!");
    }




    public static void main(String[] args) {
        TestShip testShip = new TestShip();

        System.out.println("Firing events!");

        testShip.fireEvent(TestShip.WIFI_CONNECTED);
        testShip.fireEvent(TestShip.SHIP_MOVING);
        testShip.fireEvent(TestShip.SHIP_STOPPED);

        System.out.println("Exiting!");
    }


}
