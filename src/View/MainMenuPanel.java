package View;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
  public MainMenuPanel(HRSFrame app) {
    setLayout(new GridLayout(3, 1));

    JLabel titleLabel = new JLabel("Hotel Management System", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
    add(titleLabel);

    JButton manageHotelsButton = new JButton("Manage Hotels");
    manageHotelsButton.addActionListener(e -> app.switchPanel("HotelManagement"));
    add(manageHotelsButton);

    JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(e -> System.exit(0));
    add(exitButton);
  }
}
