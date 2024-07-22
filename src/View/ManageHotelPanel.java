package View;

import javax.swing.*;
import java.awt.*;

public class ManageHotelPanel extends JPanel {
  private MainView app;
  private JButton changeNameOfHotelButton;
  private JButton addRoomButton;
  private JButton removeRoomButton;
  private JButton updateBasePriceOfRoomButton;
  private JButton removeReservationButton;
  private JButton removeHotelButton;
  private JButton backButton;
  
//    System.out.printf("1 - Change name of the hotel\n");
//    System.out.printf("2 - Add a room\n");
//    System.out.printf("3 - Remove a room\n");
//    System.out.printf("4 - Update the base price of the rooms\n");
//    System.out.printf("5 - Remove a reservation\n");
//    System.out.printf("6 - Remove hotel\n");
//    System.out.printf("0 - Go back to previous menu\n");
  public ManageHotelPanel(MainView app) {
    this.app = app;
    setLayout(new BorderLayout());

    JPanel buttonPanel = new JPanel();
    this.changeNameOfHotelButton = new JButton("Change name of the Hotel");
    this.addRoomButton = new JButton("Add a Room");
    this.removeRoomButton = new JButton("Remove a Room");
    this.updateBasePriceOfRoomButton = new JButton("Update the base price of the Rooms");
    this.removeReservationButton = new JButton("Remove a Reservation");
    this.removeHotelButton = new JButton("Remove Hotel");
    this.backButton = new JButton("Go back to Main Menu");

    buttonPanel.add(changeNameOfHotelButton);
    buttonPanel.add(addRoomButton);
    buttonPanel.add(removeRoomButton);
    buttonPanel.add(removeReservationButton);
    buttonPanel.add(updateBasePriceOfRoomButton);
    buttonPanel.add(removeReservationButton);
    buttonPanel.add(removeHotelButton);
    buttonPanel.add(backButton);

    // TODO: maybe show specific (chosen) hotel details on BorderLayout.NORTH ?
    add(buttonPanel, BorderLayout.SOUTH);

    this.backButton.addActionListener(e -> app.switchPanel("MainMenu"));
    
    // TODO: use TextLayout to get input here
//    changeNameOfHotelButton.addActionListener(e -> {
//      String hotelName = JOptionPane.showInputDialog(this, "Enter hotel name:");
//      if (hotelName != null && !hotelName.trim().isEmpty()) {
//        double basePrice = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter base price per room:"));
//        HRSController.
//        app.getHotels().add(hotel);
//        JOptionPane.showMessageDialog(this, "Hotel added successfully!");
//      }
//    });

  }
  
}
