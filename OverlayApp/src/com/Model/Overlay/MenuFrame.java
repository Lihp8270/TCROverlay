package com.Model.Overlay;

import com.Engine.ButtonEngine;
import com.Engine.OverlayController;

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

    public MenuFrame(String title, Boolean visibility) throws IOException, FontFormatException {
        super(title, visibility);
        buttonController = new ButtonEngine();
        buttons = new ArrayList<>();
        overlayController = new OverlayController("config/config.json");

        initialiseFrame(overlayController.getOverlayFrame());
        createButtons();
        createTextFields();
        showButtons();
    }

    private void initialiseFrame(OverlayFrame overlayFrame) {
        this.frame.setSize(250,270);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLayout(null);
        this.overlayFrame = overlayFrame;
    }

    /**
     * Create button objects
     */
    private void createButtons() {
        addButtons("Show Overlay", 40, 30, 150, 30);
        addButtons("Start Overlay", 40, 70, 150, 30);
        addButtons("Set Laps", 40, 150, 150, 30);

        createButtonActions();
    }

    private void createTextFields() {
        this.lapTextField = new JTextField("No. of Laps");
        lapTextField.setBounds(40,110,150,30);
    }

    /**
     * Create button
     * @param label Button label
     * @param x pixels
     * @param y pixels
     * @param width pixels
     * @param height pixels
     */
    private void addButtons(String label, int x, int y, int width, int height) {
        JButton button = new JButton();
        button.setBounds(x, y, width, height);
        button.setText(label);

        buttons.add(button);
    }

    private void createButtonActions() {
        final String[] toggleLabel = {"Show Overlay"};

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

        buttons.get(1).addActionListener(new ActionListener() {
            final String[] toggleLabel = {"Start Overlay"};
            @Override
            public void actionPerformed(ActionEvent e) {
                if (overlayController.getRunning()) {
                    toggleLabel[0] = "Start Overlay";
                    buttons.get(1).setText(toggleLabel[0]);
                    try {
                        overlayController.stop();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    toggleLabel[0] = "Stop Overlay";
                    buttons.get(1).setText(toggleLabel[0]);
                    try {
                        overlayController.run();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        buttons.get(2).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(lapTextField.getText().equals("No. of Laps"))) {
                    overlayFrame.setMaxLaps(lapTextField.getText());
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
    }

}
