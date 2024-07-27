package Model;

/**
 * The Reservation class represents a reservation made by a guest, including
 * details such as
 * guest name, check-in and check-out dates, the reserved room, and the cost per
 * night.
 */
public class Reservation {
  private int Id;
  private String guestName;
  private int checkInDate;
  private int checkOutDate;
  private Room room;
  private double costPerNight;
  private boolean isDiscounted;
  private String discountCode;

  /**
   * Constructs a new Reservation with the specified guest name, room, check-in
   * date, and check-out date.
   *
   * @param guestName    the name of the guest making the reservation
   * @param room         the room being reserved
   * @param checkInDate  the check-in date for the reservation
   * @param checkOutDate the check-out date for the reservation
   */
  public Reservation(int Id, String guestName, Room room, int checkInDate, int checkOutDate) {
    this.Id = Id;
    this.guestName = guestName;
    this.room = room;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
    this.costPerNight = room.getPricePerNight();
    this.isDiscounted = false;
    setDiscountCode("NA");
  }

  public Reservation(String guestName, Room room, int checkInDate, int checkOutDate, String discountCode) {
    this.guestName = guestName;
    this.room = room;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
    this.costPerNight = room.getPricePerNight();
    this.isDiscounted = true;
    setDiscountCode(discountCode);
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
   * Calculates the total price of the reservation based on the check-in and
   * check-out dates and the cost per night.
   *
   * @return the total price of the reservation
   */
  public double getTotalPrice() {
    // TODO: check if stay (number of days of stay) is accurate 1-2 = 1 day stay
    double totalPrice = 0.0;
    int stay = checkOutDate - checkInDate;
    if (stay < 1)
      stay = 1;

    if (stay == 1)
      totalPrice = room.getPriceOnDate(checkInDate);

    // handle modified date price
    if (stay > 1) {
      for (int i = checkInDate; i < checkOutDate; i++) {
        totalPrice += room.getPriceOnDate(i);
      }
    }

    if (isDiscounted) {
      // TODO: call calculateDiscount here - check first if isDiscounted == false, if
      // true then no discount for this reservation
      // TODO: how tf do i get discountCode from view to here
    }

    return totalPrice; // if not discounted then just rawTotalPrice
  }

  /**
   * Gets the cost per night for the room reserved.
   *
   * @return the cost per night
   */
  public double getCostPerNight() {
    return this.costPerNight;
  }

  public String getDiscountCode() {
    return this.discountCode;
  }

  public void setDiscountCode(String discountCode) {
    this.discountCode = discountCode;
  }

  /**
   * Calculates the discount given a discount code
   *
   * @return the discount price that is subtracted from the original total price
   */
  public double calculateDiscountedPrice(String discountCode, double rawTotalPrice, int stay) {
    if (discountCode.equals("I_WORK_HERE"))
      return rawTotalPrice * 0.10 - rawTotalPrice;
    else if (discountCode.equals("STAY_4_GET_1")) {
      // then first day is free
      if (stay >= 5)
        return (stay - 1) * getCostPerNight() + getCostPerNight(); // handle negative diff value (for overnight)
    } else if (discountCode.equals("PAYDAY")) {
      // stop when availability encountered is true
      for (int i = room.getIndexOfReservedDate(checkInDate); i <= room.getIndexOfReservedDate(checkOutDate); i++) {
        // if i covers day 15 or 30 (excluding checkout: 15 or checkout: 30)
        if ((i >= 15 && i <= 30) && (checkOutDate != 15 && checkOutDate != 30))
          return rawTotalPrice * 0.07 - rawTotalPrice;
      }
    }

    return rawTotalPrice; // default discountCode "NA"
  }
}
