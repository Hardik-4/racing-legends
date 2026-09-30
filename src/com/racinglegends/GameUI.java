package com.racinglegends;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/** HUD helpers for controls, status, and win overlay. */
public final class GameUI {
    private GameUI() {}

    public static void drawHud(Graphics2D g2d, Car player1, Car player2,
                               int p1CheckpointsPassed, int p2CheckpointsPassed,
                               int totalCheckpoints, int fps, boolean gameOver, String winner) {
        g2d.setColor(GameConstants.UI_PANEL_COLOR);
        g2d.fillRoundRect(12, 12, 280, 150, 12, 12);
        g2d.setColor(Color.WHITE);
        g2d.drawRoundRect(12, 12, 280, 150, 12, 12);

        g2d.setFont(GameConstants.UI_FONT);
        g2d.setColor(GameConstants.UI_TEXT_COLOR);
        g2d.drawString("Racing Legends — local multiplayer", 24, 36);
        g2d.drawString("P1 (Red): WASD", 24, 60);
        g2d.drawString("P2 (Blue): Arrow keys", 24, 80);
        g2d.drawString("Pass all checkpoints, then finish line", 24, 100);
        g2d.drawString("Press R to restart", 24, 120);

        if (player1 != null) {
            g2d.drawString("P1 checkpoints: " + p1CheckpointsPassed + "/" + totalCheckpoints, 24, 144);
        }
        if (player2 != null) {
            g2d.drawString("P2 checkpoints: " + p2CheckpointsPassed + "/" + totalCheckpoints,
                    GameConstants.WINDOW_WIDTH - 220, 40);
        }

        g2d.drawString("FPS: " + fps, GameConstants.WINDOW_WIDTH - 80, 20);

        if (gameOver) {
            g2d.setColor(new Color(0, 0, 0, 160));
            g2d.fillRect(0, 0, GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
            g2d.setColor(Color.YELLOW);
            g2d.setFont(GameConstants.WIN_FONT);
            String message = winner + " Wins!";
            FontMetrics fm = g2d.getFontMetrics();
            int x = (GameConstants.WINDOW_WIDTH - fm.stringWidth(message)) / 2;
            int y = GameConstants.WINDOW_HEIGHT / 2;
            g2d.drawString(message, x, y);
            g2d.setFont(GameConstants.UI_FONT);
            g2d.setColor(Color.WHITE);
            String hint = "Press R to race again";
            FontMetrics hintFm = g2d.getFontMetrics();
            g2d.drawString(hint,
                    (GameConstants.WINDOW_WIDTH - hintFm.stringWidth(hint)) / 2,
                    y + 40);
        }
    }

    // --- helpers kept for existing unit tests ---
    private static int currentLap = 1;
    private static int racePosition = 1;

    public static void drawUI(Graphics2D g2d, Car car) {
        g2d.setColor(GameConstants.UI_TEXT_COLOR);
        g2d.setFont(GameConstants.TITLE_FONT);
        g2d.drawString("RACING LEGENDS", GameConstants.WINDOW_WIDTH / 2 - 150, 60);
        g2d.setFont(GameConstants.UI_FONT);
        g2d.drawString("Controls:", 20, 100);
        g2d.drawString("W = Up", 20, 120);
        g2d.drawString("S = Down", 20, 140);
        g2d.drawString("A = Left", 20, 160);
        g2d.drawString("D = Right", 20, 180);
        g2d.drawString("LAP: " + currentLap, GameConstants.WINDOW_WIDTH - 100, 50);
        g2d.drawString("POSITION: " + racePosition, GameConstants.WINDOW_WIDTH - 100, 80);
        if (car != null) {
            g2d.drawString("Car Position:", 20, 220);
            g2d.drawString("X: " + car.getX(), 20, 240);
            g2d.drawString("Y: " + car.getY(), 20, 260);
            int speed = Math.abs(car.getVelocityX()) + Math.abs(car.getVelocityY());
            g2d.drawString("Speed:", 20, 300);
            g2d.drawString(String.valueOf(speed), 20, 320);
        }
    }

    public static void drawControlsBox(Graphics2D g2d) {
        g2d.setColor(GameConstants.UI_PANEL_COLOR);
        g2d.fillRect(10, 80, 180, 130);
        g2d.setColor(Color.WHITE);
        g2d.drawRect(10, 80, 180, 130);
        g2d.setFont(GameConstants.UI_FONT);
        g2d.drawString("CONTROLS", 50, 100);
        g2d.drawString("W - Up", 30, 120);
        g2d.drawString("S - Down", 30, 140);
        g2d.drawString("A - Left", 30, 160);
        g2d.drawString("D - Right", 30, 180);
    }

    public static void completeLap() {
        currentLap++;
    }

    public static void resetLap() {
        currentLap = 1;
    }

    public static void setRacePosition(int position) {
        racePosition = position;
    }

    public static int getCurrentLap() {
        return currentLap;
    }

    public static int getRacePosition() {
        return racePosition;
    }
}
