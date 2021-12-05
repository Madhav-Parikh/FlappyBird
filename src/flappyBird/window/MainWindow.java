package flappyBird.window;

import java.awt.Color;

import javax.swing.JFrame;

// import java.awt.Toolkit;
// import java.awt.GraphicsDevice;
// import java.awt.GraphicsEnvironment;

public class MainWindow extends JFrame {

    // public static int HEIGHT, WIDTH;

    private String title;
    // private GraphicsEnvironment graphics;
    // private GraphicsDevice device;

    private void windowConfigs() {
        // graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        // device = graphics.getDefaultScreenDevice();
        this.setTitle(title);
        this.setSize(700, 500);
        // this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.requestFocus();
        // this.setUndecorated(true);
        this.setResizable(false);
        this.setBackground(Color.cyan);
        // device.setFullScreenWindow(this);
        // HEIGHT = this.getHeight();
        // WIDTH = this.getWidth();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public MainWindow(String title) {
        this.title = title;
        windowConfigs();

        Home home = new Home();
        this.add(home);

    }
}
