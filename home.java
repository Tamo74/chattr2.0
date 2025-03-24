import javax.swing.*;
import java.awt.*;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Write a description of class welcome here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class home
{
   JFrame frame = new JFrame();
   JLabel welcomeLabel = new JLabel("chattr");
   JPanel topPanel = new JPanel();
   JPanel bottomPanel = new JPanel();
   
   home(String userID)
   {
        welcomeLabel.setBounds(10,10,200,35);
        welcomeLabel.setFont(new Font(null, Font.PLAIN,25));
        welcomeLabel.setText("Hello, " + userID);
        
        topPanel.setBounds(1,1,400,50);
        topPanel.setBorder(new LineBorder(Color.BLACK, 2));
        topPanel.setLayout(null);
        topPanel.add(welcomeLabel);
        

        bottomPanel.setBounds(1,320,400,50);
        bottomPanel.setBorder(new LineBorder(Color.BLACK, 2));
        bottomPanel.setLayout(new GridLayout(1, 4, 10, 0)); // 1 row, 4 columns, 10px horizontal gap
        
        // Adding icons to the bottom panel
        // Adding resized icons to the bottom panel
        for (int i = 0; i < 4; i++) {
            JLabel iconLabel = new JLabel();
            ImageIcon icon = new ImageIcon("C:\\Users\\Katherine Curtis\\OneDrive - University of Dundee\\Computing\\1st Year\\Intro to DS & A\\Final Project\\chattr2.0\\icon" + (i + 1) + ".png"); // Replace with actual icon paths
            Image img = icon.getImage();
            Image resizedImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Resize to 40x40 pixels
            iconLabel.setIcon(new ImageIcon(resizedImg));
            iconLabel.setBorder(new EmptyBorder(0, 25, 0, 0)); // Add 10px padding to the left
            bottomPanel.add(iconLabel);
        }
        
        frame.add(topPanel);
        frame.add(bottomPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }
   
}

