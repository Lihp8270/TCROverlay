package com.Model;

import com.Util.Constants;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SessionQueueTest {

    SessionQueue sessionTestCreate() {
        LinkedList<Session> sessionsToAdd = new LinkedList<>();

        Session practice = new Session("Practice", "P", Constants.MINS, 60);
        Session qualifying = new Session("Qualifying","Q", Constants.MINS, 60);
        Session race = new Session("Race", "R", Constants.LAPS, 15);

        sessionsToAdd.addFirst(race);
        sessionsToAdd.addFirst(qualifying);
        sessionsToAdd.addFirst(practice);

        return new SessionQueue(sessionsToAdd);
    }

    @Test
    void readNextSession() {
        SessionQueue sessionQueue = sessionTestCreate();

        assertEquals("Practice", sessionQueue.readNextSession().getSessionName());
        assertEquals("P", sessionQueue.readNextSession().getSessionID());
        assertEquals(2, sessionQueue.readNextSession().getSessionType());
        assertEquals(60, sessionQueue.readNextSession().getSessionLength());
    }

    @Test
    void getCurrentSession() {
        SessionQueue sessionQueue = sessionTestCreate();

        assertNull(sessionQueue.getCurrentSession()); // Should be null, no current session

        sessionQueue.nextSession(); // Set next session

        // Current Session is Practice
        assertEquals("Practice", sessionQueue.getCurrentSession().getSessionName());
        assertEquals("P", sessionQueue.getCurrentSession().getSessionID());
        assertEquals(2, sessionQueue.getCurrentSession().getSessionType());
        assertEquals(60, sessionQueue.getCurrentSession().getSessionLength());

        sessionQueue.nextSession();
        assertEquals("Qualifying", sessionQueue.getCurrentSession().getSessionName());
        assertEquals("Q", sessionQueue.getCurrentSession().getSessionID());
        assertEquals(2, sessionQueue.getCurrentSession().getSessionType());
        assertEquals(60, sessionQueue.getCurrentSession().getSessionLength());

        sessionQueue.nextSession();
        assertEquals("Race", sessionQueue.getCurrentSession().getSessionName());
        assertEquals("R", sessionQueue.getCurrentSession().getSessionID());
        assertEquals(1, sessionQueue.getCurrentSession().getSessionType());
        assertEquals(15, sessionQueue.getCurrentSession().getSessionLength());

        sessionQueue.nextSession();
        assertNull(sessionQueue.getCurrentSession());
    }

    @Test
    void sessionsRemaining() {
        SessionQueue sessionQueue = sessionTestCreate();

        assertEquals(3, sessionQueue.sessionsRemaining());

        sessionQueue.nextSession();
        assertEquals(2,sessionQueue.sessionsRemaining());

        sessionQueue.nextSession();
        assertEquals(1,sessionQueue.sessionsRemaining());

        sessionQueue.nextSession();
        assertEquals(0,sessionQueue.sessionsRemaining());
    }
}