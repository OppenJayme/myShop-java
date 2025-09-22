package ui;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import db.db;

public class ChangePassword extends JFrame {

    private final JTextField emailField = new JTextField(20);
    private final JPasswordField currentPassField = new JPasswordField(20);
    private final JPasswordField newPassField = new JPasswordField(20);
    private final JPasswordField confirmPassField = new JPasswordField(20);

    public ChangePassword(String email) {
        setTitle("Change Password");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(480, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(12, 12));

        // Header (plain ASCII)
        JLabel header = new JLabel("Change Password", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        add(header, BorderLayout.NORTH);

        // Form using GridBagLayout with correct weightx so fields are wide & editable
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(8, 8, 8, 8);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.WEST;

        int r = 0;
        // column 0 = labels (no stretch), column 1 = fields (stretch)
        // Email (read-only)
        g.gridx = 0; g.gridy = r; g.weightx = 0; form.add(new JLabel("Email:"), g);
        emailField.setText(email);
        emailField.setEditable(false);
        g.gridx = 1; g.gridy = r; g.weightx = 1.0; form.add(emailField, g);
        r++;

        // Current password
        g.gridx = 0; g.gridy = r; g.weightx = 0; form.add(new JLabel("Current Password:"), g);
        g.gridx = 1; g.gridy = r; g.weightx = 1.0; form.add(currentPassField, g);
        r++;

        // New password
        g.gridx = 0; g.gridy = r; g.weightx = 0; form.add(new JLabel("New Password:"), g);
        g.gridx = 1; g.gridy = r; g.weightx = 1.0; form.add(newPassField, g);
        r++;

        // Confirm new password
        g.gridx = 0; g.gridy = r; g.weightx = 0; form.add(new JLabel("Confirm New Password:"), g);
        g.gridx = 1; g.gridy = r; g.weightx = 1.0; form.add(confirmPassField, g);
        r++;

        add(form, BorderLayout.CENTER);

        // Buttons
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");
        buttons.add(saveBtn);
        buttons.add(cancelBtn);
        add(buttons, BorderLayout.SOUTH);

        // Actions
        saveBtn.addActionListener(e -> handleChangePassword());
        cancelBtn.addActionListener(e -> dispose());

        // Focus the first editable field
        SwingUtilities.invokeLater(() -> currentPassField.requestFocusInWindow());

        setVisible(true);
    }

    private void handleChangePassword() {
        String email = emailField.getText().trim();
        char[] current = currentPassField.getPassword();
        char[] next = newPassField.getPassword();
        char[] confirm = confirmPassField.getPassword();

        if (current.length == 0 || next.length == 0 || confirm.length == 0) {
            JOptionPane.showMessageDialog(this, "Please fill out all password fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!String.valueOf(next).equals(String.valueOf(confirm))) {
            JOptionPane.showMessageDialog(this, "New passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (next.length < 6) {
            JOptionPane.showMessageDialog(this, "New password must be at least 6 characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // NOTE: plaintext example. If you hash passwords, verify hash then update hash.
        String sql = "UPDATE users SET password = ? WHERE email = ? AND password = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, String.valueOf(next));
            ps.setString(2, email);
            ps.setString(3, String.valueOf(current));

            int rows = ps.executeUpdate();
            if (rows == 1) {
                JOptionPane.showMessageDialog(this, "Password changed successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Incorrect current password (or user not found).",
                        "Change Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            wipe(current); wipe(next); wipe(confirm);
        }
    }

    private void wipe(char[] a) {
        if (a != null) java.util.Arrays.fill(a, '\0');
    }
}
