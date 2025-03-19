import javax.swing.*;
import java.awt.*;

/**
 * Write a description of class welcome here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class home
{
   JFrame  frame = new JFrame();
   JLabel welcomeLabel = new JLabel("chattr");
   
   home(String userID)
   {
        welcomeLabel.setBounds(0,0,200,35);
        welcomeLabel.setFont(new Font(null, Font.PLAIN,25));
        welcomeLabel.setText("Hello,    " + userID);
        
        frame.add(welcomeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }
   
}

