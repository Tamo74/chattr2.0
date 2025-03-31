import java.io.*;
import java.util.*;

public class followManage {
    private static HashMap<String, Set<String>> followingMap = new HashMap<>();
    private static HashMap<String, Set<String>> followersMap = new HashMap<>();
    private static final String FOLLOW_FILE = "follows.txt";  // File to store follow relationships

    static {
        loadFollows(); // Load follow data when the app starts
    }

    public static void follow(String currentUser, String userToFollow) {
        if (currentUser.equals(userToFollow)) {
            System.out.println("You can't follow yourself!");
            return;
        }

        followingMap.putIfAbsent(currentUser, new HashSet<>());
        followersMap.putIfAbsent(userToFollow, new HashSet<>());

        followingMap.get(currentUser).add(userToFollow);
        followersMap.get(userToFollow).add(currentUser);

        saveFollows();  // Save follow data
    }

    public static void unfollow(String currentUser, String userToUnfollow) {
        if (followingMap.containsKey(currentUser)) {
            followingMap.get(currentUser).remove(userToUnfollow);
        }
        if (followersMap.containsKey(userToUnfollow)) {
            followersMap.get(userToUnfollow).remove(currentUser);
        }

        saveFollows();  // Save follow data
    }

    public static Set<String> getFollowing(String username) {
        return followingMap.getOrDefault(username, new HashSet<>());
    }

    public static Set<String> getFollowers(String username) {
        return followersMap.getOrDefault(username, new HashSet<>());
    }

    private static void saveFollows() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FOLLOW_FILE))) {
            for (String user : followingMap.keySet()) {
                for (String followed : followingMap.get(user)) {
                    bw.write(user + "->" + followed);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving follow data: " + e.getMessage());
        }
    }

    private static void loadFollows() {
        try (BufferedReader br = new BufferedReader(new FileReader(FOLLOW_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("->");
                if (parts.length == 2) {
                    follow(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("No follow data found.");
        }
    }
}
