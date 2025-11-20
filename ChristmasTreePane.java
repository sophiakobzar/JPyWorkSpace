package org.paumard;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ChristmasTreePane extends JPanel {
    private final int rows;
    private final Random random = new Random();

    public ChristmasTreePane(int rows) {
        this.rows = rows;
        setBackground(Color.BLACK);
        new Timer(500, e -> repaint()).start(); // Blink every 500ms
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        @SuppressWarnings("unused")
        int height = getHeight();
        int centerX = width / 2;
        int y = 50;

        // Draw star
        g.setColor(Color.YELLOW);
        g.drawString("★", centerX - 5, y);
        y += 20;

        // Draw tree rows
        for (int i = 1; i <= rows; i++) {
            int rowWidth = 2 * i - 1;
            int startX = centerX - (rowWidth * 5) / 2;
            for (int j = 0; j < rowWidth; j++) {
                double rand = random.nextDouble();
                if (rand < 0.15) {
                    g.setColor(Color.RED); // Red ornament
                } else if (rand < 0.30) {
                    g.setColor(Color.YELLOW); // Yellow ornament
                } else {
                    g.setColor(Color.GREEN); // Tree
                }
                g.fillOval(startX + j * 5, y, 5, 5);
            }
            y += 10;
        }

        // Draw trunk
        g.setColor(new Color(139, 69, 19)); // Brown
        g.fillRect(centerX - 10, y, 20, 30);
    }

    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("How tall do you want the tree to be (suggested 35)?");
        int rows = Integer.parseInt(input);

        JFrame frame = new JFrame("Christmas Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(new ChristmasTreePane(rows));
        frame.setVisible(true);
    }
}