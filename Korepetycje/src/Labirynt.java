import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.util.*;
import javax.swing.*;
 
public class Labirynt extends JPanel {
    enum Dir {
        N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
        final int bit;
        final int dx;
        final int dy;
        Dir opposite;
 
        static {
            N.opposite = S;
            S.opposite = N;
            E.opposite = W;
            W.opposite = E;
        }
 
        Dir(int bit, int dx, int dy) {
            this.bit = bit;
            this.dx = dx;
            this.dy = dy;
        }
    };
    final int nCols;
    final int nRows;
    final int cellSize = 25;
    final int margin = 25;
    final int[][] maze;
 
    public Labirynt(int size) {
        setPreferredSize(new Dimension(650, 650));
        setBackground(Color.white);
        nCols = size;
        nRows = size;
        maze = new int[nRows][nCols];
        generateMaze(0, 0);
 
    }
 
    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
 
        g.setStroke(new BasicStroke(5));
        g.setColor(Color.black);
 
        for (int r = 0; r < nRows; r++) {
            for (int c = 0; c < nCols; c++) {
 
                int x = margin + c * cellSize;
                int y = margin + r * cellSize;
 
                if ((maze[r][c] & 1) == 0) // N
                    g.drawLine(x, y, x + cellSize, y);
 
                if ((maze[r][c] & 2) == 0) // S
                    g.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
 
                if ((maze[r][c] & 4) == 0) // E
                    g.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
 
                if ((maze[r][c] & 8) == 0) // W
                    g.drawLine(x, y, x, y + cellSize);
            }
        }
 
        // draw pathfinding animation
        int offset = margin + cellSize / 2;
 
        g.setColor(Color.blue);
        g.fillOval(offset - 5, offset - 5, 10, 10);
 
        g.setColor(Color.green);
        int x = offset + (nCols - 1) * cellSize;
        int y = offset + (nRows - 1) * cellSize;
        g.fillOval(x - 5, y - 5, 10, 10);
 
    }
 
    void generateMaze(int r, int c) {
        Dir[] dirs = Dir.values();
        Collections.shuffle(Arrays.asList(dirs));
        for (Dir dir : dirs) {
            int nc = c + dir.dx;
            int nr = r + dir.dy;
            if (withinBounds(nr, nc) && maze[nr][nc] == 0) {
                maze[r][c] |= dir.bit;
                maze[nr][nc] |= dir.opposite.bit;
                generateMaze(nr, nc);
            }
        }
    }
 
    boolean withinBounds(int r, int c) {
        return c >= 0 && c < nCols && r >= 0 && r < nRows;
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle("Labirynt");
            f.setResizable(false);
            f.add(new Labirynt(24), BorderLayout.CENTER);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}