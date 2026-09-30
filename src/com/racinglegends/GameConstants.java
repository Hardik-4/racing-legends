package com.racinglegends;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

/** Shared configuration for window, track, cars, and controls. */
public final class GameConstants {
    private GameConstants() {}

    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 800;
    public static final String GAME_TITLE = "Racing Legends";

    public static final int TARGET_FPS = 60;

    public static final int TRACK_LEFT = 100;
    public static final int TRACK_RIGHT = 1100;
    public static final int TRACK_TOP = 100;
    public static final int TRACK_BOTTOM = 700;
    public static final int TRACK_WIDTH = TRACK_RIGHT - TRACK_LEFT;
    public static final int TRACK_HEIGHT = TRACK_BOTTOM - TRACK_TOP;

    public static final int CAR_WIDTH = 40;
    public static final int CAR_HEIGHT = 60;
    public static final int CAR_SPEED = 5;

    public static final int PLAYER1_START_X = 315;
    public static final int PLAYER1_START_Y = 120;
    public static final int PLAYER2_START_X = 315;
    public static final int PLAYER2_START_Y = 190;

    /** Legacy aliases kept for existing unit tests. */
    public static final int PLAYER_START_X = PLAYER1_START_X;
    public static final int PLAYER_START_Y = PLAYER1_START_Y;

    public static final int P1_UP = KeyEvent.VK_W;
    public static final int P1_DOWN = KeyEvent.VK_S;
    public static final int P1_LEFT = KeyEvent.VK_A;
    public static final int P1_RIGHT = KeyEvent.VK_D;

    public static final int P2_UP = KeyEvent.VK_UP;
    public static final int P2_DOWN = KeyEvent.VK_DOWN;
    public static final int P2_LEFT = KeyEvent.VK_LEFT;
    public static final int P2_RIGHT = KeyEvent.VK_RIGHT;

    public static final int KEY_RESTART = KeyEvent.VK_R;

    public static final Color TRACK_COLOR = new Color(50, 50, 50);
    public static final Color TRACK_BORDER_COLOR = Color.WHITE;
    public static final Color UI_TEXT_COLOR = Color.WHITE;
    public static final Color UI_PANEL_COLOR = new Color(0, 0, 0, 150);

    public static final Font UI_FONT = new Font("SansSerif", Font.PLAIN, 14);
    public static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 36);
    public static final Font WIN_FONT = new Font("SansSerif", Font.BOLD, 48);
}
