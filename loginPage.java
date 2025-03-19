import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

/**
 * Write a description of class logkn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class loginPage implements ActionListener
{
    JFrame frame = new JFrame();
    JButton loginButton = new JButton("login");
    JButton resetButton = new JButton("Reset");
    JButton createButton = new JButton("sign up");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("userID");
    JLabel userPasswordLabel = new JLabel("Password");
    JLabel messageLabel = new JLabel();
    
    HashMap<String,String> loginInfo = new HashMap<String,String>();
    
    loginPage(HashMap<String,String> loginInfoOrigin)
    {
        loginInfo = loginInfoOrigin;
        //System.out.println("stored login info" + loginInfo);
        
        userIDLabel.setBounds(50,100,75,25); //x, y, width, height
        userPasswordLabel.setBounds(50,150,75,25);
        
        messageLabel.setBounds(125, 250, 230, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC,20));
        messageLabel.setPreferredSize(new Dimension(400 , 100));
        messageLabel.setVisible(true);
        
        userIDField.setBounds(125,100,200,25);
        userPasswordField.setBounds(125,150,200,25);
        
        loginButton.setBounds(125,200,100,25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        
        resetButton.setBounds(225,200,100,25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        
        createButton.setBounds(125, 300, 200, 25);
        createButton.setFocusable(false);
        createButton.addActionListener(this);
        
        
        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(createButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==resetButton) 
	{
		userIDField.setText("");
		userPasswordField.setText("");
	}
		
	if(e.getSource()==loginButton) 
	{
        	String userID = userIDField.getText();
		String password = String.valueOf(userPasswordField.getPassword());
			
		if(loginInfo.containsKey(userID)) 
		{
			if(loginInfo.get(userID).equals(password))
			{
				messageLabel.setForeground(Color.green);
				messageLabel.setText("Login successful");
				frame.dispose();
				home h = new home (userID);
			}
			else 
			{
				messageLabel.setForeground(Color.red);
				messageLabel.setText("Wrong password");
			}

		}
		else 
		{
			messageLabel.setForeground(Color.red);
			messageLabel.setText("username not found");
		}
	}
	
	if (e.getSource() == createButton)
	{
            new SignUp(loginInfo);
	}
    }	

        
}
    

