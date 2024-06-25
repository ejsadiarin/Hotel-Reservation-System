import View.DisplayManager;

import java.util.Scanner;

public class Driver {
  public static void main(String[] args) {
    HotelReservationSystem hotelReservationSystem = new HotelReservationSystem();
    Scanner scanner = new Scanner(System.in);
    
    while (true) {
      DisplayManager.displayMainUI();
      
      System.out.printf("Choose your action (Enter 0 to exit): ");
      int choice = scanner.nextInt();
      scanner.nextLine();
      
      switch (choice) {
        case 0: // exit
          System.out.printf("Exiting...\n");
          scanner.close();
          return;
        case 1: // create hotel
          System.out.printf("\nCreating hotel...\n");
          System.out.printf("Enter hotel name: ");
          String createHotelName = scanner.nextLine();
          System.out.printf("Enter number of rooms in the hotel: ");
          int numOfRooms = scanner.nextInt();
          hotelReservationSystem.createHotel(createHotelName, numOfRooms);
          break;
        case 2: // view hotel
          System.out.printf("\nViewing hotels...\n");
          hotelReservationSystem.viewAllHotels();
          // add option to view specific Hotel via hotelName
          if (!hotelReservationSystem.getHotels().isEmpty()) {
            System.out.printf("\nEnter the name of the hotel you want to VIEW (Enter 0 to exit): ");
            String viewHotelName = scanner.nextLine();
            if (viewHotelName.equals("0"))
              System.out.printf("Going back...\n");
            else
              hotelReservationSystem.viewSpecificHotel(viewHotelName);
          }
          break;
        case 3: // manage hotel
          if (hotelReservationSystem.getHotels().isEmpty()) {
            System.out.printf("\nNo hotels present. Create a hotel first.\n");
          }
          else {
            System.out.printf("\n============Manage Hotel============\n");
            hotelReservationSystem.viewAllHotels();
            System.out.println();
            System.out.printf("Enter the name of the hotel you want to MANAGE (Enter 0 to exit): ");
            String manageHotelName = scanner.nextLine();
            if (manageHotelName.equals("0"))
              System.out.printf("Going back...\n");
            else
              hotelReservationSystem.manageHotel(manageHotelName);
          }
          break;
        case 4: // simulate booking
          if (hotelReservationSystem.getHotels().isEmpty()) {
            System.out.printf("\nNo hotels present. Create a hotel first.\n");
          }
          else {
            hotelReservationSystem.viewAllHotels();
            System.out.printf("\n==========Simulate Booking===========\n");
            System.out.printf("Enter hotel name to BOOK from: ");
            String simulateHotelName = scanner.nextLine();
            while (hotelReservationSystem.findHotelByName(simulateHotelName) == null) {
              System.out.printf("\nHotel '%s' not found. Please try again: ", simulateHotelName);
              simulateHotelName = scanner.nextLine();
            }
            System.out.printf("Enter guest name: ");
            String simulateGuestName = scanner.nextLine();
            System.out.printf("Enter check-in date (1-30): ");
            int simulateCheckInDate = scanner.nextInt();
            while (simulateCheckInDate < 1 || simulateCheckInDate > 30) {
              System.out.printf("\nInvalid check-in date. Enter check-in date from 1-30 only: ");
              simulateCheckInDate = scanner.nextInt();
            }
            System.out.printf("Enter check-out date (2-31): ");
            int simulateCheckOutDate = scanner.nextInt();
            while (simulateCheckOutDate < 2 || simulateCheckOutDate > 31) {
              System.out.printf("\nInvalid check-out date. Enter check-out date from 2-31 only: ");
              simulateCheckOutDate = scanner.nextInt();
            }
            // allow overnight booking
            if (simulateCheckInDate <= simulateCheckOutDate)
              hotelReservationSystem.simulateBooking(simulateHotelName, simulateGuestName, simulateCheckInDate, simulateCheckOutDate);
            else
              System.out.printf("\nInvalid check-in and check-out dates. Please try again.\n");
          }
          break;
        default:
          System.out.printf("Invalid choice. Please try again.");
      }
      
    }
    
  }
}
