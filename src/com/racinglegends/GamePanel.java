package com.racinglegends;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/** Main game loop, rendering, win condition, and restart handling. */
public class GamePanel extends JPanel implements Runnable {
    private Thread gameThread;
    private final KeyHandler keyHandler;
    private final Car playerCar;
    private final Car player2Car;
    private final Track track;
    private volatile boolean running;
    private int fps;
    private boolean gameOver;
    private String winner = "";
    private boolean[] p1Checkpoints;
    private boolean[] p2Checkpoints;

    public GamePanel() {
        setPreferredSize(new Dimension(GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT));
        setBackground(Color.DARK_GRAY);
        setFocusable(true);

        keyHandler = new KeyHandler();
        addKeyListener(keyHandler);

        track = new Track("track.png");
        playerCar = new Car(
                "car_red.png",
                1,
                GameConstants.PLAYER1_START_X,
                GameConstants.PLAYER1_START_Y,
                GameConstants.CAR_WIDTH,
                GameConstants.CAR_HEIGHT,
                Color.RED);
        player2Car = new Car(
                "car_blue.png",
                2,
                GameConstants.PLAYER2_START_X,
                GameConstants.PLAYER2_START_Y,
                GameConstants.CAR_WIDTH,
                GameConstants.CAR_HEIGHT,
                Color.BLUE);
        player2Car.setPlayer2(true);

        resetRaceState();
        running = true;
    }

    public void startGame() {
        if (gameThread != null && gameThread.isAlive()) {
            return;
        }
        gameThread = new Thread(this, "RacingLegends-Loop");
        gameThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / GameConstants.TARGET_FPS;
        long lastFrame = System.nanoTime();
        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                update();
                repaint();
                lastFrame = now;
                frames++;
            }
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                fps = frames;
                frames = 0;
                lastCheck = System.currentTimeMillis();
            }
        }
    }

    private void update() {
        if (keyHandler.restartPressed) {
            restartRace();
            keyHandler.restartPressed = false;
        }

        applyPlayerInput(playerCar, false);
        applyPlayerInput(player2Car, true);

        playerCar.update();
        player2Car.update();

        CollisionDetector.checkTrackBoundary(playerCar);
        CollisionDetector.checkTrackBoundary(player2Car);
        if (CollisionDetector.areCarsColliding(playerCar, player2Car)) {
            CollisionDetector.handleCarCollision(playerCar, player2Car);
        }

        if (!gameOver) {
            int cp1 = track.checkCheckpointCollision(playerCar.getBounds(), p1Checkpoints);
            if (cp1 != -1) {
                p1Checkpoints[cp1] = true;
            }
            int cp2 = track.checkCheckpointCollision(player2Car.getBounds(), p2Checkpoints);
            if (cp2 != -1) {
                p2Checkpoints[cp2] = true;
            }

            if (allTrue(p1Checkpoints) && track.crossesFinishLine(playerCar.getBounds())) {
                gameOver = true;
                winner = "Player 1 (Red)";
            } else if (allTrue(p2Checkpoints) && track.crossesFinishLine(player2Car.getBounds())) {
                gameOver = true;
                winner = "Player 2 (Blue)";
            }
        }
    }

    private void applyPlayerInput(Car car, boolean player2) {
        int dx = 0;
        int dy = 0;
        if (player2) {
            if (keyHandler.p2Up) {
                dy = -1;
            }
            if (keyHandler.p2Down) {
                dy = 1;
            }
            if (keyHandler.p2Left) {
                dx = -1;
            }
            if (keyHandler.p2Right) {
                dx = 1;
            }
        } else {
            if (keyHandler.p1Up) {
                dy = -1;
            }
            if (keyHandler.p1Down) {
                dy = 1;
            }
            if (keyHandler.p1Left) {
                dx = -1;
            }
            if (keyHandler.p1Right) {
                dx = 1;
            }
        }

        if (dx != 0 || dy != 0) {
            float speed = GameConstants.CAR_SPEED * keyHandler.getDiagonalMultiplier(player2);
            car.setVelocityX((int) (dx * speed));
            car.setVelocityY((int) (dy * speed));
        } else {
            car.stop();
        }
    }

    private void restartRace() {
        playerCar.reset(GameConstants.PLAYER1_START_X, GameConstants.PLAYER1_START_Y);
        player2Car.reset(GameConstants.PLAYER2_START_X, GameConstants.PLAYER2_START_Y);
        resetRaceState();
        keyHandler.reset();
    }

    private void resetRaceState() {
        int gates = Math.max(0, track.getCheckpointCount() - 1);
        p1Checkpoints = new boolean[gates];
        p2Checkpoints = new boolean[gates];
        gameOver = false;
        winner = "";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        try {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            track.draw(g2d, this);
            playerCar.draw(g2d, this);
            player2Car.draw(g2d, this);
            GameUI.drawHud(
                    g2d,
                    playerCar,
                    player2Car,
                    countTrue(p1Checkpoints),
                    countTrue(p2Checkpoints),
                    p1Checkpoints.length,
                    fps,
                    gameOver,
                    winner);
        } finally {
            g2d.dispose();
        }
    }

    private static boolean allTrue(boolean[] arr) {
        for (boolean value : arr) {
            if (!value) {
                return false;
            }
        }
        return true;
    }

    private static int countTrue(boolean[] arr) {
        int count = 0;
        for (boolean value : arr) {
            if (value) {
                count++;
            }
        }
        return count;
    }
}
