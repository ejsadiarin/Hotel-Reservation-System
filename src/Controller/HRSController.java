package Controller;

import Helper.InputHelper;
import Model.AvailabilityDate;
import Model.Hotel;

import Helper.MessageHelper;
import Model.Reservation;
import Model.Room;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The HotelReservationSystem class manages the operations related to hotel
 * reservations.
 * This allows creating hotels, viewing hotel details, managing hotel
 * properties, and simulating bookings.
 */
public class HRSController {
  private ArrayList<Hotel> hotels;

  /**
   * Constructs a new HotelReservationSystem with an empty list of hotels.
   */
  public HRSController() {
    this.hotels = new ArrayList<>();
  }

  // public void showMainView() {
  // this.view.setContentPane(this.view.getContentPane());
  // this.view.invalidate();
  // this.view.validate();
  // }

  /**
   * Creates a new hotel with the specified name and number of rooms.
   */
  public void createHotel(String hotelName, int numOfRooms) {

    for (Hotel hotel : hotels) {
      if (hotel.getName().equals(hotelName)) {
        MessageHelper.showErrorMessage(String.format("Hotel name '%s' already exists.", hotelName));
        return;
      }
    }
    if (numOfRooms < 1 || numOfRooms > 50)
      MessageHelper.showErrorMessage( String.format("'%d' number of rooms is not allowed (must be from 1 to 50 only).", numOfRooms));
    else {
      hotels.add(new Hotel(hotelName, numOfRooms));
      MessageHelper .showSuccessMessage(String.format("Hotel '%s' successfully created with %d rooms.", hotelName, numOfRooms));
    }

    // TODO: Clear text field in CreateHotelPanel view
  }

  /**
   * Displays the names of all hotels in a list.
   * @return a string array list of hotel names
   */
  public ArrayList<String> getHotelNames() {
    ArrayList<String> hotelNames = new ArrayList<>();

    for (Hotel hotel : this.getHotels()) {
      hotelNames.add(hotel.getName());
    }

    return hotelNames;
  }
  
  public HashMap<String, String> getHotelGeneralInfo(String hotelName) {
    HashMap<String, String> info = new HashMap<>();
    Hotel selectedHotel = findHotelByName(hotelName);
    int overallReservations = 0;
    
    if (selectedHotel != null) {
      info.put("Hotel Name", selectedHotel.getName());
      info.put("Base Price Per Room", String.valueOf(selectedHotel.getBasePrice()));
      info.put("Estimated Earnings", String.valueOf(selectedHotel.getEstimatedEarnings()));
      info.put("Number of Rooms", String.valueOf(selectedHotel.getNumOfRooms()));
      for (Room room : selectedHotel.getRooms()) {
        overallReservations += room.getReservations().size();
      }
      info.put("Overall Reservations", String.valueOf(overallReservations));
    }

    return info;
  }
  
  /**
   * @param hotelName is the name of the hotel to be checked
   * @return name of the hotel, otherwise null. 
   */
  public String checkIfHotelExists(String hotelName) {
    Hotel hotel = findHotelByName(hotelName);
    if (hotel != null) {
      return hotel.getName();
    }
    
    return null;
  }
  
  public String checkIfRoomExists(String roomName, String hotelName) {
    if (checkIfHotelExists(hotelName) != null) {
      Hotel selectedHotel = findHotelByName(hotelName);
      Room selectedRoom = selectedHotel.getRoom(roomName);
      if (selectedRoom != null) {
        return selectedRoom.getName();
      }
    }
    return null;
  }
  
  public String checkIfReservationExists(String hotelName, String roomName, String reservationId) {
    if (checkIfHotelExists(hotelName) != null && checkIfRoomExists(roomName, hotelName) != null) {
      Hotel selectedHotel = findHotelByName(hotelName);
      Room selectedRoom = selectedHotel.getRoom(roomName);
      Reservation selectedReservation = selectedRoom.getReservation(Integer.parseInt(reservationId));
      if (selectedReservation != null) {
        return String.valueOf(selectedReservation.getId());
      }
    }
    return null;
  }


//  public HashMap<> getRoomsOnDate() {
//
//  }
  
  /*
  * 
  * @return room name, room type, price per night, and number of reservations
  * */
  public ArrayList<HashMap<String, String>> getAllRoomInfoOnHotel(String hotelName) {
    if (checkIfHotelExists(hotelName) != null) {
      ArrayList<HashMap<String, String>> listOfRoomInfo = new ArrayList<>();
      Hotel selectedHotel = findHotelByName(hotelName);
      
      for (Room room : selectedHotel.getRooms()) {
        HashMap<String, String> specificRoomInfo = getSpecificRoomInfo(hotelName, room.getName());
        listOfRoomInfo.add(specificRoomInfo);
      }
      return listOfRoomInfo;
    }
    
    return null;
  }
  
