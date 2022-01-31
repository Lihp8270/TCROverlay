package com.Engine;

import com.Overlay.OverlayFrame;

public class ButtonEngine {
    public ButtonEngine() {

    }

     /**
     * Show frame
     */
    public void show(OverlayFrame frame) {
        frame.show();
    }

    /**
     * Hide frame
     * @param frame
     */
    public void hide(OverlayFrame frame) {
        frame.hide();
    }
}
