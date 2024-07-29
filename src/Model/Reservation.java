package Model;

/**
 * The Reservation class represents a reservation made by a guest, including details such as
 * guest name, check-in and check-out dates, the reserved room, and the cost per night.
 */
public class Reservation {
  private int Id;
  private String guestName;
  private int checkInDate;
  private int checkOutDate;
  private Room room;
  private String discountCode;
  private double totalPrice;

  /**
   * Constructs a new Reservation with the specified guest name, room, check-in
   * date, and check-out date.
   *
   * @param Id           the ID of the reservation
   * @param guestName    the name of the guest making the reservation
   * @param room         the room being reserved
   * @param checkInDate  the check-in date for the reservation
   * @param checkOutDate the check-out date for the reservation
   * @param discountCode the discount code for the reservation
   */
  public Reservation(int Id, String guestName, Room room, int checkInDate, int checkOutDate, String discountCode) {
    this.Id = Id;
    this.guestName = guestName;
    this.room = room;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
    setDiscountCode(discountCode);
    this.totalPrice = calculateTotalPrice(discountCode);
  }

  /**
   * Gets the ID of the reservation
   *
   * @return the ID of the reservation
   */
  public int getId() {
    return Id;
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
   * Gets the discount code associated with the reservation.
   *
   * @return the discount code
   */
  public String getDiscountCode() {
    return this.discountCode;
  }

  /**
   * Sets the discount code for the reservation.
   *
   * @param discountCode the discount code
   */
  public void setDiscountCode(String discountCode) {
    if (discountCode.isEmpty())
      this.discountCode = "N/A";
    else
      this.discountCode = discountCode;
  }

  /**
   * Calculates the total price of the reservation based on the check-in and check-out dates and the cost per night.
   *
   * @return the total price of the reservation
   */
  public double getTotalPrice() {
    return this.totalPrice;
  }

  /**
   * Calculates the total price of the reservation based on the check-in and check-out dates and the cost per night.
   *
   * @param discountCode the discount code
   * @return the total price of the reservation
   */
  public double calculateTotalPrice(String discountCode) {
    double calculatedTotalPrice = 0.0;
    
    // get raw total price first
    int stay = checkOutDate - checkInDate;
    if (stay < 1)
      stay = 1;

    if (stay == 1)
      calculatedTotalPrice = room.getPriceOnDate(checkInDate);

    // handle modified date price
    if (stay > 1) {
      for (int i = checkInDate; i < checkOutDate; i++) {
        if (i == checkInDate && discountCode.equals("STAY4_GET1")) {
          continue; // skip first iteration if STAY4_GET1
        }
        calculatedTotalPrice += room.getPriceOnDate(i);
      }
    }

    // if discounted then calculate
    if (checkIfValidDiscountCode(discountCode)) {
      if (discountCode.equals("I_WORK_HERE"))
        return calculatedTotalPrice - (calculatedTotalPrice * 0.10);

      if (discountCode.equals("STAY4_GET1")) {
        // then first day is free
        if (stay >= 5)
          return calculatedTotalPrice; // first day is free is calculated in the loop above
      }

      if (discountCode.equals("PAYDAY")) {
        // if range of check-in & check-out date covers 15 or 30 (excluding checkout-15-or-30)
        for (int i = checkInDate; i < checkOutDate; i++) {
          if ((i == 15 || i == 30))
            return calculatedTotalPrice - (calculatedTotalPrice * 0.07);
        }
      }
    }

    return calculatedTotalPrice; 
  }
  
  /**
   * Checks if the discount code is valid.
   *
   * @param discountCode the discount code
   * @return true if the discount code is valid, otherwise false
   */
  public boolean checkIfValidDiscountCode(String discountCode) {
    if (discountCode.equals("I_WORK_HERE") || discountCode.equals("STAY4_GET1") || discountCode.equals("PAYDAY"))
      return true;
    else
      return false;
  }
}
