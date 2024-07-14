package Model;

import java.util.ArrayList;

/**
 * The Hotel class represents a hotel with a unique name, a collection of rooms, and various properties 
 * related to room pricing and availability.
 */
public class Hotel {
  private String name;
  private ArrayList<Room> rooms;
  private double basePrice;
  private final int maxRooms; // always 50
  private int numOfRooms; // actual number of rooms (initialized rooms)

  /**
   * Constructs a new Hotel with the specified name and number of rooms.
   *
   * @param name the unique name of the hotel
   * @param numOfRooms the number of rooms to initialize in the hotel
   */
  public Hotel(String name, int numOfRooms) {
    this.name = name; // must be unique
    this.rooms = new ArrayList<>();
    this.basePrice = 1299.0;
    this.maxRooms = 50;
    this.setNumOfRooms(numOfRooms);
    this.initializeRooms(this.numOfRooms);
  }

  /**
   * Gets the name of the hotel.
   *
   * @return the name of the hotel
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the hotel.
   *
   * @param name the new name of the hotel
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the list of rooms in the hotel.
   *
   * @return the list of rooms
   */
  public ArrayList<Room> getRooms() {
    return rooms;
  }

  /**
   * Initializes the rooms in the hotel with unique names and the base price, utilizing an automated naming generation based on the current number of rooms.
   *
   * @param numOfRooms the number of rooms to initialize
   */
  private void initializeRooms(int numOfRooms) {
    // automated unique naming of room names until numOfRooms
    int roomNumber = 1;
    while (roomNumber <= numOfRooms) {
      String roomName = "Room-" + roomNumber;
      rooms.add(new Room(roomName, this.basePrice, "Standard"));
      roomNumber++;
    }
  }

  /**
   * Gets a specific room by its name.
   *
   * @param roomName the name of the room to find
   * @return the Room object if found, otherwise null
   */
  public Room getRoom(String roomName) {
    for (Room room : rooms) {
      if (room.getName().equals(roomName))
        return room;
    }
    return null;
  }

  /**
   * Adds a new room to the hotel, utilizing an automated naming generation based on the current number of rooms.
   */
  public void addRoom(String roomType) {
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
    rooms.add(new Room(roomName, this.basePrice, roomType));
  }

  /**
   * Removes a room from the hotel by its name.
   *
   * @param roomName the name of the room to remove
   */
  public void removeRoom(String roomName) {
    rooms.removeIf(room -> room.getName().equals(roomName));
  }

  /**
   * Gets the current number of rooms in the hotel.
   *
   * @return the number of rooms
   */
  public int getNumOfRooms() {
    return rooms.size();
  }

  /**
   * Sets the number of rooms in the hotel, ensuring it is within the valid range.
   *
   * @param numOfRooms the number of rooms to set
   */
  public void setNumOfRooms(int numOfRooms) {
    // if invalid numOfRooms then set to default minimum of 1 room
    if (numOfRooms > getMaxRooms() || numOfRooms < 1) {
      System.out.printf("Cannot have %d number of rooms (minimum is 1, maximum is 50). Defaulting to minimum of 1 room.\n", numOfRooms);
      this.numOfRooms = 1;
    }
    else
      this.numOfRooms = numOfRooms;
  }

  /**
   * Gets the maximum number of rooms allowed in the hotel.
   *
   * @return the maximum number of rooms
   */
  public int getMaxRooms() {
    return this.maxRooms;
  }

  /**
   * Gets the list of available rooms within a specific date range.
   *
   * @param checkInDate the check-in date
   * @param checkOutDate the check-out date
   * @return the list of available rooms
   */
  public ArrayList<Room> getAvailableRoomsOnDate(int checkInDate, int checkOutDate) {
    ArrayList<Room> availableRooms = new ArrayList<>();
    
    for (Room room : rooms) {
      if (room.isAvailable(checkInDate, checkOutDate))
        availableRooms.add(room);
    }
    
    return availableRooms;
  }

  /**
   * Checks if all rooms in the hotel have no reservations.
   *
   * @return true if all rooms have no reservations, otherwise false
   */
  public boolean areEmptyReservations() {
    for (Room room : rooms) {
      if (!room.getReservations().isEmpty()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Gets the base price for rooms in the hotel.
   *
   * @return the base price
   */
  public double getBasePrice() {
    return basePrice;
  }

  /**
   * Sets the base price for rooms in the hotel, assuming there are no reservations.
   *
   * @param basePrice the new base price
   */
  public void setBasePrice(double basePrice) {
    // assumes that there are no reservations for ALL rooms
    this.basePrice = basePrice;
    for (Room room : rooms)
      room.setPricePerNight(basePrice);
  }

  /**
   * Gets the estimated total earnings from all rooms in the hotel.
   *
   * @return the estimated earnings
   */
  public double getEstimatedEarnings() {
    double totalEarnings = 0;
    for (Room room : rooms) {
      totalEarnings += room.getTotalEarnings();
    }
    return totalEarnings;
  }
}
