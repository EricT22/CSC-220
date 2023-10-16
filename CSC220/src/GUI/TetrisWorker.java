package GUI;

import java.awt.Point;

import GUI.Tetris.GamePanel;

public class TetrisWorker extends TetrisPieceConstants implements Runnable{
    // TODO: Add a next array, and connect everything to main GUI
    
    private char[][][] universe = new char[2][GamePanel.ROWS][GamePanel.COLS];
    private int display = 0;

    private boolean pieceInPlay = false;
    private Point center;
    private char curPiece;
    private int orientation; // 0 up, 1 right, 2 down, 3 left
    Point[][] curPieceConsts;

    private int numLinesCleared = 0;

    private char heldPiece = 0;
    private boolean holdPieceTriggered = false;
    private boolean holdLockedOut = false;

    Bag bag = new Bag();

    private boolean gameOverReached = false;
    private boolean stop = true;
    private boolean paused = false;
    private int tickSpeed;
    private GamePanel gpanel;

    
    public void movePieceDown(){
        if (pieceInPlay && !stop){
            try {
                removePieceFromBoard();

                center.y += 1;
                
                for (int i = 0; i < curPieceConsts[orientation].length; i++){
                    if (!(universe[display][center.y + curPieceConsts[orientation][i].y][center.x + curPieceConsts[orientation][i].x] == 0)){
                        center.y -= 1;
                        returnPieceToBoard();
                        pieceInPlay = !pieceInPlay;
                        return;
                    }
                }

                for (int i = 0; i < curPieceConsts[orientation].length; i++){
                    universe[display][center.y + curPieceConsts[orientation][i].y][center.x + curPieceConsts[orientation][i].x] = curPiece;
                }

                copyToProcess();
            } catch (IndexOutOfBoundsException e){
                center.y -= 1;
                returnPieceToBoard();
                pieceInPlay = !pieceInPlay;
            }
        }
    }

    public void autoDown(){
        while(pieceInPlay && !stop){
            movePieceDown();
        }
        Thread.currentThread().interrupt();
    }

    public void movePieceSideways(boolean moveRight){
        if (pieceInPlay && !stop){
            try {
                removePieceFromBoard();

                if (moveRight)
                    center.x += 1;
                else
                    center.x -= 1;
            
                for (int i = 0; i < curPieceConsts[orientation].length; i++){
                    if (!(universe[display][center.y + curPieceConsts[orientation][i].y][center.x + curPieceConsts[orientation][i].x] == 0)){
                        if (moveRight)
                            center.x -= 1;
                        else
                            center.x += 1;

                        returnPieceToBoard();
                        return;
                    }
                }

                for (int i = 0; i < curPieceConsts[orientation].length; i++){
                    universe[display][center.y + curPieceConsts[orientation][i].y][center.x + curPieceConsts[orientation][i].x] = curPiece;
                }

                copyToProcess();

            } catch (IndexOutOfBoundsException e){
                if (moveRight)
                    center.x -= 1;
                else
                    center.x += 1;

                returnPieceToBoard();
            }
        }
    }
    
    public void rotatePiece(boolean rotatingRight){
        if (pieceInPlay && curPiece != 'O' && !stop){
            int storedOrientation = orientation;
            try {
                removePieceFromBoard();

                if (rotatingRight){
                    orientation = (orientation + 1) % 4;
                } else {
                    orientation = (orientation -1 + 4) % 4;
                }

                for (int i = 0; i < curPieceConsts[orientation].length; i++){
                    if (!(universe[display][center.y + curPieceConsts[orientation][i].y][center.x + curPieceConsts[orientation][i].x] == 0)){
                        orientation = storedOrientation;
                        returnPieceToBoard();
                        return;
                    }
                }

                for (int i = 0; i < curPieceConsts[orientation].length; i++){
                    universe[display][center.y + curPieceConsts[orientation][i].y][center.x + curPieceConsts[orientation][i].x] = curPiece;
                }

                copyToProcess();

            } catch (IndexOutOfBoundsException e){
                orientation = storedOrientation;
                returnPieceToBoard();
            }
        }
    }

    private void returnPieceToBoard() {
        for (int i = 0; i < curPieceConsts[orientation].length; i++){
            universe[display][center.y + curPieceConsts[orientation][i].y][center.x + curPieceConsts[orientation][i].x] = curPiece;
        }
    }

    private void removePieceFromBoard() {
        for (int i = 0; i < curPieceConsts[orientation].length; i++){
            universe[display][center.y + curPieceConsts[orientation][i].y][center.x + curPieceConsts[orientation][i].x] = 0;
        }
    }

