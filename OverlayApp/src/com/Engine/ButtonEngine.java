package com.Engine;

import com.Model.Overlay.OverlayFrame;

public class ButtonEngine {
    public ButtonEngine() {

    }

    /**
     * Toggles visibility of a frame
     * @param frame Overlay Frame to toggle on and off
     */
    public void toggleShow(OverlayFrame frame) {
        frame.toggleVisibility();
    }
}
