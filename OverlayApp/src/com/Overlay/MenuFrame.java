package com.Overlay;

import com.Engine.ButtonEngine;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MenuFrame extends InitFrame {
    private ButtonEngine buttonController;
    private OverlayFrame overlayFrame;
    private ArrayList<JButton> buttons;

    public MenuFrame(String title, OverlayFrame overlayFrame, Boolean visibility) {
        super(title, visibility);
        buttonController = new ButtonEngine();
        buttons = new ArrayList<>();

        initialiseFrame(overlayFrame);

        createButtons();
        showButtons();
    }

    private void initialiseFrame(OverlayFrame overlayFrame) {
        this.frame.setSize(800,600);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLayout(null);
        this.overlayFrame = overlayFrame;
    }

    /**
     * Create button objects
     */
    private void createButtons() {
        final String[] toggleLabel = {"Show Overlay"};
        addButtons(toggleLabel[0], 50, 100, 150, 30);
        addButtons("Update", 50, 150, 150, 30);

        buttons.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonController.toggleShow(overlayFrame);
                if (overlayFrame.getVisibility()) {
                    toggleLabel[0] = "Hide Overlay";
                    buttons.get(0).setText(toggleLabel[0]);
                } else {
                    toggleLabel[0] = "Show Overlay";
                    buttons.get(0).setText(toggleLabel[0]);
                }
            }
        });
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

    /**
     * Show buttons
     */
    private void showButtons() {
        for (JButton button : buttons) {
            this.frame.add(button);
        }
    }

}
