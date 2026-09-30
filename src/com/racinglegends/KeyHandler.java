package com.racinglegends;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/** Keyboard input for both players plus restart. */
public class KeyHandler implements KeyListener {
    public boolean p1Up;
    public boolean p1Down;
    public boolean p1Left;
    public boolean p1Right;
    public boolean p2Up;
    public boolean p2Down;
    public boolean p2Left;
    public boolean p2Right;
    public boolean restartPressed;

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == GameConstants.P1_UP) {
            p1Up = true;
        }
        if (code == GameConstants.P1_DOWN) {
            p1Down = true;
        }
        if (code == GameConstants.P1_LEFT) {
            p1Left = true;
        }
        if (code == GameConstants.P1_RIGHT) {
            p1Right = true;
        }
        if (code == GameConstants.P2_UP) {
            p2Up = true;
        }
        if (code == GameConstants.P2_DOWN) {
            p2Down = true;
        }
        if (code == GameConstants.P2_LEFT) {
            p2Left = true;
        }
        if (code == GameConstants.P2_RIGHT) {
            p2Right = true;
        }
        if (code == GameConstants.KEY_RESTART) {
            restartPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == GameConstants.P1_UP) {
            p1Up = false;
        }
        if (code == GameConstants.P1_DOWN) {
            p1Down = false;
        }
        if (code == GameConstants.P1_LEFT) {
            p1Left = false;
        }
        if (code == GameConstants.P1_RIGHT) {
            p1Right = false;
        }
        if (code == GameConstants.P2_UP) {
            p2Up = false;
        }
        if (code == GameConstants.P2_DOWN) {
            p2Down = false;
        }
        if (code == GameConstants.P2_LEFT) {
            p2Left = false;
        }
        if (code == GameConstants.P2_RIGHT) {
            p2Right = false;
        }
        if (code == GameConstants.KEY_RESTART) {
            restartPressed = false;
        }
    }

    public void reset() {
        p1Up = p1Down = p1Left = p1Right = false;
        p2Up = p2Down = p2Left = p2Right = false;
        restartPressed = false;
    }

    /** Normalize diagonal movement so it is not faster than cardinal movement. */
    public float getDiagonalMultiplier(boolean forPlayer2) {
        boolean vertical;
        boolean horizontal;
        if (forPlayer2) {
            vertical = p2Up || p2Down;
            horizontal = p2Left || p2Right;
        } else {
            vertical = p1Up || p1Down;
            horizontal = p1Left || p1Right;
        }
        return (vertical && horizontal) ? 0.707f : 1.0f;
    }

    /** Backward-compatible helper used by existing tests. */
    public float getDiagonalMultiplier() {
        return getDiagonalMultiplier(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // unused
    }
}
