import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Write a description of class friendManager here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class friendManager implements ActionListener
{
    private JFrame frame;
    private JTextField followField;
    private JButton followButton, unfollowButton, showFollowingButton;
    private String currentUser;
    
     public friendManager(String currentUser) 
     {
        this.currentUser = currentUser;
        
        frame = new JFrame("Chattr");
        followField = new JTextField();
        followButton = new JButton("Follow");
        unfollowButton = new JButton("Unfollow");
        showFollowingButton = new JButton("Following");
        
        followField.setBounds(50, 50, 200, 30);
        followButton.setBounds(50, 100, 100, 30);
        unfollowButton.setBounds(160, 100, 100, 30);
        showFollowingButton.setBounds(50, 150, 200, 30);

        followButton.addActionListener(this);
        unfollowButton.addActionListener(this);
        showFollowingButton.addActionListener(this);

        frame.add(followField);
        frame.add(followButton);
        frame.add(unfollowButton);
        frame.add(showFollowingButton);

        frame.setSize(320, 250);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String userToFollow = followField.getText().trim();
        if (userToFollow.isEmpty()) return;

        if (e.getSource() == followButton) {
            followManage.follow(currentUser, userToFollow);
            JOptionPane.showMessageDialog(frame, "Followed " + userToFollow);
        } else if (e.getSource() == unfollowButton) {
            followManage.unfollow(currentUser, userToFollow);
            JOptionPane.showMessageDialog(frame, "Unfollowed " + userToFollow);
        } else if (e.getSource() == showFollowingButton) {
            JOptionPane.showMessageDialog(frame, "Following: " + followManage.getFollowing(currentUser));
        }
    }
    }

