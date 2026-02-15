package com.gic;

import com.gic.console.MinesweeperInterface;
import com.gic.service.MinesweeperService;

import java.util.Scanner;

public class Application {
    /*public static void main(String[] args) {
        System.out.println("Hello world!");
    }*/


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        MinesweeperService service = new MinesweeperService();

        MinesweeperInterface console = new MinesweeperInterface();

        System.out.println("Enter grid size:");

        int size = scanner.nextInt();

        System.out.println("Enter number of mines:");

        int mines = scanner.nextInt();

        service.start(size, mines);

        while (service.getState().name().equals("IN_PROGRESS")) {

            console.printBoard(service.getMatrix());

            System.out.println("Enter row and column:");

            int row = scanner.nextInt();

            int col = scanner.nextInt();

            service.reveal(row, col);
        }

        console.printBoard(service.getMatrix());

        System.out.println("Game Over: " + service.getState());
    }
}