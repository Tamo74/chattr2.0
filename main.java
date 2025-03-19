
/**
 * Write a description of class main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class main
{
    public static void main(String[] args)
    {
        ID id = new ID();
        
        loginPage lp = new loginPage(id.getLoginInfo()); // the lp stands for login page
    }
}