  public ArrayList<HashMap<String, String>> getAllDatesOnRoom(String hotelName, String roomName) {
    if (checkIfHotelExists(hotelName) != null && checkIfRoomExists(roomName, hotelName) != null) {
      ArrayList<HashMap<String, String>> listOfDateInfo = new ArrayList<>();
      Hotel selectedHotel = findHotelByName(hotelName);
      Room selectedRoom = selectedHotel.getRoom(roomName);
      // AvailabilityDate is always created so no need for null checks
      for (AvailabilityDate date : selectedRoom.getAvailabilityDates()) {
        HashMap<String, String> specificDateInfo = getSpecificDateInfo(hotelName, roomName, date.getDateNumber());
        listOfDateInfo.add(specificDateInfo);
      }
      return  listOfDateInfo;
    }
    return null;
  }
  
  public ArrayList<HashMap<String, String>> getAllReservationInfoOnRoom(String hotelName, String roomName) {
    if (checkIfHotelExists(hotelName) != null && checkIfRoomExists(roomName, hotelName) != null) {
      ArrayList<HashMap<String, String>> listOfReservationInfo = new ArrayList<>();
      Hotel selectedHotel = findHotelByName(hotelName);
      Room selectedRoom = selectedHotel.getRoom(roomName);
      
      if (selectedRoom != null) {
        for (Reservation reservation : selectedRoom.getReservations()) {
          HashMap<String, String> specificReservationInfo = getSpecificReservationInfo(hotelName, roomName, reservation.getId());
          listOfReservationInfo.add(specificReservationInfo);
        }
        return listOfReservationInfo;
      }
    }
    return null;
  }

  /**
   * Gets the available rooms given a certain check-in and check-out date. 
   */
  public ArrayList<HashMap<String, String>> getAllRoomsAvailableOnDate(String hotelName, int checkInDate, int checkOutDate) {
    ArrayList<HashMap<String, String>> allRoomsAvailableOnDateList = new ArrayList<>();
    Hotel selectedHotel = findHotelByName(hotelName);
    ArrayList<Room> availableRooms = selectedHotel.getAvailableRoomsOnDate(checkInDate, checkOutDate);
    for (Room room : availableRooms) {
      HashMap<String, String> availableRoomsInfo = new HashMap<>();
      availableRoomsInfo.put("Room Name", room.getName());
      availableRoomsInfo.put("Room Type", room.getRoomType());
      availableRoomsInfo.put("Price Per Night", String.valueOf(room.getPricePerNight()));
      availableRoomsInfo.put("Number of Reservations", String.valueOf(room.getReservations().size()));
      allRoomsAvailableOnDateList.add(availableRoomsInfo);
    }
    return allRoomsAvailableOnDateList;
  }
  
  public HashMap<String, String> getSpecificRoomInfo(String hotelName, String roomName) {
    if (checkIfHotelExists(hotelName) != null && checkIfRoomExists(roomName, hotelName) != null) {
      HashMap<String, String> specificRoomInfo = new HashMap<>();
      Hotel selectedHotel = findHotelByName(hotelName);
      Room selectedRoom = selectedHotel.getRoom(roomName);
      double estimatedEarnings = 0.0;
      
      if (selectedRoom != null) {
        specificRoomInfo.put("Room Name", selectedRoom.getName());
        specificRoomInfo.put("Room Type", selectedRoom.getRoomType());
        specificRoomInfo.put("Price Per Night", String.valueOf(selectedRoom.getPricePerNight()));
        specificRoomInfo.put("Number of Reservations", String.valueOf(selectedRoom.getReservations().size()));
        for (Reservation reservation : selectedRoom.getReservations()) {
          estimatedEarnings += reservation.getTotalPrice();
        }
        specificRoomInfo.put("Estimated Earnings", String.valueOf(estimatedEarnings));
      }
      return specificRoomInfo;
    }
    
    return null;
  }
  
  public HashMap<String, String> getSpecificDateInfo(String hotelName, String roomName, int dateNumber) {
   if (checkIfHotelExists(hotelName) != null && checkIfRoomExists(roomName, hotelName) != null) {
     HashMap<String, String> specificDateInfo = new HashMap<>();
     Hotel selectedHotel = findHotelByName(hotelName);
     Room selectedRoom = selectedHotel.getRoom(roomName);
     AvailabilityDate date = selectedRoom.getAvailabilityDate(dateNumber);
     if (date != null) {
       specificDateInfo.put("Date Number", String.valueOf(date.getDateNumber()));
       specificDateInfo.put("Is Available", date.isAvailable() ? "Available" : "Booked");
       specificDateInfo.put("Base Price", String.valueOf(date.getBasePrice()));
       specificDateInfo.put("Modified Price", String.valueOf(date.getModifiedPrice()));
     }
     return specificDateInfo;
   } 
   
   return null;
  }
  
