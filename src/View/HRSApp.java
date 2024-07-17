package View;

import Controller.HRSController;

import javax.swing.*;
import java.awt.*;

public class HRSApp extends JFrame {
  private CardLayout cardLayout;
  private JPanel mainPanel;
  private MainMenuPanel mainMenuPanel;
  private ManageHotelPanel manageHotelPanel;
//  private CardLayout cardLayout;
  
  public HRSApp() {
    setTitle("Hotel Reservation System");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    cardLayout = new CardLayout();
    mainPanel = new JPanel(cardLayout);

    // Add different views to the main panel
    mainPanel.add(new MainMenuPanel(this), "MainMenu");
    mainPanel.add(new ManageHotelPanel(this), "ManageHotel");

    add(mainPanel);

    // Show the main menu initially
    cardLayout.show(mainPanel, "MainMenu");
  }
  
  public void switchPanel(String panelName) {
    cardLayout.show(mainPanel, panelName);
  }
  
  public MainMenuPanel getMainMenuPanel() {
    return this.mainMenuPanel;
  }
  
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      HRSApp app = new HRSApp();
      HRSController controller = new HRSController();
      app.setVisible(true);
    });
  }
}
