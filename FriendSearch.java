import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class FriendSearch implements ActionListener {

    private JFrame frame;
    private JTextField searchField;
    private JButton searchButton, followButton, unfollowButton;
    private JLabel messageLabel, searchResultLabel;
    private String currentUser;
    private HashMap<String, Friends> friendsList;

    public FriendSearch(String currentUser, HashMap<String, Friends> friendsList) {
        this.currentUser = currentUser;
        this.friendsList = friendsList;

        frame = new JFrame("Friend Search");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel searchLabel = new JLabel("Enter username to search:");
        searchLabel.setBounds(50, 50, 200, 25);
        frame.add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(200, 50, 150, 25);
        frame.add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(125, 90, 150, 30);
        searchButton.addActionListener(this);
        frame.add(searchButton);

        messageLabel = new JLabel("");
        messageLabel.setBounds(50, 200, 300, 25);
        frame.add(messageLabel);

        searchResultLabel = new JLabel("");
        searchResultLabel.setBounds(50, 130, 300, 25);
        frame.add(searchResultLabel);

        followButton = new JButton("Follow");
        followButton.setBounds(50, 160, 100, 30);
        followButton.addActionListener(this);
        followButton.setEnabled(false); // Disabled by default
        frame.add(followButton);

        unfollowButton = new JButton("Unfollow");
        unfollowButton.setBounds(150, 160, 100, 30);
        unfollowButton.addActionListener(this);
        unfollowButton.setEnabled(false); // Disabled by default
        frame.add(unfollowButton);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String usernameToSearch = searchField.getText().trim();

            if (usernameToSearch.isEmpty()) {
                messageLabel.setText("Please enter a username.");
                return;
            }

            // Search for the user in the friends list
            if (friendsList.containsKey(usernameToSearch)) {
                // Display the search result
                Friends foundUser = friendsList.get(usernameToSearch);
                searchResultLabel.setText("Found user: " + foundUser.getUsername());

                // Check if current user is already following
                if (foundUser.getFollowers().contains(currentUser)) {
                    unfollowButton.setEnabled(true);
                    followButton.setEnabled(false);
                } else {
                    followButton.setEnabled(true);
                    unfollowButton.setEnabled(false);
                }

                messageLabel.setText("");
            } else {
                searchResultLabel.setText("User not found.");
                messageLabel.setText("");
                followButton.setEnabled(false);
                unfollowButton.setEnabled(false);
            }
        }

        // Follow button clicked
        if (e.getSource() == followButton) {
            String usernameToFollow = searchField.getText().trim();
            Friends foundUser = friendsList.get(usernameToFollow);
            Friends currentUserFriend = friendsList.get(currentUser);

            currentUserFriend.follow(foundUser);
            messageLabel.setText("You followed " + foundUser.getUsername());
            followButton.setEnabled(false);
            unfollowButton.setEnabled(true);
        }

        // Unfollow button clicked
        if (e.getSource() == unfollowButton) {
            String usernameToUnfollow = searchField.getText().trim();
            Friends foundUser = friendsList.get(usernameToUnfollow);
            Friends currentUserFriend = friendsList.get(currentUser);

            currentUserFriend.unfollow(foundUser);
            messageLabel.setText("You unfollowed " + foundUser.getUsername());
            followButton.setEnabled(true);
            unfollowButton.setEnabled(false);
        }
    }
}
