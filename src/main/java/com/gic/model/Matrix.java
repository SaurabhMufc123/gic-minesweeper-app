package com.gic.model;

import com.gic.util.MineGeneratorStrategy;
import lombok.Data;

@Data
public class Matrix {

    private int size;
    private int totalMines;
    private Cell[][] grid;

    public Matrix(int size, int totalMines, MineGeneratorStrategy generator) {

        this.size = size;
        this.totalMines = totalMines;

        grid = new Cell[size][size];

        initializeMatrix();

        generator.generateMines(this);
    }

    private void initializeMatrix() {

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                grid[i][j] = new Cell();
            }
        }
    }

    private boolean isValid(int row, int col) {

        return row >= 0 && col >= 0 &&
                row < size && col < size;
    }

    public Cell getCell(int row, int col) {

        return grid[row][col];
    }
}


