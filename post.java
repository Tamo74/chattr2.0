import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class Post {
    private String username;
    private String content;
    private Date timestamp;
    private Set<User> likedUsers;
    private List<Comment> comments;

    // Constructor for a post with username and content
    public Post(String username, String content) {
        this.username = username;
        this.content = content;
        this.timestamp = new Date();
        this.likedUsers = new HashSet<>();
        this.comments = new ArrayList<>();
    }

    // Constructor for a post with username, content, and timestamp
    public Post(String username, String content, Date timestamp) {
        this.username = username;
        this.content = content;
        this.timestamp = timestamp;
        this.likedUsers = new HashSet<>();
        this.comments = new ArrayList<>();
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    // Method to add a like to the post
    public void likePost(User user) {
        likedUsers.add(user);
    }

    // Method to get the number of likes
    public int getLikeCount() {
        return likedUsers.size();
    }

    // Method to add a comment
    public void addComment(User user, String message) {
        Comment comment = new Comment(user, message);
        comments.add(comment);
    }

    // Method to get the comments
    public List<Comment> getComments() {
        return comments;
    }

    // Method to return a string representation of the post for saving to a file
    public String toFileString() {
        return username + "|" + content.replace("\n", "\\n") + "|" + timestamp.getTime();
    }

    // Static method to create a post from a file string
    public static Post fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid post data format: " + line);
        }
        return new Post(parts[0], parts[1].replace("\\n", "\n"), new Date(Long.parseLong(parts[2])));
    }
}
