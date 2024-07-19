package View;

import Controller.HRSController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateHotelPanel extends JPanel {
  private HRSController controller;

  public CreateHotelPanel() {
    this.controller = new HRSController();
    setLayout(new BorderLayout());
    
    JPanel formPanel = new JPanel(new GridLayout(2, 2));
    add(formPanel, BorderLayout.CENTER);

    createHotelComponent(formPanel);
  }

  public void createHotelComponent(JPanel formPanel) {
    formPanel.setLayout(null);

    JLabel hotelNameLabel = new JLabel("Hotel Name:");
    hotelNameLabel.setBounds(10, 20, 80, 25);
    formPanel.add(hotelNameLabel);

    JTextField hotelNameField = new JTextField(20);
    hotelNameField.setBounds(100, 20, 165, 25);
    formPanel.add(hotelNameField);

    JLabel numOfRoomsLabel = new JLabel("Number of Rooms:");
    numOfRoomsLabel.setBounds(10, 50, 80, 25);
    formPanel.add(numOfRoomsLabel);

    JTextField numOfRoomsField = new JTextField(20);
    numOfRoomsField.setBounds(100, 50, 165, 25);
    formPanel.add(numOfRoomsField);

    JButton createHotelButton = new JButton("Create Hotel");
    createHotelButton.setBounds(10, 80, 150, 25);
    formPanel.add(createHotelButton);

    createHotelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String hotelName = hotelNameField.getText();
        int numOfRooms = Integer.parseInt(numOfRoomsField.getText());
        controller.createHotel(hotelName, numOfRooms);
      }
    });
  }
}
