/** 
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Home page class to display home feed.
 *
 * @author group 6
 * @version 1.0
 
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
            ImageIcon icon = new ImageIcon("icon" + (i + 1) + ".png"); // Replace with actual icon paths
            Image img = icon.getImage();
            Image resizedImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Resize to 40x40 pixels
            iconLabel.setIcon(new ImageIcon(resizedImg));
            iconLabel.setBorder(new EmptyBorder(0, 25, 0, 0)); // Add 10px padding to the left

            // Add mouse listener to handle click events
            final int index = i + 1;
            iconLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (index == 2) {
                        runClass("Search");
                    }
                    else if (index == 3) 
                    {
                        runClass("post");
                    } else if (index == 4) 
                    {
                        runClass("Friends");
                    }
                }
            });

            bottomPanel.add(iconLabel);
        }
        
        frame.add(topPanel);
        frame.add(bottomPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    // Method to run the specified class
    private void runClass(String className) {
    try {
        Class<?> clazz = Class.forName(className);
        clazz.getDeclaredMethod("main", String[].class).invoke(null, (Object) new String[]{});
    } catch (Exception e) {
        e.printStackTrace();
        }
    }

}
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Home page class to display home feed.
 *
 * @author group 6
 * @version 1.0
 */
public class home implements ActionListener{
    JFrame frame = new JFrame();
    JLabel welcomeLabel = new JLabel("chattr");
    JPanel topPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
   

    home(String userID) {
        welcomeLabel.setBounds(10, 10, 200, 35);
        welcomeLabel.setFont(new Font(null, Font.PLAIN, 25));
        welcomeLabel.setText("Hello, " + userID);
        
        topPanel.setBounds(1, 1, 400, 50);
        topPanel.setBorder(new LineBorder(Color.BLACK, 2));
        topPanel.setLayout(null);
        topPanel.add(welcomeLabel);

        bottomPanel.setBounds(1, 320, 400, 50);
        bottomPanel.setBorder(new LineBorder(Color.BLACK, 2));
        bottomPanel.setLayout(new GridLayout(1, 4, 10, 0)); // 1 row, 4 columns, 10px horizontal gap

        
        
        // Adding icon buttons to the bottom panel
        for (int i = 0; i < 4; i++) {
            JButton iconButton = new JButton();
            ImageIcon icon = new ImageIcon("icon" + (i + 1) + ".png"); // Replace with actual icon paths
            Image img = icon.getImage();
            Image resizedImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Resize to 40x40 pixels
            iconButton.setIcon(new ImageIcon(resizedImg));
            iconButton.setBorder(new EmptyBorder(0, 10, 0, 10)); // Add padding
            iconButton.setContentAreaFilled(false); // Makes the button background transparent
            iconButton.setBorderPainted(false); // Removes button border
            
            // Adding action listeners for each button
            final int buttonIndex = i;
            iconButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    handleButtonClick(buttonIndex);
                }
            });

            bottomPanel.add(iconButton);
        }
        
        frame.add(topPanel);
        frame.add(bottomPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {}

    // Method to handle button clicks
    private void handleButtonClick(int buttonIndex) {
        switch (buttonIndex) {
            case 0:
                System.out.println("Home button clicked");
                break;
            case 1:
                System.out.println("Friends button clicked");
                break;
            case 2:
                System.out.println("Messages button clicked");
                break;
            case 3:
                System.out.println("Profile button clicked");
                break;
            default:
                System.out.println("Unknown button clicked");
        }
    }
}



