package src;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Fichier");
        JMenuItem newItem = new JMenuItem("Nouveau");
        JMenuItem openItem = new JMenuItem("Ouvrir");
        JMenuItem saveItem = new JMenuItem("Enregistrer");
        JMenuItem saveAsItem = new JMenuItem("Enregistrer sous");
        JMenuItem exitItem = new JMenuItem("Quitter");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        JMenu editMenu = new JMenu("Édition");
        JMenuItem addCellItem = new JMenuItem("Ajouter une cellule");
        JMenuItem pauseItem = new JMenuItem("Mettre en pause");
        JMenuItem resumeItem = new JMenuItem("Reprendre");
        JMenuItem resetItem = new JMenuItem("Réinitialiser");

        pauseItem.addActionListener(e -> timer.stop());
        resumeItem.addActionListener(e -> timer.start());
        resetItem.addActionListener(e -> {
            timer.stop();
            grid = new CellGrid(gridWidth, gridHeight);
            panel.updateGrid(grid, windowWidth, windowHeight);
            timer.start();
        });
        addCellItem.addActionListener(e -> {
            int randomX = (int) (Math.random() * grid.getWidth());
            int randomY = (int) (Math.random() * grid.getHeight());
            grid.setAlive(randomX, randomY, true);
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int neighbourX = randomX + i;
                    int neighbourY = randomY + j;
                    if (neighbourX >= 0 && neighbourX < grid.getWidth() && neighbourY >= 0 && neighbourY < grid.getHeight()) {
                        grid.setAlive(neighbourX, neighbourY, true);
                    }
                }
            }
            panel.repaint();
        });

        editMenu.add(addCellItem);
        editMenu.add(pauseItem);
        editMenu.add(resumeItem);
        editMenu.add(resetItem);
        menuBar.add(editMenu);

        setJMenuBar(menuBar);

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
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "GameOfLifeApp");

        java.awt.EventQueue.invokeLater(() -> {
            new GameOfLifeApp().setVisible(true);
        });
    }

}

