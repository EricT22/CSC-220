package GUI;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tetris extends JFrame{
    private final int WIDTH = 800;
    private final int HEIGHT = 650;

    private GamePanel gpanel;
    private DisplayPanelRight dpr;
    private DisplayPanelLeft dlr;

    public Tetris(){
        super("Tetris");
        
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));

        gpanel = new GamePanel();
        dpr = new DisplayPanelRight();
        dlr = new DisplayPanelLeft();

        this.add(gpanel, BorderLayout.CENTER);
        this.add(dpr, BorderLayout.EAST);
        this.add(dlr, BorderLayout.WEST);
        
        this.setResizable(false);
        gpanel.repaint();
        this.setVisible(true);
        gpanel.setFocusable(true);
        gpanel.requestFocusInWindow();
    }

    private class GamePanel extends JPanel implements KeyListener{
        private final int COLS = 10;
        private final int ROWS = 20;
        private Color lineColor = Color.BLACK;
        private Color backgroundColor = new Color(60, 130, 200);

        public GamePanel(){
            super();

            this.setBackground(backgroundColor);
            this.addKeyListener(this);

        }

        
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("Key PRESSED: " + e.getKeyCode());
            gpanel.repaint();
        }

        // Use this for setting speed back to normal
        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("Key released: " + e.getKeyCode());
            gpanel.repaint();
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(50, 50);
        }

        @Override
        public void paint(Graphics g){
            super.paintComponent(g);

            Graphics2D g2;
            if (g instanceof Graphics2D){
                g2 = (Graphics2D) g;
            } else {
                System.out.println("Get a better computer peasant, thanks! :)");
                return;
            }

            int height = this.getHeight();
            int width = this.getWidth();

            int horzs = COLS;
            double horzspacing = width / (double)horzs;
            
            int verts = ROWS;
            double vertspacing = height / (double)verts;
            
            g2.setColor(lineColor);
            g2.setStroke(new BasicStroke((float) 1.5));

            double x0 = 0.0;
            for (int i = 0; i < horzs; ++i) {
                g2.drawLine((int)x0, 0, (int)x0, height);
                x0 += horzspacing;
            }
            
            double y0 = 0.0;
            for (int i = 0; i < verts; ++i) {
                g2.drawLine(0, (int)y0, width, (int)y0);
                y0 += vertspacing;
            }
        }
        
    }

    private class DisplayPanelRight extends JPanel {
        

        public DisplayPanelRight(){
            super();
            
            this.setBackground(Color.BLACK);

            prepareComponents();
        }

        private void prepareComponents() {
            
        }
        
        @Override
        public Dimension getPreferredSize(){
            return new Dimension(200, 500);
        }
    }

    private class DisplayPanelLeft extends JPanel {
        private JLabel hold;
        private JLabel level;
        private JLabel score;
        private JLabel lines;

        public DisplayPanelLeft(){
            super();

            this.setBackground(Color.BLACK);

            prepareComponents();

            this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

            this.add(hold);
            this.add(new holdPanel(125, 100));
        }

        private void prepareComponents() {
            hold = new JLabel("HOLD");
            hold.setFont(new Font("Arial", Font.PLAIN, 50));
            hold.setForeground(Color.WHITE);
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(200, 500);
        }
    }

    private class holdPanel extends JPanel{
        private int height, width;

        public holdPanel(int width, int height){
            this.height = height;
            this.width = width;

            this.setBackground(Color.GRAY);
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(width, height);
        }

        @Override
        public void paint(Graphics g){
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        new Tetris();
    }
}
