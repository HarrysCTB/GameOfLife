package src;
import javax.swing.JPanel;
import java.awt.Graphics;

public class GridPanel extends JPanel {
    private CellGrid grid;
    private int cellSize;

    public GridPanel(CellGrid grid, int windowWidth, int windowHeight) {
        this.grid = grid;
        this.cellSize = Math.min(windowWidth / grid.getWidth(), windowHeight / grid.getHeight());
    }

    public void updateGrid(CellGrid newGrid, int newWindowWidth, int newWindowHeight) {
        this.grid = newGrid;
        this.cellSize = Math.min(newWindowWidth / grid.getWidth(), newWindowHeight / grid.getHeight());
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
