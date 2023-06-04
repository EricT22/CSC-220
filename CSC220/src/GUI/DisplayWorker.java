package GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import GUI.GameOfLife.GraphicsPanel;

public class DisplayWorker implements Runnable, Serializable{

    private static final long serialVersionUID = 8767842381397319727L;


    private int[][][] universe = new int[2][GraphicsPanel.ROWS][GraphicsPanel.COLS];
    private int display = 0;
    private boolean wrapped = true;

    // Transient because the data need not be saved
    private transient boolean stop = true;
    private transient int speed;
    private transient GraphicsPanel gpanel;

    public DisplayWorker(int speed, GraphicsPanel gpanel) {
        this.speed = speed;
        this.gpanel = gpanel;
    }

    public void changeWrap(){
        wrapped = !wrapped;
    }

    public boolean getWrapped(){
        return wrapped;
    }

    public void serialize(String pathname){
        try {
            FileOutputStream fos = new FileOutputStream(new File(pathname));
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this);
            oos.flush();
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e){
            System.out.println("Can't read file.");
        }
    }

    public void deserialize(String pathname){
        try {
            FileInputStream fis = new FileInputStream(new File(pathname));
            ObjectInputStream ois = new ObjectInputStream(fis);

            Object o = ois.readObject();
            ois.close();
            fis.close();

            if (o instanceof DisplayWorker){
                DisplayWorker dw = (DisplayWorker) o;
                this.universe = dw.universe;
                this.display = dw.display;
                this.wrapped = dw.wrapped;

                GraphicsPanel.ROWS = this.universe[this.display].length;
                GraphicsPanel.COLS = this.universe[this.display][0].length;
                this.gpanel.repaint();
            } else {
                System.out.println("Expected a DisplayWorker, got a " + o.getClass().getName());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e){
            System.out.println("Can't read file.");
        } catch (ClassNotFoundException e){
            System.out.println("Class not found.");
        }
    }

    public void updateNumCells(){
        universe = new int[2][GraphicsPanel.ROWS][GraphicsPanel.COLS];
    }

    public void setCell(int x, int y){
        double rowSpacing = gpanel.getHeight() / (double) universe[display].length;
        double colSpacing = gpanel.getWidth() / (double) universe[display][0].length;

        int row = (int) (y / rowSpacing);
        int col = (int) (x / colSpacing);
        
        if (GraphicsPanel.drawingCells){
            universe[display][row][col] = 1;

        } else {
            universe[display][row][col] = 0;
        }

        copyToProcess();
    }

    public boolean cellOn(int row, int col){
        return universe[display][row][col] == 1;
    }

    public void stop(){
        stop = true;
    }
    

    // Run and all helpers below
    @Override
    public void run() {
        stop = false;
        while (!stop){
            try {
                if (wrapped){
                    processWrappedGen();
                } else {
                    processUnwrappedGen();
                }
                
                updateGen();

                gpanel.repaint();
                Thread.sleep(speed);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }        
    }

    private void updateGen() {
        display = 1 - display;
        copyToProcess();
    }

    private void processUnwrappedGen(){
        for (int i = 1; i < universe[display].length - 1; i++){
            for (int j = 1; j < universe[display][i].length - 1; j++){
                int numNeighbors = numNeighbors(i, j, -1, 1, -1, 1);

                processCell(i, j, numNeighbors);
            }
        }
        checkBorders();
        checkCorners();
    }

    private void processWrappedGen(){
        for (int i = 0; i < universe[display].length; i++){
            for (int j = 0; j < universe[display][i].length; j++){
                int numWrappedNeighbors = numWrappedNeighbors(i, j);

                processCell(i, j, numWrappedNeighbors);
            }
        }
    }

    private void checkCorners() {
        // top left corner
        int row = 0, col = 0;
        int numNeighbors = numNeighbors(row, col, 0, 1, 0, 1);

        processCell(row, col, numNeighbors);


        // top right corner
        col = universe[display][0].length - 1;
        numNeighbors = numNeighbors(row, col, 0, 1, -1, 0);
        processCell(row, col, numNeighbors);

        // bottom right corner
        row = universe[display].length - 1;
        numNeighbors = numNeighbors(row, col, -1, 0, -1, 0);
        processCell(row, col, numNeighbors);

        // bottom left corner
        col = 0;
        numNeighbors = numNeighbors(row, col, -1, 0, 0, 1);
        processCell(row, col, numNeighbors);
    }

    private void checkBorders() {
        checkLeftBorder();
        checkRightBorder();
        checkTopBorder();
        checkBottomBorder();
    }

    private void checkLeftBorder() {
        for (int i = 1; i < universe[display].length - 1; i++){
            int leftCol = 0;
            int numNeighbors = numNeighbors(i, leftCol, -1, 1, 0, 1);

            processCell(i, leftCol, numNeighbors);
        }
    }

    private void checkRightBorder() {
        for (int i = 1; i < universe[display].length - 1; i++){
            int rightCol = universe[display][0].length - 1;
            int numNeighbors = numNeighbors(i, rightCol, -1, 1, -1, 0);

            processCell(i, rightCol, numNeighbors);
        }
    }

    private void checkTopBorder() {
        for (int i = 1; i < universe[display][0].length - 1; i++){
            int topRow = 0;
            int numNeighbors = numNeighbors(topRow, i, 0, 1, -1, 1);

            processCell(topRow, i, numNeighbors);
        }
    }

    private void checkBottomBorder() {
        for (int i = 1; i < universe[display][0].length - 1; i++){
            int bottomRow = universe[display].length - 1;
            int numNeighbors = numNeighbors(bottomRow, i, -1, 0, -1, 1);

            processCell(bottomRow, i, numNeighbors);
        }
    }

    private void processCell(int i, int j, int numNeighbors){
        if (cellOn(i, j)){
            if (!(numNeighbors == 2 || numNeighbors == 3)){
                turnOff(i, j);
            }
        } else {
            if (numNeighbors == 3){
                turnOn(i, j);
            }
        }
    }

    private void turnOff(int i, int j) {
        universe[1 - display][i][j] = 0;
    }

    private void turnOn(int i, int j) {
        universe[1 - display][i][j] = 1;
    }

    private int numNeighbors(int i, int j, int top, int bottom, int left, int right) {
        int count = 0;
        for (int k = top; k <= bottom; k++){
            for (int l = left; l <= right; l++){
                if (k == 0 && l == 0){
                    continue;
                }
                count += cellOn(i + k, j + l) ? 1 : 0;
            }
        }
        return count;
    }

    private int numWrappedNeighbors(int i, int j) {
        int count = 0;
        for (int k = -1; k <= 1; k++){
            for (int l = -1; l <= 1; l++){
                if (k == 0 && l == 0){
                    continue;
                }

                int i2 = ((i + k) >= 0) ? (i + k) % universe[display].length : (i + k + universe[display].length) % universe[display].length;
                int j2 = ((j + l) >= 0) ? (j + l) % universe[display][i].length : (j + l + universe[display][i].length) % universe[display][i].length;
                count += cellOn(i2, j2) ? 1 : 0;
            }
        }
        return count;
    }



    private void copyToProcess(){
        int[][] copy = new int[universe[display].length][universe[display][0].length];

        for (int i = 0; i < copy.length; i++){
            for (int j = 0; j < copy[i].length; j++){
                copy[i][j] = universe[display][i][j];
            }
        }

        universe[1 - display] = copy;
    }    
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setGpanel(GraphicsPanel gpanel) {
        this.gpanel = gpanel;
    }
}
