import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
import java.awt.Font;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTextField;

public class SignUp implements ActionListener {
    JFrame frame = new JFrame("Chattr");
    JButton createButton = new JButton("Sign Up");

    JTextField firstNameField = new JTextField();
    JTextField surnameField = new JTextField();
    JTextField ageField = new JTextField();
    JTextField newUseridField = new JTextField();
    JPasswordField newPasswordField = new JPasswordField();
    JPasswordField verifyPasswordField = new JPasswordField();

    JLabel firstNameLabel = new JLabel("First name");
    JLabel surnameLabel = new JLabel("Surname");
    JLabel ageLabel = new JLabel("Age");
    JLabel newUseridLabel = new JLabel("Enter a username");
    JLabel newPasswordLabel = new JLabel("Enter a password");
    JLabel verifyPasswordLabel = new JLabel("Verify password");
    JLabel messageLabel = new JLabel();

    HashMap<String, String> loginInfo;
    ID idManager;

    public SignUp(HashMap<String, String> loginInfo) {
        this.loginInfo = loginInfo;
        idManager = new ID();

        setupUI();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void setupUI() {
        firstNameLabel.setBounds(50, 50, 120, 25);
        firstNameField.setBounds(180, 50, 200, 25);

        surnameLabel.setBounds(50, 100, 120, 25);
        surnameField.setBounds(180, 100, 200, 25);

        ageLabel.setBounds(50, 150, 120, 25);
        ageField.setBounds(180, 150, 200, 25);

        newUseridLabel.setBounds(50, 200, 120, 25);
        newUseridField.setBounds(180, 200, 200, 25);

        newPasswordLabel.setBounds(50, 250, 120, 25);
        newPasswordField.setBounds(180, 250, 200, 25);

        verifyPasswordLabel.setBounds(50, 300, 120, 25);
        verifyPasswordField.setBounds(180, 300, 200, 25);

        createButton.setBounds(160, 350, 100, 25);
        createButton.setFocusable(false);
        createButton.addActionListener(this);

        messageLabel.setBounds(160, 400, 250, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 15));

        frame.add(firstNameLabel);
        frame.add(surnameLabel);
        frame.add(firstNameField);
        frame.add(surnameField);
        frame.add(ageLabel);
        frame.add(ageField);
        frame.add(newUseridLabel);
        frame.add(newPasswordField);
        frame.add(newUseridField);
        frame.add(newPasswordLabel);
        frame.add(verifyPasswordLabel);
        frame.add(verifyPasswordField);
        frame.add(createButton);
        frame.add(messageLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            handleSignUp();
        }
    }

    private void handleSignUp() {
        String firstName = firstNameField.getText().trim();
        String surname = surnameField.getText().trim();
        String ageText = ageField.getText().trim();
        String newUserid = newUseridField.getText().trim();
        String newPassword = String.valueOf(newPasswordField.getPassword());
        String verifyPassword = String.valueOf(verifyPasswordField.getPassword());

        if (newUserid.isEmpty() || newPassword.isEmpty()) {
            showMessage("Fields cannot be empty!", Color.RED);
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
            if (age < 16) {
                showMessage("Must be 16 or older", Color.RED);
                return;
            }
        } catch (NumberFormatException ex) {
            showMessage("Invalid age!", Color.RED);
            return;
        }

        if (!isValidPassword(newPassword)) {
            showMessage("Must be 8+ characters, uppercase, and a number.", Color.RED);
            return;
        }

        if (!newPassword.equals(verifyPassword)) {
            showMessage("Passwords don't match, try again", Color.RED);
            return;
        }

        if (idManager.addUser(newUserid, newPassword)) {
            showMessage("Account created!", Color.GREEN);
            try {
                saveUser(newUserid, newPassword);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            frame.dispose();
            new Home(newUserid, idManager);
        } else {
            showMessage("Username already exists!", Color.RED);
        }
    }

    private void showMessage(String message, Color color) {
        messageLabel.setForeground(color);
        messageLabel.setText(message);
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*\\d.*");
    }

    private void saveUser(String username, String password) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("user.txt", true))) {
            bw.write(username + ":" + password);
            bw.newLine();
        } catch (IOException e) {
            showMessage("Error saving account", Color.RED);
        }
    }
}