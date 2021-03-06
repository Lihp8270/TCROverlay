package com.Model.Overlay;

import com.Model.Config;
import com.Util.TimeParser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class TopPanel extends InitPanel {
    private Font font;
    private JLabel lapLabel;
    private final ImageIcon icon;
    private String lap;
    private String maxLaps;
    private int secondsRemaining;
    private boolean finalLap;
    private TimeParser timeParser;
    private int sessionMode;
    private SwingWorker timerWorker;
    private String sessionIdentifier;
    private boolean timerPause;
    private boolean timerRunning;
    private boolean resetReady;
    private int targetDelay;
    private boolean correctRequired;
    private int overshoot;

    public TopPanel(Config config) throws IOException, FontFormatException {
        super();
        icon = new ImageIcon(config.getLapIcon());
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        this.panel.setBorder(new EmptyBorder(config.getLapIconTopPadding(),config.getLapIconLeftPadding(),0,0));
        font = Font.createFont(Font.TRUETYPE_FONT, new File(config.getLapCountFont()));
        font = font.deriveFont(Font.BOLD,config.getLapCountFontSize());
        this.lap = "0";
        this.secondsRemaining = 0;
        this.finalLap = false;
        timeParser = new TimeParser();
        this.sessionMode = 0;
        this.sessionIdentifier = "N/A";
        timerPause = true;
        timerRunning = false;
        targetDelay = 1000;
        correctRequired = false;
        overshoot = 0;

        createLapLabel();
    }

    @Override
    public JPanel getPanel() {
        lapLabel.setText(lapText(sessionMode));
        this.panel.add(lapLabel);

        return this.panel;
    }

    public void setResetReady(boolean resetReady) {
        this.resetReady = resetReady;
    }

    public boolean isResetReady() {
        return resetReady;
    }

    /**
     * Create the lap counter label
     */
    public void createLapLabel() {
        lapLabel = new JLabel();
        lapLabel.setForeground(Color.WHITE);
        lapLabel.setIcon(icon);
        lapLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lapLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        lapLabel.setFont(font);

        lapLabel.setText(lapText(sessionMode));
    }

    /**
     * Set lap count
     * @param lap current lap
     */
    public void setLaps(String lap) {
        if(!finalLap) {
            this.lap = lap;
        }

        if (this.lap.equals(maxLaps)) {
            finalLap = true;
        }
    }

    /**
     * Set maximum laps on the label
     * @param maxLaps maximum number of laps
     */
    public void setMaxLaps(String maxLaps) {
        setSessionMode(1);
        this.maxLaps = maxLaps;
    }

    /**
     * Set seconds remaining from given minutes
     * @param mins number of minutes for the session
     */
    public void setSecondsRemaining(String mins) {
        setSessionMode(2);
        this.secondsRemaining = timeParser.getSecondsFromMinutes(mins);
        this.maxLaps = "99"; // Required to prevent application from crashing
    }

    /**
     * Build lap text label
     * @param mode 1 = laps, 2 = timed
     * @return Concat of laps / max laps
     */
    public String lapText(int mode) {
        switch(mode) {
            case 1:
                return sessionIdentifier + " " + lap + " / " + maxLaps;
            case 2:
                if (secondsRemaining < 45) {
                    resetReady = true;
                }
                if (secondsRemaining == 0) {
                    setTimerPause(true);
                }
                return sessionIdentifier + " " + timeParser.getTimeRemainingFromSeconds(secondsRemaining);
            default:
                return "NOT SET";
        }
    }

    /**
     * Gets max race laps as integer
     * @return integer max laps
     */
    public int getMaxLaps() {
        return Integer.valueOf(maxLaps);
    }

    /**
     * Sets session mode
     * @param mode 1 = Laps, 2 = Timed
     */
    private void setSessionMode(int mode) {
        this.sessionMode = mode;
    }

    /**
     * Create timer thread
     */
    public void createTimer() {
        if(timerWorker == null) {
            SwingWorker swTimer = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    while(true) {
                        Instant start = Instant.now();
                        Thread.sleep(targetDelay);
                        Instant finish = Instant.now();

                        Long elapsed = Duration.between(start, finish).toMillis();

                        overshoot = (int) (elapsed - targetDelay);

                        targetDelay = 1000 - overshoot;

                        if (!timerPause) {
                            tick();
                        }
                    }
                }
            };
            this.timerWorker = swTimer;
        }
    }

    /**
     * Start the timer
     */
    public void startTimer() {
        if(timerWorker != null) {
            if (timerRunning != true) {
                timerWorker.execute();
                timerRunning = true;
                timerPause = false;
            }
//            timerPause = false;
        }
    }

    /**
     * Reduce time remaining by 1 second
     */
    private void tick() {
        if(secondsRemaining > 0) {
            secondsRemaining--;
        }
    }

    /**
     * Get selected session mode
     * @return 1 = Laps, 2 = Timed
     */
    public int getSessionMode() {
        return sessionMode;
    }

    /**
     * Seconds remainning in the session
     * @return integer of seconds remaining
     */
    public int getSecondsRemaining() {
        return secondsRemaining;
    }

    public void setSessionIdentifier(String sessionIdentifier) {
        this.sessionIdentifier = sessionIdentifier;
    }

    public String getSessionIdentifier() {
        return sessionIdentifier;
    }

    public void setTimerPause(boolean pause) {
        timerPause = pause;
    }

}
