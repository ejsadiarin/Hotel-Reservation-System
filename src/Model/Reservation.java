package Model;

public class Reservation {
  private String guestName;
  private int checkInDate;
  private int checkOutDate;
  private Room room;
  private double costPerNight;
  
  public Reservation(String guestName, Room room, int checkInDate, int checkOutDate) {
    this.guestName = guestName;
    this.room = room;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
    this.costPerNight = room.getPricePerNight();
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
