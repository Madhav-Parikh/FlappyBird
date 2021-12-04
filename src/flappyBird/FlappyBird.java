package flappyBird;

import java.awt.Graphics;

import java.awt.*;

import javax.swing.JFrame;

import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlappyBird implements ActionListener {

    public static FlappyBird flappyBird;
    public Renderer renderer;
    public Rectangle bird;

    public static int HEIGHT, WIDTH;

    private GraphicsEnvironment graphics;
    private GraphicsDevice device;

    public FlappyBird() {
        graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        device = graphics.getDefaultScreenDevice();
        JFrame jframe = new JFrame();
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
        bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
        jframe.setVisible(true);
        timer.start();
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        renderer.repaint();
    }

    public void repaint(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.orange);
        g.fillRect(0, HEIGHT - 150, WIDTH, 150);

        g.setColor(Color.green);
        g.fillRect(0, HEIGHT - 150, WIDTH, 20);

        g.setColor(Color.yellow);
        g.fillRect(bird.x, bird.y, bird.width, bird.height);
    }
    // public static void main(String[] args) throws Exception {

    // flappyBird = new FlappyBird();
    // }
}
