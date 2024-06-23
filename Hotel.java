import java.util.ArrayList;
    
public class Hotel {
  private String name;
  private ArrayList<Room> rooms;
  private ArrayList<Reservation> reservations;
  private double basePrice;
  private int numOfRooms;
  
  Hotel(String name, int maxRooms) {
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
    return rooms.get()
  }
  
  public void addRoom(Room newRoom) {
    
  }
  
  public void removeRoom(String roomName) {
    
  }
  
  public int getNumOfRooms() {
    
  }
  
  public int getAvailableRooms() {
    
  }

  public ArrayList<Reservation> getReservations() {
    return reservations;
  }
  
  public Reservation getReservation(String guestName) {
    
  }
  
  public void addReservation(Reservation reservation) {
    
  }
  
  public void removeReservation(String guestName) {

  }

  public double getBasePrice() {
    return basePrice;
  }

  public void setBasePrice(double basePrice) {
    this.basePrice = basePrice;
  }
  
  public double getEstimatedEarnings() {
    
  }
}
