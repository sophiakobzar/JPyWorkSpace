import java.awt.*;
import java.util.Random;
import javax.swing.JFrame;

public class FractalTree extends JFrame {

    public FractalTree() {
        super("Fractal Tree");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void drawTree(Graphics g, int x1, int y1, double angle, double depth, double thickness) {
        if (depth == 0) return;

        int x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * depth * 10.0);
        int y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * depth * 10.0);

        // Calculate the current thickness based on depth
        int currentThickness = (int) (thickness * (depth / 12.0)); // Start thickening earlier

        // Set the line thickness
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(Math.max(1, currentThickness))); // Ensure thickness is at least 1

        // Draw the line
        g.drawLine(x1, y1, x2, y2);

        // Recursively draw branches
        drawTree(g, x2, y2, angle - new Random().nextInt(20), depth - 1, thickness);
        drawTree(g, x2, y2, angle + new Random().nextInt(20), depth - 1, thickness);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        drawTree(g, 400, 500, -90, 9, 10); // 10 is the initial thickness
    }

    public static void main(String[] args) {
        new FractalTree().setVisible(true);
    }
}
