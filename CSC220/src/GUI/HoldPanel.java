package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import GUI.Tetris.GamePanel;

public class HoldPanel extends JPanel{
    public static final int COLS = 5;
    public static final int ROWS = 4;
    

    private int height, width;

    private Point center = new Point(2, 2);
    private char heldPiece = 0;
    
    private char[][] holdUniverse = new char[HoldPanel.ROWS][HoldPanel.COLS];

    public HoldPanel(int width, int height){
        super();

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

        Graphics2D g2;
        if (g instanceof Graphics2D){
            g2 = (Graphics2D) g;
        } else {
            System.out.println("Get a better computer peasant, thanks! :)");
            return;
        }

        int horzs = COLS;
        double horzspacing = width / (double)horzs;
        
        int verts = ROWS;
        double vertspacing = height / (double)verts;
        
        g2.setColor(GamePanel.lineColor);
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

                if (holdUniverse[i][j] == 0){
                    continue;
                } else if (holdUniverse[i][j] == 'T'){
                    g2.setColor(Tetris.COLOR_PURPLE);
                } else if (holdUniverse[i][j] == 'L'){
                    g2.setColor(Color.ORANGE);
                } else if (holdUniverse[i][j] == 'J'){
                    g2.setColor(Color.BLUE);
                } else if (holdUniverse[i][j] == 'I'){
                    g2.setColor(new Color(30, 220, 252)); // light blue
                } else if (holdUniverse[i][j] == 'O'){
                    g2.setColor(Color.YELLOW);
                } else if (holdUniverse[i][j] == 'S'){
                    g2.setColor(Color.GREEN);
                } else if (holdUniverse[i][j] == 'Z'){
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

    public void updateHeldPiece(char piece){
        clearHoldUniverse();

        heldPiece = piece;

        Point[][] pieceConsts = TetrisPieceConstants.getConstants(heldPiece);

        for (int i = 0; i < pieceConsts[0].length; i++){
            holdUniverse[center.y + pieceConsts[0][i].y][center.x + pieceConsts[0][i].x] = heldPiece;
        }

        this.repaint();
    }

    private void clearHoldUniverse() {
        holdUniverse = new char[HoldPanel.ROWS][HoldPanel.COLS];
    }

    public void resetHoldPanel(){
        heldPiece = 0;

        clearHoldUniverse();
        this.repaint();
    }
}