package GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

// -- inheritance relationship: SwingGUIApp "is-a" JFrame
public class SwingGUIApp extends JFrame {

    // -- serialization identifier -- system generated
    private static final long serialVersionUID = -6167569334213042018L;

    // -- set the size of the JFrame. JPanels will adapt to this size
    private final int WIDTH = 700;
    private final int HEIGHT = 700;

    // private Timer animationTimer = null;

    private GraphicPanelInner graphicsPanel;
    private ControlPanelInner controlPanel;

    public SwingGUIApp ()
    {
        // -- construct the base JFrame first
        super();

        // -- set the application title
        this.setTitle("Java Swing Application");

        // -- initial size of the frame: width, height
        this.setSize(WIDTH, HEIGHT);

        // -- center the frame on the screen
        this.setLocationRelativeTo(null);

        // -- shut down the entire application when the frame is closed
        //    if you don't include this the application will continue to
        //    run in the background and you'll have to kill it by pressing
        //    the red square in eclipse
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // -- set the layout manager and add items
        //    5, 5 is the border around the edges of the areas
        setLayout(new BorderLayout(5, 5));

        // -- construct a JPanel for graphics
        graphicsPanel = new GraphicPanelInner();
        this.add(graphicsPanel, BorderLayout.CENTER);

        // -- construct a JPanel for controls
        controlPanel = new ControlPanelInner();
        this.add(controlPanel, BorderLayout.EAST);

        // -- Timer will generate an event every 10mSec once it is started
        //    First parameter is the delay in mSec, second is the ActionListener
        //    that will handle the timer events
        // animationTimer = new Timer(1000, // mSec
        //         // -- ActionListener (event handler) for the timer event
        //         //    an example of Real-Time Programming
        //         //    events occur at arbitrary times and our program
        //         //    must be prepared to deal with them
        //         new ActionListener() {
        //             public void actionPerformed(ActionEvent arg0) {
        //                 System.out.println("tic");
        //             }
        //         }
        // );

        // -- can make it so the user cannot resize the frame
        this.setResizable(false);

        // -- paint the graphics canvas before the initial display
        graphicsPanel.repaint();

        // -- show the frame on the screen
        this.setVisible(true);

        // -- set keyboard focus to the graphics panel
        graphicsPanel.setFocusable(true);
        graphicsPanel.requestFocus();

    }


    // -- Inner class for the graphics panel
    //    inner classes are convenient as they have full access to all
    //    outer (containing) class member variables and functions regardless of access
    //    modifier. Their down side is that they cannot be instantiated by
    //    other, separate classes. They should only be used if they are not
    //    going to be used by other applications (not involved in code reuse)
    public class GraphicPanelInner extends JPanel implements MouseMotionListener {

        private static final long serialVersionUID = 7056793999538384084L;
        private boolean drawrect = true;

        public GraphicPanelInner ()
        {
            super();
            this.setBackground(Color.YELLOW);
            this.prepareActionHandlers();

            this.addMouseMotionListener(this);
        }

        // -- action listeners should not perform time consuming computations
        //    since they are running at a high priority and will not be
        //    interrupted leaving the GUI non-responsive

