package com.gic.console;

import com.gic.model.Matrix;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MinesweeperInterfaceTest {

    @Test
    void testPrintMatrix() {

        Matrix matrix = new Matrix(2, 0, m -> {
            // no-op strategy
        });

        matrix.getCell(0,0).setRevealed(true);
        matrix.getCell(0,0).setAdjacentMines(1);

        MinesweeperInterface ui = new MinesweeperInterface();

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        System.setOut(new PrintStream(output));

        ui.printGameMatrix(matrix);

        String result = output.toString();

        assertTrue(result.contains("1"));
        assertTrue(result.contains("_"));
        assertTrue(result.contains("A"));
        assertTrue(result.contains("B"));
    }
}
