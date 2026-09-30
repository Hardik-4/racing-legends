package com.racinglegends.test;
import com.racinglegends.Car;
import com.racinglegends.GameConstants;
import org.junit.jupiter.api.*;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CarTest {
	private Car player1Car;
	private Car player2Car;
    @BeforeEach
    void setUp() {
    	player1Car = new Car("car_red.png", 1, 100, 100, 
                     GameConstants.CAR_WIDTH, GameConstants.CAR_HEIGHT, Color.RED);
    	player2Car = new Car("car_blue.png", 2, 200, 100,
                GameConstants.CAR_WIDTH, GameConstants.CAR_HEIGHT, Color.BLUE);
    }

	@Test
	@DisplayName("Test car initial position")
	void testCarInitialPosition() {
		assertEquals(100, player1Car.getX());
        assertEquals(100, player1Car.getY());
        assertNotNull(player1Car.getBounds());
	}
	
	@Test
    @DisplayName("Test identify Player 2 car - NEW")
    void testIdentifyPlayer2() {
        player2Car.setPlayer2(true);
        assertTrue(player2Car.isPlayer2());
        assertFalse(player1Car.isPlayer2());
    }
	
	@Test
    @DisplayName("Test cars bounce off each other - NEW")
    void testCarsBounce() {
        int car1X = player1Car.getX();
        int car1Y = player1Car.getY();
        int car2X = player2Car.getX();
        int car2Y = player2Car.getY();
        
        // Simulate collision
        player1Car.bounce(player2Car);
        
        // Cars should move away from each other
        assertNotEquals(car1X, player1Car.getX());
        assertNotEquals(car2X, player2Car.getX());
    }
	
	@Test
    @DisplayName("Test cars stop on collision - NEW")
    void testCarsStopOnCollision() {
        player1Car.moveRight(5);
        player2Car.moveLeft(5);
        
        player1Car.update();
        player2Car.update();
        
        player1Car.bounce(player2Car);
        
        assertEquals(0, player1Car.getVelocityX());
        assertEquals(0, player1Car.getVelocityY());
        assertEquals(0, player2Car.getVelocityX());
        assertEquals(0, player2Car.getVelocityY());
    }
	
	@Test
    @DisplayName("Test car moves up")
    void testCarMoveUp() {
		player1Car.moveUp(5);
		player1Car.update();
        assertEquals(95, player1Car.getY());
    }
    
    @Test
    @DisplayName("Test car moves down")
    void testCarMoveDown() {
    	player1Car.moveDown(5);
    	player1Car.update();
        assertEquals(105, player1Car.getY());
    }
    
    @Test
    @DisplayName("Test car moves left")
    void testCarMoveLeft() {
    	player1Car.moveLeft(5);
    	player1Car.update();
        assertEquals(95, player1Car.getX());
    }
    
    @Test
    @DisplayName("Test car moves right")
    void testCarMoveRight() {
    	player1Car.moveRight(5);
    	player1Car.update();
        assertEquals(105, player1Car.getX());
    }
    
    @Test
    @DisplayName("Test car stops")
    void testCarStop() {
    	player1Car.moveRight(5);
    	player1Car.update();
        int positionAfterMove = player1Car.getX();
        player1Car.stop();
        player1Car.update();
        assertEquals(positionAfterMove, player1Car.getX());
        assertEquals(0, player1Car.getVelocityX());
        assertEquals(0, player1Car.getVelocityY());
    }
    
    @Test
    @DisplayName("Test car bounds update when position changes")
    void testCarBoundsUpdate() {
    	player1Car.setX(200);
    	player1Car.setY(200);
        Rectangle bounds = player1Car.getBounds();
        assertEquals(200, bounds.x);
        assertEquals(200, bounds.y);
        assertEquals(GameConstants.CAR_WIDTH, bounds.width);
        assertEquals(GameConstants.CAR_HEIGHT, bounds.height);
    }
}
