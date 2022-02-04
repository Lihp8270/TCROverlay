package com.Overlay;

import javax.swing.JPanel;

public class InitPanel {
    protected JPanel panel;
    protected Boolean visible;

    public InitPanel() {
        panel = new JPanel();
        this.panel.setOpaque(false);
    }

    /**
     * Returns JPanel object
     * @return JPanel
     */
    public JPanel getPanel() {
        return panel;
    }

}
