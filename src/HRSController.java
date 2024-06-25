import Model.Hotel;
import Model.Reservation;
import Model.Room;
import View.DisplayManager;

import java.util.ArrayList;
import java.util.Scanner;

public class HRSController {
  private ArrayList<Hotel> hotels;
  
  public HRSController() {
    this.hotels = new ArrayList<>();
  }
  
  public void createHotel(String hotelName, int numOfRooms) {
    for (Hotel hotel : hotels) {
      if (hotel.getName().equals(hotelName)) {
        System.out.printf("Hotel name '%s' already exists.\n", hotelName);
        return;
      }
    }
    hotels.add(new Hotel(hotelName, numOfRooms));
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
    if (this.hotels.isEmpty()) {
      System.out.printf("No hotels found.\n");
    }
    else {
      for (Hotel hotel : hotels) {
        if (findHotelByName(hotelName) != null) {
          DisplayManager.displayHotelGeneralInfo(hotel);
          System.out.printf("\n=========ROOM DETAILS=========\n");
          System.out.printf("Base Price per Room: %s\n", hotel.getBasePrice());
//          System.out.printf("\n=====SEE AVAILABLE ROOMS======\n");
//          for (Room room : hotel.getRooms()) {
//            System.out.printf("%s - %s\n", room.getName(), room.getAvailability() ? "Available" : "Unavailable");
//          }
          System.out.printf("\n=====RESERVATION DETAILS======\n");
          System.out.printf("Base Price per Room: %s\n", hotel.getBasePrice());
          System.out.printf("=========================================\n");
        }
        else
          System.out.printf("Hotel name '%s' not found.\n", hotelName);
      }
    }
  }
  
  public void manageHotel(String hotelName) {
    Scanner scanner = new Scanner(System.in);

    Hotel chosenHotel = findHotelByName(hotelName);

    if (chosenHotel == null) {
      System.out.printf("Hotel name '%s' not found. Exiting...\n", hotelName);
    }
    else {
      while (true) {
        DisplayManager.displayManageHotelUI();

        System.out.printf("Choose your action (Enter 0 to exit): ");
        int choice = scanner.nextInt();
        scanner.nextLine();        
          
        switch (choice) {
          case 0: // go back to previous          
            System.out.printf("Exiting...\n");
            return;          
          case 1:// change name of hotel (name must still be unique)
            System.out.printf("Set new name: ");
            // System.out.printf("Set new name (Enter 0 to exit): ");
            String newHotelName = scanner.nextLine();
            if (findHotelByName(newHotelName) == null) {
              chosenHotel.setName(newHotelName);
              System.out.printf("Successfully changed name to %s!\n", newHotelName);
              return;
            }
            else
              System.out.printf("%s already exists. Going back...\n", newHotelName);
            break;
          case 2: // add room(s)
            // TODO: option to add multiple rooms in one go
            chosenHotel.addRoom();
            System.out.printf("Successfully added a new room to %s!\n", chosenHotel.getName());
            break;
          case 3: // remove room(s)
            System.out.printf("Enter room name to remove: ");
            String removeRoomName = scanner.nextLine();
            chosenHotel.removeRoom(removeRoomName);
            System.out.printf("Successfully removed Room %s from hotel %s!\n", removeRoomName, chosenHotel.getName());
            break;
          case 4: // update base price of rooms
            System.out.printf("Enter new base price for rooms: ");
            double newBasePrice = scanner.nextDouble();
            chosenHotel.setBasePrice(newBasePrice);
            System.out.printf("Base price updated to %.2f for hotel '%s'.\n", newBasePrice, chosenHotel.getName());
            break;
          case 5: // remove reservation
            System.out.printf("Enter guest name to remove reservation: ");
            String guestName = scanner.nextLine();
            chosenHotel.removeReservation(guestName);
            System.out.printf("Reservation for guest '%s' removed from hotel '%s'.\n", guestName, chosenHotel.getName());
            break;
          case 6: // remove hotel
            hotels.remove(chosenHotel);
            System.out.printf("Successfully removed hotel '%s'!\n", chosenHotel.getName());
            return;
          default:
            System.out.printf("Invalid choice. Please try again.\n");
        }
      }
    }
  }

  public void simulateBooking(String hotelName, String guestName, int checkInDate, int checkOutDate) {
    Hotel chosenHotel = findHotelByName(hotelName);
    
    if (chosenHotel != null) {
      ArrayList<Room> availableRooms = chosenHotel.getAvailableRoomsOnDate(checkInDate, checkOutDate);
      if (availableRooms.isEmpty())
        System.out.printf("No rooms available from day %d to day %d in hotel '%s'.\n", checkInDate, checkOutDate, hotelName);
      else {
        System.out.printf("Available rooms from day %d to day %d in hotel '%s':\n", checkInDate, checkOutDate, hotelName);
        for (Room room : availableRooms)
          System.out.printf("%s\n", room.getName());

        // automated booking of room - get first available room
        Room roomToBook = availableRooms.getFirst();
        chosenHotel.addReservation(guestName, roomToBook.getName(), checkInDate, checkOutDate);
        System.out.printf("Booking successful for room '%s'.\n", roomToBook.getName());
      }
    }
    else
      System.out.printf("Hotel '%s' not found.\n", hotelName);
  }

  private Hotel findHotelByName(String hotelName) {
    for (Hotel hotel : hotels) {
      if (hotel.getName().equals(hotelName)) 
        return hotel;
    }
    return null;
  }
  
  public ArrayList<Hotel> getHotels() {
    return this.hotels;
  }
}
