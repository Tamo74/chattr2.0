import javax.swing.JTextArea;
import javax.swing.JFrame;
import java.awt.Frame;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
 
/**
 * Write a description of class postManage here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class postManage implements ActionListener
{
   JFrame frame = new JFrame("Chattr");
   JTextArea postTextArea = new JTextArea();
   JButton postButton = new JButton("post");
   JLabel messageLabel = new JLabel();
   
   static ArrayList<post> posts = new ArrayList<>();
   String username;
   
   static final String FILE_NAME = "post.txt";
   
   public postManage(String username)
   {
        this.username = username;
    
        JFrame frame = new JFrame("Chattr");
        postTextArea.setBounds(0, 50, 300, 100);
        postTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        postButton.setBounds(150, 150, 100, 10);
        postButton.addActionListener(this);
        
        messageLabel.setBounds(50, 200, 200, 35);
        
        frame.add(postTextArea);
        frame.add(postButton);
        frame.add(messageLabel);
        
        loadPosts();
        displayPosts();
        
        frame.setSize(420,500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == postButton)
        {
            String content = postTextArea.getText().trim();
            
            if (content.isEmpty())
            {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Post cannot be empty");
                return;
            }
            
            post p = new post(username, content);
            posts.add(0, p);
            savePosts();
            postTextArea.setText("");
            messageLabel.setForeground(Color.green);
            messageLabel.setText("Post uploaded");
            
            displayPosts();
        }
    }
    
    private void displayPosts()
    {
         int yPosition = 250;
        for (Component c: frame.getContentPane().getComponents())
        {
            if(c instanceof JTextArea && c != postTextArea)
            {
                frame.remove(c);
            }
        }
        
         for (post p : posts)
         {
                JTextArea postLabel = new JTextArea(p.getUsername()+":\n"+ p.getContent());
                postLabel.setBounds(50, yPosition, 300, 80);
                postLabel.setEditable(false);
                postLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                frame.add(postLabel);
                yPosition += 90;
            }
            frame.revalidate(); 
            frame.repaint();
    }
    
    private void savePosts() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (post p : posts) {
                bw.write(p.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("error saving posts: " + e.getMessage());
        }
    }
    
     private void loadPosts() {
        posts.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                posts.add(post.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("No previous posts found.");
        }
    }
    

}
