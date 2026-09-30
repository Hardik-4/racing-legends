package com.racinglegends;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/** Race track with optional background image and checkpoint gates. */
public class Track {
    private BufferedImage trackImage;
    private final Rectangle bounds;
    private final List<Rectangle> checkpoints = new ArrayList<>();
    private final boolean[] checkpointPassed;

    public Track(String imageFile) {
        this.bounds = new Rectangle(
                GameConstants.TRACK_LEFT,
                GameConstants.TRACK_TOP,
                GameConstants.TRACK_WIDTH,
                GameConstants.TRACK_HEIGHT);
        loadImage(imageFile);

        checkpoints.add(new Rectangle(680, 100, 10, 200));
        checkpoints.add(new Rectangle(940, 325, 200, 10));
        checkpoints.add(new Rectangle(575, 520, 10, 200));
        checkpoints.add(new Rectangle(100, 350, 200, 10));
        checkpoints.add(new Rectangle(365, 100, 10, 200)); // finish line
        checkpointPassed = new boolean[checkpoints.size()];
    }

    private void loadImage(String imageFile) {
        try {
            File file = new File("images/" + imageFile);
            if (file.exists()) {
                this.trackImage = ImageIO.read(file);
            } else {
                System.out.println("Track image not found: images/" + imageFile);
                this.trackImage = null;
            }
        } catch (IOException e) {
            System.out.println("Error loading track image: " + imageFile);
            this.trackImage = null;
        }
    }

    public void draw(Graphics2D g2d, JPanel panel) {
        if (trackImage != null) {
            g2d.drawImage(
                    trackImage,
                    GameConstants.TRACK_LEFT,
                    GameConstants.TRACK_TOP,
                    GameConstants.TRACK_WIDTH,
                    GameConstants.TRACK_HEIGHT,
                    panel);
        } else {
            g2d.setColor(GameConstants.TRACK_COLOR);
            g2d.fillRect(
                    GameConstants.TRACK_LEFT,
                    GameConstants.TRACK_TOP,
                    GameConstants.TRACK_WIDTH,
                    GameConstants.TRACK_HEIGHT);
        }

        g2d.setColor(GameConstants.TRACK_BORDER_COLOR);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(
                GameConstants.TRACK_LEFT,
                GameConstants.TRACK_TOP,
                GameConstants.TRACK_WIDTH,
                GameConstants.TRACK_HEIGHT);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean hasImage() {
        return trackImage != null;
    }

    public int checkCheckpointCollision(Rectangle carBounds, boolean[] passed) {
        int gateCount = checkpoints.size() - 1;
        for (int i = 0; i < gateCount; i++) {
            if (!passed[i] && carBounds.intersects(checkpoints.get(i))) {
                passed[i] = true;
                return i;
            }
        }
        return -1;
    }

    public boolean crossesFinishLine(Rectangle carBounds) {
        Rectangle finish = checkpoints.get(checkpoints.size() - 1);
        return carBounds.intersects(finish);
    }

    public int getCheckpointCount() {
        return checkpoints.size();
    }

    public void resetCheckpoints() {
        for (int i = 0; i < checkpointPassed.length; i++) {
            checkpointPassed[i] = false;
        }
    }

    public boolean isLapComplete() {
        for (boolean passed : checkpointPassed) {
            if (!passed) {
                return false;
            }
        }
        return true;
    }

    public List<Rectangle> getCheckpoints() {
        return Collections.unmodifiableList(checkpoints);
    }
}
