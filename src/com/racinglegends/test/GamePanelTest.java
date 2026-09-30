package com.racinglegends.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.racinglegends.GamePanel;
import com.racinglegends.GameConstants;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
class GamePanelTest {

	@Test
	@DisplayName("Test GamePanel creation")
	void testGamePanelCreation() {
		GamePanel panel = new GamePanel();
        assertNotNull(panel);
        assertEquals(GameConstants.WINDOW_WIDTH, panel.getPreferredSize().width);
        assertEquals(GameConstants.WINDOW_HEIGHT, panel.getPreferredSize().height);
        assertTrue(panel.isFocusable());
	}
	@Test
    @DisplayName("Test GameConstants values")
    void testGameConstants() {
        assertEquals(1200, GameConstants.WINDOW_WIDTH);
        assertEquals(800, GameConstants.WINDOW_HEIGHT);
        assertEquals("Racing Legends", GameConstants.GAME_TITLE);
        assertEquals(60, GameConstants.TARGET_FPS);
        assertEquals(5, GameConstants.CAR_SPEED);
        assertEquals(40, GameConstants.CAR_WIDTH);
        assertEquals(60, GameConstants.CAR_HEIGHT);
    }
    
    @Test
    @DisplayName("Test track boundaries")
    void testTrackBoundaries() {
        assertEquals(100, GameConstants.TRACK_LEFT);
        assertEquals(1100, GameConstants.TRACK_RIGHT);
        assertEquals(100, GameConstants.TRACK_TOP);
        assertEquals(700, GameConstants.TRACK_BOTTOM);
    }
    
    @Test
    @DisplayName("Test player controls")
    void testPlayerControls() {
        assertEquals(87, GameConstants.P1_UP);   // W
        assertEquals(83, GameConstants.P1_DOWN); // S
        assertEquals(65, GameConstants.P1_LEFT); // A
        assertEquals(68, GameConstants.P1_RIGHT);// D
    }
}
