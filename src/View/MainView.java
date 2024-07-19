package View;

import Controller.HRSController;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
  private HRSController controller;

  public MainView() {
    setTitle("Hotel Reservation System");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    setLayout(null);

    // create 2 components
    JPanel hotelsPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();
    add(hotelsPanel);
    add(buttonsPanel);

    JLabel titleLabel = new JLabel("Hotel Reservation System", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
    add(titleLabel);

    // TODO: call viewAllHotels from controller (display hotels, if none then "No hotels created yet")
    listHotelsComponent(hotelsPanel);
    buttonsComponent(buttonsPanel);

  }

  public void listHotelsComponent(JPanel hotelsPanel) {
    setLayout(new BorderLayout());
    controller.viewAllHotels();
  }

  public void buttonsComponent(JPanel buttonsPanel) {
    setLayout(new BorderLayout());

    JButton SWITCH_createHotel = new JButton("Create Hotel");
    SWITCH_createHotel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        CreateHotelPanel createHotelPanel = new CreateHotelPanel();
        createHotelPanel.setVisible(true);
      }
    });

    JButton SWITCH_viewSpecificHotelButton = new JButton("View Specific Hotel");
    SWITCH_viewSpecificHotelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ViewSpecificHotelPanel viewSpecificHotelPanel = new ViewSpecificHotelPanel();
        viewSpecificHotelPanel.setVisible(true);
      }
    });

    JButton SWITCH_manageHotelButton = new JButton("Manage Hotel");
    SWITCH_manageHotelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ManageHotelPanel manageHotelPanel = new ManageHotelPanel();
        manageHotelPanel.setVisible(true);
      }
    });

    JButton SWITCH_simulateBookingButton = new JButton("Simulate Booking");
    SWITCH_simulateBookingButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(e -> System.exit(0));

    buttonsPanel.add(SWITCH_createHotel, BorderLayout.WEST);
    buttonsPanel.add(SWITCH_viewSpecificHotelButton, BorderLayout.EAST);
    buttonsPanel.add(SWITCH_manageHotelButton, BorderLayout.NORTH);
    buttonsPanel.add(SWITCH_simulateBookingButton, BorderLayout.SOUTH);

  }

}
