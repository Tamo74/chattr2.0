import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserSearchApp extends JFrame {

    private ID id;  // assuming ID is passed for later use

    public UserSearchApp(ID id) {
        this.id = id;

        // Set up JFrame properties
        setTitle("User Search");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the search field and user list
        JTextField searchField = new JTextField(20);
        JList<String> userList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(userList);

        // Add components to JFrame
        add(searchField, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add KeyListener to search users dynamically
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String query = searchField.getText();
                DefaultListModel<String> model = new DefaultListModel<>();
                
               
            }
        });

        // Make the JFrame visible
        setVisible(true);
    }
}
