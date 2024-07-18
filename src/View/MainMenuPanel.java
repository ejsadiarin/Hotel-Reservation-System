package View;

import Controller.HRSController;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
  private HRSController controller;
  private JButton createHotelButton;
  private JButton viewSpecificHotelButton;
  private JButton manageHotelButton;
  private JButton simulateBookingButton;
  private JButton exitButton;
  
  public MainMenuPanel(HRSApp app) {
    setSize(800, 600);
    setLayout(new BorderLayout());

    JLabel titleLabel = new JLabel("Hotel Reservation System", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
    add(titleLabel);

    // TODO: call viewAllHotels from controller (display hotels, if none then "No hotels created yet")
    controller = new HRSController(app);

    // TODO: create panels for these buttons
    createHotelButton = new JButton("Create Hotel");
    add(createHotelButton);

    viewSpecificHotelButton = new JButton("View Specific Hotel");
    add(viewSpecificHotelButton);
    
    manageHotelButton = new JButton("Manage Hotel");
    add(manageHotelButton);
    
    simulateBookingButton = new JButton("Simulate Booking");
    add(simulateBookingButton);

    exitButton = new JButton("Exit");
    add(exitButton);
  }
  
  public JButton getCreateHotelButton() {
    return this.createHotelButton;
  }

  public JButton getViewSpecificHotelButton() {
    return this.viewSpecificHotelButton;
  }

  public JButton getManageHotelButton() {
    return this.manageHotelButton;
  }

  public JButton getSimulateBookingButton() {
    return this.simulateBookingButton;
  }

  public JButton getExitButton() {
    return this.exitButton;
  }
}
