package GUI;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import GUI.Tetris.GamePanel;

public class TetrisWorker implements Runnable, TetrisPieceConstants{
    // TODO: finish changing over to HashMap, then implement new pieces
    
    public static Map<Character, Point[][]> pieces = new HashMap<Character, Point[][]>();

    static {
        pieces.put('T', T);
    }








































    
    private char[][][] universe = new char[2][GamePanel.ROWS][GamePanel.COLS];
    private int display = 0;

    private boolean pieceInPlay = false;
    private Point center;
    private char curPiece;
    private int orientation; // 0 up, 1 right, 2 down, 3 left
    private boolean stop = true;
    
    private int tickSpeed;
    private GamePanel gpanel;

    // may have to put try-catch blocks b/c of long boi but for now we're good (or we can do orientation if stmts for that specifically)
    public void movePieceDown(){
        if (pieceInPlay){
            try {
                removePieceFromBoard();

                center.y += 1;
                
                if (curPiece == 'T'){
                    for (int i = 0; i < T[orientation].length; i++){
                        if (!(universe[display][center.y + T[orientation][i].y][center.x + T[orientation][i].x] == 0)){
                            center.y -= 1;
                            returnPieceToBoard();
                            pieceInPlay = !pieceInPlay;
                            return;
                        }
                    }

                    for (int i = 0; i < T[orientation].length; i++){
                        universe[display][center.y + T[orientation][i].y][center.x + T[orientation][i].x] = 'T';
                    }
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
        while(pieceInPlay){
            movePieceDown();
        }
    }

    public void movePiece(boolean moveRight){
        if (pieceInPlay){
            try {
                removePieceFromBoard();

                if (moveRight)
                    center.x += 1;
                else
                    center.x -= 1;
                
                if (curPiece == 'T'){
                    for (int i = 0; i < T[orientation].length; i++){
                        if (!(universe[display][center.y + T[orientation][i].y][center.x + T[orientation][i].x] == 0)){
                            if (moveRight)
                                center.x -= 1;
                            else
                                center.x += 1;

                            returnPieceToBoard();
                            return;
                        }
                    }

                    for (int i = 0; i < T[orientation].length; i++){
                        universe[display][center.y + T[orientation][i].y][center.x + T[orientation][i].x] = 'T';
                    }
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
        if (pieceInPlay && curPiece != 'O'){
            int storedOrientation = orientation;
            try {
                removePieceFromBoard();

                if (rotatingRight){
                    orientation = (orientation + 1) % 4;
                } else {
                    orientation = (orientation -1 + 4) % 4;
                }

                if (curPiece == 'T'){
                    for (int i = 0; i < T[orientation].length; i++){
                        if (!(universe[display][center.y + T[orientation][i].y][center.x + T[orientation][i].x] == 0)){
                            orientation = storedOrientation;
                            returnPieceToBoard();
                            return;
                        }
                    }

                    for (int i = 0; i < T[orientation].length; i++){
                        universe[display][center.y + T[orientation][i].y][center.x + T[orientation][i].x] = 'T';
                    }
                }
            } catch (IndexOutOfBoundsException e){
                orientation = storedOrientation;
                returnPieceToBoard();
            }
        }
    }

    private void returnPieceToBoard() {
        Point[][] pieceConst = pieces.get(curPiece);
        
        for (int i = 0; i < pieceConst[orientation].length; i++){
            universe[display][center.y + pieceConst[orientation][i].y][center.x + pieceConst[orientation][i].x] = curPiece;
        }
    }

    private void removePieceFromBoard() {
        Point[][] pieceConst = pieces.get(curPiece);

        for (int i = 0; i < pieceConst[orientation].length; i++){
            universe[display][center.y + pieceConst[orientation][i].y][center.x + pieceConst[orientation][i].x] = 0;
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

                if (!pieceInPlay){
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
    }
    
    private void spawnPiece() {
        // TODO: Implement bag logic
        
        // for now, just spawns a T piece;

        center = new Point(4, 1);
        orientation = 0;
        curPiece = 'T';
        
        universe[1 - display][center.y][center.x] = 'T';
        universe[1 - display][center.y][center.x + 1] = 'T';
        universe[1 - display][center.y][center.x - 1] = 'T';
        universe[1 - display][center.y - 1][center.x] = 'T';

        pieceInPlay = true;
    }

    public char pieceAtPoint(int x, int y){
        return universe[display][x][y];
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
}
