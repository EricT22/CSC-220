package GUI;

import java.awt.Point;

public interface TetrisPieceConstants {
    Point[][] T = {{new Point(0, 0), new Point(1, 0), new Point(-1, 0), new Point(0, -1)},
                   {new Point(0, 0), new Point(0, 1), new Point(0, -1), new Point(1, 0)},
                   {new Point(0, 0), new Point(-1, 0), new Point(1, 0), new Point(0, 1)},
                   {new Point(0, 0), new Point(0, -1), new Point(0, 1), new Point(-1, 0)}};
                  
    
    // implement the rest of the pieces
}