        // -- prepare the controls and their associated action listeners
        private void prepareActionHandlers()
        {
            // -- The JPanel can have a mouse listener if desired
            //    note that the listener is an anonymous object (it has no specified reference)
            this.addMouseListener(new MouseListener() {

                                      @Override
                                      // -- mouse button when up and down without moving the cursor
                                      public void mouseClicked(MouseEvent event) {
                                          System.out.println("Mouse Clicked at (" + event.getX() + ", " + event.getY() + ")");
                                      }

                                      @Override
                                      // -- where the cursor entered the window
                                      public void mouseEntered(MouseEvent event) {
                                          System.out.println("Mouse Entered at (" + event.getX() + ", " + event.getY() + ")");
                                      }

                                      @Override
                                      // -- where the cursor exited the window
                                      public void mouseExited(MouseEvent event) {
                                          System.out.println("Mouse Exited at (" + event.getX() + ", " + event.getY() + ")");
                                      }

                                      @Override
                                      // -- when a button is pressed
                                      public void mousePressed(MouseEvent event) {
                                          // -- BUTTON1 is the left, BUTTON3 is the right
                                          if (event.getButton() == MouseEvent.BUTTON1) {
                                              System.out.println("Left button pressed");
                                          }
                                          else if (event.getButton() == MouseEvent.BUTTON3) {
                                              System.out.println("Right button pressed");
                                          }
                                          graphicsPanel.requestFocus();
                                      }

                                      @Override
                                      // -- when a button is released
                                      public void mouseReleased(MouseEvent event) {
                                          // -- BUTTON1 is the left, BUTTON3 is the right
                                          if (event.getButton() == MouseEvent.BUTTON1) {
                                              System.out.println("Left button released");
                                          }
                                          else if (event.getButton() == MouseEvent.BUTTON3) {
                                              System.out.println("Right button released");
                                          }
                                          graphicsPanel.requestFocus();
                                          repaint();
                                      }
                                  }
            );

            // -- keyboard listener
            //    note that the JPanel must have focus for these to
            //    generate events. You can click the mouse in the JPanel
            //    or call graphicsPanel.requestFocus();
            //    listener is created as an anonymous object
            this.addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent event) {
                    System.out.println("key typed: " + event.getKeyCode());
                    graphicsPanel.repaint();
                }

                @Override
                public void keyPressed(KeyEvent event) {
                    System.out.println("key pressed: " + event.getKeyCode());
                    graphicsPanel.repaint();
                }

