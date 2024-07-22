package View;

import Controller.HRSController;

import javax.swing.*;
import java.awt.*;

public class CreateHotelPanel extends JPanel {
  private MainView view;
  private JTextField hotelName;
  private JTextField numOfRooms;
  private HRSController controller;

  public CreateHotelPanel(MainView view) {
    this.view = view;
    this.controller = new HRSController(view);
    setLayout(new BorderLayout());
    
    JPanel formPanel = new JPanel(new GridLayout(2, 2));
    JLabel hotelNameLabel = new JLabel("Hotel Name");
    hotelNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
    formPanel.add(hotelNameLabel);
    hotelName = new JTextField();
    formPanel.add(hotelName);

    JLabel numOfRoomsLabel = new JLabel("Number of Rooms");
    numOfRoomsLabel.setHorizontalAlignment(SwingConstants.CENTER);
    formPanel.add(numOfRoomsLabel);
    numOfRooms = new JTextField();
    formPanel.add(numOfRooms);

    this.add(formPanel, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    JButton createButton = new JButton("Create");
    createButton.addActionListener(e -> controller.createHotel()); // Replace with actual create hotel logic
    buttonPanel.add(createButton);
    
    JButton backButton = new JButton("Back");
    backButton.addActionListener(e -> {
      view.switchPanel("MainMenuPane");
    });
    buttonPanel.add(backButton);

    this.add(buttonPanel, BorderLayout.SOUTH);
  }

  // return the hotel name input from this view panel
  public JTextField getHotelName() {
    return this.hotelName;
  }
  
  public JTextField getNumOfRooms() {
    return this.numOfRooms;
  }
  
}
