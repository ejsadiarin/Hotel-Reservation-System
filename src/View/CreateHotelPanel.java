package View;

import javax.swing.*;
import java.awt.*;

public class CreateHotelPanel extends JPanel {
  private HRSApp app;
  private JTextField hotelName;
  private JTextField numOfRooms;

  public CreateHotelPanel(HRSApp app) {
    this.app = app;
    setLayout(new BorderLayout());
    
    setBorder(BorderFactory.createLineBorder(Color.black));
    JLabel hotelNameLabel = new JLabel("Hotel Name");
    hotelNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
    add(hotelNameLabel, BorderLayout.WEST);
    hotelName = new JTextField();
    add(hotelName, BorderLayout.CENTER);

    JLabel numOfRoomsLabel = new JLabel("Number of Rooms");
    numOfRoomsLabel.setHorizontalAlignment(SwingConstants.CENTER);
    add(numOfRoomsLabel, BorderLayout.EAST);
    numOfRooms = new JTextField();
    add(numOfRooms, BorderLayout.CENTER);
  }

  // return the hotel name input from this view panel
  public JTextField getHotelName() {
    return this.hotelName;
  }
  
  public JTextField getNumOfRooms() {
    return this.numOfRooms;
  }
  
}
