package ui;

import javax.swing.*;
import java.awt.*;

public class LandingPage extends JFrame {
    // keep user data so buttons can use it
    private final String firstName;
    private final String lastName;
    private final String email;

    public LandingPage(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.email     = email;

        setTitle("Landing Page");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top title
        JLabel shopTitle = new JLabel("MY SHOP", SwingConstants.CENTER);
        shopTitle.setFont(new Font("Arial", Font.BOLD, 20));
        shopTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        shopTitle.setOpaque(true);
        shopTitle.setBackground(new Color(230, 230, 230));
        add(shopTitle, BorderLayout.NORTH);

        // Center welcome
        JLabel msg = new JLabel("Hello " + firstName + "!", SwingConstants.CENTER);
        msg.setFont(new Font("Arial", Font.PLAIN, 18));
        add(msg, BorderLayout.CENTER);

        // Right-side vertical buttons
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JButton profileBtn     = new JButton("Profile");
        JButton changePassBtn  = new JButton("Change Password");
        JButton logoutBtn      = new JButton("Logout");

        profileBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        changePassBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        rightPanel.add(profileBtn);
        rightPanel.add(Box.createVerticalStrut(15));
        rightPanel.add(changePassBtn);
        rightPanel.add(Box.createVerticalStrut(15));
        rightPanel.add(logoutBtn);

        add(rightPanel, BorderLayout.EAST);

        // Actions
        profileBtn.addActionListener(e ->
            new ProfilePage(firstName, lastName, email).setVisible(true)
        );

        changePassBtn.addActionListener(e -> {
            // open the change password window, passing the logged-in email
            new ChangePassword(email).setVisible(true);
        });
        

        logoutBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "You have logged out.");
            dispose();
            new LoginPage().setVisible(true); // adjust to your actual login class
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
