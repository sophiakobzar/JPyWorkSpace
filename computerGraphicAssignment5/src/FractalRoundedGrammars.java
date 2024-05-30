// FractalRoundedGrammars.java
// written by Sophia Kobzar
// used FractalGrammars.java as reference
// made edges look rounded by dividing them into parts

import java.awt.*;
import java.awt.event.*;

public class FractalRoundedGrammars extends Frame {
    public static void main(String[] args) {
        if (args.length == 0)
            System.out.println("Use filename as program argument.");
        else
            new FractalRoundedGrammars(args[0]);
    }

    FractalRoundedGrammars(String fileName) {
        super("Click left or right mouse button to change the level");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        setSize(800, 600);
        add("Center", new CvFractalRoundedGrammars(fileName));
        setVisible(true);
    }
}

class CvFractalRoundedGrammars extends Canvas {
    // Declare variables for the strings and parameters
    String fileName, axiom, strF, strf, strX, strY;
    String strU, strV;
    int maxX, maxY, level = 1;
    double xLast, yLast, dir, rotation, dirStart, fxStart, fyStart,
            lengthFract, reductFact, edgePoint1, edgePoint2;


    // Constructor
    CvFractalRoundedGrammars(String fileName) {
        Input inp = new Input(fileName);
        if (inp.fails())
            error("Cannot open input file.");
        // Read strings and parameters from the input file
        axiom = inp.readString();
        inp.skipRest();
        strF = inp.readString();
        inp.skipRest();
        strf = inp.readString();
        inp.skipRest();
        strX = inp.readString();
        inp.skipRest();
        strY = inp.readString();
        inp.skipRest();
        strU = inp.readString();
        inp.skipRest();
        strV = inp.readString();
        inp.skipRest();
        rotation = inp.readFloat();
        inp.skipRest();
        dirStart = inp.readFloat();
        inp.skipRest();
        fxStart = inp.readFloat();
        inp.skipRest();
        fyStart = inp.readFloat();
        inp.skipRest();
        lengthFract = inp.readFloat();
        inp.skipRest();
        reductFact = inp.readFloat();
        if (inp.fails()) error("Input file incorrect.");
        // Mouse listener to change fractal level
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if ((evt.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
                    level--; // Right mouse button decreases level
                    if (level < 1) level = 1;
                } else
                    level++; // Left mouse button increases level
                repaint();
            }
        });

    }

    // Method to handle drawing errors
    void error(String str) {
        System.out.println(str);
        System.exit(1);
    }

    // Convert double to integer for X coordinate
    int iX(double x) {
        return (int) Math.round(x);
    }

    // Convert double to integer for Y coordinate
    int iY(double y) {
        return (int) Math.round(maxY - y);
    }

    // Draw a line from the last point to the specified point
    void drawTo(Graphics g, double x, double y) {
        ((Graphics2D) g).setStroke(new BasicStroke(2));
        g.drawLine(iX(xLast), iY(yLast), iX(x), iY(y));
        xLast = x;
        yLast = y;
    }

    // Move to the specified point without drawing
    void moveTo(Graphics g, double x, double y) {
        xLast = x;
        yLast = y;
    }

    void saveCorners(double corner1, double corner2) {
        edgePoint1 += corner1;
        edgePoint2 += corner2;
    }

    // Paint method to draw the fractal
    public void paint(Graphics g) {
        Dimension d = getSize();
        maxX = d.width - 1;
        maxY = d.height - 1;
        xLast = fxStart * maxX;
        yLast = fyStart * maxY;
        dir = dirStart; // Initial direction in degrees
        edgePoint1 = xLast; // Set the initial position for the 1st Edge Point
        edgePoint2 = yLast; // Set the initial position for the 2nd Edge Point
        turtleGraphics(g, axiom, level, lengthFract * maxY);
    }

    public void turtleGraphics(Graphics g, String instruction, int depth, double len) {
        // Initialize markers for position and direction
        double xMark = 0, yMark = 0, dirMark = 0;
        // Iterate over each character in the instruction string
        for (int i = 0; i < instruction.length(); i++) {
            char ch = instruction.charAt(i);
            switch (ch) {
                case 'F': // Step forward and draw
                    // If depth is 0, draw with rounded corners
                    if (depth == 0) {
                        // Calculate direction in radians
                        double rad = Math.PI / 180 * dir;
                        // Calculate displacement in x and y directions
                        double dx = len * Math.cos(rad), dy = len * Math.sin(rad);

                        // Set the composite for the Graphics2D object
                        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
                        ((Graphics2D) g).setComposite(ac);
                        // Set the color and draw the blue line
                        g.setColor(Color.BLUE);
                        // Draw the first edge of the BLUE line
                        drawTo(g, edgePoint1 + dx * 0.16, edgePoint2 + dy * 0.16);
                        saveCorners(dx, dy);// Save the corners for drawing the RED line
                        // Change the color and draw the red line
                        g.setColor(Color.RED);
                        // Draw the second edge of the RED line
                        drawTo(g, edgePoint1 - dx * 0.16, edgePoint2 - dy * 0.16);
                    } else {
                        // If depth is not 0, recursively call the function with reduced depth and length
                        turtleGraphics(g, strF, depth - 1, reductFact * len);
                    }
                    break;
                case 'f': // Step forward without drawing
                    // If depth is 0, move to the new position without drawing
                    if (depth == 0) {
                        double rad = Math.PI / 180 * dir;
                        double dx = len * Math.cos(rad), dy = len * Math.sin(rad);
                        moveTo(g, edgePoint1 + dx, edgePoint2 + dy);
                    } else {
                        // If depth is not 0, recursively call the function with reduced depth and length
                        turtleGraphics(g, strf, depth - 1, reductFact * len);
                    }
                    break;
                case 'X':
                    if (depth > 0)
                        turtleGraphics(g, strX, depth - 1, reductFact * len);
                    break;
                case 'Y':
                    if (depth > 0)
                        turtleGraphics(g, strY, depth - 1, reductFact * len);
                    break;
                case 'U':
                    if (depth > 0)
                        turtleGraphics(g, strU, depth - 1, reductFact * len);
                    break;
                case 'V':
                    if (depth > 0)
                        turtleGraphics(g, strV, depth - 1, reductFact * len);
                    break;
                case '+': // Turn right
                    dir -= rotation;
                    break;
                case '-': // Turn left
                    dir += rotation;
                    break;
                case '[': // Save position and direction
                    dirMark = dir;
                    yMark = edgePoint2;
                    xMark = edgePoint1;
                    break;
                case ']': // Back to saved position and direction
                    dir = dirMark;
                    edgePoint2 = yMark;
                    edgePoint1 = xMark;
                    break;
            }
        }
    }
}