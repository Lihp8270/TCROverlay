package com.Util;

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

}
