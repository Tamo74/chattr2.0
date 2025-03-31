import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDirectory   {
    private List<User> users;

    public UserDirectory() {
        this.users = new ArrayList<>();
    }

    // Method to load users from a file
    public void loadUsersFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    users.add(new User(line.trim()));  // Assuming each line in the file contains a username
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Search for users by username (case-insensitive)
    public List<User> searchUsers(String query) {
        List<User> result = new ArrayList<>();
        for (User user : users) {
            if (user.getUsername().toLowerCase().contains(query.toLowerCase())) {
                result.add(user);
            }
        }
        return result;
    }

    public List<User> getUsers() {
        return users;
    }
}
