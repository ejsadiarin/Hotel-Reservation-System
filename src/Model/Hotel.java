package Model;

import java.util.ArrayList;
    
public class Hotel {
  private String name;
  private ArrayList<Room> rooms;
  private ArrayList<Reservation> reservations;
  private double basePrice;
  private int numOfRooms;
  
  public Hotel(String name, int maxRooms) {
    this.name = name; // must be unique
    this.rooms = new ArrayList<Room>();
    this.reservations = new ArrayList<Reservation>();
    this.basePrice = 1299.0;
    this.numOfRooms = maxRooms;
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
  
  public Room getRoom(String roomName) {
    for (Room room : rooms) {
      if (room.getName().equals(roomName))
        return room;
    }
    return null;
  }
  
  public void addRoom(String roomName) {
    for (Room room : rooms) {
      if (room.getName().equals(roomName)) {
        System.out.printf("Room name already exists.\n");
        return;
      }
    }
    rooms.add(new Room(roomName, this.basePrice));
  }
  
  public void removeRoom(String roomName) {
    rooms.removeIf(room -> room.getName().equals(roomName) && room.isAvailable());
  }
  
  public int getNumOfRooms() {
    return this.numOfRooms;
  }
  
  public void setNumOfRooms(int maxRooms) {
    this.numOfRooms = maxRooms;
  }
  
  public int getAvailableRooms() {
  }

  public ArrayList<Reservation> getReservations() {
    return reservations;
  }
  
  public Reservation getReservation(String guestName) {
    if ()
  }
  
  public void addReservation(Reservation reservation) {
    reservations.add(reservation);
  }
  
  public void removeReservation(String guestName) {
    reservations.removeIf(reservation -> reservation.getGuestName().equals(guestName));
  }

  public double getBasePrice() {
    return basePrice;
  }

  public void setBasePrice(double basePrice) {
    if (reservations.isEmpty() && basePrice >= 100.0) {
      this.basePrice = basePrice;
      for (Room room : rooms) {
        room.setPricePerNight(basePrice);
      }
    }
  }
  
  public double getEstimatedEarnings() {
    return 
  }
}