    public TetrisWorker(int level, GamePanel gpanel){
        tickSpeed = 1000 / level;
        this.gpanel = gpanel;
    }

    @Override
    public void run() {
        stop = false;
        while (!stop){
            try {
                if (gameOverReached){
                    clearBoard();
                    gameOverReached = false;
                }

                if (!pieceInPlay){
                    if (!holdPieceTriggered){
                        checkLineClear();
                        System.out.println("Lines Cleared: " + numLinesCleared);
                    }

                    resetLineClearCounter();
                    spawnPiece();
                } else {
                    movePieceDown();
                }

                updateGen();

                gpanel.repaint();
                Thread.sleep(tickSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!paused){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                gameOver();
            }
        }
    }

    // May be taken out... have a counter tracking total line clears and then check how much
    // it incremented by to calculate score
    private void resetLineClearCounter(){
        numLinesCleared = 0;
    }

    private void checkLineClear() {
        for (int row = universe[1 - display].length - 1; row >= 0; row--){
            if (rowFilled(row)){
                removeRow(row);
                numLinesCleared++;
                row+=1;
            }
        }
        
        copyToProcess();
        gpanel.repaint();
    }

    private boolean rowFilled(int row) {
        for (char c : universe[display][row]){
            if (c == 0){
                return false;
            }
        }
        return true;
    }
    
    private void removeRow(int row) {
        for (int i = row; i > 0; i--){
            universe[display][i] = universe[display][i - 1];
        }
        universe[display][0] = new char[GamePanel.COLS];
    }

    private void spawnPiece() {
        if (!holdPieceTriggered && holdLockedOut){
            holdLockedOut = false;
        } else if (holdPieceTriggered){
            holdLockedOut = true;
            removePieceFromBoard();
            copyToProcess();
        } 

        center = new Point(4, 1);
        orientation = 0;

        if (!holdPieceTriggered){
            curPiece = bag.remove(0);
        } else if (holdPieceTriggered && heldPiece == 0){
            heldPiece = curPiece;
            curPiece = bag.remove(0);
        } else {
            char tempPiece = heldPiece;
            heldPiece = curPiece;
            curPiece = tempPiece;
        }
        holdPieceTriggered = false;
        
        curPieceConsts = pieces.get(curPiece);

        for (int i = 0; i < curPieceConsts[orientation].length; i++){
            if (universe[1 - display][center.y + curPieceConsts[orientation][i].y][center.x + curPieceConsts[orientation][i].x] != 0){
                stop = true;
                return;
            }
        }

        for (int i = 0; i < curPieceConsts[orientation].length; i++){
            universe[1 - display][center.y + curPieceConsts[orientation][i].y][center.x + curPieceConsts[orientation][i].x] = curPiece;
        }

        pieceInPlay = true;
    }

    public void holdPiece(){
        // TODO: do something to update the display on the screen
        
        if (holdLockedOut){
            return;
        }

        holdPieceTriggered = true;
        pieceInPlay = false;
        Thread.currentThread().interrupt();
    }

    public char pieceAtPoint(int x, int y){
        return universe[display][x][y];
    }

    private void gameOver() {
        clearBoard();
        drawEndMessage();

        gameOverReached = true;
    }

    private void drawEndMessage(){
        for (int i = 4; i <= 9; i++){
            universe[display][i][1] = 'T';
        }
        universe[display][4][2] = 'T';
        universe[display][4][3] = 'T';
        universe[display][9][2] = 'T';
        universe[display][9][3] = 'T';
        universe[display][8][3] = 'T';
        universe[display][7][3] = 'T';

        for (int i = 9; i <= 14; i++){
            universe[display][i][5] = 'O';
        }
        universe[display][9][6] = 'O';
        universe[display][9][7] = 'O';
        universe[display][14][6] = 'O';
        universe[display][14][7] = 'O';
        universe[display][13][7] = 'O';
        universe[display][12][7] = 'O';

        gpanel.repaint();
    }

    public void stop(){
        stop = true;
    }

    public void setTickSpeed(int level){
        tickSpeed = 1000 / level;
    }

    public void clearBoard(){
        universe = new char[2][GamePanel.ROWS][GamePanel.COLS];
        pieceInPlay = false;
    }

    private void updateGen() {
        display = 1 - display;
        copyToProcess();
    }

    private void copyToProcess(){
        char[][] copy = new char[universe[display].length][universe[display][0].length];

        for (int i = 0; i < copy.length; i++){
            for (int j = 0; j < copy[i].length; j++){
                copy[i][j] = universe[display][i][j];
            }
        }

        universe[1 - display] = copy;
    }

    public void pause() {
        paused = true;
    }

    public void unpause() {
        paused = false;
    }    
}
