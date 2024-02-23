package src;
import javax.swing.JPanel;

import java.awt.Graphics;

public class GridPanel extends JPanel {
    private CellGrid grid;
    private final int cellSize = 10;

    public GridPanel(CellGrid grid) {
        this.grid = grid;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                if (grid.isAlive(x, y)) {
                    g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }
    }
}

