package GUI;

import GUI.SwingGUIApp.GraphicPanelInner;

public class UpdateDisplay implements Runnable{

    private boolean stop = true;
    private int speed = 100;
    private GraphicPanelInner gpanel;

    public UpdateDisplay(GraphicPanelInner gpanel){
        this.gpanel = gpanel;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public void setGraphicPanel(GraphicPanelInner gpanel){
        this.gpanel = gpanel;
    }

    public void stop(){
        stop = true;
    }

    @Override
    public void run() {
        stop = false;

        while(!stop){
            try {
                System.out.println("tic");
                gpanel.repaint();
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
