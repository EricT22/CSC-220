package GUI;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GUI.Tetris.GamePanel;

public class TetrisWorker implements Runnable, TetrisPieceConstants{
    // TODO: Find way to end game then link it up with main GUI
    
    public static Map<Character, Point[][]> pieces = new HashMap<Character, Point[][]>();

    static {
        pieces.put('T', T);
        pieces.put('L', L);
        pieces.put('J', J);
        pieces.put('O', O);
        pieces.put('S', S);
        pieces.put('Z', Z);
        pieces.put('I', I);
    }








































    
    private char[][][] universe = new char[2][GamePanel.ROWS][GamePanel.COLS];
    private int display = 0;

    private boolean pieceInPlay = false;
    private Point center;
    private char curPiece;
    private int orientation; // 0 up, 1 right, 2 down, 3 left
    Point[][] curPieceConsts;

    private List<Character> bag = new ArrayList<Character>(7);

    private boolean gameOverReached = false;
    private boolean stop = true;
    private boolean paused = false;
    private int tickSpeed;
    private GamePanel gpanel;

    // may have to put try-catch blocks b/c of long boi but for now we're good (or we can do orientation if stmts for that specifically)
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
                // TODO: Every tick, piece moves down till it hits the bottom

                if (gameOverReached){
                    clearBoard();
                    gameOverReached = false;
                }

                if (!pieceInPlay){
                    checkTetrisClear();
                    checkTripleLineClear();
                    checkDoubleLineClear();
                    checkLineClear();
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

    

    private void checkTetrisClear() {
        for (int row = universe[1 - display].length - 1; row >= 3; row--){
            if (rowFilled(row) && rowFilled(row - 1) && rowFilled(row - 2) && rowFilled(row - 3)){
                removeRow(row);
                removeRow(row - 1);
                removeRow(row - 2);
                removeRow(row - 3);
            }
        }
    }
    
    private void checkTripleLineClear() {
        for (int row = universe[1 - display].length - 1; row >= 2; row--){
            if (rowFilled(row) && rowFilled(row - 1) && rowFilled(row - 2)){
                removeRow(row);
                removeRow(row - 1);
                removeRow(row - 2);
            }
        }
    }

    private void checkDoubleLineClear() {
        for (int row = universe[1 - display].length - 1; row >= 1; row--){
            if (rowFilled(row) && rowFilled(row - 1)){
                removeRow(row);
                removeRow(row - 1);
            }
        }
    }

    private void checkLineClear() {
        for (int row = universe[1 - display].length - 1; row >= 0; row--){
            if (rowFilled(row)){
                removeRow(row);
            }
        }
    }

    private boolean rowFilled(int row) {
        for (char c : universe[1 -display][row]){
            if (c == 0){
                return false;
            }
        }
        return true;
    }
    
    private void removeRow(int row) {
        for (int i = row; i > 0; i--){
            universe[1 - display][i] = universe[1 - display][i - 1];
        }
        universe[1 - display][0] = new char[GamePanel.COLS];
    }

    private void spawnPiece() {
        if (bag.isEmpty()){
            bag.add('T');
            bag.add('L');
            bag.add('J');
            bag.add('O');
            bag.add('S');
            bag.add('Z');
            bag.add('I');

            Collections.shuffle(bag);
        }

        center = new Point(4, 1);
        orientation = 0;
        curPiece = bag.remove(0);
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

    public char pieceAtPoint(int x, int y){
        return universe[display][x][y];
    }

    private void gameOver() {
        clearBoard();
        
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

        gameOverReached = true;
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
