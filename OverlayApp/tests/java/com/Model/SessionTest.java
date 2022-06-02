package com.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.Util.Constants;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    @Test
    void getSessionName() {
        Session session = new Session("Test", "T", Constants.LAPS,6);
        assertEquals("Test", session.getSessionName());
    }

    @Test
    void getSessionID() {
        Session session = new Session("Test", "T", Constants.LAPS,6);
        assertEquals("T", session.getSessionID());
    }

    @Test
    void getSessionType() {
        Session session = new Session("Test", "T", Constants.LAPS,6);
        assertEquals(1, session.getSessionType());

        session.setSessionType(Constants.MINS);
        assertEquals(2, session.getSessionType());
    }

    @Test
    void getSessionLength() {
        Session session = new Session("Test", "T", Constants.LAPS,6);
        assertEquals(6, session.getSessionLength());
    }
}