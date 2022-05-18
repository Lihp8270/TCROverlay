package com.Model;

public class Session {
    private String sessionName;
    private String sessionID;
    private int sessionType;
    private int sessionLength;

    /**
     * Constructor
     *
     * @param sessionName
     * @param sessionID
     * @param sessionType 1 = Laps / 2 = Mins
     * @param sessionLength
     */
    public Session(String sessionName, String sessionID, int sessionType, int sessionLength) {
        this.sessionName = sessionName;
        this.sessionID = sessionID;
        this.sessionType = sessionType;
        this.sessionLength = sessionLength;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public int getSessionType() {
        return sessionType;
    }

    public void setSessionType(int sessionType) {
        this.sessionType = sessionType;
    }

    public int getSessionLength() {
        return sessionLength;
    }

    public void setSessionLength(int sessionLength) {
        this.sessionLength = sessionLength;
    }
}
