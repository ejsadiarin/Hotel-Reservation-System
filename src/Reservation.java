public class Reservation {
  private String guestName;
  private int checkInDate;
  private int checkOutDate;
  private Room room;
  private double totalPrice;
  private double costPerNight;
  
  public Reservation(String guestName, int checkInDate, int checkOutDate, Room room) {
    
  }

  public String getGuestName() {
    return guestName;
  }

  public int getCheckInDate() {
    return checkInDate;
  }

  public int getCheckOutDate() {
    return checkOutDate;
  }

  public Room getRoom() {
    return room;
  }

  public double getTotalPrice() {
    return (checkOutDate - checkInDate) * getCostPerNight();
  }

  public double getCostPerNight() {
    return costPerNight;
  }
}
