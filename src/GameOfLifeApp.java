package src;
import javax.swing.JFrame;
import javax.swing.Timer;

public class GameOfLifeApp extends JFrame {

    public GameOfLifeApp() {
        CellGrid grid = new CellGrid(50, 50); // Taille de la grille exemple
        GridPanel panel = new GridPanel(grid);
        add(panel);
        setSize(800, 600); // Taille de la fenêtre exemple
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Timer timer = new Timer(100, e -> {
            grid.update();
            panel.repaint();
        });
        timer.start();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new GameOfLifeApp().setVisible(true);
        });
    }
}

