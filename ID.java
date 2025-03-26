import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

/**
 * Write a description of class ID here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ID
{
  private HashMap<String, String> loginInfo = new HashMap<>();
  private final String FILE_NAME = "users.txt";
    ID()
    {
        try
        {
            loadUsers();
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
    
    private void loadUsers() throws java.io.IOException
    {
        File file = new File(FILE_NAME);
        if (!file.exists())
        {
            System.out.println("no user found. new file creating..."); // need to remove this 
            return;
        }
        
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME)))
        {
            String line;
            while ((line = br.readLine()) !=null)
            {
                String[] parts = line.split(":");
                if (parts.length == 2)
                {
                loginInfo.put(parts[0], parts[1]);
                }
            }
        }
        catch (IOException e )
        {
            System.out.println("no user found");
        }
    }
    
    private void saveUser()
    throws java.io.IOException
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true)))
        {
            for (String user: loginInfo.keySet())
            {
             bw.write(user + ":" + loginInfo.get(user));
             bw.newLine();
            }
        }
        catch(IOException e)
        {
            System.out.println("error saving users" + e.getMessage());
        }
    }
    
    public boolean addUser(String  userID, String password)
    {
        if (loginInfo.containsKey(userID))
        {
            return false;
        }
        
        loginInfo.put(userID, password);
        try
        {
            saveUser();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        return true;
    }
    
    public boolean isValidUser(String userID, String password)
    {
        return loginInfo.containsKey(userID) && loginInfo.get(userID).equals(password);
    }
    
      protected HashMap <String, String> getLoginInfo()
      {
       return loginInfo;   
    }
    
}
