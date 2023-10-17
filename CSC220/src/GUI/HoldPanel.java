package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class HoldPanel extends JPanel{
        private int height, width;

        private char heldPiece = 0;

        public HoldPanel(int width, int height){
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
        }

        public void updateHeldPiece(char piece){
            heldPiece = piece;

            this.repaint();
        }
}