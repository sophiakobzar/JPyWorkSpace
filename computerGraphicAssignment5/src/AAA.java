// written by Sophia Kobzar
// question 2 - Hw#5

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class AAA {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new DrawingPanel());
        frame.setVisible(true);
    }
}

class DrawingPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        Font font = new Font("TimesRoman", Font.PLAIN, 200);
        // Transparency
        g2d.setFont(font);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
        g2d.drawString("A", 50, 200);

        // Gradient
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        GradientPaint gp = new GradientPaint(100, 100, Color.RED, 200,200 , Color.BLUE);  
        g2d.setPaint(gp); 
        g2d.drawString("A", 150, 200);

        // Texture
        BufferedImage img = null; 
        try {
            img = ImageIO.read(new File("woodPicture.jpg")); // Load a texture image here
        } catch (IOException e) {
            e.printStackTrace();
        }
        TexturePaint tp = new TexturePaint(img,new Rectangle(0 ,0 ,img.getWidth(), img.getHeight()));
        g2d.setPaint(tp); 
        g2d.drawString("A",250 ,200 );
    }
}
