package com.Model;

import javax.swing.*;
import java.awt.*;

public class Driver {
    private String name;
    private final int driverID;
    private int currentPos;
    private int startingPos;
    private int qualPos;
    private String qualTime;
    private String fastestLap;
    private String car;

    public Driver(int driverID, String name) {
        this.driverID = driverID;
        this.name = name;
        this.currentPos = 0;
        this.startingPos = 0;
        this.qualTime = "";
        this.fastestLap = "";
        this.car = "";
        this.qualPos = 0;
    }

    public int getQualPos() {
        return qualPos;
    }

    public void setQualPos(int qualPos) {
        this.qualPos = qualPos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDriverID() {
        return driverID;
    }

    public int getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(int currentPos) {
        this.currentPos = currentPos;
    }

    public int getStartingPos() {
        return startingPos;
    }

    public void setStartingPos(int startingPos) {
        this.startingPos = startingPos;
    }

    public String getQualTime() {
        return qualTime;
    }

    public void setQualTime(String qualTime) {
        this.qualTime = qualTime;
    }

    public String getFastestLap() {
        return fastestLap;
    }

    public void setFastestLap(String fastestLap) {
        this.fastestLap = fastestLap;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public JLabel getJLabel() {
        JLabel driverLabel = new JLabel(name);
        driverLabel.setForeground(Color.WHITE);

        return driverLabel;
    }
}
