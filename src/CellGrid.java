package src;
public class CellGrid {
    private boolean[][] grid;
    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isAlive(int x, int y) {
        return grid[x][y];
    }

    public CellGrid(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new boolean[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = Math.random() > 0.5;
            }
        }
    }

    public void update() {
        boolean[][] nextGrid = new boolean[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int livingNeighbors = countLivingNeighbors(x, y);
                if (grid[x][y]) {
                    nextGrid[x][y] = livingNeighbors == 2 || livingNeighbors == 3;
                } else {
                    nextGrid[x][y] = livingNeighbors == 3;
                }
            }
        }
        grid = nextGrid;
    }

    private int countLivingNeighbors(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // Skip the cell itself
                int dx = x + i;
                int dy = y + j;
                if (dx >= 0 && dx < width && dy >= 0 && dy < height && grid[dx][dy]) {
                    count++;
                }
            }
        }
        return count;
    }

    public void setAlive(int x, int y, boolean alive) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            grid[x][y] = alive;
        }
    }
}

