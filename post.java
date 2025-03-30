import java.util.Date;


/**
 * Write a description of class profile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class post
{
   private String username;
   private String content;
   private Date timestamp;

   
   public post(String username, String content)
   {
        this.username = username;
        this.content = content;
        this.timestamp = new Date();
    }
    
       public post(String username, String content, Date timestamp) {
        this.username = username;
        this.content = content;
        this.timestamp = timestamp;
    }

   public String getUsername()
   {
        return username;
    }
    
    public String getContent()
    {
        return content;
    }
    
    public Date getTimestamp()
    {
        return timestamp;
    }
   
    public String toFileString()
    {
        return username  + "|" + content.replace("\n", "\\n") + "|" + timestamp.getTime();
        
    }
    
    public static post fromFileString(String line)
    {
        String[] parts = line.split("\\|");
       if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid post data format: " + line);
        }

        return new post(parts[0], parts[1].replace("\\n", "\n"), new Date(Long.parseLong(parts[2])));
    }
}