  public HashMap<String, String> getSpecificReservationInfo(String hotelName, String roomName, int id) {
    if (checkIfHotelExists(hotelName) != null && checkIfRoomExists(roomName, hotelName) != null) {
      HashMap<String, String> specificReservationInfo = new HashMap<>();
      Hotel selectedHotel = findHotelByName(hotelName);
      Room selectedRoom = selectedHotel.getRoom(roomName);
      Reservation selectedReservation = selectedRoom.getReservation(id);
      if (selectedReservation != null) {
        specificReservationInfo.put("Id", String.valueOf(selectedReservation.getId()));
        specificReservationInfo.put("Guest Name", selectedReservation.getGuestName());
        specificReservationInfo.put("Check In Date", String.valueOf(selectedReservation.getCheckInDate()));
        specificReservationInfo.put("Check Out Date", String.valueOf(selectedReservation.getCheckOutDate()));
        specificReservationInfo.put("Total Price", String.valueOf(selectedReservation.getTotalPrice()));
      }
      return specificReservationInfo;
    }
    
    return null;
  }


  /**
   * @return the list of date numbers as a string of the booked dates in a room 
   */
  public ArrayList<String> getRoomBookedDatesList(String hotelName, String roomName) {
    if (checkIfHotelExists(hotelName) != null && checkIfRoomExists(roomName, hotelName) != null) {
      ArrayList<String> bookedDates = new ArrayList<>();
      Hotel selectedHotel = findHotelByName(hotelName);
      Room selectedRoom = selectedHotel.getRoom(roomName);

      if (selectedRoom != null) {
        for (AvailabilityDate date : selectedRoom.getReservedDates()) {
          bookedDates.add(String.valueOf(date.getDateNumber()));
        }
        return bookedDates;
      }
    }
    return null;
  }
  
  public ArrayList<String> getRoomAvailableDatesList(String hotelName, String roomName) {
    if (checkIfHotelExists(hotelName) != null && checkIfRoomExists(roomName, hotelName) != null) {
      ArrayList<String> availableDates = new ArrayList<>();
      Hotel selectedHotel = findHotelByName(hotelName);
      Room selectedRoom = selectedHotel.getRoom(roomName);
      if (selectedRoom != null) {
        for (AvailabilityDate date : selectedRoom.getAvailabilityDates()) {
          if (date.isAvailable())
            availableDates.add(String.valueOf(date.getDateNumber()));
        }
        return availableDates;
      }
    }
    return null;
  }
  
  public ArrayList<String> getPriceBreakdownOnReservationList(String hotelName, String roomName, String reservationId) {
    if (checkIfReservationExists(hotelName, roomName, reservationId) != null) {
      ArrayList<String> listOfPriceBreakdown = new ArrayList<>();
      Hotel selectedHotel = findHotelByName(hotelName);
      Room selectedRoom = selectedHotel.getRoom(roomName);
      Reservation selectedReservation = selectedRoom.getReservation(Integer.parseInt(reservationId));
      if (selectedReservation != null) {
        for (int i = selectedReservation.getCheckInDate() - 1; i < selectedReservation.getCheckOutDate(); i++) {
          if (selectedReservation.getCheckInDate() == selectedReservation.getCheckOutDate()) { // handle overnight case
            listOfPriceBreakdown.add(String.format("Overnight Stay %d: %.2f", i+1, selectedReservation.getRoom().getPriceOnDate(i+1)));
            break;
          } 
          // if i != index of check out date, then add (prevents extra day issue)
          if (i != (selectedReservation.getCheckOutDate() - 1))
            listOfPriceBreakdown.add(String.format("Day %d-%d: %.2f", i+1, i+2, selectedReservation.getRoom().getPriceOnDate(i+1)));
        }
        return listOfPriceBreakdown;
      }
    }
    
    return null;
  }
  
  /**
   * @return true if successful, otherwise false
   * */
  public boolean changeHotelName(String hotelToChange, String newName) {
    if (checkIfHotelExists(newName) != null) {
      MessageHelper.showErrorMessage(String.format("Hotel name %s already exists!", newName));
      return false;
    }
    Hotel selectedHotel = findHotelByName(hotelToChange);
    if (selectedHotel != null) {
      selectedHotel.setName(newName);
      MessageHelper.showSuccessMessage(String.format("Hotel name successfully changed to %s!", newName));
      return true;
    }
    
    return false;
  }
  
