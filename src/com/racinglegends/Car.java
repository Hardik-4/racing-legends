package com.racinglegends;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/** Player-controlled car with image rendering and bounce physics. */
public class Car {
    private int x;
    private int y;
    private int velocityX;
    private int velocityY;
    private final int width;
    private final int height;
    private final Color color;
    private final int playerId;
    private final Rectangle bounds;
    private BufferedImage image;
    private boolean player2;

    public Car(String imageFile, int playerId, int startX, int startY,
               int width, int height, Color color) {
        this.playerId = playerId;
        this.x = startX;
        this.y = startY;
        this.width = width;
        this.height = height;
        this.color = color;
        this.velocityX = 0;
        this.velocityY = 0;
        this.bounds = new Rectangle(x, y, width, height);
        this.player2 = false;
        loadImage(imageFile);
    }

    private void loadImage(String imageFile) {
        try {
            File file = new File("images/" + imageFile);
            if (file.exists()) {
                this.image = ImageIO.read(file);
            } else {
                System.out.println("Image not found: images/" + imageFile);
                this.image = null;
            }
        } catch (IOException e) {
            System.out.println("Error loading image: " + imageFile);
            this.image = null;
        }
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public void update() {
        x += velocityX;
        y += velocityY;
        updateBounds();
    }

    public void moveUp(int speed) {
        velocityX = 0;
        velocityY = -speed;
    }

    public void moveDown(int speed) {
        velocityX = 0;
        velocityY = speed;
    }

    public void moveLeft(int speed) {
        velocityX = -speed;
        velocityY = 0;
    }

    public void moveRight(int speed) {
        velocityX = speed;
        velocityY = 0;
    }

    public void stop() {
        velocityX = 0;
        velocityY = 0;
    }

    public void reset(int startX, int startY) {
        setX(startX);
        setY(startY);
        stop();
    }

    private void updateBounds() {
        bounds.setBounds(x, y, width, height);
    }

    public void draw(Graphics2D g2d, JPanel panel) {
        if (image != null) {
            g2d.drawImage(image, x, y, width, height, panel);
        } else {
            g2d.setColor(color);
            g2d.fillRect(x, y, width, height);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, y, width, height);
            g2d.setColor(Color.WHITE);
            g2d.drawString(String.valueOf(playerId), x + 15, y + 35);
        }
    }

    public void bounce(Car other) {
        Rectangle thisBounds = this.getBounds();
        Rectangle otherBounds = other.getBounds();

        int overlapX = Math.min(thisBounds.x + thisBounds.width, otherBounds.x + otherBounds.width)
                - Math.max(thisBounds.x, otherBounds.x);
        int overlapY = Math.min(thisBounds.y + thisBounds.height, otherBounds.y + otherBounds.height)
                - Math.max(thisBounds.y, otherBounds.y);

        if (overlapX < overlapY) {
            if (thisBounds.x < otherBounds.x) {
                this.setX(this.getX() - overlapX / 2);
                other.setX(other.getX() + overlapX / 2);
            } else {
                this.setX(this.getX() + overlapX / 2);
                other.setX(other.getX() - overlapX / 2);
            }
        } else {
            if (thisBounds.y < otherBounds.y) {
                this.setY(this.getY() - overlapY / 2);
                other.setY(other.getY() + overlapY / 2);
            } else {
                this.setY(this.getY() + overlapY / 2);
                other.setY(other.getY() - overlapY / 2);
            }
        }

        this.stop();
        other.stop();
    }

    public boolean isPlayer2() {
        return player2;
    }

    public void setPlayer2(boolean player2) {
        this.player2 = player2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
        updateBounds();
    }

    public void setY(int y) {
        this.y = y;
        updateBounds();
    }

    public int getVelocityX() {
        return velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getPlayerId() {
        return playerId;
    }

    public BufferedImage getImage() {
        return image;
    }
}
