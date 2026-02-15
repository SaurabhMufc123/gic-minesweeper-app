package com.gic.service;

import com.gic.model.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinesweeperServiceTest {

    private MinesweeperService service;

    @BeforeEach
    void setup() {

        service = new MinesweeperService();
        service.start(2, 0);
    }

    @Test
    void testStartGame() {

        assertNotNull(service.getMatrix());

        assertEquals(GameState.IN_PROGRESS, service.getState());

        assertEquals(0, service.getRevealedCells());
    }

    @Test
    void testRevealSafeCellStateInProgress() {

        GameState state = service.reveal(0,0);

        assertEquals(GameState.IN_PROGRESS, state);
    }

    @Test
    void testWinCondition() {

        service.start(2,0);

        service.reveal(0,0);
        service.reveal(0,1);
        service.reveal(1,0);

        GameState state = service.reveal(1,1);

        assertEquals(GameState.WON, state);
    }

    @Test
    void testLoseCondition() {

        service.start(2,1);

        service.getMatrix().getCell(0,0).setMine(true);

        GameState state = service.reveal(0,0);

        assertEquals(GameState.LOST, state);
    }
}
