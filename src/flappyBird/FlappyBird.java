package flappyBird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.plaf.ColorUIResource;

public class FlappyBird implements ActionListener, KeyListener, MouseListener {

    private JFrame jframe;

    public static FlappyBird flappyBird;
    public Renderer renderer;
    public Rectangle bird;

    public int ticks, yMotion, score;
    public boolean gameOver = false, started = false;

    public Random rand;

    public ArrayList<Rectangle> columns;

    public static int HEIGHT, WIDTH;

    private GraphicsEnvironment graphics;
    private GraphicsDevice device;

    public FlappyBird() {
        graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        device = graphics.getDefaultScreenDevice();
        jframe = new JFrame("Flappy Bird");
        jframe.addKeyListener(this);
        jframe.addMouseListener(this);
        Timer timer = new Timer(20, this);
        renderer = new Renderer();
        jframe.add(renderer);
        // jframe.setSize(WIDTH, HEIGHT);
        jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jframe.setUndecorated(true);
        jframe.setResizable(false);
        device.setFullScreenWindow(jframe);
        HEIGHT = jframe.getHeight();
        WIDTH = jframe.getWidth();

        bird = new Rectangle(WIDTH / 2, HEIGHT / 2, 20, 20);
        columns = new ArrayList<Rectangle>();

        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);

        jframe.setVisible(true);
        timer.start();
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void addColumn(boolean isStart) {
        int space = 300;
        int width = 100;
        rand = new Random();
        int height = 50 + rand.nextInt(300);

        if (isStart) {
            columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
            columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
        } else {
            columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 120, width, height));
            columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
        }
    }

    public void paintColumn(Graphics g, Rectangle column) {
        g.setColor(Color.green.darker());
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    public void jump() {
        if (gameOver) {
            bird = new Rectangle(WIDTH / 2, HEIGHT / 2, 20, 20);
            columns.clear();
            yMotion = 0;
            score = 0;

            addColumn(true);
            addColumn(true);
            addColumn(true);
            addColumn(true);

            gameOver = false;
        }
        if (!started) {
            started = true;
        } else if(!gameOver) {
            if(yMotion > 0) {
                yMotion = 0;
            }
            yMotion -= 10;
        }
    }

    public void actionPerformed(ActionEvent e) {

        int speed = 10;
        ticks++;

        if (started) {

            for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);
                column.x -= speed;
            }

            if (ticks % 2 == 0 && yMotion < 15) {
                yMotion += 2;
            }

            for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);
                if (column.x + column.width < 0) {
                    columns.remove(column);

                    if (column.y == 0) {
                        addColumn(false);
                    }
                }
            }
            bird.y += yMotion;

            for (Rectangle column : columns) {
                if(column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 5 && bird.x + bird.width / 2 < column.x + column.width / 2 + 5) {
                    score += 1;
                }
                if (bird.intersects(column)) {
                    gameOver = true;
                    if(bird.x <= column.x) {
                        bird.x = column.x - bird.width;

                    } else {
                        if(column.y != 0) {
                            bird.y = column.y - bird.height;
                        } else if(bird.y < column.height) {
                            bird.y = column.height;
                        }
                    }
                }
            }
            if (bird.y > HEIGHT - 120 || bird.y < 0) {
                gameOver = true;
            }
            if (bird.y + yMotion >= HEIGHT - 120) {
                bird.y = (HEIGHT - 120) - bird.height;
            }
        }
        renderer.repaint();
    }

    public void repaint(Graphics g) {
        g.setColor(new ColorUIResource(178, 255, 255));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.orange);
        g.fillRect(0, HEIGHT - 120, WIDTH, 150);

        g.setColor(Color.green);
        g.fillRect(0, HEIGHT - 120, WIDTH, 20);

        g.setColor(Color.red);
        g.fillRect(bird.x, bird.y, bird.width, bird.height);

        for (Rectangle column : columns) {
            paintColumn(g, column);
        }

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 48));

        if (!started) {
            g.drawString("Press Space/Click to Start", (WIDTH - 550) / 2, HEIGHT / 2 - 170);
        }

        if (gameOver) {
            g.drawString("Game Over. Try Again!", (WIDTH - 470) / 2, HEIGHT / 2 - 45);
        }

        if(!gameOver && started) {
            g.drawString(String.valueOf(score), (WIDTH - 100) / 2, 100);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            jump();
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            jframe.dispose();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        jump();
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}
