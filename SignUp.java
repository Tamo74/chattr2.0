import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
import java.awt.Font;
import java.awt.Color;

//import java.awt.TextField;

/**
 * Write a description of class SignUp here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SignUp implements ActionListener
{
    JFrame frame = new JFrame();
    JButton createButton = new JButton("Sign Up");
    JTextField newUseridField = new JTextField();
    JPasswordField newPasswordField = new JPasswordField();
    JLabel newUseridLabel = new JLabel("new username");
    JLabel newPasswordLabel = new JLabel("enter a password");
    JLabel messageLabel= new JLabel();
    
    HashMap<String, String> loginInfo;
    
    public SignUp(HashMap<String, String> loginInfo)
    {
         this.loginInfo = loginInfo;
         
          newUseridLabel.setBounds(50, 100, 120, 25);
        newUseridField.setBounds(180, 100, 200, 25);
        
        newPasswordLabel.setBounds(50, 150, 120, 25);
        newPasswordField.setBounds(180, 150, 200, 25);
         
         createButton.setBounds(160, 200, 100,25);
         createButton.setFocusable(false);
         createButton.addActionListener(this);
         
         messageLabel.setBounds(160,250,250,35);
         messageLabel.setFont(new Font(null , Font.ITALIC, 15));
         
         frame.add(newUseridLabel);
         frame.add(newPasswordField);
         frame.add(newUseridField);
         frame.add(newPasswordLabel);
         frame.add(createButton);
         frame.add(messageLabel);
         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         
         frame.setSize(420,420);
         frame.setLayout(null);
         frame.setVisible(true);
        }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == createButton)
        {
            String newUserid = newUseridField.getText().trim();
            String newPassword = String.valueOf(newPasswordField.getPassword());
        
        if (newUserid.isEmpty() || newPassword.isEmpty())
            {   
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Fields cannot be empty!");
            }
            else if (loginInfo.containsKey(newUserid))

            {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("username already exists ");
            }
        
        else
        {
            loginInfo.put(newUserid, newPassword);
            messageLabel.setForeground(Color.green);
            messageLabel.setText("account created");
            frame.dispose();
            home h = new home (newUserid);
        }
        }
    }
}
        
    


