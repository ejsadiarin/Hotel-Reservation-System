package View;

import Controller.HRSController;

import javax.swing.*;
import java.awt.*;

public class HRSApp extends JFrame {
  private CardLayout cardLayout;
  private JPanel mainPanel;
  private MainMenuPanel mainMenuPanel;
  private CreateHotelPanel createHotelPanel;
  private ViewSpecificHotelPanel viewSpecificHotelPanel;
  private ManageHotelPanel manageHotelPanel;
  private SimulateBookingPanel simulateBookingPanel;
//  private CardLayout cardLayout;
  
  public HRSApp() {
    setTitle("Hotel Reservation System");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    cardLayout = new CardLayout();
    mainPanel = new JPanel(cardLayout);
    mainMenuPanel = new MainMenuPanel(this);
    createHotelPanel = new CreateHotelPanel(this);
    viewSpecificHotelPanel = new ViewSpecificHotelPanel(this);
    manageHotelPanel = new ManageHotelPanel(this);
    simulateBookingPanel = new SimulateBookingPanel(this);

    // Add different views to the main panel
    mainPanel.add(mainMenuPanel, "MainMenu");
    mainPanel.add(createHotelPanel, "CreateHotel");
    mainPanel.add(viewSpecificHotelPanel, "ViewSpecificHotel");
    mainPanel.add(manageHotelPanel, "ManageHotel");
    mainPanel.add(simulateBookingPanel, "SimulateBooking");

    add(mainPanel);

    // Show the main menu initially
    cardLayout.show(mainPanel, "MainMenu");

    new HRSController(this);
  }
  
  public void switchPanel(String panelName) {
    cardLayout.show(mainPanel, panelName);
  }
  
  public MainMenuPanel getMainMenuPanel() {
    return this.mainMenuPanel;
  }

  public CreateHotelPanel getCreateHotelPanel() {
    return this.createHotelPanel;
  }

  public ViewSpecificHotelPanel getViewSpecificHotelPanel() {
    return this.viewSpecificHotelPanel;
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      HRSApp app = new HRSApp();
      app.setVisible(true);
    });
  }
}
