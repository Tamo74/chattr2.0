import java.util.HashMap;

/**
 * Write a description of class ID here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ID
{
  HashMap<String, String> loginInfo = new HashMap<>();
  
  ID()
  {
        loginInfo.put("bro","password"); // id `and password in order
        loginInfo.put("rea","exam");
        loginInfo.put("tam","pool");
    }

  protected HashMap <String, String> getLoginInfo()
  {
       return loginInfo;   
    }
}
