package ui;

import javax.swing.*;
import java.awt.*;
import db.db;

public class RegisterPage extends JFrame {
    private final JTextField firstNameField = new JTextField(15);
    private final JTextField lastNameField = new JTextField(15);
    private final JTextField gmailField = new JTextField(15);
    private final JPasswordField passField = new JPasswordField(15);


    public RegisterPage(){
        setTitle("Register Page");
        setSize(400, 400);
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

        // First Name Field 
        JLabel firstNameLabel = new JLabel("First Name:");
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(firstNameLabel, gbc);

        gbc.gridx = 1;
        centerPanel.add(firstNameField, gbc);

        // Last Name Field
        JLabel lastNameLabel = new JLabel("Last Name:");

        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(lastNameLabel, gbc);

        gbc.gridx = 1;
        centerPanel.add(lastNameField, gbc);

        // Gmail label + field
        JLabel gmailLabel = new JLabel("Gmail:");

        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(gmailLabel, gbc);

        gbc.gridx = 1;
        centerPanel.add(gmailField, gbc);

        // Password label + field
        JLabel passLabel = new JLabel("Password:");

        gbc.gridx = 0;
        gbc.gridy = 3;
        centerPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        centerPanel.add(passField, gbc);
        

        // Register
        JButton registerButton = new JButton("Register ");
        registerButton.addActionListener(e -> onRegister());

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        centerPanel.add(registerButton, gbc);

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

    private void onRegister(){
        // Registration logic here
        String firstName = firstNameField.getText().trim(); // get from field
        String lastName = lastNameField.getText().trim();  // get from field
        String gmail = gmailField.getText().trim();     // get from field
        String password = new String(passField.getPassword()).trim(); // get from 
        
        if (firstName.isEmpty() || lastName.isEmpty() || gmail.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //email validation
        if (!gmail.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email.", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "INSERT INTO users(first_name, last_name, email, password) VALUES (?, ?, ?, ?)";

        try (java.sql.Connection conn = db.getConnection();
             java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, gmail);
            ps.setString(4, password);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "User registered!");
            firstNameField.setText("");
            lastNameField.setText("");
            gmailField.setText("");
            passField.setText("");
        } catch (java.sql.SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(this, "Email already exists!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
