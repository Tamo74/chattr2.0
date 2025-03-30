import java.util.HashMap;
import java.util.HashSet;

/**
 * Write a description of class followManage here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class followManage
{
    private static HashMap<String, HashSet<String>> followingMap = new HashMap<>();

    
    public static void follow(String currentUser, String userToFollow)
    {
        followingMap.putIfAbsent(currentUser, new HashSet<>());
        followingMap.get(currentUser).add(userToFollow);
        System.out.println(currentUser + " followed " + userToFollow);
    }

    
    public static void unfollow(String currentUser, String userToUnfollow) 
    {
        if (followingMap.containsKey(currentUser)) 
        {
            followingMap.get(currentUser).remove(userToUnfollow);
            System.out.println(currentUser + " unfollowed " + userToUnfollow);
        }
    }

    
    public static HashSet<String> getFollowing(String currentUser)
    {
        return followingMap.getOrDefault(currentUser, new HashSet<>());
    }
}
