package com.racinglegends.test;
import com.racinglegends.KeyHandler;
import com.racinglegends.GameConstants;
import org.junit.jupiter.api.*;
import java.awt.event.KeyEvent;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class KeyHandlerTest {
private KeyHandler keyHandler;
    
    @BeforeEach
    void setUp() {
        keyHandler = new KeyHandler();
    }
    
	@Test
	@DisplayName("Test W key press (up)")
	void testWKeyPress() {
		KeyEvent event = new KeyEvent(new java.awt.Label(), KeyEvent.KEY_PRESSED,System.currentTimeMillis(), 0, GameConstants.P1_UP, 'W');
		keyHandler.keyPressed(event);
		assertTrue(keyHandler.p1Up);
	}
	
	@Test
    @DisplayName("Test S key press (down)")
    void testSKeyPress() {
        KeyEvent event = new KeyEvent(new java.awt.Label(), KeyEvent.KEY_PRESSED, 
                                      System.currentTimeMillis(), 0, 
                                      GameConstants.P1_DOWN, 'S');
        keyHandler.keyPressed(event);
        assertTrue(keyHandler.p1Down);
    }
    
    @Test
    @DisplayName("Test A key press (left)")
    void testAKeyPress() {
        KeyEvent event = new KeyEvent(new java.awt.Label(), KeyEvent.KEY_PRESSED, 
                                      System.currentTimeMillis(), 0, 
                                      GameConstants.P1_LEFT, 'A');
        keyHandler.keyPressed(event);
        assertTrue(keyHandler.p1Left);
    }
    
    @Test
    @DisplayName("Test D key press (right)")
    void testDKeyPress() {
        KeyEvent event = new KeyEvent(new java.awt.Label(), KeyEvent.KEY_PRESSED, 
                                      System.currentTimeMillis(), 0, 
                                      GameConstants.P1_RIGHT, 'D');
        keyHandler.keyPressed(event);
        assertTrue(keyHandler.p1Right);
    }
    
    @Test
    @DisplayName("Test Player 2 UP arrow key")
    void testP2Up() {
        KeyEvent event = new KeyEvent(new java.awt.Label(), KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
        keyHandler.keyPressed(event);
        assertTrue(keyHandler.p2Up);
    }
    @Test
    @DisplayName("Test Player 2 DOWN arrow key")
    void testP2Down() {
        KeyEvent event = new KeyEvent(new java.awt.Label(), KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED);
        keyHandler.keyPressed(event);
        assertTrue(keyHandler.p2Down);
    }

    @Test
    @DisplayName("Test Player 2 LEFT arrow key")
    void testP2Left() {
        KeyEvent event = new KeyEvent(new java.awt.Label(), KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        keyHandler.keyPressed(event);
        assertTrue(keyHandler.p2Left);
    }

    @Test
    @DisplayName("Test Player 2 RIGHT arrow key")
    void testP2Right() {
        KeyEvent event = new KeyEvent(new java.awt.Label(), KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        keyHandler.keyPressed(event);
        assertTrue(keyHandler.p2Right);
    }
    @Test
    @DisplayName("Test key release")
    void testKeyRelease() {
        KeyEvent pressEvent = new KeyEvent(new java.awt.Label(), KeyEvent.KEY_PRESSED, 
                                          System.currentTimeMillis(), 0, 
                                          GameConstants.P1_UP, 'W');
        KeyEvent releaseEvent = new KeyEvent(new java.awt.Label(), KeyEvent.KEY_RELEASED, 
                                            System.currentTimeMillis(), 0, 
                                            GameConstants.P1_UP, 'W');
        keyHandler.keyPressed(pressEvent);
        keyHandler.keyReleased(releaseEvent);
        assertFalse(keyHandler.p1Up);
    }
    
    @Test
    @DisplayName("Test reset all keys")
    void testResetKeys() {
        KeyEvent event = new KeyEvent(new java.awt.Label(), KeyEvent.KEY_PRESSED, 
                                      System.currentTimeMillis(), 0, 
                                      GameConstants.P1_UP, 'W');
        keyHandler.keyPressed(event);
        keyHandler.reset();
        assertFalse(keyHandler.p1Up);
        assertFalse(keyHandler.p1Down);
        assertFalse(keyHandler.p1Left);
        assertFalse(keyHandler.p1Right);
    }
    
    @Test
    @DisplayName("Test diagonal multiplier")
    void testDiagonalMultiplier() {
        KeyEvent upEvent = new KeyEvent(new java.awt.Label(), KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0, GameConstants.P1_UP, 'W');
        KeyEvent leftEvent = new KeyEvent(new java.awt.Label(), KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0, GameConstants.P1_LEFT, 'A');
        
        keyHandler.keyPressed(upEvent);
        keyHandler.keyPressed(leftEvent);
        
        float multiplier = keyHandler.getDiagonalMultiplier();
        assertEquals(0.707f, multiplier, 0.001f);
    }
}
