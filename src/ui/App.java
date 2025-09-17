package ui;
import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Small Input Window");
        frame.setSize(400, 400); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create the center panel (2 columns)
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        frame.add(centerPanel, BorderLayout.CENTER);

        // Left side: Title text, centered
        JTextArea titleText = new JTextArea("WELCOME TO MY SHOP!");
        titleText.setFont(new Font("Arial", Font.BOLD, 18));
        titleText.setLineWrap(true);
        titleText.setWrapStyleWord(true);
        titleText.setEditable(false);
        titleText.setOpaque(false);
        titleText.setFocusable(false);

        // Wrap inside a panel with GridBagLayout (centers the text)
        JPanel textPanel = new JPanel(new GridBagLayout());
        textPanel.add(titleText); 
        centerPanel.add(textPanel);

        // Right side: Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton loginButton = new JButton(" Login");
        JButton registerButton = new JButton(" Register");

        Dimension buttonSize = new Dimension(150, 150);
        loginButton.setMaximumSize(buttonSize);
        registerButton.setMaximumSize(buttonSize);

        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginButton.addActionListener(e -> {
            new LoginPage(); // open login window
            frame.dispose(); // close the main window 
        });

        registerButton.addActionListener(e -> {
            new RegisterPage(); // open register window
            frame.dispose();    // close the main window
        });

        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(registerButton);
        buttonPanel.add(Box.createVerticalGlue());

        centerPanel.add(buttonPanel);

        // Show the frame
        frame.setVisible(true);
    }
}
