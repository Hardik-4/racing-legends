package com.racinglegends.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import com.racinglegends.Track;
import com.racinglegends.GameConstants;
import org.junit.jupiter.api.*;
import java.awt.*;
class TrackTest {
	private Track track;
    
    @BeforeEach
    void setUp() {
        track = new Track("track.png");
    }
	

	@Test
	@DisplayName("Test track creation")
	void testTrackCreation() {
		assertNotNull(track);
        assertNotNull(track.getBounds());	}
	@Test
    @DisplayName("Test track bounds")
    void testTrackBounds() {
        Rectangle bounds = track.getBounds();
        assertEquals(GameConstants.TRACK_LEFT, bounds.x);
        assertEquals(GameConstants.TRACK_TOP, bounds.y);
        assertEquals(GameConstants.TRACK_WIDTH, bounds.width);
        assertEquals(GameConstants.TRACK_HEIGHT, bounds.height);
    }
    
    @Test
    @DisplayName("Test track dimensions")
    void testTrackDimensions() {
        Rectangle bounds = track.getBounds();
        assertEquals(GameConstants.TRACK_RIGHT - GameConstants.TRACK_LEFT, bounds.width);
        assertEquals(GameConstants.TRACK_BOTTOM - GameConstants.TRACK_TOP, bounds.height);
    }
    
    /*@Test
    @DisplayName("Test checkpoint collision returns -1 when no collision")
    void testCheckpointNoCollision() {
        Rectangle carBounds = new Rectangle(100, 100, 40, 60);
        int result = track.checkCheckpointCollision(carBounds);
        assertEquals(-1, result);
    }*/


    @Test
    @DisplayName("Test lap starts incomplete")
    void testLapNotComplete() {
        assertFalse(track.isLapComplete());
    }

    @Test
    @DisplayName("Test reset checkpoints works")
    void testResetCheckpoints() {
        track.resetCheckpoints();
        assertFalse(track.isLapComplete());
    }
	
}

