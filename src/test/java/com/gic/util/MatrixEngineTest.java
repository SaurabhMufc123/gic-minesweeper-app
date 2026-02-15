package com.gic.util;

import com.gic.model.Matrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixEngineTest {

    private Matrix matrix;
    private MatrixEngine engine;

    @BeforeEach
    void setup() {

        matrix = new Matrix(3, 0, new RandomMineGenerator());

        engine = new MatrixEngine(matrix, m -> {
            matrix.getCell(0,0).setMine(true);
        });
    }

    @Test
    void testRevealMinePositive() {

        matrix.getCell(1,1).setMine(true);

        boolean result = engine.revealCell(1,1);

        assertTrue(result);
        assertTrue(matrix.getCell(1,1).isRevealed());
    }

    @Test
    void testRevealSafeCellNegative() {

        boolean result = engine.revealCell(2,2);

        assertFalse(result);
        assertTrue(matrix.getCell(2,2).isRevealed());
    }

    @Test
    void testRevealAlreadyRevealedCellNegative() {

        engine.revealCell(1,1);

        boolean result = engine.revealCell(1,1);

        assertFalse(result);
    }

    @Test
    void testAdjacentMineCalculation() {

        matrix.getCell(0,0).setMine(true);

        engine = new MatrixEngine(matrix, m -> {});

        int adjacent = matrix.getCell(0,1).getAdjacentMines();

        assertEquals(1, adjacent);
    }

    @Test
    void testDFSReveal() {

        engine = new MatrixEngine(matrix, m -> {});

        engine.revealCell(2,2);

        assertTrue(matrix.getCell(2,2).isRevealed());
        assertTrue(matrix.getCell(2,1).isRevealed());
        assertTrue(matrix.getCell(1,2).isRevealed());
    }
}
