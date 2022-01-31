package com.Overlay;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.Engine.ButtonEngine;

public class MenuFrame extends OverlayFrame {
    private ButtonEngine buttonController;
    private OverlayFrame overlayFrame;
    private ArrayList<JButton> buttons;

    public MenuFrame(String title, OverlayFrame overlayFrame) {
        super(title);
        this.overlayFrame = overlayFrame;
        buttonController = new ButtonEngine();
        buttons = new ArrayList<JButton>();
        // TODO Move to add buttons method
        addButtons("Hide Overlay", 50, 100, 95, 30);
        addButtons("Show Overlay", 50, 150, 95, 30);

        buttons.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonController.hide(overlayFrame);
            }
        });

        buttons.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonController.show(overlayFrame);
            }
        });

        showButtons();
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
        for (int i = 0; i < buttons.size(); i++) {
            this.frame.add(buttons.get(i));
        }
    }

}
