package View;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
  public MainMenuPanel(HRSFrame app) {
    setLayout(new GridLayout(3, 1));

    JLabel titleLabel = new JLabel("Hotel Reservation System", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
    add(titleLabel);

    
    // TODO: create panels for these buttons
    JButton createHotelButton = new JButton("Create Hotel");
    createHotelButton.addActionListener(e -> app.switchPanel("CreateHotel"));
    add(createHotelButton);

    JButton viewSpecificHotelButton = new JButton("View Specific Hotel");
    viewSpecificHotelButton.addActionListener(e -> app.switchPanel("ViewSpecificHotel"));
    add(viewSpecificHotelButton);
    
    JButton manageHotelButton = new JButton("Manage Hotel");
    manageHotelButton.addActionListener(e -> app.switchPanel("ManageHotel"));
    add(manageHotelButton);
    
    JButton simulateBookingButton = new JButton("Simulate Booking");
    simulateBookingButton.addActionListener(e -> app.switchPanel("SimulateBooking"));
    add(simulateBookingButton);

    JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(e -> System.exit(0));
    add(exitButton);
  }
}
