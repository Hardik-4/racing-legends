package com.racinglegends.test;
import com.racinglegends.CollisionDetector;
import com.racinglegends.Car;
import com.racinglegends.GameConstants;
import org.junit.jupiter.api.*;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CollisionDetectorTest {
	private Car car;
	private Car player2Car;
    @BeforeEach
    void setUp() {
        car = new Car("car_red.png", 1, 200, 200, 
                     GameConstants.CAR_WIDTH, GameConstants.CAR_HEIGHT, Color.RED);
        player2Car = new Car("car_blue.png", 2, 210, 200,
                GameConstants.CAR_WIDTH, GameConstants.CAR_HEIGHT, Color.BLUE);
    }
    
    @Test
    @DisplayName("Test detect car collision when overlapping")
    void testDetectCarCollision() {
        // Cars are overlapping (200-240 and 210-250)
        boolean colliding = CollisionDetector.areCarsColliding(car, player2Car);
        assertTrue(colliding);
    }
	@Test
    @DisplayName("Test car inside track - within bounds")
	void testCarInsideTrack() {
        assertTrue(CollisionDetector.isWithinTrack(car));
	}
	@Test
    @DisplayName("Test car at left boundary - fixed")
    void testCarLeftBoundary() {
        car.setX(GameConstants.TRACK_LEFT - 20);
        CollisionDetector.checkTrackBoundary(car);
        assertEquals(GameConstants.TRACK_LEFT, car.getX());
        assertEquals(0, car.getVelocityX());
    }
    
    @Test
    @DisplayName("Test car at right boundary - fixed")
    void testCarRightBoundary() {
        car.setX(GameConstants.TRACK_RIGHT - GameConstants.CAR_WIDTH + 20);
        CollisionDetector.checkTrackBoundary(car);
        assertEquals(GameConstants.TRACK_RIGHT - GameConstants.CAR_WIDTH, car.getX());
    }
    
    @Test
    @DisplayName("Test car at top boundary - fixed")
    void testCarTopBoundary() {
        car.setY(GameConstants.TRACK_TOP - 20);
        CollisionDetector.checkTrackBoundary(car);
        assertEquals(GameConstants.TRACK_TOP, car.getY());
    }
    
    @Test
    @DisplayName("Test car at bottom boundary - fixed")
    void testCarBottomBoundary() {
        car.setY(GameConstants.TRACK_BOTTOM - GameConstants.CAR_HEIGHT + 20);
        CollisionDetector.checkTrackBoundary(car);
        assertEquals(GameConstants.TRACK_BOTTOM - GameConstants.CAR_HEIGHT, car.getY());
    }
    
    @Test
    @DisplayName("Test car collision detection")
    void testCarCollision() {
        Car car2 = new Car("car_blue.png", 2, 210, 200,
                          GameConstants.CAR_WIDTH, GameConstants.CAR_HEIGHT, Color.BLUE);
        assertTrue(CollisionDetector.checkCarCollision(car, car2));
    }
    
    @Test
    @DisplayName("Test car no collision")
    void testCarNoCollision() {
        Car car2 = new Car("car_blue.png", 2, 300, 200,
                          GameConstants.CAR_WIDTH, GameConstants.CAR_HEIGHT, Color.BLUE);
        assertFalse(CollisionDetector.checkCarCollision(car, car2));
    }
    @Test
    @DisplayName("Test no collision when cars are apart")
    void testNoCarCollision() {
        player2Car.setX(300);
        player2Car.setY(300);
        //player2Car.updateBounds();
        
        boolean colliding = CollisionDetector.areCarsColliding(car, player2Car);
        assertFalse(colliding);
    }
    
    @Test
    @DisplayName("Test handle collision separates cars")
    void testHandleCollisionSeparatesCars() {
        CollisionDetector.handleCarCollision(car, player2Car);
        
        // Cars should no longer overlap
        boolean stillColliding = car.getBounds().intersects(player2Car.getBounds());
        assertFalse(stillColliding);
    }
    
    @Test
    @DisplayName("Test handle collision stops both cars")
    void testHandleCollisionStopsCars() {
        car.moveRight(5);
        player2Car.moveLeft(5);
        
        CollisionDetector.handleCarCollision(car, player2Car);
        
        assertEquals(0, car.getVelocityX());
        assertEquals(0, car.getVelocityY());
        assertEquals(0, player2Car.getVelocityX());
        assertEquals(0, player2Car.getVelocityY());
    }
    
    @Test
    @DisplayName("Test collision ignores same player")
    void testCollisionIgnoresSamePlayer() {
        // Same player ID should not count as collision
        Car samePlayerCar = new Car("car_red.png", 1, 210, 200,
                           GameConstants.CAR_WIDTH, GameConstants.CAR_HEIGHT, Color.RED);
        
        boolean colliding = CollisionDetector.checkCarCollision(car, samePlayerCar);
        assertFalse(colliding);
    }
}
