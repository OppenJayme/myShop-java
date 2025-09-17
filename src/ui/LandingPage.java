package ui;

import javax.swing.*;
import java.awt.*;

public class LandingPage extends JFrame {
    public LandingPage(String firstName) {
        setTitle("Landing Page");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel shopTitle = new JLabel("MY SHOP");
        shopTitle.setFont(new Font("Arial", Font.BOLD, 20));
        shopTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        shopTitle.setOpaque(true);
        shopTitle.setBackground(new Color(230, 230, 230));
        add(shopTitle, BorderLayout.NORTH);

        JLabel msg = new JLabel("Hello " + firstName + "!", SwingConstants.CENTER);
        msg.setFont(new Font("Arial", Font.PLAIN, 18));
        add(msg, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
