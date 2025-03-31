import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class ID {
    private HashMap<String, String> loginInfo = new HashMap<>();
    private Set<String> allUsers;
    private HashMap<String, Set<String>> followers = new HashMap<>();
    private HashMap<String, Set<String>> following = new HashMap<>();
    private HashMap<String, ArrayList<String>> posts = new HashMap<>();
    private final String FILE_NAME = "users.txt";

    public ID() {
        allUsers = new HashSet<>();
        followers = new HashMap<>();
        following = new HashMap<>();
        posts = new HashMap<>();
        try {
            loadUsers();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void loadUsers() throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No user found. New file creating...");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    loginInfo.put(parts[0], parts[1]);
                    allUsers.add(parts[0]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users: " + e.getMessage());
        }
    }

    private void saveUser(String username, String password) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(username + ":" + password);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    public boolean addUser(String username, String password) {
        if (allUsers.contains(username)) {
            return false; // Username already exists
        }

        allUsers.add(username);
        loginInfo.put(username, password);
        following.put(username, new HashSet<>());

        try {
            saveUser(username, password);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return true;
    }

    public boolean isValidUser(String userID, String password) {
        return loginInfo.containsKey(userID) && loginInfo.get(userID).equals(password);
    }

    public void follow(String currentUser, String targetUser) {
        if (loginInfo.containsKey(targetUser) && !currentUser.equals(targetUser)) {
            following.computeIfAbsent(currentUser, k -> new HashSet<>()).add(targetUser);
            followers.computeIfAbsent(targetUser, k -> new HashSet<>()).add(currentUser);
        }
    }

    public void unfollow(String currentUser, String targetUser) {
        if (loginInfo.containsKey(targetUser) && !currentUser.equals(targetUser)) {
            following.getOrDefault(currentUser, new HashSet<>()).remove(targetUser);
            followers.getOrDefault(targetUser, new HashSet<>()).remove(currentUser);
        }
    }

    public Set<String> getFollowers(String user) {
        return followers.getOrDefault(user, new HashSet<>());
    }

    public Set<String> getFollowing(String user) {
        return following.getOrDefault(user, new HashSet<>());
    }

    public ArrayList<String> getPosts(String user) {
        return posts.getOrDefault(user, new ArrayList<>());
    }

    public void addPost(String user, String post) {
        if (loginInfo.containsKey(user)) {
            posts.computeIfAbsent(user, k -> new ArrayList<>()).add(post);
        }
    }

    public int UserPostCount(String user) {
        return posts.getOrDefault(user, new ArrayList<>()).size();
    }

    protected HashMap<String, String> getLoginInfo() {
        return loginInfo;
    }

    public Object getAllPosts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPosts'");
    }
}