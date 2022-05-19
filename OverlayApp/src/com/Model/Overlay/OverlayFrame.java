package com.Model.Overlay;

import com.Model.Config;
import com.Model.Session;
import com.Model.SessionQueue;
import com.Util.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class OverlayFrame extends InitFrame {
    private final Dimension dim;
    private JPanel driverPanel;
    private JPanel advertPanel;
    private JPanel driverNamePanel;
    private JPanel lapPanel;
    private DriversPanel drivers;
    private AdvertPanel advert;
    private BottomPanel bottomPanel;
    private TopPanel topPanel;
    private final Config config;
    private String currentFocussedDriver;
    private int lastLapDisplayed;
    private int deltaMode;
    private int lastSecondsRemaining;
    private boolean hasRaceStarted;
    private boolean hasRaceFinished;
    private SessionQueue sessionQueue;

    /**
     * Constructor for overlay
     *
     * @param title
     * @param visibility
     * @param drivers
     * @param advert
     * @param bottomPanel
     * @param topPanel
     * @param config
     */
    public OverlayFrame(String title, Boolean visibility, DriversPanel drivers, AdvertPanel advert, BottomPanel bottomPanel, TopPanel topPanel, Config config) {
        super(title, visibility);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.drivers = drivers;
        this.advert = advert;
        this.config = config;
        this.bottomPanel = bottomPanel;
        this.topPanel = topPanel;
        this.currentFocussedDriver = "";
        this.lastLapDisplayed = 0;
        this.lastSecondsRemaining = 0;
        this.deltaMode = 0;
        this.hasRaceStarted = false;
        this.hasRaceFinished = false;
        initialiseFrame();
    }

    private void initialiseFrame() {
        this.frame.setLocation(config.getOverlayXOffset(), config.getOverlayYOffset());
        this.frame.setSize(dim);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setLayout(new BorderLayout());
        this.frame.setUndecorated(true);
        this.frame.setBackground(new Color(0, 255, 0, 0));
        this.frame.setAlwaysOnTop(true);

        this.frame.setContentPane(new ContentPane());
        this.frame.getContentPane().setBackground(Color.BLACK);
        this.frame.setLayout(new BorderLayout());

        driverPanel = drivers.getPanel();
        advertPanel = advert.getPanel();
        driverNamePanel = bottomPanel.getPanel();
        lapPanel = topPanel.getPanel();

        topPanel.createTimer();

        this.frame.add(driverPanel, BorderLayout.WEST);
        this.frame.add(advertPanel, BorderLayout.EAST);
        this.frame.add(driverNamePanel, BorderLayout.SOUTH);
        this.frame.add(lapPanel, BorderLayout.CENTER);
    }

    public void setSessionQueue(LinkedList<Session> sessionsFromMenu) {
        this.sessionQueue = new SessionQueue(sessionsFromMenu);

        sessionQueue.nextSession();
        setSessionDuration();
    }

    private void setSessionDuration() {
        if (sessionQueue.getCurrentSession().getSessionType() == Constants.LAPS) {
            setMaxLaps(String.valueOf(sessionQueue.getCurrentSession().getSessionLength()));
        } else {
            setSecondsRemaining(String.valueOf(sessionQueue.getCurrentSession().getSessionLength()));
        }
    }

    private void endSession() {
        if (sessionQueue.readNextSession().getSessionID().equals("R")) {
            topPanel.setTimerPause(true);
        }

        sessionQueue.nextSession();
        setSessionDuration();
        topPanel.setResetReady(false);
    }

    /**
     * Toggle overlay visibility
     */
    public void toggleVisibility() {
        if (visible) {
            this.frame.setVisible(false);
            visible = false;
        } else {
            this.frame.setVisible(true);
            visible = true;
        }
    }

    public void startStopSession() {
        if (hasRaceStarted == false) {
            if (sessionQueue.getCurrentSession().getSessionID().equals("R")) {
                if(drivers.getRaceStarted()) {
                    hasRaceStarted = true;
                    if (!topPanel.isTimerRunning()) {
                        topPanel.startTimer();
                        topPanel.setTimerPause(false);
                    } else {
                        topPanel.setTimerPause(false);
                    }
                }
            } else {
                hasRaceStarted = true;
                if (!topPanel.isTimerRunning()) {
                    topPanel.startTimer();
                }
            }
        }
    }

    /**
     * Update Drivers panel
     * @param drivers new DriverPanel to use
     */
    public void updateDrivers(DriversPanel drivers, boolean sessionReset) {
        if(!getHasRaceFinished()) {
            this.driverPanel.removeAll();
            this.driverPanel = drivers.getPanel(currentFocussedDriver, deltaMode, topPanel, sessionQueue.getCurrentSession());

            // TODO Crashing when going to Race Mode
            if (sessionReset && topPanel.isResetReady()) {
                endSession();
            }

            startStopSession();

            this.frame.add(driverPanel, BorderLayout.WEST);
            this.frame.repaint();
            this.frame.revalidate();
        }
    }

    /**
     * Updates focussed driver name label
     * @param driverName Driver name being focussed
     */
    public void updateLargeName(String driverName) {
        if (!(currentFocussedDriver.equals(driverName))) {
            this.driverNamePanel.removeAll();
            bottomPanel.setDriverName(driverName);

            for (int i = 0; i < drivers.getDrivers().size(); i++) {
                if (drivers.getDrivers().get(i).getName().equals(driverName)) {
                    bottomPanel.setCarLogo(drivers.getDrivers().get(i).getCar());
                }
            }

            this.driverNamePanel = bottomPanel.getPanel();
            this.frame.add(driverNamePanel, BorderLayout.SOUTH);
            this.frame.repaint();
            this.frame.revalidate();

            currentFocussedDriver = driverName;
        }
    }

    /**
     * Update the graphic for current lap
     */
    public void updateLapGraphic() {
        switch (topPanel.getSessionMode()) {
            case 1:
                if (!(lastLapDisplayed == drivers.getDrivers().get(0).getCompletedLaps() + 1)) {
                    this.lapPanel.removeAll();
                    topPanel.setLaps(String.valueOf(drivers.getDrivers().get(0).getCompletedLaps() + 1));
                    rebuildLapPanel();
                    lastLapDisplayed = drivers.getDrivers().get(0).getCompletedLaps() + 1;
                }
                break;
            case 2:
                if(!(lastSecondsRemaining == topPanel.getSecondsRemaining())) {
                    rebuildLapPanel();
                    lastSecondsRemaining = topPanel.getSecondsRemaining();
                    if (lastSecondsRemaining == 0) {
                        hasRaceFinished = true;
                    }
                }
                break;
            default:
                break;
        }
    }

    private void rebuildLapPanel() {
        topPanel.setSessionIdentifier(sessionQueue.getCurrentSession().getSessionID());

        this.lapPanel = topPanel.getPanel();
        this.frame.add(lapPanel, BorderLayout.CENTER);
        this.frame.repaint();
        this.frame.revalidate();
    }

    /**
     * Set the max number of laps from the menu
     * Also sets session mode flag as 1
     * @param maxLaps number of laps
     */
    public void setMaxLaps(String maxLaps) {
        topPanel.setMaxLaps(maxLaps);
    }

    /**
     * Set session length in minutes from the menu
     * Also sets session mode flag as 2
     * @param mins
     */
    public void setSecondsRemaining(String mins) {
        topPanel.setSecondsRemaining(mins); // This is from the race tab in menu
    }

    /**
     * Toggle delta display mode
     */
    public void toggleDeltaMode() {
        if (deltaMode == 0) {
            deltaMode = 1;
        } else {
            deltaMode = 0;
        }
    }

    /**
     * Get delta mode
     * @return 0 = Delta to leader, 1 = Delta to car ahead
     */
    public int getDeltaMode() {
        return deltaMode;
    }

    public boolean getHasRaceFinished() {
        return hasRaceFinished;
    }

}
