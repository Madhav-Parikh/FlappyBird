package flappyBird.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.ColorUIResource;

import flappyBird.FlappyBird;

public class Home extends JPanel implements ActionListener{
    
    private JLabel title;
    private JButton playButton, helpButton, quitButton;
    private BoxLayout layout;
    private Font font1, font2;

    public Home() {
        this.setName("Home");

        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);
        this.setBackground(new ColorUIResource(178, 255, 255));

        font1 = new Font("Arial", Font.BOLD, 32);
        font2 = new Font("Arial", Font.PLAIN, 24);

        final Dimension componentSize = new Dimension(300, 90);
        final Dimension boxSize = new Dimension(0, 50);
        
        this.add(Box.createRigidArea(boxSize));
        
        title = new JLabel("Flappy Bird", JLabel.CENTER);
        title.setFont(font1);
        title.setMaximumSize(componentSize);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        this.add(title);
        
        this.add(Box.createRigidArea(boxSize));

        playButton = new JButton("Play");
        playButton.setFont(font2);
        playButton.setMaximumSize(componentSize);
        playButton.setBackground(Color.ORANGE);
        playButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        playButton.addActionListener(this);
        this.add(playButton);

        this.add(Box.createRigidArea(boxSize));

        helpButton = new JButton("Help");
        helpButton.setFont(font2);
        helpButton.setMaximumSize(componentSize);
        helpButton.setBackground(Color.ORANGE);
        helpButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        helpButton.addActionListener(this);
        this.add(helpButton);

        this.add(Box.createRigidArea(new Dimension(0,50)));
        
        quitButton = new JButton("Quit");
        quitButton.setFont(font2);
        quitButton.setMaximumSize(componentSize);
        quitButton.setBackground(Color.ORANGE);
        quitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        quitButton.addActionListener(this);
        this.add(quitButton);

        this.add(Box.createRigidArea(boxSize));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton) {
            FlappyBird.flappyBird = new FlappyBird();
        }
        if(e.getSource() == helpButton) {
            new Help();
        }
        if(e.getSource() == quitButton) {
            System.exit(0);
        }
    }
}
