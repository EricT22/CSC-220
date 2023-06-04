package CellGame;

import javax.swing.JFrame;

public class Conway {
    private GridCanvas grid;

    public static void main(String[] args) {
        String title = "Conway's Game of Life";
        Conway game = new Conway();

        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(game.grid);
        frame.pack();
        frame.setVisible(true);
        game.mainloop();
    }

    public Conway(){
        grid = new GridCanvas(5, 10, 20);
        grid.turnOn(2, 1);
        grid.turnOn(2, 2);
        grid.turnOn(2, 3);
        grid.turnOn(1, 7);
        grid.turnOn(2, 7);
        grid.turnOn(3, 7);
    }

    private void mainloop(){
        while(true){
            this.update();
            grid.repaint();
            try{
                Thread.sleep(500);
            } catch (InterruptedException e){
                //do nothing
            }
        }
    }

    public void update(){
        int[][] counts;
    }
}