                @Override
                public void keyReleased(KeyEvent event) {
                    System.out.println("key released: " + event.getKeyCode());
                    graphicsPanel.repaint();
                }

            });
        }

        // -- Mouse motion event handlers
        @Override
        // -- cursor movement with a button pressed
        public void mouseDragged(MouseEvent event) {
            System.out.println("Mouse dragged to (" + event.getX() + ", " + event.getY() + ")");
        }

        @Override
        // -- cursor movement with no buttons pressed
        public void mouseMoved(MouseEvent event) {
            System.out.println("Mouse moved to (" + event.getX() + ", " + event.getY() + ")");
        }

        // -- this override sets the desired size of the JPanel which is
        //    used by some layout managers -- default desired size is 0,0
        //    which is, in general, not good -- will pull from layout manager
        public Dimension getPreferredSize()
        {
            return new Dimension(50, 50);
        }

        // -- this override is where all the painting should be done.
        //    DO NOT call it directly. Consider it an event handler.
        //    Rather, call repaint() and let the
        //    event handling system decide when to call it
        //    DO NOT put graphics function calls elsewhere in the code, although
        //    legal, it's bad practice and could destroy the integrity of the display
        //    This function is used for all "permanent" painting
        @Override
        public void paint(Graphics g)
        {
            // -- the base class paintComponent(g) method ensures
            //    the drawing area will be cleared properly. Do not
            //    modify any attributes of g prior to sending it to
            //    the base class
            super.paintComponent(g);

            System.out.println("Repaint");
            // -- for legacy reasons the parameter comes in as type Graphics
            //    but it is really a Graphics2D object. Cast it up since the
            //    Graphics2D class is more capable
            Graphics2D graphicsContext = null;
            if (g instanceof Graphics2D) {
                graphicsContext = (Graphics2D)g;
            }
            else {
                System.out.println("you have an old JVM");
                return;
            }

            int height = this.getHeight();
            int width = this.getWidth();

            // -- draw an image of random color on the panel
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            // for (int i = 0; i < bi.getHeight(); ++i) {
            //     for (int j = 0; j < bi.getWidth(); ++j) {
            //         int pixel =	((int)(Math.random() * 255) << 16) | ((int)(Math.random() * 255) << 8) | ((int)(Math.random() * 255));
            //         bi.setRGB(j, i, pixel);
            //     }
            // }

            try {
                bi = ImageIO.read(new File("C:\\Users\\Eric\\OneDrive\\Documents\\Wallpapers\\barca.jpg"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
                
            graphicsContext.drawImage(bi, 0, 0, this.getWidth(), this.getHeight(), null);

            // -- overlay a grid to fill the entire space evenly
            int horzs = 14;
            double horzspacing = width / (double)horzs;
            double x0 = 0.0;
            graphicsContext.setColor(Color.BLUE);
            for (int i = 0; i < horzs; ++i) {
                graphicsContext.drawLine((int)x0, 0, (int)x0, height);
                x0 += horzspacing;
            }

            int verts = 14;
            double vertspacing = height / (double)verts;
            double y0 = 0.0;
            graphicsContext.setColor(Color.RED);
            for (int i = 0; i < verts; ++i) {
                graphicsContext.drawLine(0, (int)y0, width, (int)y0);
                y0 += vertspacing;
            }

            // -- draw a red ellipse in the center of the graphics area
            
            if (drawrect){    
                graphicsContext.setColor(Color.YELLOW);
                graphicsContext.fillRect(width / 2 - 25, height / 2 - 25, 50, 50);
            }
            drawrect = !drawrect;
        }

    }

    // -- Inner class for control panel
    public class ControlPanelInner extends JPanel {

        private static final long serialVersionUID = -8776438726683578403L;

        // -- push buttons
        private JButton readtextfieldButton;
        private JButton saveButton;
        private JButton loadButton;
        private JButton timerOnButton;
        private JButton timerOffButton;
        private JButton textareaButton;
        private JButton stallButton;

        private UpdateDisplay updatedDisplay;

        // -- field to hold 1 line of text
        private JTextField textField;

        // -- a fixed label, can be changed by the program but not the user
        private JLabel jlabel = new JLabel("  A JLabel   ");

        // -- area to hold multiple lines of text
        private JTextArea textArea;
        private JScrollPane scrollableTextArea;
        private JRadioButton radio1;
        private JRadioButton radio2;
        private ButtonGroup bg;

        public ControlPanelInner ()
        {
            // -- set up buttons
            prepareButtonHandlers();

            // -- set the layout manager
            //    this will determine how items are added to the JPanel
            //    the goal is to keep the GUI usable no matter what the user
            //    does to it
            setLayout(new FlowLayout());

            // -- construct the JTextField, 5 characters wide
            textField = new JTextField("Default", 5);

            // -- add items to the JPanel in order (FlowLayout)
            this.add(readtextfieldButton);
            this.add(textField);
            this.add(textareaButton);
            this.add(loadButton);
            this.add(saveButton);
            this.add(timerOnButton);
            this.add(timerOffButton);

            // -- add a JTextArea with scroll bars, 7 rows, 5 columns
            //    scrollbar areas will show as soon as the JScrollPane
            //    is constructed. If you remove the calls to setHorizontalScrollBarPolicy
            //    and setVerticalScrollBarPolicy the scrollbars will only show
            //    when needed
            textArea = new JTextArea(7, 5);
            scrollableTextArea = new JScrollPane(textArea);
            scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            this.add(scrollableTextArea);

            this.add(jlabel);

            // -- construct radio buttons and group them
            radio1 = new JRadioButton("radio 1");
            radio2 = new JRadioButton("radio 2");
            // -- add to the JPanel
            this.add(radio1);
            this.add(radio2);

            bg = new ButtonGroup();
            bg.add(radio1);
            bg.add(radio2);
            radio1.setSelected(true);

            // -- add a JPanel within the JPanel
            this.add(new smallGraphicPanel(80, 80));

            this.add(stallButton);

            updatedDisplay = new UpdateDisplay(graphicsPanel);

        }

        // -- construct JButtons and add event handlers
        private void prepareButtonHandlers()
        {
            // -- action listeners should not preform time consuming computations
            //    since they are running at a high priority and will not be
            //    interrupted leaving the GUI non-responsive

            // -- Construct the JButtons and their associated event handlers
            readtextfieldButton = new JButton("Read Speed");
            readtextfieldButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            // -- do not do anything that is time consuming
                            //    doing so will make the GUI non-responsive to the user
                            String textFieldString = textField.getText();
                            try {
                                int x = Integer.parseInt(textFieldString);
                                System.out.println(x);
                                updatedDisplay.setSpeed(x);
                            }
                            catch (Exception e) {
                                System.out.println("not a number");
                            }
                            System.out.println(textFieldString);

                            jlabel.setText("Changed");

                            // -- send focus back to the graphicsPanel
                            graphicsPanel.requestFocus();
                        }
                    }
            );

            textareaButton = new JButton("Set Text");
            textareaButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            for (int i = 0; i < 10; ++i) {
                                textArea.append("Very Wide String " + i + "\n");
                            }
                            if (radio1.isSelected()) {
                                textArea.append("radio 1");
                            }
                            else if (radio2.isSelected()) {
                                textArea.append("radio 2");
                            }
                            // -- send focus back to the graphicsPanel
                            graphicsPanel.requestFocus();
                        }
                    }
            );

            timerOnButton = new JButton("Timer on");
            timerOnButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            //animationTimer.start();
                            
                            new Thread(updatedDisplay).start();
                            // -- send focus back to the graphicsPanel
                            graphicsPanel.requestFocus();
                        }
                    }
            );

            timerOffButton = new JButton("Timer off");
            timerOffButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            //animationTimer.stop();

                            updatedDisplay.stop();
                            // -- send focus back to the graphicsPanel
                            graphicsPanel.requestFocus();
                        }
                    }
            );

            saveButton = new JButton("Save");
            saveButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            JFileChooser jfc = new JFileChooser();
                            if (jfc.showDialog(null, "Save") == JFileChooser.APPROVE_OPTION) {
                                System.out.println(jfc.getSelectedFile().getName());
                            }
                            // -- send focus back to the graphicsPanel
                            graphicsPanel.requestFocus();
                        }
                    }
            );

            loadButton = new JButton("Load");
            loadButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            JFileChooser jfc = new JFileChooser();
                            if (jfc.showDialog(null, "Load") == JFileChooser.APPROVE_OPTION) {
                                System.out.println(jfc.getSelectedFile().getName());
                            }
                            // -- send focus back to the graphicsPanel
                            graphicsPanel.requestFocus();
                        }
                    }
            );

            // -- show what not to do in an event handler
            stallButton = new JButton("Stall GUI");
            stallButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            // -- send focus back to the graphicsPanel
                            graphicsPanel.requestFocus();
                        }
                    }
            );

        }

        // -- sets the size of the JPanel
        public Dimension getPreferredSize()
        {
            return new Dimension(100, 500);
        }

    }

    // -- define a small JPanel to be included in the
    //    control panel. Note that this has its own
    //    paint function that can be used to draw
    //    graphics
    public class smallGraphicPanel extends JPanel {

        private static final long serialVersionUID = -2990735412635929550L;

        private int height, width;

        public smallGraphicPanel (int height, int width)
        {
            this.height = height;
            this.width = width;

            this.setLayout(new FlowLayout());
            this.setBackground(Color.RED);
        }

        // -- sets the size of the JPanel
        public Dimension getPreferredSize()
        {
            return new Dimension (this.width, this.height);
        }

        @Override
        public void paint (Graphics g)
        {
            super.paintComponent(g);
            int w = this.getWidth();
            int h = this.getHeight();

            Graphics2D g2d = (Graphics2D)g;

            g2d.setColor(Color.GRAY);
            g2d.fillOval(0,  0,  w, h);

        }
    }


    public static void main (String[] args)
    {
        // -- can run as an anonymous object since Swing
        //    is multi-threaded (the main function can terminate
        //    its thread while the Swing thread continues on)
        //    the object is created on the heap but has no stack reference
        //    thus we call it "anonymous"
        new SwingGUIApp();

        // -- this line demonstrates that the Swing JFrame runs in
        //    its own thread
        System.out.println("Main thread terminating");
    }

}
