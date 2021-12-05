package flappyBird.window;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.plaf.ColorUIResource;

public class Help {

    private final String helpContent = "Welcome to Flappy Bird! Hope you have come ready with your enthusiasm sky high, because this game will challenge you to the maximum!\n\nAlthough the way to play it is fairly simple, you just have to Press Space or Click your Left Mouse Button to make the bird fly. Sounds extremely simple, right?\nHowever, since us humans don't have an experience in flying by ourselves, especially near those dangerous pipes, playing the game isn't always as easy as it looks.\n\nHowever, anything challenging is always fun. So enjoy flying. You deserve it.\n\nEvery single time you manage to fly past the pipes, your score increases by one.\nAlthough, if you aren't able to judge your flight correctly and hit one of those pipes, the game ends there.\n\nWe'd love to know how well (or poorly :P) you've performed.\nShare your highscores with us on our socials! :D\n\nCreated and developed by -\nJay Nakum (https://github.com/JayNakum)\nMadhav Parikh (https://github.com/Madhav-Parikh)";

    private JFrame frame;

    public Help() {
        frame = new JFrame("Help");
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.requestFocus();
        frame.setBackground(Color.cyan);

        Font font = new Font("Arial", Font.PLAIN, 18);

        JTextArea label = new JTextArea(helpContent);
        label.setFont(font);
        label.setLineWrap(true);
        label.setWrapStyleWord(true);
        label.setEditable(false);
        label.setBackground(new ColorUIResource(178, 255, 255));

        frame.add(label);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
