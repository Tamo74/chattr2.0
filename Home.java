import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;


public class Home implements ActionListener {
    private String userID;
    private ID id;

    JFrame frame = new JFrame("Chattr");
    JLabel welcomeLabel = new JLabel("Chattr");
    JPanel topPanel = new JPanel();
    JPanel navbarPanel = new JPanel();
    JButton profileButton = new JButton("Profile");
    JButton searchButton = new JButton("Search");
    JButton addPostButton = new JButton("Add Post");
    JButton homeButton = new JButton("Home");

    public Home(String username, ID id) {
        this.userID = username;
        this.id = id;

        welcomeLabel.setBounds(10, 10, 200, 35);
        welcomeLabel.setFont(new Font(null, Font.PLAIN, 25));
        welcomeLabel.setText("Hello, " + userID);

        topPanel.setBounds(1, 1, 400, 50);
        topPanel.setBorder(new LineBorder(Color.BLACK, 2));
        topPanel.setLayout(null);
        topPanel.add(welcomeLabel);

        navbarPanel.setBounds(1, 320, 400, 50);
        navbarPanel.setBorder(new LineBorder(Color.BLACK, 2));
        navbarPanel.setLayout(new GridLayout(1, 3, 10, 0)); 

        profileButton.setFocusable(false);
        profileButton.addActionListener(this);
        navbarPanel.add(profileButton);

        searchButton.setFocusable(false);
        searchButton.addActionListener(this);
        navbarPanel.add(searchButton);

        addPostButton.setFocusable(false);
        addPostButton.addActionListener(this);
        navbarPanel.add(addPostButton);

        homeButton.setFocusable(false); 
        homeButton.addActionListener(this);
        navbarPanel.add(homeButton);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(navbarPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == profileButton) {
            new Profile(userID, id, userID);
        } else if (e.getSource() == searchButton) {
            
            new UserSearchApp(userID); 
        } else if (e.getSource() == addPostButton) {
            new postManage(userID);
        }else if (e.getSource() == homeButton) {
            
            new Home(userID, null); 
        }
    }

    public static void main(String[] args) {
        ID id = new ID();
        new Home("testUser", id);
    }
}
