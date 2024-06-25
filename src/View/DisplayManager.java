package View;

import Model.Hotel;
import Model.Reservation;
import Model.Room;

import java.util.ArrayList;

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
    Room selectedRoom = hotel.getRoom(roomName);
    
    if (selectedRoom != null) {
      System.out.printf("\n===========ROOM INFORMATION==============\n");
      System.out.printf("Room Name: %s\n", selectedRoom.getName());
      System.out.printf("Price per Night: %.2f\n", selectedRoom.getPricePerNight());
      System.out.printf("Room Earnings: %.2f\n", selectedRoom.getTotalEarnings());
      System.out.printf("Number of Reservations: %d\n", selectedRoom.getReservations().size());
      System.out.printf("\n-----Availability for the entire month:-----\n");
      for (int i = 1; i <= 30; i++) {
        System.out.printf("Day %d: %s\n", i, selectedRoom.isAvailable(i, i + 1) ? "Available" : "Booked");
      }
      
    } 
    else
      System.out.printf("\nRoom '%s' not found.\n", roomName);
  }

  public static void displayReservationInfoByRoom(Hotel hotel, Room room) {
    System.out.printf("\n===========RESERVATION INFORMATION for Room %s==============\n", room.getName());
    if (room.getReservations().isEmpty()) {
      System.out.printf("No reservations found for room '%s'.\n", room.getName());
    } else {
      System.out.printf("Reservations for room '%s' (%d in total):\n", room.getName(), room.getReservations().size());
      
      // shows the reservations in the selected room separated by guest name
      for (Reservation reservation : room.getReservations()) {
        System.out.printf("\n======================================\n");
        System.out.printf("Guest: %s, Check-in: %d, Check-out: %d\n", reservation.getGuestName(), reservation.getCheckInDate(), reservation.getCheckOutDate());
        System.out.printf("Total Price: %.2f\n", reservation.getTotalPrice());
        System.out.printf("Price Breakdown per Night:\n");
        for (int i = reservation.getCheckInDate(); i < reservation.getCheckOutDate(); i++) {
          System.out.printf("Day %d: %.2f\n", i, reservation.getRoom().getPricePerNight());
        }
        System.out.printf("\n======================================\n");
      }
    }
  }

  // 3: Information about a selected reservation
  public static void displayReservationsByGuestName(Hotel hotel, String guestName) {
    boolean hasReservation = false; // false, bc assume that there is no reservation for guest yet
    
    for (Room room : hotel.getRooms()) {
      Reservation selectedReservation = room.getReservation(guestName);
      if (selectedReservation != null) {
        hasReservation = true;
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
      }
    }
      
    // if no reservation for guest 
    if (!hasReservation) {
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
  
  public static void displayAllHotels(ArrayList<Hotel> hotels) {
    System.out.printf("\n-----ALL HOTELS------\n");
    for (Hotel hotel : hotels) {
      System.out.printf("%s\n", hotel.getName());
    }
  }
  
  public static void displayAllRoomsInHotel(Hotel hotel) {
    System.out.printf("\n-----ALL ROOMS in Hotel %s------\n", hotel.getName());
    for (Room room : hotel.getRooms()) {
      System.out.printf("%s - %d total booked reservations\n", room.getName(), room.getReservations().size());
    }
  } 
}
