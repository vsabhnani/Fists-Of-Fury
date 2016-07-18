// Viren Sabhnani
import java.awt.*;//needed for graphics
import java.awt.event.*;//needed for Key Listener + Action Listener
import java.util.ArrayList;
import javax.swing.*;//needed for JFrame window
import java.util.Random;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class fistsoffury extends JFrame implements KeyListener, ActionListener // used to so that we can use keys and motion etc. 
{

// the following are variables, booleans, colors, color matrices that i established so that I can use them thorugh out the program with ease
    Color grey1 = new Color(128, 138, 135);
    Color lime = new Color(0, 255, 0);
    Color wheat = new Color(245, 222, 179);
    int height;
    int width;
    public static String enter;
    public static int x = 0, velX = 2, x1 = 0;
    public static int y = 0, y1 = 0, velY = 2, x2 = 700, y3 = 900;
    public static int xvar = 255;
    public static int y2 = 0;
    public static int score = 0;
    Font font1 = new Font("Courier", Font.BOLD, 40);
    public static int ticks;
    public static final int TICK = 20;
    public static final int FPS = 1000 / TICK;

    public static boolean startScreen = true;

    public static boolean spaceshoot = false;

    public static int bulletdownX;
    public static int bulletdownY;
    public static int bulletleftX;
    public static int bulletleftY;
    public static int bulletupX;
    public static int bulletupY;
    public static int bulletrightX;
    public static int bulletrightY;

    public int yPower = 0;
    public boolean collisionPower;
    public static boolean isLeft;
    public static boolean isUp;
    public static boolean changeCharacter;
    public static boolean isDown;
    public static boolean isRight;
    public static boolean isNormal;
    public static boolean isSpace;
    public static boolean ypositive;
    public static boolean ynegative;
    public static Timer t;
    Color brown = new Color(139, 69, 19);
    Color red = Color.red;
    Color blue = Color.blue;
    Color yellow = Color.yellow;
    Color black = Color.black;
    Color white = Color.white;
    Color gold = new Color(255, 185, 15);
    public static boolean isJumping = false;
    Rectangle rectMaincharacter;
    Rectangle rectPower;
    Rectangle rectBrick;
    Color bblack = new Color(89, 89, 89);

    Color[][] currentImage; // this is used to know what the main chacacter looks like at all times (for example, when he is punching right, he has a different orientation than when he is punching left)

    Color[][] punchRight = {
        {lime, lime, grey1, grey1, grey1, grey1, grey1, lime, lime}, //1
        {lime, lime, wheat, wheat, wheat, wheat, brown, lime, lime}, //2
        {lime, lime, wheat, wheat, wheat, white, white, lime, lime}, //3
        {lime, lime, lime, black, black, lime, lime, lime, lime}, //4
        {lime, lime, lime, black, black, black, black, black, wheat}, //5
        {lime, lime, lime, black, black, lime, lime, lime, lime}, //6
        {lime, lime, grey1, grey1, grey1, grey1, lime, lime, lime}, //7
        {lime, lime, grey1, lime, lime, grey1, lime, lime, lime}, //8
        {lime, lime, grey1, lime, lime, grey1, lime, lime, lime}, //9
    // this color matrix is used for when the the main charcter punches right 
    };

    Color[][] punchUp = {
        {lime, lime, lime, lime, wheat, lime, lime, lime, lime}, //1
        {lime, lime, lime, lime, black, lime, lime, lime, lime}, //2
        {lime, lime, lime, lime, black, lime, lime, lime, lime}, //3
        {lime, lime, lime, lime, black, lime, lime, lime, lime}, //4
        {lime, lime, grey1, grey1, grey1, grey1, lime, lime, lime}, //5
        {lime, lime, grey1, grey1, grey1, grey1, lime, lime, lime}, //6
        {lime, lime, grey1, grey1, grey1, grey1, lime, lime, lime}, //7
        {lime, lime, lime, lime, lime, lime, lime, lime, lime}, //8
        {lime, lime, lime, lime, lime, lime, lime, lime, lime}, //9
    //this color matrix is used for when the main character punches up
    };
    Color[][] punchDown = {
        {lime, lime, lime, lime, lime, lime, lime, lime, lime}, //1
        {lime, lime, lime, lime, lime, lime, lime, lime, lime}, //2
        {lime, lime, grey1, grey1, grey1, grey1, lime, lime, lime}, //3
        {lime, lime, grey1, grey1, grey1, grey1, lime, lime, lime}, //4
        {lime, lime, grey1, grey1, grey1, grey1, lime, lime, lime}, //5
        {lime, lime, lime, lime, black, lime, lime, lime, lime}, //6
        {lime, lime, lime, lime, black, lime, lime, lime, lime}, //7
        {lime, lime, lime, lime, black, lime, lime, lime, lime}, //8
        {lime, lime, lime, lime, wheat, lime, lime, lime, lime}, //9
    // this color matrix is used for when the main character punches down
    };
    Color[][] punchLeft = {
        {lime, lime, lime, grey1, grey1, grey1, grey1, lime, lime}, //1
        {lime, lime, lime, brown, wheat, wheat, wheat, lime, lime}, //2
        {lime, lime, lime, white, white, wheat, wheat, lime, lime}, //3
        {lime, lime, lime, lime, black, black, lime, lime, lime}, //4
        {wheat, black, black, black, black, black, lime, lime, lime}, //5
        {lime, lime, lime, lime, black, black, lime, lime, lime}, //6
        {lime, lime, lime, grey1, grey1, grey1, grey1, lime, lime}, //7
        {lime, lime, lime, grey1, lime, lime, grey1, lime, lime}, //8
        {lime, lime, lime, grey1, lime, lime, grey1, lime, lime}, //9
    // this color matrix is used for when the main character punches to the left 
    };

    Color[][] Maincharacter = {
        {lime, lime, grey1, grey1, grey1, grey1, grey1, lime, lime}, //1
        {lime, lime, wheat, brown, wheat, brown, wheat, lime, lime}, //2
        {lime, lime, wheat, wheat, wheat, wheat, wheat, lime, lime}, //3
        {lime, lime, wheat, white, white, white, wheat, lime, lime}, //4
        {lime, lime, lime, black, black, black, lime, lime, lime}, //5
        {wheat, black, black, black, black, black, black, black, wheat}, //6
        {lime, lime, lime, black, black, black, lime, lime, lime}, //7
        {lime, lime, lime, grey1, grey1, grey1, lime, lime, lime}, //8
        {lime, lime, lime, grey1, lime, grey1, lime, lime, lime}, //9
    // this color matrix is for when the main character is normal (at rest position and not fighting) 
    };

    public Color[][] enemyfromright = {
        {lime, lime, black, black, black, black, black, lime, lime}, //1
        {lime, lime, brown, wheat, wheat, wheat, wheat, lime, lime}, //2
        {lime, lime, black, black, black, black, black, lime, lime}, //3
        {lime, lime, black, black, black, black, black, lime, lime}, //4
        {lime, lime, lime, black, black, black, lime, lime, lime}, //5
        {wheat, black, black, black, black, black, lime, lime, lime}, //6
        {lime, lime, lime, black, black, black, lime, lime, lime}, //7
        {lime, lime, lime, bblack, bblack, bblack, lime, lime, lime}, //8
        {lime, lime, lime, bblack, lime, bblack, lime, lime, lime}, //9
    //color matrix for enemy coming from right
    };
    public Color[][] enemyfromdown = {
        {lime, lime, lime, lime, lime, lime, lime, lime, lime}, //1
        {lime, lime, black, black, black, black, black, lime, lime}, //2
        {lime, lime, black, black, black, black, black, lime, lime}, //3
        {lime, lime, black, black, black, black, black, lime, lime}, //4
        {lime, lime, lime, black, black, black, lime, lime, lime}, //5
        {lime, lime, lime, black, black, black, lime, lime, lime}, //6
        {lime, lime, lime, black, black, black, lime, lime, lime}, //7
        {lime, lime, lime, bblack, bblack, bblack, lime, lime, lime}, //8
        {lime, lime, lime, bblack, lime, bblack, lime, lime, lime}, //9
    //color matrix for enemy coming from down 
    };
    public Color[][] enemyfromup = {
        {lime, lime, black, black, black, black, black, lime, lime}, //1
        {lime, lime, wheat, brown, wheat, brown, wheat, lime, lime}, //2
        {lime, lime, black, black, black, black, black, lime, lime}, //3
        {lime, lime, black, black, black, black, black, lime, lime}, //4
        {lime, lime, lime, black, black, black, lime, lime, lime}, //5
        {wheat, black, black, black, black, black, black, black, wheat}, //6
        {lime, lime, lime, black, black, black, lime, lime, lime}, //7
        {lime, lime, lime, bblack, bblack, bblack, lime, lime, lime}, //8
        {lime, lime, lime, bblack, lime, bblack, lime, lime, lime}, //9
    //color matrix for enemy coming from up 
    };
    public Color[][] enemyfromleft = {
        {lime, lime, black, black, black, black, black, lime, lime}, //1
        {lime, lime, wheat, wheat, wheat, wheat, brown, lime, lime}, //2
        {lime, lime, black, black, black, black, black, lime, lime}, //3
        {lime, lime, black, black, black, black, black, lime, lime}, //4
        {lime, lime, lime, black, black, black, lime, lime, lime}, //5
        {lime, lime, lime, black, black, black, black, black, lime}, //6
        {lime, lime, lime, black, black, black, lime, lime, lime}, //7
        {lime, lime, lime, bblack, bblack, bblack, lime, lime, lime}, //8
        {lime, lime, lime, bblack, lime, bblack, lime, lime, lime}, //9 
    //color matrix for enemy coming from left
    };

    public static ArrayList<Color[][]> colorMatrixs = new ArrayList<>();
    public static ArrayList<Integer> enemyX = new ArrayList<>();
    public static ArrayList<Integer> enemyY = new ArrayList<>();
    public static ArrayList<Integer> enemyDirection = new ArrayList<>();

    public fistsoffury() //start constructor
    {
        currentImage = Maincharacter; // this sets the current image of the character in rest position
        Container c = getContentPane();//c is the name...can be changed
        c.setBackground(Color.black);//makes the background white...color can be changed
        add(new JP()); // for the JPanel
        t = new Timer(TICK, this); // timer for animations
        t.start(); // starts the timer
        addKeyListener(this); // allows the key listener to run properly
        play("/enemypkg/homebase.wav");

    }//end constructor

    public static void main(String[] args) //start main 
    {
        fistsoffury w = new fistsoffury(); //w is the variable that will define this graphics class
        w.setTitle("FISTS OF FURY - VERSION 1.0 - VMS");//makes a header for the window
        w.setPreferredSize(new Dimension(600, 800));//or set the size of the window to the dimension that you want (use this OR above)
        w.setResizable(false);//makes the screen not resizable...obviously
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//stops program from running when X out
        w.pack();
        w.setLocationRelativeTo(null);
        w.setVisible(true);//allows window to open

    }//end main

    public void keyTyped(KeyEvent ke) {
// this is an abstract method that I don't use, but is needed to implement the parts of the method keyListener I need

    }

    public void keyPressed(KeyEvent ke) // method for when you press any key \
    {
// 0 = coming from the left
// 1 = coming from the above
// 2 = coming from the right
// 3 = coming from the below
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            isLeft = true; //sets a boolean true for when you hit the left arrow key (look below)
            isNormal = false; // sets 
            collisiondectect(0);
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            isRight = true;
            isNormal = false;
            collisiondectect(2);

        }
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            isUp = true;
            isNormal = false;
            collisiondectect(1);
        }
        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            isDown = true;
            isNormal = false;
            collisiondectect(3);
        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!spaceshoot) {
                spaceshoot = true;

                bulletdownX = 250;
                bulletdownY = 450;
                bulletleftX = 230;
                bulletleftY = 350;
                bulletupX = 250;
                bulletupY = 330;
                bulletrightX = 350;
                bulletrightY = 350;

            }
        }
    }

    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            isLeft = false;
            isNormal = true;

        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            isRight = false;
            isNormal = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_UP) {

            isUp = false;
            isNormal = true;

        }
        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            isDown = false;
            isNormal = true;
        }

    }

    public void actionPerformed(ActionEvent ae) {
        ticks++;

        if (isLeft) {
            left();
        }
        if (isRight) {
            right();
        }
        if (isUp) {
            up();
        }

        if (isDown) {
            down();
        }
        if (isNormal) {
            normal();
        }

        if (x < -960) {
            x = -960;
        }

        for (int i = 0; i < enemyDirection.size(); i++) {
// 0 = coming from the left
// 1 = coming from the above
// 2 = coming from the right
// 3 = coming from the below
            System.out.println(enemyDirection.get(i));
            if (enemyDirection.get(i) == 0) {
                enemyX.set(i, enemyX.get(i) + velX);
            } else if (enemyDirection.get(i) == 1) {
                enemyY.set(i, enemyY.get(i) + velY);

            } else if (enemyDirection.get(i) == 2) {
                enemyX.set(i, enemyX.get(i) - velX);
            } else if (enemyDirection.get(i) == 3) {
                enemyY.set(i, enemyY.get(i) - velY);
            }
        }
        repaint();
    }

    public void left() {
        currentImage = punchLeft;
    }

    public void right() {
        currentImage = punchRight;
    }

    public void up() {
        currentImage = punchUp;
    }

    public void down() {
        currentImage = punchDown;
    }

    public void normal() {
        currentImage = Maincharacter;
    }

    public void collisiondectect(int direction) {
        for (int i = enemyDirection.size() - 1; i >= 0; i--) {
            if (direction == 0) {
                if (new Rectangle(230, 350, 20, 100).intersects(new Rectangle(enemyX.get(i), enemyY.get(i), 100, 100))) {
                    enemyDirection.remove(i);
                    colorMatrixs.remove(i);
                    enemyX.remove(i);
                    enemyY.remove(i);
                    score += 10;
                }
            }
            if (direction == 1) {
                if (new Rectangle(250, 330, 100, 20).intersects(new Rectangle(enemyX.get(i), enemyY.get(i), 100, 100))) {
                    enemyDirection.remove(i);
                    colorMatrixs.remove(i);
                    enemyX.remove(i);
                    enemyY.remove(i);
                    score += 10;
                }
            }
            if (direction == 2) {
                if (new Rectangle(350, 350, 20, 100).intersects(new Rectangle(enemyX.get(i), enemyY.get(i), 100, 100))) {
                    enemyDirection.remove(i);
                    colorMatrixs.remove(i);
                    enemyX.remove(i);
                    enemyY.remove(i);
                    score += 10;
                }
            }
            if (direction == 3) {
                if (new Rectangle(250, 450, 100, 20).intersects(new Rectangle(enemyX.get(i), enemyY.get(i), 100, 100))) {
                    enemyDirection.remove(i);
                    colorMatrixs.remove(i);
                    enemyX.remove(i);
                    enemyY.remove(i);
                    score += 10;
                }
            }
        }
    }

    public void play(String sound) {
        try {
            AudioPlayer.player.start(new AudioStream(getClass().getResourceAsStream(sound)));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    private class JP extends JPanel {

        public JP() {
            setBackground(Color.black);
        }

        public void paint(Graphics g) {
            super.paint(g);//allows for painting and drawing to take place in the frame we have created

            for (int v = 0; v < 5; v++) {
                drawblock(g, 250, 150 + 100 * v);

//this for loop is for drawing multiple bricks horizontally
            }
            for (int v = 0; v < 5; v++) {
                drawblock(g, 50 + 100 * v, 350);
//this for loop is for drawing multiple bricks vertically

            }
            drawMatrix(g, 255 + x, 360, currentImage); // calls the method for drawing the main character

            for (int i = 0; i < colorMatrixs.size(); i++) {
                drawMatrix(g, enemyX.get(i), enemyY.get(i), colorMatrixs.get(i));
            }
//the following comments are just tests to see if the code works

// g.setColor(red);
// g.drawRect(255 + x,360,100,100);
// g.setColor(blue);
// g.drawRect(255, y1, 100, 100);
// g.fillRect(350, 350, 10, 10);
// g.fillRect(240, 350, 10, 10);
// g.fillRect(250, 450, 10, 10);
//g.fillRect(350, 350, 10, 10);
            for (int i = enemyX.size() - 1; i >= 0; i--) {
                if (new Rectangle(255 + x, 360, 100, 100).intersects(new Rectangle(enemyX.get(i), enemyY.get(i), 100, 100))) {
                    enemyX.remove(i);
                    enemyY.remove(i);
                    enemyDirection.remove(i);
                    colorMatrixs.remove(i);
                    //System.exit(0);
                     enter = JOptionPane.showInputDialog(null, "Would You Like to Continue (Y/N)?");
                    if (enter.equalsIgnoreCase("Y"))
                    {
                        score = 0;
                        spaceshoot = false;
                        
                    }
                    else if (enter.equalsIgnoreCase("N"))
                    {
                        System.exit(0);
                    }
                    
                    else 
                    {
                         enter = JOptionPane.showInputDialog(null, "Would You Like to Continue (Y/N)?");
                    }
                    
                }
            }

            g.setColor(brown);

            g.drawRect(250, 450, 100, 20);
            g.drawRect(230, 350, 20, 100);
            g.drawRect(250, 330, 100, 20);
            g.drawRect(350, 350, 20, 100);

//                bulletdownX = 275;
//                bulletdownY = 450;
//                bulletleftX = 230;
//                bulletleftY = 350;
//                bulletupX = 250; 
//                bulletupY = 330;
//                bulletrightX = 350;
//                bulletrightY = 350;
            if (spaceshoot) {
                g.setColor(red);
                g.fillRect(bulletdownX, bulletdownY, 100, 20);
                g.fillRect(bulletleftX, bulletleftY, 20, 100);
                g.fillRect(bulletupX, bulletupY, 100, 20);
                g.fillRect(bulletrightX, bulletrightY, 20, 100);

                int speed = 4;

                bulletdownY += speed;
                bulletleftX -= speed;
                bulletupY -= speed;
                bulletrightX += speed;

                for (int i = enemyX.size() - 1; i >= 0; i--) {
                    if (new Rectangle(bulletdownX, bulletdownY, 100, 20).intersects(new Rectangle(enemyX.get(i), enemyY.get(i), 100, 100))) {
                        enemyX.remove(i);
                        enemyY.remove(i);
                        enemyDirection.remove(i);
                        colorMatrixs.remove(i);
                        score += 10;
                    } else if (new Rectangle(bulletleftX, bulletleftY, 20, 100).intersects(new Rectangle(enemyX.get(i), enemyY.get(i), 100, 100))) {
                        enemyX.remove(i);
                        enemyY.remove(i);
                        enemyDirection.remove(i);
                        colorMatrixs.remove(i);
                        score += 10;
                    } else if (new Rectangle(bulletupX, bulletupY, 100, 20).intersects(new Rectangle(enemyX.get(i), enemyY.get(i), 100, 100))) {
                        enemyX.remove(i);
                        enemyY.remove(i);
                        enemyDirection.remove(i);
                        colorMatrixs.remove(i);
                        score += 10;
                    } else if (new Rectangle(bulletrightX, bulletrightY, 20, 100).intersects(new Rectangle(enemyX.get(i), enemyY.get(i), 100, 100))) {
                        enemyX.remove(i);
                        enemyY.remove(i);
                        enemyDirection.remove(i);
                        colorMatrixs.remove(i);
                        score += 10;
                    }

                }

            }

//
// g.drawRect(255, y1, 100, 100);
// g.setColor(red);
// g.drawRect(255 + x, 360, 100, 100);
            if (ticks % (FPS * 10) == 0) {
                velX++;
                velY++;
            }
            if (ticks % (FPS * 3) == 0) {
                Random rnd = new Random();
                int x3 = rnd.nextInt(4);
                System.out.println(x3);
// 0 = coming from the left
// 1 = coming from the above
// 2 = coming from the right
// 3 = coming from the below
                if (x3 == 0) {
                    colorMatrixs.add(enemyfromleft);
                    enemyX.add(0);
                    enemyY.add(250 + 110);
                    enemyDirection.add(0);

                } else if (x3 == 1) {
                    colorMatrixs.add(enemyfromup);
                    enemyX.add(255);
                    enemyY.add(0);
                    enemyDirection.add(1);

                } else if (x3 == 2) {
                    colorMatrixs.add(enemyfromright);
                    enemyX.add(700);
                    enemyY.add(250 + 110);
                    enemyDirection.add(2);
                } else if (x3 == 3) {
                    colorMatrixs.add(enemyfromdown);
                    enemyX.add(255);
                    enemyY.add(900);
                    enemyDirection.add(3);

                }
            }

            g.setColor(white);
            g.setFont(font1);
            g.drawString("Score:" + score, 50, 200);

        }//close paint method

        public void drawblock(Graphics g, int xc, int yc) {

//draws the basis of the block in lime
            g.setColor(lime);
            g.fillRect(xc, yc, 100, 100);

//the following sets the color to brown using an RGB value
            Color brown = new Color(139, 69, 19);
            g.setColor(brown);

//the following are for the brown dots and lines on the lime bricks 
            g.fillRect(xc + 5, yc + 5, 5, 5);
            g.fillRect(xc + 50, yc + 50, 5, 5);
            g.fillRect(xc + 90, yc + 5, 5, 5);
            g.fillRect(xc + 5, yc + 90, 5, 5);
            g.fillRect(xc + 90, yc + 90, 5, 5);
            g.drawLine(xc, yc, xc + 98, yc);
            g.drawLine(xc, yc, xc, yc + 98);
        }

        public void drawMatrix(Graphics g, int xcoord, int ycoord, Color[][] matrix) {
            rectMaincharacter = new Rectangle(xcoord, ycoord, 100, 100);

            int pixel = 10;
            int x = xcoord;
            int y = ycoord;
            for (int a = 0; a < matrix.length; a++) {
                for (int b = 0; b < matrix[a].length; b++) {
                    if (matrix[a][b] != lime) {
                        g.setColor(matrix[a][b]);
                        g.fillRect(x, y, pixel, pixel);
                    }
                    x += pixel;
                }
                x = xcoord;

                y += pixel; // goes down the matrix...
            }

        }
    }

    private class StartMenu extends JPanel {

        public StartMenu() {
            setBackground(black);

            JButton button = new JButton("Button");

            button.setPreferredSize(new Dimension(200, 30));

            button.setBounds(50, 85, 200, 30);

            button.setFont(new Font("Courier", 1, 12));

            button.setForeground(java.awt.Color.white);

            button.setBorder(BorderFactory.createEmptyBorder());

            button.setContentAreaFilled(false);

            button.setFocusPainted(false);

            button.addActionListener(new java.awt.event.ActionListener() {

                @Override

                public void actionPerformed(ActionEvent e) {

                    System.out.println("Test");
                }
            });

            this.add(button);

        }

    }

}
