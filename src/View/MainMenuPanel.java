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
    setLayout(new BorderLayout());

    JLabel titleLabel = new JLabel("Hotel Reservation System", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
    add(titleLabel);

    // TODO: call viewAllHotels from controller (display hotels, if none then "No hotels created yet")
    controller = new HRSController(app);

    
    // TODO: create panels for these buttons
    createHotelButton = new JButton("Create Hotel");
    createHotelButton.addActionListener(e -> app.switchPanel("CreateHotel"));
    add(createHotelButton);

    viewSpecificHotelButton = new JButton("View Specific Hotel");
    viewSpecificHotelButton.addActionListener(e -> app.switchPanel("ViewSpecificHotel"));
    add(viewSpecificHotelButton);
    
    manageHotelButton = new JButton("Manage Hotel");
    manageHotelButton.addActionListener(e -> app.switchPanel("ManageHotel"));
    add(manageHotelButton);
    
    simulateBookingButton = new JButton("Simulate Booking");
    simulateBookingButton.addActionListener(e -> app.switchPanel("SimulateBooking"));
    add(simulateBookingButton);

    exitButton = new JButton("Exit");
    exitButton.addActionListener(e -> System.exit(0));
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
