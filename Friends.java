import java.util.Set;

/**
 * Write a description of class follow here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Friends
{
    private String username;
    private Set<String > followers; 
    private Set<String> following;
    
    public Friends(String username)
    {
        this.username = username;
        this.followers = followers;
        this.following = following;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public Set<String> getFollowers()
    {
        return followers;
    }
    
    public Set<String> getFollowing()
    {
        return following;
    }
    
    public void follow(Friends friend) 
    {
        if (!following.contains(friend.getUsername())) 
        {
            following.add(friend.getUsername());
            friend.followers.add(this.username);
            System.out.println(username + " followed " + friend.getUsername());
    } else {
        System.out.println(username + " is already following " + friend.getUsername());
    }
    }

    
    public void unfollow (Friends friend)
    {
        if (following.contains(friend.getUsername()))
        {
           following.remove(friend.getUsername());
           friend.followers.remove(this.username);
           System.out.println(username+"unfollowed" + friend.getUsername());
        }
            else
        {
            System.out.println(username+"follow" + friend.getUsername());
        }
    }

    public void showFollowers()
    {
        System.out.println("followers" + username+ followers);
    }
    
    public void showFollowing()
    {
        System.out.println(username + "is following:" + following);
    }
    
    
}