  public void addRoom(String hotelName, String roomType) {
    if (!(roomType.equals("Standard") || roomType.equals("Deluxe") || roomType.equals("Executive"))) {
      MessageHelper.showErrorMessage("Invalid room type!");
      return;
    }
    Hotel selectedHotel = findHotelByName(hotelName);
    if (selectedHotel != null) {
      boolean isSuccessfullyAdded = selectedHotel.addRoom(roomType);
      if (isSuccessfullyAdded)
        MessageHelper.showSuccessMessage("Successfully added a new room!");
      else // only check for max room limit, since there is no way that room names can be equal (or there is?) thanks to automated room name creation (or maybe some cosmic ray will flip the bits and change its value)
        MessageHelper.showErrorMessage(String.format("Limit reached (%d). Cannot add more rooms.", selectedHotel.getNumOfRooms()));
    }
  }
  
  public void removeRoom(String hotelName, String roomToRemove) {
    Hotel selectedHotel = findHotelByName(hotelName);
    Room selectedRoom = selectedHotel.getRoom(roomToRemove);
    
    // ensure that there is no reservations in the room first before removing it
    if (selectedRoom != null && selectedRoom.getReservations().isEmpty()) {
      // ask for confirmation
      int confirmation = InputHelper.askConfirmation(String.format("Confirm removing %s?", roomToRemove));
      if (confirmation == 1) { // 0 is yes, 1 is no
        MessageHelper.showCancelMessage();
        return;
      }
      selectedHotel.removeRoom(roomToRemove);
      MessageHelper.showSuccessMessage(String.format("Successfully removed Room %s from hotel %s!\n", roomToRemove, hotelName));
    }
    else
      MessageHelper.showErrorMessage("Cannot remove rooms since there are some reservations existing or room doesn't exist at all.");
  }
  
  public void updateBasePrice(String hotelName, double newBasePrice) {
    Hotel selectedHotel = findHotelByName(hotelName);
    if (selectedHotel.areEmptyReservations()) {
      if (newBasePrice < 100.0) {
        MessageHelper.showErrorMessage("Base price must be at least 100.0!");
        return;
      }
      selectedHotel.setBasePrice(newBasePrice);
      MessageHelper.showSuccessMessage(String.format("Base price updated to %.2f for hotel '%s'.", newBasePrice, selectedHotel.getName()));
    }
    else 
      MessageHelper.showErrorMessage("There are some reservations left! Cannot update base price.");
  }
  
  /**
   * Removes a reservation in the specified room given the reservation ID.
   * 
   * @param hotelName is the name of the hotel that the room belongs to
   * @param roomName is the name of the room that the reservation belongs to
   * @param reservationId is the ID of the reservation to be removed
   * @return true if successful, otherwise false
   * */
  public boolean removeReservation(String hotelName, String roomName, int reservationId) {
    Hotel selectedHotel = findHotelByName(hotelName);
    Room selectedRoom = selectedHotel.getRoom(roomName);
    Reservation selectedReservation = selectedRoom.getReservation(reservationId);
    if (!selectedRoom.getReservations().isEmpty() && selectedReservation != null) {
      int confirmation = InputHelper.askConfirmation(String.format("REMOVING Reservation with ID '%d': Are you sure?", selectedReservation.getId()));
      if (confirmation == 1) {
        MessageHelper.showCancelMessage();
        return false;
      }
      selectedRoom.removeReservation(reservationId);
      MessageHelper.showSuccessMessage(String.format("Reservation with ID '%d' is successfully removed!", selectedReservation.getId()));
      return true;
    }
    
    return false;
  }
  
  /**
   * @param hotelName is the name of the hotel to be removed
   * @return true if hotelName is successfully removed, otherwise false
   * */
  public boolean removeHotel(String hotelName) {
    Hotel selectedHotel = findHotelByName(hotelName);
    if (selectedHotel != null && selectedHotel.areEmptyReservations()) {
      int confirmation = InputHelper.askConfirmation(String.format("REMOVING Hotel '%s': Are you sure?", selectedHotel.getName()));
      if (confirmation == 1) {
        MessageHelper.showCancelMessage();
        return false;
      }
      hotels.remove(selectedHotel);
      MessageHelper.showSuccessMessage(String.format("Hotel '%s' successfully removed!", selectedHotel.getName()));
      return true;
    }
    
    return false;
  }
  
