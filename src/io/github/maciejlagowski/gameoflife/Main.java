package io.github.maciejlagowski.gameoflife;

import io.github.maciejlagowski.gameoflife.printer.GuiPrinter;

public class Main {
    public static void main(String[] args) {
        final int CELLS = 30;
        final int RECTANGLE_SIZE = 30;
        final double START_LIVE_PROBABILITY = 0.4;
        final long SLEEP_TIME_MILIS = 100;
        final int ITERATIONS = 20;

        new GameOfLife(CELLS, START_LIVE_PROBABILITY, SLEEP_TIME_MILIS, new GuiPrinter(CELLS, RECTANGLE_SIZE)).runSimulation();
//        new GameOfLife(N, PROBABILITY, SLEEP_TIME_MILIS, new GuiPrinter(N, RECTANGLE_SIZE), new ConsolePrinter()).runSimulation(ITERATIONS);
    }
}
