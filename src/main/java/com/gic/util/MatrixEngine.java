package com.gic.util;

import com.gic.model.Cell;
import com.gic.model.Matrix;

public class MatrixEngine {

    private final Matrix matrix;
    private final MineGeneratorStrategy mineGenerator;

    public MatrixEngine(Matrix matrix, MineGeneratorStrategy mineGenerator) {

        this.matrix = matrix;
        this.mineGenerator = mineGenerator;

        initialize();
    }

    private void initialize() {

        mineGenerator.generateMines(matrix);

        calculateAdjacentMines();
    }

    public boolean revealCell(int row, int col) {

        Cell cell = matrix.getCell(row, col);

        if (cell.isRevealed())
            return false;

        cell.setRevealed(true);

        if (cell.isMine())
            return true;

        if (cell.getAdjacentMines() == 0)
            revealDFS(row, col);

        return false;
    }

    private void revealDFS(int row, int col) {

        for (int i = -1; i <= 1; i++) {

            for (int j = -1; j <= 1; j++) {

                int newRow = row + i;
                int newCol = col + j;

                if (isValid(newRow, newCol)) {

                    Cell neighbor = matrix.getCell(newRow, newCol);

                    if (!neighbor.isRevealed() &&
                            !neighbor.isMine()) {

                        neighbor.setRevealed(true);

                        if (neighbor.getAdjacentMines() == 0)
                            revealDFS(newRow, newCol);
                    }
                }
            }
        }
    }

    private void calculateAdjacentMines() {

        for (int i = 0; i < matrix.getSize(); i++) {

            for (int j = 0; j < matrix.getSize(); j++) {

                Cell cell = matrix.getCell(i, j);

                if (!cell.isMine()) {

                    int count = countAdjacentMines(i, j);

                    cell.setAdjacentMines(count);
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {

        int count = 0;

        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                if (isValid(row + i, col + j) &&
                        matrix.getCell(row + i, col + j).isMine())
                    count++;

        return count;
    }

    private boolean isValid(int row, int col) {

        return row >= 0 &&
                col >= 0 &&
                row < matrix.getSize() &&
                col < matrix.getSize();
    }
}

