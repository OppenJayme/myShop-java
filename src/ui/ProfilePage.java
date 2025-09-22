package ui;

import javax.swing.*;
import java.awt.*;

public class ProfilePage extends JFrame {
    public ProfilePage(String firstName, String lastName, String email) {
        setTitle("Profile");
        setSize(600, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(16, 16));

        JLabel shopTitle = new JLabel("MY SHOP", SwingConstants.CENTER);
        shopTitle.setFont(new Font("Arial", Font.BOLD, 20));
        shopTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        shopTitle.setOpaque(true);
        shopTitle.setBackground(new Color(230, 230, 230));
        add(shopTitle, BorderLayout.NORTH);

        JPanel leftWrap = new JPanel(new GridBagLayout());
        JLabel picPlaceholder = new JLabel("Picture", SwingConstants.CENTER);
        picPlaceholder.setPreferredSize(new Dimension(180, 180));
        picPlaceholder.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        leftWrap.add(picPlaceholder);
        add(leftWrap, BorderLayout.WEST);

        JPanel info = new JPanel(new GridBagLayout());
        info.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(8,8,8,8);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.WEST;
        int r = 0;

        g.gridx=0; g.gridy=r; info.add(new JLabel("First Name:"), g);
        JTextField f1 = new JTextField(firstName, 20); f1.setEditable(false);
        g.gridx=1; g.gridy=r++; info.add(f1, g);

        g.gridx=0; g.gridy=r; info.add(new JLabel("Last Name:"), g);
        JTextField f2 = new JTextField(lastName, 20); f2.setEditable(false);
        g.gridx=1; g.gridy=r++; info.add(f2, g);

        g.gridx=0; g.gridy=r; info.add(new JLabel("Email:"), g);
        JTextField f3 = new JTextField(email, 20); f3.setEditable(false);
        g.gridx=1; g.gridy=r++; info.add(f3, g);

        add(info, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton back = new JButton("Back");
        back.addActionListener(e -> dispose());
        bottom.add(back);
        add(bottom, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
