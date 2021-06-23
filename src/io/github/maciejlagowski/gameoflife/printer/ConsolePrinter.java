package io.github.maciejlagowski.gameoflife.printer;

public class ConsolePrinter implements GridPrinter{

    @Override
    public void printGrid(boolean[][] grid) {
        int N = grid.length;
        for (boolean[] rows : grid) {
            for (int i = 0; i < N; i++) {
                System.out.print((rows[i] ? "X" : " ") + "|");
            }
            System.out.println();
        }
        System.out.println("----------------");
    }
}
