import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePlay extends JPanel implements ActionListener, KeyListener {
    private int[] snakexlength = new int[750];
    private int[] snakeylength = new int[750];
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon leftmouth;
    private ImageIcon downmouth;

    private int lengthofsnake = 3;

    private Timer timer;
    private int delay = 100;

    private ImageIcon snakeimage;

    private int[] enemxpos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375,
            400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};

    private int[] enemypos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375,
            400, 425, 450, 475, 500, 525, 550, 575, 600, 625};

    private ImageIcon enemyimage;

    private Random random = new Random();

    private int moves = 0;

    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);

    private ImageIcon tittleImage;
    public GamePlay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        //for default position of a snake if game
        // has started for the first time
        if (moves == 0) {
            snakexlength[2] = 50;
            snakexlength[1] = 75;
            snakexlength[0] = 100;

            snakeylength[2] = 100;
            snakeylength[1] = 100;
            snakeylength[0] = 100;
        }

        // draw tittle image border
        g.setColor(Color.white);
        g.drawRect(24, 10, 851,55);

        // draw the tittle image
        tittleImage = new ImageIcon("snaketitle.jpg");
        tittleImage.paintIcon(this, g, 25, 11 );

        //for the score
        g.setColor(Color.yellow);
        g.drawString(Integer.toString(lengthofsnake - 3), 800, 40);

        //draw border for gameplay
        g.setColor(Color.WHITE);
        g.fillRect(25,74,851,577);

        //draw background for the gameplay
        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575);
        rightmouth = new ImageIcon("rightmouth.png");
        rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);

        for(int a = 0; a < lengthofsnake; a++) {
            if (a == 0 && right) {
                rightmouth = new ImageIcon("rightmouth.png");
                rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
            }

            if (a == 0 && left) {
                leftmouth = new ImageIcon("leftmouth.png");
                leftmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
            }

            if (a == 0 && down) {
                downmouth = new ImageIcon("downmouth.png");
                downmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
            }

            if (a == 0 && up) {
                upmouth = new ImageIcon("upmouth.png");
                upmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
            }

            if (a != 0) {
                snakeimage = new ImageIcon("snakeimage.png");
                snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }

            for (int i = 1; i < snakexlength.length; i++) {
                if (snakexlength[0] == snakexlength[i] && snakeylength[0] == snakeylength[i]) {
                    this.repaint();
                    g.setColor(Color.black);
                    g.fillRect(25, 75, 850, 575);
                    g.setColor(Color.RED);
                    Font f = new Font("Arial", Font.BOLD, 100);
                    g.setFont(f);
                    timer.stop();
                    g.drawString("GAME OVER", 80, 320);
                    /*
                    Button b = new Button();
                    b.setLabel("REPLAY");
                    b.setBounds(220,250, 250, 300);
                    this.add(b);
                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            //replay game when we
                        }
                    });
                */
                }
            }
        }

        enemyimage = new ImageIcon("enemy.png");
        //set enemy at random position
        if(enemxpos[xpos] == snakexlength[0] && enemypos[ypos] == snakeylength[0]) {
            lengthofsnake++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }

        enemyimage.paintIcon(this, g, enemxpos[xpos], enemypos[ypos]);
        g.dispose();

    }

    public  void actionPerformed(ActionEvent var1) {
        timer.start();
        //for moving
        if(right) {
            for(int r= lengthofsnake - 1; r >= 0; r--) {
                snakeylength[r + 1] = snakeylength[r];
            }
            for(int r = lengthofsnake; r >= 0; r--) {
                if (r <= 0) {
                    snakexlength[r] = snakexlength[r] + 25;
                } else {
                    snakexlength[r] = snakexlength[r -1];
                }
                if(snakexlength[r] > 850) {
                    snakexlength[r] = 25;
                }
            }
            repaint();
        }
        if(left) {
            for(int r= lengthofsnake - 1; r >= 0; r--) {
                snakeylength[r + 1] = snakeylength[r];
            }
            for(int r = lengthofsnake; r >= 0; r--) {
                if (r == 0) {
                    snakexlength[r] = snakexlength[r] - 25;
                } else {
                    snakexlength[r] = snakexlength[r -1];
                }
                if(snakexlength[r] < 25) {
                    snakexlength[r] = 850;
                }
            }
            repaint();
        }

        if(down) {
            for(int r= lengthofsnake - 1; r >= 0; r--) {
                snakexlength[r + 1] = snakexlength[r];
            }
            for(int r = lengthofsnake; r >= 0; r--) {
                if (r == 0) {
                    snakeylength[r] = snakeylength[r] + 25;
                } else {
                    snakeylength[r] = snakeylength[r -1];
                }
                if(snakeylength[r] > 625) {
                    snakeylength[r] = 75;
                }
            }
            repaint();
        }
        if(up) {
            for(int r= lengthofsnake - 1; r >= 0; r--) {
                snakexlength[r + 1] = snakexlength[r];
            }
            for(int r = lengthofsnake; r >= 0; r--) {
                if (r == 0) {
                    snakeylength[r] = snakeylength[r] -  25;
                } else {
                    snakeylength[r] = snakeylength[r -1];
                }
                if(snakeylength[r] < 75) {
                    snakeylength[r] = 625;
                }
            }
            repaint();
        }
    }
    //to know which key is pressed
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moves++;
            right = true;
            if(!left) {
                right = true;
            } else {
                right = false;
                left = true;
            }
            down = false;
            up = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
            moves++;
            if(!right) {
                left = true;
            } else {
                left = false;
                right = true;
            }
            down = false;
            up = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            moves++;
            up = true;
            if(!down) {
                up = true;
            } else {
                up = false;
                down = true;
            }
            right = false;
            left = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            moves++;
            down = true;
            if (!up) {
                down = true;
            } else {
                up = true;
                down = false;
            }
            right = false;
            left = false;
        }

    }

    public void keyTyped(KeyEvent var1) {

    }


    public void keyReleased(KeyEvent var1) {

    }
}