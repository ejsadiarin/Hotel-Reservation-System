package View;

import Model.Hotel;
import Model.Reservation;
import Model.Room;

public class DisplayManager {
  public static void displayHotelGeneralInfo(Hotel hotel) {
    // display high-level information about the hotel
    System.out.printf("\n===========HIGH-LEVEL INFORMATION==============\n");
    System.out.printf("Hotel Name: %s\n", hotel.getName());
    System.out.printf("Estimated Earnings: %s\n", hotel.getEstimatedEarnings());
    System.out.printf("Number of Rooms: %s\n", hotel.getNumOfRooms());
  }

  // 1: Total number of available and booked rooms for a selected date
  public static void displayRoomsOnDate(Hotel hotel, int date) {
    int availableRooms = 0;
    int bookedRooms = 0;

    for (Room room : hotel.getRooms()) {
      if (room.isAvailable(date, date + 1)) {
        availableRooms++;
      } else {
        bookedRooms++;
      }
    }
    System.out.printf("Total number of available rooms on day %d: %d\n", date, availableRooms);
    System.out.printf("Total number of booked rooms on day %d: %d\n", date, bookedRooms);
  }

  // 2: Information about a selected room
  public static void displaySpecificRoomInfo(Hotel hotel, String roomName) {
    Room selectedRoom = null;
    for (Room room : hotel.getRooms()) {
      if (room.getName().equals(roomName)) {
        selectedRoom = room;
        break;
      }
    }

    if (selectedRoom != null) {
      System.out.printf("\n===========ROOM INFORMATION==============\n");
      System.out.printf("Room Name: %s\n", selectedRoom.getName());
      System.out.printf("Price per Night: %.2f\n", selectedRoom.getPricePerNight());
      System.out.printf("Availability for the entire month:\n");

      for (int i = 1; i <= 30; i++) {
        System.out.printf("Day %d: %s\n", i, selectedRoom.isAvailable(i, i + 1) ? "Available" : "Booked");
      }
    } else {
      System.out.printf("\nRoom '%s' not found.\n", roomName);
    }
    
  }

  // 3: Information about a selected reservation
  public static void displaySelectedReservation(Hotel hotel, String guestName) {
    Reservation selectedReservation = null;
    for (Reservation reservation : hotel.getReservations()) {
      if (reservation.getGuestName().equals(guestName)) {
        selectedReservation = reservation;
        break;
      }
    }

    if (selectedReservation != null) {
      System.out.printf("\n===========RESERVATION INFORMATION==============\n");
      System.out.printf("Guest Name: %s\n", selectedReservation.getGuestName());
      System.out.printf("Room Name: %s\n", selectedReservation.getRoom().getName());
      System.out.printf("Check-in Date: %d\n", selectedReservation.getCheckInDate());
      System.out.printf("Check-out Date: %d\n", selectedReservation.getCheckOutDate());
      System.out.printf("Total Price: %.2f\n", selectedReservation.getTotalPrice());

      System.out.printf("Price Breakdown per Night:\n");
      for (int i = selectedReservation.getCheckInDate(); i < selectedReservation.getCheckOutDate(); i++) {
        System.out.printf("Day %d: %.2f\n", i, selectedReservation.getRoom().getPricePerNight());
      }
    } else {
      System.out.printf("\nReservation for guest '%s' not found.\n", guestName);
    }

    System.out.printf("=========================================\n");
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
