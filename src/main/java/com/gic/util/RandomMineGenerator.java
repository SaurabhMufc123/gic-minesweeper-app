package com.gic.util;

import com.gic.model.Matrix;
import java.util.Random;

public class RandomMineGenerator implements MineGeneratorStrategy {

    private Random random = new Random();

    @Override
    public void generateMines(Matrix matrix) {

        int size = matrix.getSize();
        int minesToPlace = matrix.getTotalMines();

        int placed = 0;

        while (placed < minesToPlace) {

            int row = random.nextInt(size);
            int col = random.nextInt(size);

            if (!matrix.getCell(row, col).isMine()) {

                matrix.getCell(row, col).setMine(true);
                placed++;
            }
        }
    }
}


