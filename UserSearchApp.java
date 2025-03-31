import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserSearchApp extends JFrame implements ActionListener {
    private List<String> allUsers = new ArrayList<>();
    private DefaultListModel<String> listModel;
    private JList<String> userList;
    private String currentUser;
    private ID id;
    
    private JButton profileButton = new JButton("Profile");
    private JButton searchButton = new JButton("Search");
    private JButton addPostButton = new JButton("Add Post");
    private JButton homeButton = new JButton("Home");

    public UserSearchApp(String currentUser) {
        this.currentUser = currentUser;
        
        setTitle("User Search");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextField searchField = new JTextField(20);
        listModel = new DefaultListModel<>();
        userList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(userList);
        JPanel navbarPanel = new JPanel();

       
        loadUsersFromFile();

        
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        
        navbarPanel.setBorder(new LineBorder(Color.BLACK, 2));
        navbarPanel.setLayout(new GridLayout(1, 4, 10, 0)); 

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

        
        add(searchField, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);
        add(navbarPanel, BorderLayout.SOUTH);

        
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterUsers(searchField.getText().toLowerCase());
            }
        });

        userList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedUser = userList.getSelectedValue();
                if (selectedUser != null) {
                    showFollowDialog(selectedUser);
                }
            }
        });

        setVisible(true);
    }

    private void loadUsersFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length > 0) {
                    allUsers.add(parts[0].trim()); // Store only the username
                    listModel.addElement(parts[0].trim());
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading users", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filterUsers(String query) {
        listModel.clear();
        for (String user : allUsers) {
            if (user.toLowerCase().contains(query)) {
                listModel.addElement(user);
            }
        }
    }

    private void showFollowDialog(String selectedUser) {
        if (currentUser.equals(selectedUser)) {
            JOptionPane.showMessageDialog(this, "You can't follow yourself!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFrame followFrame = new JFrame("Follow User");
        followFrame.setSize(250, 150);
        followFrame.setLayout(new FlowLayout());
        followFrame.setLocationRelativeTo(this);

        JLabel label = new JLabel("Follow " + selectedUser + "?");
        JButton followButton = new JButton("Follow");
        JButton unfollowButton = new JButton("Unfollow");

        followButton.addActionListener(e -> {
            followManage.follow(currentUser, selectedUser);
            JOptionPane.showMessageDialog(followFrame, "You are now following " + selectedUser + "!");
            followFrame.dispose();
        });

        unfollowButton.addActionListener(e -> {
            followManage.unfollow(currentUser, selectedUser);
            JOptionPane.showMessageDialog(followFrame, "You have unfollowed " + selectedUser + "!");
            followFrame.dispose();
        });

        followFrame.add(label);
        followFrame.add(followButton);
        followFrame.add(unfollowButton);
        followFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == profileButton) {
            new Profile(currentUser, id, currentUser);
        } else if (e.getSource() == searchButton) {
            new UserSearchApp(currentUser);
        } else if (e.getSource() == addPostButton) {
            new postManage(currentUser);
        } else if (e.getSource() == homeButton) {
            new Home(currentUser, id);
        }
    }
}
