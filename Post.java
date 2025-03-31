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

    public Post(String username, String content) {
        this.username = username;
        this.content = content;
        this.timestamp = new Date();
        this.likedUsers = new HashSet<>();
        this.comments = new ArrayList<>();
    }

    public Post(String username, String content, Date timestamp) {
        this.username = username;
        this.content = content;
        this.timestamp = timestamp;
        this.likedUsers = new HashSet<>();
        this.comments = new ArrayList<>();
    }

   
    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

   
    public void likePost(User user) {
        likedUsers.add(user);
    }

   
    public int getLikeCount() {
        return likedUsers.size();
    }

    
    public void addComment(User user, String message) {
        Comment comment = new Comment(user, message);
        comments.add(comment);
    }

   
    public List<Comment> getComments() {
        return comments;
    }

   
    public String toFileString() {
        return username + "|" + content.replace("\n", "\\n") + "|" + timestamp.getTime();
    }

    
    public static Post fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid post data format: " + line);
        }
        return new Post(parts[0], parts[1].replace("\\n", "\n"), new Date(Long.parseLong(parts[2])));
    }
}
