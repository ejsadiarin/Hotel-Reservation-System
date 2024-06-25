import View.DisplayManager;

import java.util.Scanner;

public class Driver {
  public static void main(String[] args) {
    HRSController hrsController = new HRSController();
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
          hrsController.createHotel(createHotelName, numOfRooms);
          break;
        case 2: // view hotel
          System.out.printf("\nViewing hotels...\n");
          hrsController.viewAllHotels();
          // add option to view specific Hotel via hotelName
          if (!hrsController.getHotels().isEmpty()) {
            System.out.printf("\nEnter the name of the hotel you want to VIEW (Enter 0 to exit): ");
            String viewHotelName = scanner.nextLine();
            if (viewHotelName.equals("0"))
              System.out.printf("Going back...\n");
            else
              hrsController.viewSpecificHotel(viewHotelName);
          }
          break;
        case 3: // manage hotel
          if (hrsController.getHotels().isEmpty()) {
            System.out.printf("\nNo hotels present. Create a hotel first.\n");
          }
          else {
            System.out.printf("\n============Manage Hotel============\n");
            hrsController.viewAllHotels();
            System.out.println();
            System.out.printf("Enter the name of the hotel you want to MANAGE (Enter 0 to exit): ");
            String manageHotelName = scanner.nextLine();
            if (manageHotelName.equals("0"))
              System.out.printf("Going back...\n");
            else
              hrsController.manageHotel(manageHotelName);
          }
          break;
        case 4: // simulate booking
          if (hrsController.getHotels().isEmpty()) {
            System.out.printf("\nNo hotels present. Create a hotel first.\n");
          }
          else {
            System.out.printf("\n==========Simulate Booking===========\n");
            System.out.printf("Enter hotel name to BOOK from: ");
            String simulateHotelName = scanner.nextLine();
            System.out.printf("\nEnter guest name: ");
            String simulateGuestName = scanner.nextLine();
            System.out.printf("\nEnter check-in date (1-30): ");
            int simulateCheckInDate = scanner.nextInt();
            System.out.printf("\nEnter check-out date (2-31): ");
            int simulateCheckOutDate = scanner.nextInt();
            if (simulateCheckInDate <= simulateCheckOutDate)
              hrsController.simulateBooking(simulateHotelName, simulateGuestName, simulateCheckInDate, simulateCheckOutDate);
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
