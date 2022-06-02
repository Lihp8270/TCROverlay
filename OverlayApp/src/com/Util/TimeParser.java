package com.Util;

import java.util.concurrent.TimeUnit;

public class TimeParser {

    public TimeParser() {

    }

    /**
     * Returns number of seconds from given minutes
     * @param mins
     * @return
     */
    public int getSecondsFromMinutes(int mins) {
        return mins * 60;
    }

    /**
     * Returns number of seconds from given minutes from string input
     * @param mins
     * @return
     */
    public int getSecondsFromMinutes(String mins) {
        return getSecondsFromMinutes(Integer.valueOf(mins));
    }

    public String getTimeRemainingFromSeconds(int seconds) {
        int minutesRemaining = 0;
        int secondsRemaining = 0;

        minutesRemaining = (seconds % 3601) / 60;
        secondsRemaining = seconds % 60;

        return String.format("%02d:%02d", minutesRemaining, secondsRemaining);
    }

    public String getLapTime(Long ms) {
        if (ms == 999999) {
            return "NO TIME";
        }

        long minutes = TimeUnit.MILLISECONDS.toMinutes(ms);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(ms) - (minutes * 60);
        long afterDec = TimeUnit.MILLISECONDS.toMillis(ms) - (minutes * 60000) - (seconds * 1000);

        return String.format("%01d:%02d.%03d", minutes, seconds, afterDec);

    }

}
