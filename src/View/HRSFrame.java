package View;

import javax.swing.*;
import java.awt.*;

public class HRSFrame extends JFrame {
  private CardLayout cardLayout;
  private JPanel mainPanel;
//  private CardLayout cardLayout;
  
  public HRSFrame() {
    setTitle("Hotel Reservation System");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    cardLayout = new CardLayout();
    mainPanel = new JPanel(cardLayout);

    // Add different views to the main panel
//    mainPanel.add(new MainMenuPanel(this), "MainMenu");
//    mainPanel.add(new HRSFrame(this), "HotelManagement");

    add(mainPanel);

    // Show the main menu initially
    cardLayout.show(mainPanel, "MainMenu");
  }
  
  public void switchPanel(String panelName) {
    cardLayout.show(mainPanel, panelName);
  }
  
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      HRSFrame app = new HRSFrame();
      app.setVisible(true);
    });
  }
}
