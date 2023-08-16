package GUI;

import java.awt.Point;

public interface TetrisPieceConstants {
    Point[][] T = {{new Point(0, 0), new Point(1, 0), new Point(-1, 0), new Point(0, -1)},
                   {new Point(0, 0), new Point(0, 1), new Point(0, -1), new Point(1, 0)},
                   {new Point(0, 0), new Point(-1, 0), new Point(1, 0), new Point(0, 1)},
                   {new Point(0, 0), new Point(0, -1), new Point(0, 1), new Point(-1, 0)}};

    Point[][] L = {{new Point(0, 0), new Point(1, 0), new Point(-1, 0), new Point(1, -1)},
                   {new Point(0, 0), new Point(0, 1), new Point(0, -1), new Point(1, 1)},
                   {new Point(0, 0), new Point(-1, 0), new Point(1, 0), new Point(-1, 1)},
                   {new Point(0, 0), new Point(0, -1), new Point(0, 1), new Point(-1, -1)}};

    Point[][] J = {{new Point(0, 0), new Point(1, 0), new Point(-1, 0), new Point(-1, -1)},
                   {new Point(0, 0), new Point(0, 1), new Point(0, -1), new Point(1, -1)},
                   {new Point(0, 0), new Point(-1, 0), new Point(1, 0), new Point(1, 1)},
                   {new Point(0, 0), new Point(0, -1), new Point(0, 1), new Point(-1, 1)}};

    Point[][] O = {{new Point(0, 0), new Point(1, 0), new Point(0, -1), new Point(1, -1)}};

    Point[][] S = {{new Point(0, 0), new Point(1, -1), new Point(-1, 0), new Point(0, -1)},
                   {new Point(0, 0), new Point(1, 1), new Point(0, -1), new Point(1, 0)},
                   {new Point(0, 0), new Point(-1, 1), new Point(1, 0), new Point(0, 1)},
                   {new Point(0, 0), new Point(-1, -1), new Point(0, 1), new Point(-1, 0)}};
    
    Point[][] Z = {{new Point(0, 0), new Point(-1, -1), new Point(1, 0), new Point(0, -1)},
                   {new Point(0, 0), new Point(1, -1), new Point(0, 1), new Point(1, 0)},
                   {new Point(0, 0), new Point(1, 1), new Point(-1, 0), new Point(0, 1)},
                   {new Point(0, 0), new Point(-1, 1), new Point(0, -1), new Point(-1, 0)}};
    
    Point[][] I = {{new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(-1, 0)},
                   {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, -1)},
                   {new Point(1, 1), new Point(0, 1), new Point(-1, 1), new Point(2, 1)},
                   {new Point(0, 1), new Point(0, 0), new Point(0, -1), new Point(0, 2)}};
}
