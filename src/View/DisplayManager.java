package View;

import Model.Hotel;

public class DisplayManager {
  public static void displayHotelGeneralInfo(Hotel hotel) {
    // display high-level information about the hotel
    System.out.printf("\n===========HIGH-LEVEL INFORMATION==============\n");
    System.out.printf("Hotel Name: %s\n", hotel.getName());
    System.out.printf("Estimated Earnings: %s\n", hotel.getEstimatedEarnings());
    System.out.printf("Number of Rooms: %s\n", hotel.getNumOfRooms());
  }
  
  public static void displayHotelSpecificInfo(Hotel hotel) {
//    Available low-level information should include the following:
//    i. Total number of available and booked rooms for a selected date
//    ii. Information about a selected room, such as the room’s name, price per night, and
//    availability across the entire month
//    iii. Information about a selected reservation, such as the guest information, room
//    information, check-in and -out dates, the total price for the booking, and the
//    breakdown of the price per night
    
  }
  
  public static void displayMainUI() {
    System.out.printf("\n==============================================\n");
    System.out.printf("1 - Create a new hotel\n");
    System.out.printf("2 - View all hotels\n");
    System.out.printf("3 - Manage a hotel\n");
    System.out.printf("4 - Simulate booking\n");
    System.out.printf("0 - Exit the program\n");
    System.out.printf("==============================================\n");
  }
  
  public static void displayManageHotelUI() {
    System.out.printf("\n==============================================\n");
    System.out.printf("1 - Change name of the hotel\n");
    System.out.printf("2 - Add a room\n");
    System.out.printf("3 - Remove a room\n");
    System.out.printf("4 - Update the base price of the rooms\n");
    System.out.printf("5 - Remove a reservation\n");
    System.out.printf("6 - Remove hotel\n");
    System.out.printf("0 - Go back to previous menu\n");
    System.out.printf("==============================================\n");
  }
}
