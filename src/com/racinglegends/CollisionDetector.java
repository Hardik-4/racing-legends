package com.racinglegends;

import java.awt.Rectangle;

/** Track-boundary and car-to-car collision helpers. */
public final class CollisionDetector {
    private CollisionDetector() {}

    public static void checkTrackBoundary(Car car) {
        if (car == null) {
            return;
        }

        Rectangle carBounds = car.getBounds();

        if (carBounds.x < GameConstants.TRACK_LEFT) {
            car.setX(GameConstants.TRACK_LEFT);
            car.stop();
        }
        if (carBounds.x + carBounds.width > GameConstants.TRACK_RIGHT) {
            car.setX(GameConstants.TRACK_RIGHT - carBounds.width);
            car.stop();
        }
        if (carBounds.y < GameConstants.TRACK_TOP) {
            car.setY(GameConstants.TRACK_TOP);
            car.stop();
        }
        if (carBounds.y + carBounds.height > GameConstants.TRACK_BOTTOM) {
            car.setY(GameConstants.TRACK_BOTTOM - carBounds.height);
            car.stop();
        }
    }

    public static boolean isWithinTrack(Car car) {
        if (car == null) {
            return false;
        }
        Rectangle carBounds = car.getBounds();
        return carBounds.x >= GameConstants.TRACK_LEFT
                && carBounds.x + carBounds.width <= GameConstants.TRACK_RIGHT
                && carBounds.y >= GameConstants.TRACK_TOP
                && carBounds.y + carBounds.height <= GameConstants.TRACK_BOTTOM;
    }

    public static boolean checkCarCollision(Car car1, Car car2) {
        if (car1 == null || car2 == null) {
            return false;
        }
        if (car1.getPlayerId() == car2.getPlayerId()) {
            return false;
        }
        return car1.getBounds().intersects(car2.getBounds());
    }

    public static boolean areCarsColliding(Car car1, Car car2) {
        if (car1 == null || car2 == null) {
            return false;
        }
        return car1.getBounds().intersects(car2.getBounds());
    }

    public static void handleCarCollision(Car car1, Car car2) {
        if (car1 == null || car2 == null) {
            return;
        }
        if (!areCarsColliding(car1, car2)) {
            return;
        }
        car1.bounce(car2);
        checkTrackBoundary(car1);
        checkTrackBoundary(car2);
    }
}
