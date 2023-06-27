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
import javax.swing.JTextField;

public class Tetris extends JFrame{
    private final int WIDTH = 800;
    private final int HEIGHT = 650;
    private final Color COLOR_PURPLE = new Color(155, 0, 228);

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
        public int level;
        public int score;
        public int linesCleared;
        
        private JLabel holdLabel;
        private JLabel levelLabel;
        private JLabel scoreLabel;
        private JLabel linesClearedLabel;
        private JTextField levelField;
        private JTextField scoreField;
        private JTextField linesClearedField;


        public DisplayPanelLeft(){
            super();

            this.setBackground(Color.BLACK);

            prepareComponents();

            this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

            this.add(holdLabel);
            this.add(new holdPanel(125, 100));

            this.add(levelLabel);
            this.add(levelField);
            this.add(scoreLabel);
            this.add(scoreField);
            this.add(linesClearedLabel);
            this.add(linesClearedField);
        }

        private void prepareComponents() {
            holdLabel = new JLabel("HOLD");
            holdLabel.setFont(new Font("Arial", Font.PLAIN, 50));
            holdLabel.setForeground(Color.WHITE);

            levelLabel = new JLabel("LEVEL");
            levelLabel.setFont(new Font("Arial", Font.PLAIN, 50));
            levelLabel.setForeground(Color.WHITE);

            levelField = new JTextField(level + "", 5);
            levelField.setFont(new Font("Arial", Font.PLAIN, 25));
            levelField.setHorizontalAlignment(JTextField.CENTER);
            levelField.setBackground(Color.GRAY);
            levelField.setForeground(Color.RED);
            levelField.setEditable(false);
            
            scoreLabel = new JLabel("SCORE");
            scoreLabel.setFont(new Font("Arial", Font.PLAIN, 50));
            scoreLabel.setForeground(Color.WHITE);
            
            scoreField = new JTextField(score + "", 5);
            scoreField.setFont(new Font("Arial", Font.PLAIN, 25));
            scoreField.setHorizontalAlignment(JTextField.CENTER);
            scoreField.setBackground(Color.GRAY);
            scoreField.setForeground(Color.GREEN);
            scoreField.setEditable(false);

            linesClearedLabel = new JLabel("LINES");
            linesClearedLabel.setFont(new Font("Arial", Font.PLAIN, 50));
            linesClearedLabel.setForeground(Color.WHITE);
            
            linesClearedField = new JTextField(linesCleared + "", 5);
            linesClearedField.setFont(new Font("Arial", Font.PLAIN, 25));
            linesClearedField.setHorizontalAlignment(JTextField.CENTER);
            linesClearedField.setBackground(Color.GRAY);
            linesClearedField.setForeground(COLOR_PURPLE);
            linesClearedField.setEditable(false);
            
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
