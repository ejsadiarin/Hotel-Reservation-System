package Model;

/**
 * The Reservation class represents a reservation made by a guest, including details such as 
 * guest name, check-in and check-out dates, the reserved room, and the cost per night.
 */
public class Reservation {
  private String guestName;
  private int checkInDate;
  private int checkOutDate;
  private Room room;
  private double costPerNight;
  private boolean isDiscounted;

  /**
   * Constructs a new Reservation with the specified guest name, room, check-in date, and check-out date.
   *
   * @param guestName the name of the guest making the reservation
   * @param room the room being reserved
   * @param checkInDate the check-in date for the reservation
   * @param checkOutDate the check-out date for the reservation
   */
  public Reservation(String guestName, Room room, int checkInDate, int checkOutDate) {
    this.guestName = guestName;
    this.room = room;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
    this.costPerNight = room.getPricePerNight();
  }

  /**
   * Gets the name of the guest who made the reservation.
   *
   * @return the name of the guest
   */
  public String getGuestName() {
    return guestName;
  }

  /**
   * Gets the check-in date of the reservation.
   *
   * @return the check-in date
   */
  public int getCheckInDate() {
    return checkInDate;
  }

  /**
   * Gets the check-out date of the reservation.
   *
   * @return the check-out date
   */
  public int getCheckOutDate() {
    return checkOutDate;
  }

  /**
   * Gets the room associated with the reservation.
   *
   * @return the reserved room
   */
  public Room getRoom() {
    return room;
  }

  /**
   * Calculates the total price of the reservation based on the check-in and check-out dates and the cost per night.
   *
   * @return the total price of the reservation
   */
  public double getTotalPrice(String discountCode) {
    int diff = checkOutDate - checkInDate;
    // TODO: call calculateDiscount here - check first if isDiscounted == false, if true then no discount for this reservation
    
    return diff * getCostPerNight() + getCostPerNight();
  }

  /**
   * Gets the cost per night for the room reserved.
   *
   * @return the cost per night
   */
  public double getCostPerNight() {
    return costPerNight;
  }

  /**
   * Calculates the discount given a discount code
   *
   * @return the discount price that is subtracted from the original total price
   */
  public double calculateDiscount(String discountCode, double rawTotalPrice, int diff) {
    if (discountCode.equals("I_WORK_HERE")) {
      return rawTotalPrice * 0.10;
    }
    else if (discountCode.equals("STAY_4_GET_1")) {
      if (diff >= 5) {
        
      }
    }
  }
}
