package com.gic.service;

import com.gic.model.Matrix;
import com.gic.util.MatrixEngine;
import com.gic.util.RandomMineGenerator;
import lombok.Data;
import com.gic.model.GameState;

@Data
public class MinesweeperService {

    private Matrix matrix;
    private GameState state;
    private int revealedCells;
    private MatrixEngine engine;

    public void start(int size, int mines) {

        matrix = new Matrix(size, mines, new RandomMineGenerator());
        engine = new MatrixEngine(
                matrix,
                new RandomMineGenerator()
        );

        state = GameState.IN_PROGRESS;

        revealedCells = 0;
    }

    public GameState reveal(int row, int col) {

        boolean mineHit = engine.revealCell(row, col);

        if (mineHit) {

            state = GameState.LOST;

            return state;
        }

        revealedCells++;

        int totalSafeCells =
                matrix.getSize() * matrix.getSize()
                        - matrix.getTotalMines();

        if (revealedCells == totalSafeCells) {

            state = GameState.WON;
        }

        return state;
    }
}
