package com.gic.util;

import com.gic.model.Matrix;
import com.gic.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomMineGeneratorTest {

    private RandomMineGenerator generator;

    @BeforeEach
    void setup() {
        generator = new RandomMineGenerator();
    }

    private int countMines(Matrix matrix) {

        int count = 0;

        for (int i = 0; i < matrix.getSize(); i++) {

            for (int j = 0; j < matrix.getSize(); j++) {

                if (matrix.getCell(i, j).isMine()) {
                    count++;
                }
            }
        }

        return count;
    }

    @Test
    void testGenerateCorrectNumberOfMines() {

        int size = 5;
        int mines = 7;

        Matrix matrix = new Matrix(size, mines, m -> {});

        generator.generateMines(matrix);

        int mineCount = countMines(matrix);

        assertEquals(mines, mineCount,
                "Should place exact number of mines");
    }

    @Test
    void testGenerateZeroMines() {

        Matrix matrix = new Matrix(4, 0, m -> {});

        generator.generateMines(matrix);

        int mineCount = countMines(matrix);

        assertEquals(0, mineCount,
                "Should place zero mines");
    }

    @Test
    void testNoDuplicateMinePlacement() {

        int size = 4;
        int mines = 8;

        Matrix matrix = new Matrix(size, mines, m -> {});

        generator.generateMines(matrix);

        int mineCount = countMines(matrix);

        assertEquals(mines, mineCount,
                "Duplicate mines should not be placed");
    }

    @Test
    void testMaxMinePlacement() {

        int size = 3;
        int mines = 9;

        Matrix matrix = new Matrix(size, mines, m -> {});

        generator.generateMines(matrix);

        int mineCount = countMines(matrix);

        assertEquals(mines, mineCount,
                "Should fill entire matrix with mines");
    }

    @Test
    void testMinePlacementWithinBounds() {

        int size = 6;
        int mines = 10;

        Matrix matrix = new Matrix(size, mines, m -> {});

        generator.generateMines(matrix);

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                Cell cell = matrix.getCell(i, j);

                assertNotNull(cell,
                        "Cell should exist within bounds");
            }
        }
    }

}
