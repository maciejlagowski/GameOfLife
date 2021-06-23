package io.github.maciejlagowski.gameoflife;

import io.github.maciejlagowski.gameoflife.printer.GridPrinter;

import java.util.Random;

public class GameOfLife {

    private final int N;
    private final GridPrinter[] gridPrinters;
    private final long SLEEP_TIME;
    private boolean[][] grid;
    private boolean[][] tempGrid;

    public GameOfLife(int N, double probabilityOfGeneration, long sleepTimeMilis, GridPrinter... gridPrinters) {
        this.N = N;
        this.SLEEP_TIME = sleepTimeMilis;
        this.grid = generateRandomGrid(probabilityOfGeneration);
        this.tempGrid = new boolean[N][N];
        this.gridPrinters = gridPrinters;
    }

    public void runSimulation(int howLong) {
        printAll();
        for (int i = 0; i < howLong; i++) {
            stateChange();
        }
    }

    public void runSimulation() {
        printAll();
        while(true) {
            stateChange();
        }
    }

    public void stateChange() {
        tickAllPoints();
        printAll();
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printAll() {
        for (GridPrinter gridPrinter : gridPrinters) {
            gridPrinter.printGrid(grid);
        }
    }

    private void tickAllPoints() {
        tempGrid = cloneTable(grid);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int surrounding = checkMySurrounding(i, j);
                if (grid[i][j]) {
                    tempGrid[i][j] = surrounding == 2 || surrounding == 3;
                } else {
                    tempGrid[i][j] = surrounding == 3;
                }
            }
        }
        grid = cloneTable(tempGrid);
    }

    private boolean[][] cloneTable(boolean[][] table) {
        int n = table.length;
        boolean[][] clone = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(table[i], 0, clone[i], 0, n);
        }
        return clone;
    }

    private int checkMySurrounding(int i, int j) {
        // TODO unbounded surrounding
        int surroundSum = 0;
        for (int k = i - 1; k <= i + 1; k++) {
            if (!(k < 0 || k >= N)) {
                for (int l = j - 1; l <= j + 1; l++) {
                    if ((!(l < 0 || l >= N || (k == i && l == j))) && grid[k][l]) {
                        surroundSum++;
                    }
                }
            }
        }
        return surroundSum;
    }

    private boolean[][] generateRandomGrid(double probability) {
        boolean[][] grid = new boolean[N][N];
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = random.nextDouble() < probability;
            }
        }
        return grid;
    }
}
