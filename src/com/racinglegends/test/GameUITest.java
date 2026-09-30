package com.racinglegends.test;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import org.junit.jupiter.api.Test;
import com.racinglegends.GameUI;
import com.racinglegends.GameConstants;
import org.junit.jupiter.api.*;
class GameUITest {

	@Test
	 @DisplayName("Test UI class exists")
	void testUIClassExists() {
        assertNotNull(GameUI.class);
	}
	
	@Test
    @DisplayName("Test UI constants")
    void testUIConstants() {
        assertEquals(1200, GameConstants.WINDOW_WIDTH);
        assertEquals(800, GameConstants.WINDOW_HEIGHT);
        assertNotNull(GameConstants.UI_FONT);
        assertNotNull(GameConstants.TITLE_FONT);
    }
    
    @Test
    @DisplayName("Test UI colors")
    void testUIColors() {
        assertNotNull(GameConstants.TRACK_COLOR);
        assertNotNull(GameConstants.TRACK_BORDER_COLOR);
        assertNotNull(GameConstants.UI_TEXT_COLOR);
    }
    @Test
    void testDrawControlsBoxDoesNotThrow() {
        BufferedImage img = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();

        assertDoesNotThrow(() -> GameUI.drawControlsBox(g2d));
    }
    
    @Test
    @DisplayName("Test drawUI handles null car safely")
    void testDrawUIHandlesNullCar() {
        BufferedImage img = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();

        assertDoesNotThrow(() -> {
            GameUI.drawUI(g2d, null);
        });
    }
    
    @Test
    @DisplayName("Test lap starts at 1")
    void testInitialLap() {
        GameUI.resetLap();
        assertEquals(1, GameUI.getCurrentLap());
    }

    @Test
    @DisplayName("Test lap increases")
    void testLapIncrement() {
        GameUI.resetLap();
        GameUI.completeLap();
        assertEquals(2, GameUI.getCurrentLap());
    }

    @Test
    @DisplayName("Test position starts at 1")
    void testInitialPosition() {
        GameUI.setRacePosition(1);
        assertEquals(1, GameUI.getRacePosition());
    }
}
