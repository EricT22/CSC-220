package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameOfLife extends JFrame{

    private final int WIDTH = 512;
    private final int HEIGHT = 600;

    private GraphicsPanel graphicsPanel;
    private ControlPanel controlPanel;
    private AnimationPanel animationPanel;
    private DisplayWorker displayWorker;

    public GameOfLife(){
        super("Conway's Game of Life");

        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(5, 5));

        graphicsPanel = new GraphicsPanel();
        controlPanel = new ControlPanel();
        animationPanel = new AnimationPanel();
        this.add(graphicsPanel, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.WEST);
        this.add(animationPanel, BorderLayout.SOUTH);

        this.setResizable(false);
        graphicsPanel.repaint();
        this.setVisible(true);
        graphicsPanel.setFocusable(true);
        graphicsPanel.requestFocusInWindow();
    }

    public class GraphicsPanel extends JPanel implements MouseMotionListener {
        
        private static final long serialVersionUID = 7056793999548384084L;

        public static int COLS = 14;
        public static int ROWS = 14;
        public static boolean gridOn = true;
        public static Color background = Color.YELLOW;
        public static Color lineColor = Color.BLACK;
        public static boolean drawingCells = true;

        public GraphicsPanel(){
            super();
            this.setBackground(background);

            this.addMouseMotionListener(this);

            this.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Mouse clicked at (" + e.getX() + ", " + e.getY() + ")");
                    
                    displayWorker.setCell(e.getX(), e.getY());
                    repaint();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        System.out.println("Left button pressed");
                    }
                    else if (e.getButton() == MouseEvent.BUTTON3) {
                        System.out.println("Right button pressed");
                    }
                    graphicsPanel.requestFocus();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        System.out.println("Left button released");
                    }
                    else if (e.getButton() == MouseEvent.BUTTON3) {
                        System.out.println("Right button released");
                    }
                    graphicsPanel.requestFocus();
                    repaint();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    System.out.println("Mouse entered at (" + e.getX() + ", " + e.getY() + ")");
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    System.out.println("Mouse exited at (" + e.getX() + ", " + e.getY() + ")");
                }
                
            });

            this.addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {
                    System.out.println("key typed: " + e.getKeyCode());
                    graphicsPanel.repaint();
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    System.out.println("key pressed: " + e.getKeyCode());
                    graphicsPanel.repaint();
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    System.out.println("key released: " + e.getKeyCode());
                    graphicsPanel.repaint();
                }
                
            });
        }


        @Override
        public void mouseDragged(MouseEvent e) {
            System.out.println("Mouse dragged to (" + e.getX() + ", " + e.getY() + ")");

            displayWorker.setCell(e.getX(), e.getY());

            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            System.out.println("Mouse moved to (" + e.getX() + ", " + e.getY() + ")");
        }
        
        public Dimension getPreferredSize()
        {
            return new Dimension(50, 50);
        }

        @Override
        public void paint(Graphics g){
            super.paintComponent(g);

            Graphics2D g2;
            if (g instanceof Graphics2D){
                g2 = (Graphics2D) g;
            } else {
                System.out.println("Get a new computer please, thanks! ;)");
                return;
            }
            
            int height = this.getHeight();
            int width = this.getWidth();

            int horzs = COLS;
            double horzspacing = width / (double)horzs;
            
            int verts = ROWS;
            double vertspacing = height / (double)verts;
            
            g2.setColor(lineColor);
            if (gridOn){
                double x0 = 0.0;
                for (int i = 0; i < horzs; ++i) {
                    g2.drawLine((int)x0, 0, (int)x0, height);
                    x0 += horzspacing;
                }
                
                double y0 = 0.0;
                for (int i = 0; i < verts; ++i) {
                    g2.drawLine(0, (int)y0, width, (int)y0);
                    y0 += vertspacing;
                }
            }

            for (int i = 0; i < verts; i++){
                for (int j = 0; j < horzs; j++){
                    if (displayWorker.cellOn(i, j)){
                        int x = (int) (j* horzspacing);
                        int y = (int) (i * vertspacing);
                        int w = (int) ((j + 1) * horzspacing) - x;
                        int h = (int) ((i + 1) * vertspacing) - y;
                        g2.fillOval(x, y, w, h);
                    }
                }
            }
        }
    }

    
    private class ControlPanel extends JPanel {
        
        private static final long serialVersionUID = -8786438726683578403L;

        private JLabel grid;
        private JLabel rows;
        private JLabel cols;
        private JButton size;
        private JButton load;
        private JButton save;
        private JTextField numRows;
        private JTextField numCols;
        private JCheckBox gridOnButton;
        private JCheckBox drawCell;
        private JCheckBox wrapped;

        //extra bit
        private JRadioButton normalColors;
        private JRadioButton invertedColors;
        private ButtonGroup bg;

        public ControlPanel(){
            super();

            prepareComponents();            

            this.setLayout(new FlowLayout());
            this.add(grid);
            this.add(size);
            this.add(rows);
            this.add(numRows);
            this.add(cols);
            this.add(numCols);
            this.add(gridOnButton);
            this.add(drawCell);
            this.add(wrapped);
            this.add(load);
            this.add(save);

            //extra bit
            this.add(normalColors);
            this.add(invertedColors);

            bg = new ButtonGroup();
            bg.add(normalColors);
            bg.add(invertedColors);

            normalColors.setSelected(true);
        }

        private void prepareComponents(){
            grid = new JLabel("Grid");

            size = new JButton("Set Size");
            size.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Setting size");

                    String rows = numRows.getText();
                    String cols = numCols.getText();
                    try {
                        int x = Integer.parseInt(rows);
                        GraphicsPanel.ROWS = Math.abs(x);

                        int y = Integer.parseInt(cols);
                        GraphicsPanel.COLS = Math.abs(y);

                        displayWorker.updateNumCells();

                        graphicsPanel.repaint();
                    } catch (Exception ex){
                        System.out.println("Not a number.");
                    }
                    
                    graphicsPanel.requestFocus();
                }
                
            });

            rows = new JLabel("Rows");
            numRows = new JTextField(GraphicsPanel.ROWS + "", 6);
            numRows.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Receiving rows");
                    graphicsPanel.requestFocus();
                }
                
            });

            cols = new JLabel("Columns");
            numCols = new JTextField(GraphicsPanel.COLS + "", 6);
            numCols.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Receiving columns");
                    graphicsPanel.requestFocus();
                }
                
            });

            gridOnButton = new JCheckBox("Grid On", true);
            gridOnButton.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {
                    System.out.println("Grid on check box changed");

                    GraphicsPanel.gridOn = gridOnButton.isSelected();
                    graphicsPanel.repaint();
                    graphicsPanel.requestFocus();
                }
                
            });

            drawCell = new JCheckBox("Draw Cell", true);
            drawCell.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {
                    System.out.println("Draw cell check box changed");
                    GraphicsPanel.drawingCells = !GraphicsPanel.drawingCells;
                    graphicsPanel.requestFocus();
                }
                
            });

            wrapped = new JCheckBox("Wrapped", true);
            wrapped.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Changing wrap!");
                    displayWorker.changeWrap();
                    graphicsPanel.requestFocus();
                }
                
            });

            load = new JButton("Load");
            load.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser jfc = new JFileChooser("C:\\Users\\Eric\\OneDrive\\Documents\\CSC 220\\GOL Saves");
                    if (jfc.showDialog(null, "Load") == JFileChooser.APPROVE_OPTION) {
                        System.out.print("LOADING...");
                        displayWorker.deserialize(jfc.getSelectedFile().getAbsolutePath());
                        numRows.setText(GraphicsPanel.ROWS + "");
                        numCols.setText(GraphicsPanel.COLS + "");
                        wrapped.setSelected(displayWorker.getWrapped());
                        System.out.println(" DONE!");
                    }
                    // -- send focus back to the graphicsPanel
                    graphicsPanel.requestFocus();
                }
                
            });

            save = new JButton("Save");
            save.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser jfc = new JFileChooser("C:\\Users\\Eric\\OneDrive\\Documents\\CSC 220\\GOL Saves");
                    if (jfc.showDialog(null, "Save") == JFileChooser.APPROVE_OPTION) {
                        System.out.print("SAVING...");
                        displayWorker.serialize(jfc.getSelectedFile().getAbsolutePath());
                        System.out.println(" DONE!");
                    }
                    // -- send focus back to the graphicsPanel
                    graphicsPanel.requestFocus();
                }
                
            });

            //extra bit

            normalColors = new JRadioButton("Normal Tint");
            normalColors.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {
                    GraphicsPanel.background = Color.YELLOW;
                    graphicsPanel.setBackground(GraphicsPanel.background);
                    GraphicsPanel.lineColor = Color.BLACK;
                    graphicsPanel.repaint();
                    graphicsPanel.requestFocus();
                }
                
            });

            invertedColors = new JRadioButton("Inverted Tint");
            invertedColors.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {
                    System.out.println("Changing tint");
                    GraphicsPanel.background = Color.BLACK;
                    graphicsPanel.setBackground(GraphicsPanel.background);
                    GraphicsPanel.lineColor = Color.YELLOW;
                    graphicsPanel.repaint();
                    graphicsPanel.requestFocus();
                }
                
            });
        }

        public Dimension getPreferredSize(){
            return new Dimension(100, 500);
        }
    }

    private class AnimationPanel extends JPanel {
        
        private static final long serialVersionUID = -6546584465489465050L;
        private static final int MAX_SPEED = 1000;

        private JLabel animationLabel;
        private JButton startButton;
        private JSlider animationSlider;

        public AnimationPanel(){
            super();

            prepareComponents();

            this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
            this.add(animationLabel);
            this.add(startButton);
            this.add(animationSlider);

            displayWorker = new DisplayWorker(MAX_SPEED - animationSlider.getValue() * 10, graphicsPanel);
        }

        private void prepareComponents(){
            animationLabel = new JLabel("Animation");

            startButton = new JButton("Start");
            startButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    if (startButton.getText().equals("Start")){
                        startButton.setText("Stop");
                        System.out.println("Starting Timer!");
                        
                        new Thread(displayWorker).start();

                    } else {
                        startButton.setText("Start");
                        System.out.println("Stopping Timer!");
                        displayWorker.stop();
                    }

                    graphicsPanel.requestFocus();
                }
                
            });

            animationSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
            animationSlider.setPaintTicks(true);
            animationSlider.setPaintLabels(true);
            animationSlider.setMajorTickSpacing(20);
            animationSlider.setMinorTickSpacing(5);
            animationSlider.addChangeListener(new ChangeListener() {

                @Override
                public void stateChanged(ChangeEvent e) {
                    System.out.println("Changing slider");
                    displayWorker.setSpeed(MAX_SPEED - animationSlider.getValue() * 10);
                    graphicsPanel.requestFocus();
                }
                
            });
        }

        public Dimension getPreferredSize(){
            return new Dimension(500, 100);
        }
    }

    public static void main(String[] args) {
        new GameOfLife();

        System.out.println("Main thread terminating");
    }
}
