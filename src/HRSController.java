import Model.Hotel;

import java.util.ArrayList;

public class HRSController {
  private ArrayList<Hotel> hotels;
  
  public HRSController() {
    this.hotels = new ArrayList<>();
  }
  
  public void createHotel(String hotelName) {
    for (Hotel hotel : hotels) {
      if (hotel.getName().equals(hotelName)) {
        System.out.printf("Hotel name '%s' already exists.\n", hotelName);
        return;
      }
    }
    hotels.add(new Hotel(hotelName, 50));
  }

  public void viewAllHotels() {
    if (this.hotels.isEmpty()) {
      System.out.printf("No hotels found.\n");
    }
    else {
      System.out.printf("\nHotels Created:\n");
      for (Hotel hotel : hotels) {
        System.out.printf("%s\n", hotel.getName());
      }
    }
  }
  
  public void viewSpecificHotel(String hotelName) {
    for (Hotel hotel : hotels) {
      if (hotel.getName().equals(hotelName)) {
        // TODO: display all hotel information
        System.out.printf("\n==============================\n");
        System.out.printf("Hotel Name: %s\n", hotel.getName());
        System.out.printf("Number of Rooms: %s\n", hotel.getNumOfRooms());
        System.out.printf("Available Rooms: %s\n", hotel.getAvailableRooms());
        System.out.printf("Base Price per Room: %s\n", hotel.getBasePrice());
        System.out.printf("Estimated Earnings: %s\n", hotel.getEstimatedEarnings());
        System.out.printf("\n==============================\n");
      }
    }
  }
  
  public void manageHotel(String hotelName) {
  }
  
  public void simulateBooking() {
  }
  
  public Hotel findHotelByName(String hotelName) {
    for (Hotel hotel : hotels) {
      if (hotel.getName().equals(hotelName)) 
        return hotel;
    }
    return null;
  }
}
