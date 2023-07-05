package GUI;

import GUI.Tetris.GamePanel;

public class TetrisWorker implements Runnable{

    private boolean stop = true;
    private int tickSpeed;
    private GamePanel gpanel;

    public TetrisWorker(int level, GamePanel gpanel){
        tickSpeed = 1000 / level;
        this.gpanel = gpanel;
    }

    @Override
    public void run() {
        stop = false;
        while (!stop){
            try {
                gpanel.repaint();
                Thread.sleep(tickSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void stop(){
        stop = true;
    }

    public void setTickSpeed(int level){
        tickSpeed = 1000 / level;
    }
}
