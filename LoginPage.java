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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginPage implements ActionListener {
    JFrame frame = new JFrame("Chattr");
    JButton loginButton = new JButton("Login");
    JButton forgotButton = new JButton("Forgot Password");
    JButton createButton = new JButton("Sign Up");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("UserID");
    JLabel userPasswordLabel = new JLabel("Password");
    JLabel messageLabel = new JLabel();

    HashMap<String, String> loginInfo;
    ID idManager;

    public LoginPage(HashMap<String, String> loginInfoOrigin) {
        idManager = new ID();
        loginInfo = idManager.getLoginInfo();
        try {
            loadUsers();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        loginInfo = loginInfoOrigin;

        setupUI();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void setupUI() {
        userIDLabel.setBounds(50, 100, 75, 25);
        userPasswordLabel.setBounds(50, 150, 75, 25);

        messageLabel.setBounds(125, 250, 230, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 20));
        messageLabel.setPreferredSize(new Dimension(400, 100));
        messageLabel.setVisible(true);

        userIDField.setBounds(125, 100, 200, 25);
        userPasswordField.setBounds(125, 150, 200, 25);

        loginButton.setBounds(125, 200, 100, 25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        forgotButton.setBounds(225, 200, 100, 25);
        forgotButton.setFocusable(false);
        forgotButton.addActionListener(this);

        createButton.setBounds(125, 300, 200, 25);
        createButton.setFocusable(false);
        createButton.addActionListener(this);

        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(forgotButton);
        frame.add(createButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            handleLogin();
        } else if (e.getSource() == forgotButton) {
            new forgotPassword(loginInfo);
        } else if (e.getSource() == createButton) {
            new SignUp(loginInfo);
        }
    }

    private void handleLogin() {
        String userID = userIDField.getText();
        String password = String.valueOf(userPasswordField.getPassword());

        if (loginInfo.containsKey(userID)) {
            if (idManager.isValidUser(userID, password)) {
                messageLabel.setForeground(Color.GREEN);
                messageLabel.setText("Login successful!");
                frame.dispose();
                new Home(userID, idManager);
            } else {
                showMessage("Invalid username or password!", Color.RED);
            }
        } else {
            showMessage("Invalid username or password!", Color.RED);
        }
    }

    private void showMessage(String message, Color color) {
        messageLabel.setForeground(color);
        messageLabel.setText(message);
    }

    private void loadUsers() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    loginInfo.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users: " + e.getMessage());
        }
    }
}