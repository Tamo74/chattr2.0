import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.border.LineBorder;

public class postManage implements ActionListener {
    JFrame frame = new JFrame("Chattr");
    JTextArea postTextArea = new JTextArea();
    JButton postButton = new JButton("Post");
    JPanel topPanel = new JPanel();
    JPanel navbarPanel = new JPanel();
    JButton profileButton = new JButton("Profile");
    JButton searchButton = new JButton("Search");
    JButton addPostButton = new JButton("Add Post");
    JButton homeButton = new JButton("Home"); // New Home button
    JLabel messageLabel = new JLabel();

    static ArrayList<Post> posts = new ArrayList<>();
    String username;

    static final String FILE_NAME = "post.txt";

    public postManage(String username) {
        this.username = username;

        // Text Area for entering the post
        postTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        postTextArea.setEditable(true);
        
        JScrollPane scrollPane = new JScrollPane(postTextArea);
        scrollPane.setPreferredSize(new Dimension(300, 500));
        
        postButton.setFocusable(false);
        postButton.addActionListener(this);

        // Top Panel for post creation
        topPanel.setLayout(new BorderLayout());
        topPanel.add(scrollPane, BorderLayout.CENTER);
        topPanel.add(postButton, BorderLayout.SOUTH);

        // Message Label for post status
        messageLabel.setForeground(Color.GRAY);
        topPanel.add(messageLabel, BorderLayout.NORTH);

        // Navbar Panel for navigation buttons
        navbarPanel.setBorder(new LineBorder(Color.BLACK, 2));
        navbarPanel.setLayout(new GridLayout(1, 4, 10, 0)); // 1 row, 4 columns, 10px horizontal gap

        profileButton.setFocusable(false);
        profileButton.addActionListener(this);
        navbarPanel.add(profileButton);

        searchButton.setFocusable(false);
        searchButton.addActionListener(this);
        navbarPanel.add(searchButton);

        addPostButton.setFocusable(false);
        addPostButton.addActionListener(this);
        navbarPanel.add(addPostButton);

        homeButton.setFocusable(false); // Home button setup
        homeButton.addActionListener(this);
        navbarPanel.add(homeButton);

        // Frame setup
        frame.setLayout(new BorderLayout());
        frame.add(topPanel, BorderLayout.CENTER);
        frame.add(navbarPanel, BorderLayout.SOUTH);

        frame.setSize(600, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Load any saved posts
        loadPosts();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == postButton) {
            String content = postTextArea.getText().trim();

            if (content.isEmpty()) {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Post cannot be empty");
                return;
            }

            Post p = new Post(username, content);
            posts.add(0, p);
            savePost(p);
            postTextArea.setText(""); 
            messageLabel.setForeground(Color.green);
            messageLabel.setText("Post uploaded");

            displayPosts();
        } else if (e.getSource() == profileButton) {
            // Navigate to Profile
            new Profile(username, null, username); // Make sure to pass the correct parameters
        } else if (e.getSource() == searchButton) {
            // Navigate to Search
            new UserSearchApp(null); // If you're using a Swing approach, use Swing to navigate
        } else if (e.getSource() == addPostButton) {
            // Stay on Add Post
            // Optionally, you can open another add post screen if needed.
        } else if (e.getSource() == homeButton) {
            // Navigate to Home
            new Home(username, null); // Assuming you have a Home class
        }
    }

    private void displayPosts() {
        int yPosition = 250;

        // Remove previous post components
        for (Component c : frame.getContentPane().getComponents()) {
            if (c instanceof JTextArea && c != postTextArea) {
                frame.remove(c);
            }
        }

        // Display each post in a JTextArea
        for (Post p : posts) {
            JTextArea postLabel = new JTextArea(p.getUsername() + ":\n" + p.getContent());
            postLabel.setBounds(50, yPosition, 300, 80);
            postLabel.setEditable(false); 
            postLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            frame.add(postLabel);
            yPosition += 90;
        }
        frame.revalidate();
        frame.repaint();
    }

    private void savePost(Post p) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(p.toFileString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving post: " + e.getMessage());
        }
    }

    private void loadPosts() {
        posts.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                posts.add(Post.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("No previous posts found.");
        }
    }

    public static void main(String[] args) {
        new postManage("username");
    }
}
