package CellGame;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.JFrame;

public class Drawing extends Canvas{
    private static final Color[] colors = {Color.RED, Color.GREEN, Color.BLUE};

    public static void main(String[] args) {
        JFrame frame = new JFrame("My Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Drawing drawing = new Drawing();
        drawing.setSize(400, 400);
        frame.add(drawing);
        frame.pack();
        frame.setVisible(true);

    }

    public void paint(Graphics g){
        int width = getWidth() / 4;
        int height = getHeight() / 4;
        mickey(g, new Rectangle(getWidth() / 2 - (width / 2), getHeight() / 2 - (height / 2), width, height));
    }

    public void boxOval(Graphics g, Rectangle bb){
        g.setColor(colors[(int)(Math.random() * 3)]);
        g.fillOval(bb.x, bb.y, bb.width, bb.height);
    }

    public void mickey(Graphics g, Rectangle rect){
        boxOval(g, rect);

        int hx = rect.width / 2;
        int hy = rect.height / 2;
        Rectangle half = new Rectangle((int)rect.getX(), (int)rect.getY(), hx, hy);

        half.translate(-hx / 2, -hy / 2);
        boxOval(g, half);

        half.translate(hx * 2, 0);
        boxOval(g, half);
    }
}
