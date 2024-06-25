package Model;

import java.util.ArrayList;
import java.util.Formattable;

public class Hotel {
  private String name;
  private ArrayList<Room> rooms;
  private double basePrice;
  private final int maxRooms; // always 50
  private int numOfRooms; // actual number of rooms (initialized rooms)
  
  public Hotel(String name, int numOfRooms) {
    this.name = name; // must be unique
    this.rooms = new ArrayList<>();
    this.basePrice = 1299.0;
    this.maxRooms = 50;
    this.setNumOfRooms(numOfRooms);
    this.initializeRooms(this.numOfRooms);
  }

  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public ArrayList<Room> getRooms() {
    return rooms;
  }
  
  private void initializeRooms(int numOfRooms) {
    // automated unique naming of room names until numOfRooms
    int roomNumber = 1;
    while (roomNumber <= numOfRooms) {
      String roomName = "Room-" + roomNumber;
      rooms.add(new Room(roomName, this.basePrice));
      roomNumber++;
    }
  }
  
  public Room getRoom(String roomName) {
    for (Room room : rooms) {
      if (room.getName().equals(roomName))
        return room;
    }
    return null;
  }
  
  public void addRoom() {
    // automated unique naming of room names until numOfRooms
    String roomName = "Room-" + (getNumOfRooms() + 1);
    for (Room room : rooms) {
      if (room.getName().equals(roomName)) {
        System.out.printf("Room name already exists.\n");
        return;
      }
    }
    if (getNumOfRooms() > getMaxRooms()) {
      System.out.printf("Limit reached (%d). Cannot add more rooms.\n", getNumOfRooms());
      return;
    }
    rooms.add(new Room(roomName, this.basePrice));
  }
  
  public void removeRoom(String roomName) {
    rooms.removeIf(room -> room.getName().equals(roomName));
  }
  
  public int getNumOfRooms() {
    return rooms.size();
  }

  public void setNumOfRooms(int numOfRooms) {
    // if invalid numOfRooms then set to default minimum of 1 room
    if (numOfRooms > getMaxRooms() || numOfRooms < 1) {
      System.out.printf("Cannot have %d number of rooms (minimum is 1, maximum is 50). Defaulting to minimum of 1 room.\n", numOfRooms);
      this.numOfRooms = 1;
    }
    else
      this.numOfRooms = numOfRooms;
  }
  
  public int getMaxRooms() {
    return this.maxRooms;
  }
  
  public ArrayList<Room> getAvailableRoomsOnDate(int checkInDate, int checkOutDate) {
    ArrayList<Room> availableRooms = new ArrayList<>();
    
    for (Room room : rooms) {
      if (room.isAvailable(checkInDate, checkOutDate))
        availableRooms.add(room);
    }
    
    return availableRooms;
  }
  
  public boolean areEmptyReservations() {
    for (Room room : rooms) {
      if (!room.getReservations().isEmpty()) {
        return false;
      }
    }
    return true;
  }

  public double getBasePrice() {
    return basePrice;
  }

  public void setBasePrice(double basePrice) {
    // assumes that there are no reservations for ALL rooms
    this.basePrice = basePrice;
    for (Room room : rooms)
      room.setPricePerNight(basePrice);
  }
  
  public double getEstimatedEarnings() {
    double totalEarnings = 0;
    for (Room room : rooms) {
      totalEarnings += room.getTotalEarnings();
    }
    return totalEarnings;
  }
}
