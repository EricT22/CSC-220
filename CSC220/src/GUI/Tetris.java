package GUI;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// TODO: Add a ? button that opens the how-to-play instructions
public class Tetris extends JFrame{
    private final int WIDTH = 800;
    private final int HEIGHT = 650;
    private final Color COLOR_PURPLE = new Color(155, 0, 228);

    private int level = 1;
    private int score = 0;
    private int linesCleared = 0;

    private GamePanel gamePanel;
    private DisplayPanelRight dpr;
    private DisplayPanelLeft dpl;

    private TetrisWorker gameWorker;

    public Tetris(){
        super("Tetris");
        
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));

        gamePanel = new GamePanel();
        dpr = new DisplayPanelRight();
        dpl = new DisplayPanelLeft();

        this.add(gamePanel, BorderLayout.CENTER);
        this.add(dpr, BorderLayout.EAST);
        this.add(dpl, BorderLayout.WEST);
        
        this.setResizable(false);
        gamePanel.repaint();
        this.setVisible(true);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
    }

    public class GamePanel extends JPanel implements KeyListener{
        public static final int COLS = 10;
        public static final int ROWS = 20;
        public static Color lineColor = Color.BLACK;
        public static Color backgroundColor = new Color(60, 130, 200);

        public GamePanel(){
            super();

            this.setBackground(backgroundColor);
            this.addKeyListener(this);

        }

        
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 40){
                gameWorker.movePieceDown();
            } else if (e.getKeyCode() == 39){
                gameWorker.movePieceSideways(true);
            } else if (e.getKeyCode() == 37){
                gameWorker.movePieceSideways(false);
            } else if (e.getKeyCode() == 38 || e.getKeyCode() == 67){
                gameWorker.rotatePiece(true);
            } else if (e.getKeyCode() == 88){
                gameWorker.rotatePiece(false);
            } else if (e.getKeyCode() == 32){
                gameWorker.autoDown();
            } else if (e.getKeyCode() == 16){
                gameWorker.holdPiece();
            }

            System.out.println("Key PRESSED: " + e.getKeyCode());
            gamePanel.repaint();
        }

        
        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("Key released: " + e.getKeyCode());
            gamePanel.repaint();
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

            for (int i = 0; i < verts; i++){
                for (int j = 0; j < horzs; j++){
                    char curPiece = gameWorker.pieceAtPoint(i, j);

                    if (curPiece == 0){
                        continue;
                    } else if (curPiece == 'T'){
                        g2.setColor(COLOR_PURPLE);
                    } else if (curPiece == 'L'){
                        g2.setColor(Color.ORANGE);
                    } else if (curPiece == 'J'){
                        g2.setColor(Color.BLUE);
                    } else if (curPiece == 'I'){
                        g2.setColor(new Color(30, 220, 252)); // light blue
                    } else if (curPiece == 'O'){
                        g2.setColor(Color.YELLOW);
                    } else if (curPiece == 'S'){
                        g2.setColor(Color.GREEN);
                    } else if (curPiece == 'Z'){
                        g2.setColor(Color.RED);
                    } 
                        
                    int x = (int) (j* horzspacing);
                    int y = (int) (i * vertspacing);
                    int w = (int) ((j + 1) * horzspacing) - x;
                    int h = (int) ((i + 1) * vertspacing) - y;
                    g2.fillRect(x, y, w, h);

                    g2.setColor(Color.BLACK);
                    g2.drawRect(x, y, w, h);
                }
            }
        }
        
    }

    private class DisplayPanelRight extends JPanel {
        private JLabel nextLabel;
        private JButton playButton;
        private JButton resetButton;

        public DisplayPanelRight(){
            super();
            
            this.setBackground(Color.BLACK);

            prepareComponents();

            this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

            this.add(nextLabel);
            this.add(new NextPanel(125, 400));
            this.add(playButton);
            this.add(resetButton);

            gameWorker = new TetrisWorker(level, gamePanel);
        }

        private void prepareComponents() {
            nextLabel = new JLabel("NEXT");
            nextLabel.setFont(new Font("Arial", Font.PLAIN, 50));
            nextLabel.setForeground(Color.WHITE);

            playButton = new JButton("PLAY");
            playButton.setFont(new Font("Arial", Font.PLAIN, 25));
            playButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (playButton.getText().equals("PLAY")){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        
                        playButton.setText("PAUSE");

                        gameWorker.unpause();
                        new Thread(gameWorker).start();
                    } else {
                        playButton.setText("PLAY");

                        gameWorker.pause();
                        gameWorker.stop();
                    }    
                    gamePanel.requestFocus();
                }

            });

            resetButton = new JButton("RESET");
            resetButton.setFont(new Font("Arial", Font.PLAIN, 25));
            resetButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    gameWorker.pause();
                    gameWorker.stop();
                    gameWorker.clearBoard();

                    
                    playButton.setText("PLAY");

                    level = 1;
                    gameWorker.setTickSpeed(level);
                    dpl.levelField.setText(level + "");

                    score = 0;
                    dpl.scoreField.setText(score + "");

                    linesCleared = 0;
                    dpl.linesClearedField.setText(linesCleared + "");
                    
                    gamePanel.repaint();
                    gamePanel.requestFocus();
                }

            });
        }
        
        @Override
        public Dimension getPreferredSize(){
            return new Dimension(200, 500);
        }
    }

    public class DisplayPanelLeft extends JPanel {
        
        private JLabel holdLabel;
        private JLabel levelLabel;
        private JLabel scoreLabel;
        private JLabel linesClearedLabel;
        public JTextField levelField;
        public JTextField scoreField;
        public JTextField linesClearedField;


        public DisplayPanelLeft(){
            super();

            this.setBackground(Color.BLACK);

            prepareComponents();

            this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

            this.add(holdLabel);
            this.add(new HoldPanel(125, 100));

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

    public static void main(String[] args) {
        new Tetris();
    }
}
