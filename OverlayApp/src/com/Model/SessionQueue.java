package com.Model;

import java.util.LinkedList;

public class SessionQueue {
    LinkedList<Session> sessionQueue;
    Session currentSession;

    /**
     * Constructor
     * @param sessions ArrayList of session objects
     */
    public SessionQueue(LinkedList<Session> sessions) {
        this.sessionQueue = sessions;

    }

    public void addSession(Session session) {
        sessionQueue.addLast(session);
    }

    public Session readNextSession() {
        if (!sessionQueue.isEmpty()) {
            return sessionQueue.getFirst();
        }

        return null;
    }

    public Session getCurrentSession() {
        if (currentSession != null) {
            return currentSession;
        }

        return null;
    }

    public void nextSession() {
        if (!sessionQueue.isEmpty()) {
            currentSession = sessionQueue.getFirst();
            sessionQueue.removeFirst();
        } else {
            currentSession = null;
        }
    }

    public int sessionsRemaining() {
        return sessionQueue.size();
    }

}
