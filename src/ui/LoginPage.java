package ui;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.db;

public class LoginPage extends JFrame {
    private final JTextField gmailField = new JTextField(15);
    private final JPasswordField passField = new JPasswordField(15);

    public LoginPage() {
        setTitle("Login Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🔹 Top title bar
        JLabel shopTitle = new JLabel("MY SHOP");
        shopTitle.setFont(new Font("Arial", Font.BOLD, 20));
        shopTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK)); // bottom border
        shopTitle.setHorizontalAlignment(SwingConstants.LEFT);
        shopTitle.setOpaque(true);
        shopTitle.setBackground(new Color(230, 230, 230)); // light gray background
        add(shopTitle, BorderLayout.NORTH);

        // 🔹 Center panel for Gmail + Password + Login
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Gmail label + field
        JLabel gmailLabel = new JLabel("Gmail:");
        

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(gmailLabel, gbc);

        gbc.gridx = 1;
        centerPanel.add(gmailField, gbc);

        // Password label + field
        JLabel passLabel = new JLabel("Password:");
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        centerPanel.add(passField, gbc);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> onLogin());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        centerPanel.add(loginButton, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // 🔹 Bottom panel for Back button
        JPanel bottomPanel = new JPanel();
        JButton backButton = new JButton("Back");

        backButton.addActionListener(e -> {
            new App();   // go back to main App window
            dispose();   // close login page
        });

        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    private void onLogin() {
        String gmail = gmailField.getText().trim();
        String password = new String(passField.getPassword()).trim();
    
        if (gmail.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Gmail and password.", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        String sql = "SELECT first_name, last_name, email FROM users WHERE email = ? AND password = ?";
    
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
    
            ps.setString(1, gmail);
            ps.setString(2, password);
    
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String fname = rs.getString("first_name");
                    String lname = rs.getString("last_name");
                    String email = rs.getString("email");
                    JOptionPane.showMessageDialog(this, "Welcome, " + fname + "!");
    
                    System.out.println("[Login] Opening LandingPage for: " + fname);
                    SwingUtilities.invokeLater(() -> new LandingPage(fname, lname, gmail));  // show next page
    
                    dispose(); // close login after scheduling next page
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Gmail or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}    