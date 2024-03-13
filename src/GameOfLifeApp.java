package src;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameOfLifeApp extends JFrame {

    private Timer timer;
    private CellGrid grid;
    private GridPanel panel;

    public GameOfLifeApp() {
        int cellSize = 10;
        int windowWidth = 800;
        int windowHeight = 600;

        int gridWidth = windowWidth / cellSize;
        int gridHeight = windowHeight / cellSize;

        grid = new CellGrid(gridWidth, gridHeight);
        panel = new GridPanel(grid, windowWidth, windowHeight);
        add(panel);
        setSize(windowWidth, windowHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timer = new Timer(100, e -> {
            grid.update();
            panel.repaint();
        });
        timer.start();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                timer.stop();
                int newWindowWidth = getWidth();
                int newWindowHeight = getHeight();
                int newGridWidth = newWindowWidth / cellSize;
                int newGridHeight = newWindowHeight / cellSize;
                grid = new CellGrid(newGridWidth, newGridHeight);
                panel.updateGrid(grid, newWindowWidth, newWindowHeight);
                timer.start();
            }
        });
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new GameOfLifeApp().setVisible(true);
        });
    }
}