  /**
   * Modifies the price by a specified percentage on a given date.
   * 
   * @param hotelName is the name of the hotel that the room belongs to
   * @param roomName is the name of the room that the reservation belongs to
   * @param dateInput is the date or day that will be modified
   * @param modifierInput is the modifier to be used
   * @return true if modification of price on date is successful, otherwise false
   * */
  public boolean datePriceModifier(String hotelName, String roomName, int dateInput, int modifierInput) {
    if (checkIfHotelExists(hotelName) != null && checkIfRoomExists(roomName, hotelName) != null) {
      Hotel selectedHotel = findHotelByName(hotelName);
      Room selectedRoom = selectedHotel.getRoom(roomName);
      AvailabilityDate selectedDate = selectedRoom.getAvailabilityDate(dateInput);
      if (selectedDate != null) {
        double parsedModifier = (double) modifierInput / 100;
        selectedDate.setModifiedPrice(parsedModifier);
        MessageHelper.showSuccessMessage(String.format("Successfully modified the price by %d%% on Day '%d'!", modifierInput, dateInput));
        return true;
      }
      else
        MessageHelper.showErrorMessage(String.format("Day %d does not exist! Please try again.", dateInput));
    }
    return false;
  }
  
  public boolean areDatesValid(int checkInDate, int checkOutDate) {
    if (checkInDate <= checkOutDate) {
      return true; // valid
    }
    return false;
  }
  
  public boolean bookRoom(String hotelName, String guestName, String roomType, int checkInDate, int checkOutDate) {
    if (checkIfHotelExists(hotelName) != null) {
      Hotel selectedHotel = findHotelByName(hotelName);
      ArrayList<Room> availableRoomsOnDate = selectedHotel.getAvailableRoomsOnDate(checkInDate, checkOutDate, roomType);
      if (availableRoomsOnDate.isEmpty()) {
        MessageHelper.showErrorMessage(String.format("No %s rooms available on Day %d to %d", roomType, checkInDate, checkOutDate));
        return false;
      }
      Room assignedRoom = availableRoomsOnDate.getFirst();
      assignedRoom.addReservation(guestName, checkInDate, checkOutDate);
      if (checkInDate == checkOutDate)
        MessageHelper.showSuccessMessage(String.format("Successfully booked a room (ROOM: '%s') for an OVERNIGHT stay on Day %d", assignedRoom.getName(), checkInDate));
      else 
        MessageHelper.showSuccessMessage(String.format("Successfully booked a room (ROOM: '%s') on Day %d to %d", assignedRoom.getName(), checkInDate, checkOutDate));
      return true;
    }
    return false;
  }
  

  // /**
  // * Simulates the booking of a room in a specified hotel for a guest within a
  // * date range.
  // *
  // * @param hotelName the name of the hotel
  // * @param guestName the name of the guest
  // * @param checkInDate the check-in date
  // * @param checkOutDate the check-out date
  // */
  // public void simulateBooking(String hotelName, String guestName, int
  // checkInDate, int checkOutDate) {
  // Hotel chosenHotel = findHotelByName(hotelName);
  //
  // if (chosenHotel != null) {
  // ArrayList<Room> availableRooms =
  // chosenHotel.getAvailableRoomsOnDate(checkInDate, checkOutDate);
  // if (availableRooms.isEmpty())
  // System.out.printf("No rooms available from day %d to day %d in hotel
  // '%s'.\n", checkInDate, checkOutDate,
  // hotelName);
  // else {
  // System.out.printf("Available rooms from day %d to day %d in hotel '%s':\n",
  // checkInDate, checkOutDate,
  // hotelName);
  // for (Room room : availableRooms)
  // System.out.printf("%s\n", room.getName());
  //
  // // automated booking of room - get first available room
  // Room roomToBook = availableRooms.getFirst();
  // roomToBook.addReservation(guestName, checkInDate, checkOutDate);
  // System.out.printf("Booking successful for room '%s'.\n",
  // roomToBook.getName());
  // }
  // } else
  // System.out.printf("Hotel '%s' not found.\n", hotelName);:
  // }

  /**
   * Finds a hotel by its name.
   *
   * @param hotelName the name of the hotel to find
   * @return the Hotel object if found, otherwise null
   */
  public Hotel findHotelByName(String hotelName) {
    for (Hotel hotel : hotels) {
      if (hotel.getName().equals(hotelName))
        return hotel;
    }
    return null;
  }

  /**
   * Gets the list of all hotels.
   *
   * @return the list of hotels
   */
  public ArrayList<Hotel> getHotels() {
    return this.hotels;
  }
}
