package com.Model.Overlay;

import com.Engine.ButtonEngine;
import com.Engine.OverlayController;
import com.Util.InputValidators;
import com.sun.jdi.request.MonitorContendedEnterRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class MenuFrame extends InitFrame {
    private ButtonEngine buttonController;
    private OverlayFrame overlayFrame;
    private ArrayList<JButton> buttons;
    private OverlayController overlayController;
    private JTextField lapTextField;
    private InputValidators validator;
    private JLabel versionNumber;

    public MenuFrame(String title, Boolean visibility) throws IOException, FontFormatException {
        super(title, visibility);
        validator = new InputValidators();
        buttonController = new ButtonEngine();
        buttons = new ArrayList<>();
        overlayController = new OverlayController("config/config.json");
        versionNumber = new JLabel();

        initialiseFrame(overlayController.getOverlayFrame());
        createButtons();
        createTextFields();
        showButtons();
    }

    private void initialiseFrame(OverlayFrame overlayFrame) {
        this.frame.setSize(250,310);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLayout(null);
        this.overlayFrame = overlayFrame;
        addVersionNumber("v2.0.0");
    }

    private void addVersionNumber(String version) {
        this.versionNumber.setText(version);
        this.versionNumber.setHorizontalAlignment(SwingConstants.LEFT);
        this.versionNumber.setVerticalAlignment(SwingConstants.TOP);
        this.versionNumber.setSize(250,310);
    }

    /**
     * Create button objects
     */
    private void createButtons() {
        addButtons("Show Overlay", 40, 150, 150, 30, false);
        addButtons("Start Overlay", 40, 110, 150, 30, false);
        addButtons("Laps", 40, 70, 70, 30, true);
        addButtons("Delta to Lead", 40,190, 150, 30, false);
        addButtons("Mins", 120, 70, 70, 30, true);

        createButtonActions();
    }

    private void createTextFields() {
        this.lapTextField = new JTextField("Laps / Minutes");
        lapTextField.setBounds(40,30,150,30);
    }

    /**
     * Create button
     * @param label Button label
     * @param x pixels
     * @param y pixels
     * @param width pixels
     * @param height pixels
     */
    private void addButtons(String label, int x, int y, int width, int height, boolean enabled) {
        JButton button = new JButton();
        button.setBounds(x, y, width, height);
        button.setText(label);
        button.setEnabled(enabled);

        buttons.add(button);
    }

    private void createButtonActions() {
        final String[] toggleLabel = {"Show Overlay"};

        // Show / Hide Overlay Button
        buttons.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonController.toggleShow(overlayController.getOverlayFrame());
                if (overlayFrame.getVisibility()) {
                    toggleLabel[0] = "Hide Overlay";
                    buttons.get(0).setText(toggleLabel[0]);
                } else {
                    toggleLabel[0] = "Show Overlay";
                    buttons.get(0).setText(toggleLabel[0]);
                }
            }
        });

        // Start overlay button
        buttons.get(1).addActionListener(new ActionListener() {
            final String[] toggleLabel = {"Start Overlay"};
            @Override
            public void actionPerformed(ActionEvent e) {
                if (overlayController.getRunning()) {
                    toggleLabel[0] = "Start Overlay";
                    buttons.get(1).setText(toggleLabel[0]);
                    buttons.get(3).setEnabled(false);
                    try {
                        overlayController.stop();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    toggleLabel[0] = "Stop Overlay";
                    buttons.get(1).setText(toggleLabel[0]);
                    buttons.get(0).setEnabled(true);
                    buttons.get(3).setEnabled(true);
                    try {
                        overlayController.run();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Set laps button
        buttons.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validator.checkCharactersForDigitsOnly(lapTextField.getText())) {
                    overlayFrame.setMaxLaps(lapTextField.getText());
                    buttons.get(0).setEnabled(false);
                    buttons.get(1).setEnabled(true);
                    buttons.get(2).setEnabled(false);
                    buttons.get(4).setEnabled(false);
                    lapTextField.setEnabled(false);
                }
            }
        });

        // Delta select button
        buttons.get(3).addActionListener(new ActionListener() {
            final String[] toggleLabel = {"Delta to Lead"};
            @Override
            public void actionPerformed(ActionEvent e) {
                overlayFrame.toggleDeltaMode();

                if (overlayFrame.getDeltaMode() == 0) {
                    toggleLabel[0] = "Delta to Lead";
                    buttons.get(3).setText(toggleLabel[0]);
                } else {
                    toggleLabel[0] = "Delta to car ahead";
                    buttons.get(3).setText(toggleLabel[0]);
                }
            }
        });

        // Mins Button
        buttons.get(4).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validator.checkCharactersForDigitsOnly(lapTextField.getText())) {
                    if(Integer.valueOf(lapTextField.getText()) <= 60) {
                        overlayFrame.setSecondsRemaining(lapTextField.getText());
                        buttons.get(0).setEnabled(false);
                        buttons.get(1).setEnabled(true);
                        buttons.get(2).setEnabled(false);
                        buttons.get(4).setEnabled(false);
                        lapTextField.setEnabled(false);
                    }
                }
            }
        });
    }

    /**
     * Show buttons
     */
    private void showButtons() {
        for (JButton button : buttons) {
            this.frame.add(button);
        }

        this.frame.add(lapTextField);
        this.frame.add(versionNumber);
    }

}
