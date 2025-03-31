import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.HashSet;

public class Profile extends JFrame {
    private String username;
    private ID id;
    private JLabel usernameLabel, followersLabel, followingLabel, postCountLabel;
    private JTextArea postsArea;
    private JButton followButton;
    private boolean isFollowing;

    // Navbar components
    private JPanel navbarPanel = new JPanel();
    private JButton profileButton = new JButton("Profile");
    private JButton searchButton = new JButton("Search");
    private JButton addPostButton = new JButton("Add Post");

    public Profile(String username, ID id, String currentUser) {
        this.id = id;
        this.username = username;

        setTitle(username + "'s Profile");
        setSize(400, 500);
        setLayout(new BorderLayout());

        // Top Panel (Profile Details)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 1));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        usernameLabel = new JLabel("Username: " + username, SwingConstants.CENTER);
        followersLabel = createLinkLabel("Followers: ", id.getFollowers(username).size(), currentUser);
        followingLabel = createLinkLabel("Following: ", id.getFollowing(username).size(), currentUser);
        postCountLabel = new JLabel("Posts: " + id.UserPostCount(username), SwingConstants.CENTER);

        followButton = new JButton();
        updateFollowButton(currentUser);
        followButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleFollow(currentUser);
            }
        });

        topPanel.add(usernameLabel);
        topPanel.add(followersLabel);
        topPanel.add(followingLabel);
        topPanel.add(postCountLabel);
        topPanel.add(followButton);

        // Post area
        postsArea = new JTextArea();
        postsArea.setEditable(false);
        loadPosts(username);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(postsArea), BorderLayout.CENTER);

        // Navbar Panel (at the bottom)
        setupNavbar(currentUser);

       setVisible(true);
    }

    private JLabel createLinkLabel(String text, int count, String currentUser) {
        JLabel label = new JLabel("<html>" + text + "<a href='#'>" + count + "</a></html>", SwingConstants.CENTER);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showUserList(id.getFollowers(username), text, currentUser);
            }
        });
        return label;
    }

    private void showUserList(Set<String> users, String title, String currentUser) {
        JFrame listFrame = new JFrame(title);
        listFrame.setSize(300, 400);
        listFrame.setLayout(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String user : users) {
            listModel.addElement(user);
        }

        JList<String> userList = new JList<>(listModel);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedUser = userList.getSelectedValue();
                if (selectedUser != null) {
                    new Profile(selectedUser, id, currentUser);
                    listFrame.dispose();
                }
            }
        });

        listFrame.add(new JScrollPane(userList), BorderLayout.CENTER);
        listFrame.setVisible(true);
    }

    private void loadPosts(String username) {
        Set<String> userPosts = new HashSet<>(id.getPosts(username));
        if (userPosts != null) {
            for (String post : userPosts) {
                postsArea.append(post + "\n------------------\n");
            }
        } else {
            postsArea.append("No posts available.\n");
        }
    }

    private void updateFollowButton(String currentUser) {
        if (currentUser.equals(username)) {
            followButton.setVisible(false);
            return;
        }
        isFollowing = id.getFollowing(currentUser).contains(username);
        followButton.setText(isFollowing ? "Unfollow" : "Follow");
    }

    private void toggleFollow(String currentUser) {
        if (isFollowing) {
            id.unfollow(currentUser, username);
        } else {
            id.follow(currentUser, username);
        }
        updateFollowButton(currentUser);
        followersLabel.setText("<html>Followers: <a href='#'>" + id.getFollowers(username).size() + "</a></html>");
    }

    private void setupNavbar(String currentUser) {
        navbarPanel.setLayout(new GridLayout(1, 3, 10, 0)); // 1 row, 3 columns
        navbarPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        profileButton.setFocusable(false);
        profileButton.addActionListener(e -> new Profile(currentUser, id, currentUser));
        navbarPanel.add(profileButton);

        searchButton.setFocusable(false);
        searchButton.addActionListener(e -> new UserSearchApp(id)); // Assuming you are using the Swing version of UserSearchApp
        navbarPanel.add(searchButton);

        addPostButton.setFocusable(false);
        addPostButton.addActionListener(e -> new postManage(currentUser)); // Assuming postManage is defined elsewhere
        navbarPanel.add(addPostButton);

        add(navbarPanel, BorderLayout.SOUTH); // Adding the navbar at the bottom
    }

    public static void main(String[] args) {
        ID id = new ID();
        new Profile("testUser", id, "currentUser");
    }
}
