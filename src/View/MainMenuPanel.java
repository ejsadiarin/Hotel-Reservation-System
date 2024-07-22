package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.HRSController;

public class MainMenuPanel extends JPanel {
  private HRSController controller;
  private JButton createHotelButton;
  private JButton viewSpecificHotelButton;
  private JButton manageHotelButton;
  private JButton simulateBookingButton;
  private JButton exitButton;
  private DefaultListModel<String> hotelNames;
  private JList<String> hotelList; 

  public MainMenuPanel(MainView view) {
    this.controller = new HRSController(view);
    setSize(800, 600);
    setLayout(null);

    JLabel titleLabel = new JLabel("Hotel Reservation System");
    titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
    titleLabel.setBounds(300, 50, 200, 50);
    this.add(titleLabel);

    hotelNames = controller.viewAllHotels();
    hotelList = new JList<>(hotelNames);
//    if (hotelNames.isEmpty()) {
//      hotelList.setListData(new String[] { "No hotels found" });
//    }
    
    JScrollPane scrollPane = new JScrollPane(hotelList);
    scrollPane.setBounds(50, 150, 200, 300);
    this.add(scrollPane);
    
    // Set bounds for the buttons
    int buttonWidth = 200;
    int buttonHeight = 50;
    int buttonStartX = (800 - buttonWidth) / 2; // Center the buttons horizontally
    int buttonStartY = 150; // Start the buttons at y=150
    int buttonSpacing = 10; // 10 pixels spacing between buttons

    createHotelButton = new JButton("Create Hotel");
    createHotelButton.setBounds(buttonStartX, buttonStartY, buttonWidth, buttonHeight);
    this.add(createHotelButton);
    createHotelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
//        app.switchPanel("CreateHotelPane");
        new CreateHotelPanel(view).setVisible(true);
      }
    });

    viewSpecificHotelButton = new JButton("View Specific Hotel");
    viewSpecificHotelButton.setBounds(buttonStartX, buttonStartY + buttonHeight + buttonSpacing, buttonWidth, buttonHeight);
    this.add(viewSpecificHotelButton);

    manageHotelButton = new JButton("Manage Hotel");
    manageHotelButton.setBounds(buttonStartX, buttonStartY + 2 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
    this.add(manageHotelButton);

    simulateBookingButton = new JButton("Simulate Booking");
    simulateBookingButton.setBounds(buttonStartX, buttonStartY + 3 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
    this.add(simulateBookingButton);

    exitButton = new JButton("Exit");
    exitButton.setBounds(buttonStartX, buttonStartY + 4 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
    this.add(exitButton);
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
