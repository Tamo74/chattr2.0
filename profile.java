import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.SwingConstants;
/**
 * Write a description of class profile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class profile extends JFrame
{
    private ID id;
    private JFrame frame;
    private JLabel usernameLabel, followersLabel, followingLabel, postCountLabel;
    private JTextArea postsArea;
     public profile(String username, ID id) 
     {
         this.id =id;
        frame = new JFrame(username + "'s Profile");
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(4, 1));
        
        usernameLabel = new JLabel("Username: " + username, SwingConstants.CENTER);
        followersLabel = new JLabel("Followers: " + followManage.getFollowers(username).size(), SwingConstants.CENTER);
        followingLabel = new JLabel("Following: " + followManage.getFollowing(username).size(), SwingConstants.CENTER);

        postCountLabel = new JLabel("Posts: " + postManage.getPostCount(username), SwingConstants.CENTER);
        
        topPanel.add(usernameLabel);
        topPanel.add(followersLabel);
        topPanel.add(followingLabel);
        topPanel.add(postCountLabel);
        
        postsArea = new JTextArea();
        postsArea.setEditable(false);
        loadPosts(username);
        
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(postsArea), BorderLayout.CENTER);
        
        frame.setVisible(true);
    }
    private void loadPosts(String username) 
    {
        Set<String> post = postManage.getPosts(username);
        for (String posts : post) 
        {
            postsArea.append(post + "\n------------------\n");
        }
    }
    
}

class followManage 
{
    private static HashMap<String, Set<String>> followingMap = new HashMap<>();
    private static HashMap<String, Set<String>> followersMap = new HashMap<>();
    
    public static void follow(String currentUser, String userToFollow) 
    {
        followingMap.putIfAbsent(currentUser, new HashSet<>());
        followersMap.putIfAbsent(userToFollow, new HashSet<>());
        
        followingMap.get(currentUser).add(userToFollow);
        followersMap.get(userToFollow).add(currentUser);
    }
    
    public static void unfollow(String currentUser, String userToUnfollow) 
    {
        if (followingMap.containsKey(currentUser)) 
        {
            followingMap.get(currentUser).remove(userToUnfollow);
        }
        if (followersMap.containsKey(userToUnfollow)) 
        {
            followersMap.get(userToUnfollow).remove(currentUser);
        }
    }
    
    public static Set<String> getFollowing(String username) 
    {
        return followingMap.getOrDefault(username, new HashSet<>());
    }
    
    public static Set<String> getFollowers(String username) 
    {
        return followersMap.getOrDefault(username, new HashSet<>());
    }
}


class postManage
{
    private static HashMap<String, Set<String>> userPosts = new HashMap<>();
    
    public static void addPost(String username, String post) 
    {
        userPosts.putIfAbsent(username, new HashSet<>());
        userPosts.get(username).add(post);
    }
    
    public static Set<String> getPosts(String username) 
    {
        return userPosts.getOrDefault(username, new HashSet<>());
    }
    
    public static int getPostCount(String username) 
    {
        return getPosts(username).size();
    }
}

    
