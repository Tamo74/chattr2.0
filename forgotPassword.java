import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.util.HashMap;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
 * Write a description of class fogotPassword here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class forgotPassword implements ActionListener
{
    JFrame frame = new JFrame("Chattr");
    JButton resetButton = new JButton("Reset Password");
    JTextField usernameField = new JTextField();
    JPasswordField passcodeField = new JPasswordField(); // new password
    JLabel usernameLabel = new JLabel("Enter your user id: ");
    JLabel passcodeLabel = new JLabel("Enter new Password"); // new password
    JLabel messageLabel = new JLabel();
    
    HashMap<String, String> loginInfo;
    
    forgotPassword(HashMap<String, String > loginInfo)
    {
        this.loginInfo = loginInfo;
        
        usernameLabel.setBounds(50, 50, 150, 25);
        usernameField.setBounds(200, 50, 150, 25);
        
        passcodeLabel.setBounds(50, 100, 150, 25);
        passcodeField.setBounds(200, 100, 150, 25);
        
        resetButton.setBounds(150, 150, 150, 25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        
        messageLabel.setBounds(100, 200, 300, 25); 
        messageLabel.setFont(new Font(null, Font.ITALIC, 15));
        
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passcodeLabel);
        frame.add(passcodeField);   
        frame.add(resetButton);
        frame.add(messageLabel);
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(420, 300);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == resetButton)
        {
            String userID = usernameField.getText().trim();
            String passcode = String.valueOf(passcodeField.getPassword()).trim();
            
             if (userID.isEmpty() || passcode.isEmpty())
            {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("User is not found");
            
            }    
             
             if(!isValidPassword(passcode))
            {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("must be 8+ charcaters, upperscase, and a number.");
                return;
            }
            
        
        
                else if(loginInfo.containsKey(userID))
                {
                    loginInfo.put(userID, passcode);
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Password updated succesffuly");
                }
            }
                else
                {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("user not found");
                }
    }
    
    private boolean isValidPassword(String passcode)
    {
        return passcode.length() >=8 &&
        passcode.matches(".*[A-Z].*") &&
        passcode.matches(".*[a-z].*") && 
        passcode.matches(".*\\d.*");
    }
}
