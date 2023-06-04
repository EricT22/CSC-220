package CellGame;

import java.awt.Canvas;
import java.awt.Graphics;

public class GridCanvas extends Canvas{
    private Cell[][] arr;

    public GridCanvas(int rows, int cols, int size){
        arr = new Cell[rows][cols];

        for (int i = 0; i < rows; i++){
            int y = i * size;
            
            for (int j = 0; j < cols; j++){
                int x = j * size;

                arr[i][j] = new Cell(x, y, size);
            }
        }

        setSize(cols * size, rows * size);
    }

    public void paint(Graphics g){
        for (Cell[] carr : arr){
            for(Cell c : carr){
                c.draw(g);
            }
        }
    }

    public int numRows(){
        return arr.length;
    }

    public int numCols(){
        return arr[0].length;
    }

    public Cell getCell(int row, int col){
        return arr[row][col];
    }

    public void turnOn(int row, int col){
        arr[row][col].turnOn();
    }

    
    public int test(int row, int col){
        try{
            if (arr[row][col].isOn()){
                return 1;
            }
        } catch (IndexOutOfBoundsException e){
            // cell doesn't exist
        }

        return 0;
    }

    public int countAlive(int row, int col){
        int count = 0;

        count += test(row-1, col);
        count += test(row+1, col);
        count += test(row-1, col-1);
        count += test(row+1, col-1);
        count += test(row, col-1);
        count += test(row, col+1);
        count += test(row+1, col+1);
        count += test(row-1, col+1);

        return count;
    }
}
