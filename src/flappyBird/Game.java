package flappyBird;

import flappyBird.window.MainWindow;

public class Game implements Runnable{

    private void initWindow(String title) {
        MainWindow window = new MainWindow(title);        
        window.setVisible(true);
    }

    public Game(String title) {
        initWindow(title);
    }

    @Override
    public void run() {
        
    }
}
