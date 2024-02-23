/* 
- It is important to note that this program will always return "Login Successful"
- There is no input validation for this program, which is why it is used for learning defensive programming.
- This code can be used and augmented with other projects to create a working authentication page, but that will require further development. 
- I used NetBeans to compile this program. 
- This is the program used by the Login class.
*/'

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        // This sets up the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setTitle("Login");
        setLayout(new BorderLayout());

        // Header information
        JLabel headerLabel = new JLabel("Login", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(headerLabel, BorderLayout.NORTH);

        // Input fields and login button
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        // Add components to the input panel
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.add(new JLabel()); // Empty label for spacing
        inputPanel.add(loginButton);

        // Add input panel to the center of the frame
        add(inputPanel, BorderLayout.CENTER);

        // ActionListener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // For now, display a message indicating success
                JOptionPane.showMessageDialog(LoginFrame.this, "Successful Login");
            }
        });

        // Make the frame visible
        setVisible(true);
    }
}
