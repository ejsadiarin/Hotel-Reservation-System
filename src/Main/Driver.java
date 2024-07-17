package Main;

import Controller.HRSController;
import View.DisplayManager;

import java.util.Scanner;

/**
 * The Driver class is the entry point of the hotel management application.
 * It contains the main method that initializes the program and handles user interactions.
 */
public class Driver {

  /**
   * The main method initializes the program and handles user interactions.
   * It provides a menu for users to create and manage hotels, rooms, and reservations.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    HRSController HRSController = new HRSController();
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
          HRSController.createHotel(createHotelName, numOfRooms);
          break;
        case 2: // view hotel
          System.out.printf("\nViewing hotels...\n");
          HRSController.viewAllHotels();
          // add option to view specific Hotel via hotelName
          if (!HRSController.getHotels().isEmpty()) {
            System.out.printf("\nEnter the name of the hotel you want to VIEW (Enter 0 to exit): ");
            String viewHotelName = scanner.nextLine();
            if (viewHotelName.equals("0"))
              System.out.printf("Going back...\n");
            else
              HRSController.viewSpecificHotel(viewHotelName);
          }
          break;
        case 3: // manage hotel
          if (HRSController.getHotels().isEmpty()) {
            System.out.printf("\nNo hotels present. Create a hotel first.\n");
          }
          else {
            System.out.printf("\n============Manage Hotel============\n");
            HRSController.viewAllHotels();
            System.out.println();
            System.out.printf("Enter the name of the hotel you want to MANAGE (Enter 0 to exit): ");
            String manageHotelName = scanner.nextLine();
            if (manageHotelName.equals("0"))
              System.out.printf("Going back...\n");
            else
              HRSController.manageHotel(manageHotelName);
          }
          break;
        case 4: // simulate booking
          if (HRSController.getHotels().isEmpty()) {
            System.out.printf("\nNo hotels present. Create a hotel first.\n");
          }
          else {
            HRSController.viewAllHotels();
            System.out.printf("\n==========Simulate Booking===========\n");
            System.out.printf("Enter hotel name to BOOK from: ");
            String simulateHotelName = scanner.nextLine();
            while (HRSController.findHotelByName(simulateHotelName) == null) {
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
              HRSController.simulateBooking(simulateHotelName, simulateGuestName, simulateCheckInDate, simulateCheckOutDate);
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
