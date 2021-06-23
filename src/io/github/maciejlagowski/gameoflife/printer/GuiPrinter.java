package io.github.maciejlagowski.gameoflife.printer;

import javax.swing.*;
import java.awt.*;

public class GuiPrinter extends JFrame implements GridPrinter {

    private final int N;
    private final int rectangleSize;
    private boolean[][] grid;

    public GuiPrinter(int N, int rectangleSize) {
        this.N = N;
        this.rectangleSize = rectangleSize;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Metody Symulacji Komputerowej");
        setSize(N * rectangleSize, (N + 1) * rectangleSize);
        this.grid = new boolean[N][N];
        setVisible(true);
    }

    @Override
    public void printGrid(boolean[][] grid) {
        this.grid = grid;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        int size = rectangleSize;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                g.setColor(grid[i][j] ? Color.black : Color.white);
                g.fillRect((j) * size, (i + 1) * size, size, size);
            }
        }
    }
}
