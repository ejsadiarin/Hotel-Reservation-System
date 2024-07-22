package View;

import Controller.HRSController;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
  private CardLayout cardLayout;
  private JPanel mainPanel;
  private MainMenuPanel mainMenuPanel;
  private CreateHotelPanel createHotelPanel;
  // private ViewSpecificHotelPanel viewSpecificHotelPanel;
  // private ManageHotelPanel manageHotelPanel;
  // private SimulateBookingPanel simulateBookingPanel;
  // private CardLayout cardLayout;

  public MainView() {
    setTitle("Hotel Reservation System");
    setSize(800, 600);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    cardLayout = new CardLayout();
    mainPanel = new JPanel(cardLayout);
    mainMenuPanel = new MainMenuPanel(this);
    createHotelPanel = new CreateHotelPanel(this);
    // viewSpecificHotelPanel = new ViewSpecificHotelPanel(this);
    // manageHotelPanel = new ManageHotelPanel(this);
    // simulateBookingPanel = new SimulateBookingPanel(this);

    // Add different views to the main panel
    mainPanel.add(mainMenuPanel, "MainMenuPane");
    mainPanel.add(createHotelPanel, "CreateHotelPane");
    // mainPanel.add(viewSpecificHotelPanel, "ViewSpecificHotelPane");
    // mainPanel.add(manageHotelPanel, "ManageHotelPane");
    // mainPanel.add(simulateBookingPanel, "SimulateBookingPane");

    add(mainPanel);

    // Show the main menu initially
    cardLayout.show(mainPanel, "MainMenuPane");
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
  //
  // public ViewSpecificHotelPanel getViewSpecificHotelPanel() {
  // return this.viewSpecificHotelPanel;
  // }

}
