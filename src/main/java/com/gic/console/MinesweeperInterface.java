package com.gic.console;

import com.gic.model.*;

public class MinesweeperInterface {
    public void printBoard(Matrix matrix) {

        int size = matrix.getSize();

        System.out.print("  ");

        for (int i = 1; i <= size; i++)
            System.out.print(i + " ");

        System.out.println();

        for (int i = 0; i < size; i++) {

            char rowChar = (char) ('A' + i);

            System.out.print(rowChar + " ");

            for (int j = 0; j < size; j++) {

                Cell cell = matrix.getCell(i, j);

                if (!cell.isRevealed())
                    System.out.print("_ ");
                else
                    System.out.print(cell.getAdjacentMines() + " ");
            }

            System.out.println();
        }
    }
}
