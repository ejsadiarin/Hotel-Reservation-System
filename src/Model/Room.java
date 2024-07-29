package Model;

import java.util.ArrayList;

/**
 * The Room class represents a hotel room, including its name, price per night, availability, and reservations.
 * It provides methods to manage reservations and check availability.
 */
public class Room {
  private String name;
  private double pricePerNight;
  private ArrayList<Reservation> reservations;
  private String roomType;
  private ArrayList<AvailabilityDate> availabilityDates;

  /**
   * Constructs a new Room with the specified name and price per night.
   *
   * @param name          the name of the room
   * @param pricePerNight the price per night for the room
   * @param roomType      the type of the room (e.g., Deluxe, Executive, Standard)
   */
  public Room(String name, double pricePerNight, String roomType) {
    this.name = name;
    this.reservations = new ArrayList<>();
    this.availabilityDates = new ArrayList<>();
    setRoomType(roomType, pricePerNight); // room type determines the price per night

    // Initialize all days to available
    for (int i = 0; i < 31; i++) {
      this.availabilityDates.add(new AvailabilityDate(i + 1, getPricePerNight()));
    }
  }

  public Room(Room room) {
    this.name = room.getName();
    this.pricePerNight = room.getPricePerNight();
    this.reservations = new ArrayList<>();
    this.availabilityDates = new ArrayList<>();
    this.roomType = room.getRoomType();

    for (AvailabilityDate date : room.getAvailabilityDates()) {
      this.availabilityDates.add(new AvailabilityDate(date));
    }
  }

  /**
   * Gets the name of the room.
   *
   * @return the name of the room
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the price per night for the room.
   *
   * @return the price per night
   */
  public double getPricePerNight() {
    return this.pricePerNight;
  }

  /**
   * Sets the price per night for the room.
   *
   * @param price the new price per night
   */
  public void setPricePerNight(double price) {
    this.pricePerNight = price;
    for (AvailabilityDate date : availabilityDates) {
      date.setBasePrice(price);
      if (date.getIsModified()) // recalculate dates with modified prices
        date.setModifiedPrice(date.getModifier());
      else
        date.setModifiedPrice(1.0);
    }
  }

  /**
   * Gets the type of the room.
   *
   * @return the type of the room
   */
  public String getRoomType() {
    return this.roomType;
  }

  /**
   * Sets the type of the room and updates the price per night.
   *
   * @param newRoomType the new type of the room
   * @param newPrice    the new price per night
   */
  public void setRoomType(String newRoomType, double newPrice) {
    if (newRoomType.equals("Deluxe")) {
      this.roomType = "Deluxe";
      setPricePerNight(newPrice * 1.20);
    } else if (newRoomType.equals("Executive")) {
      this.roomType = "Executive";
      setPricePerNight(newPrice * 1.35);
    } else {
      this.roomType = "Standard"; // default
      setPricePerNight(newPrice);
    }
  }

  /**
   * Checks if the room is available between the specified check-in and check-out dates.
   *
   * @param checkIn  the check-in date
   * @param checkOut the check-out date
   * @return true if the room is available, otherwise false
   */
  public boolean isAvailable(int checkIn, int checkOut) {
    // handle overnight reservations
    if (checkIn == checkOut)
      return this.getAvailabilityDates().get(checkIn - 1).isAvailable(); // return availability[checkIn - 1];

    for (int i = checkIn - 1; i < checkOut; i++) {
      if (!this.getAvailabilityDates().get(i).isAvailable()) // if (!availability[i])
        return false;
    }
    return true;
  }

  /**
   * Gets the list of reservations for the room.
   *
   * @return the list of reservations
   */
  public ArrayList<Reservation> getReservations() {
    return reservations;
  }

  /**
   * Gets the reservation for the specified Id.
   *
   * @param Id the ID of the reservation
   * @return the reservation for the specified guest name, or null if not found
   */
  public Reservation getReservation(int Id) {
    for (Reservation reservation : reservations) {
      if (reservation.getId() == Id)
        return reservation;
    }
    return null;
  }

  /**
   * Adds a new reservation for the specified guest name, check-in date, and
   * check-out date.
   *
   * @param guestName    the name of the guest
   * @param checkInDate  the check-in date
   * @param checkOutDate the check-out date
   */
  public void addReservation(String guestName, int checkInDate, int checkOutDate, String discountCode) {
    if (reserveDates(checkInDate, checkOutDate)) {
      reservations
          .add(new Reservation(reservations.size() + 1, guestName, this, checkInDate, checkOutDate, discountCode));
    }
  }

  /**
   * Removes the reservation of the specified Id.
   *
   * @param Id the ID of the reservation
   */
  public void removeReservation(int Id) {
    Reservation reservation = getReservation(Id);
    if (reservation != null) {
      reservation.getRoom().cancelReserveDates(reservation.getCheckInDate(), reservation.getCheckOutDate());
      reservations.remove(reservation);
    }
  }

  /**
   * Reserves the room for the specified check-in and check-out dates.
   *
   * @param checkInDate  the check-in date
   * @param checkOutDate the check-out date
   * @return true if the reservation is successful, false otherwise
   */
  public boolean reserveDates(int checkInDate, int checkOutDate) {
    if (isAvailable(checkInDate, checkOutDate)) {
      // handle overnight reservations
      if (checkInDate == checkOutDate)
        availabilityDates.get(checkInDate - 1).setAvailable(false); // availability[checkInDate - 1] = false;
      else {
        for (int i = checkInDate - 1; i < checkOutDate; i++)
          availabilityDates.get(i).setAvailable(false); // availability[i] = false;
      }
      return true;
    }
    return false;
  }

  /**
   * Cancels the reservation for the specified check-in and check-out dates.
   *
   * @param checkInDate  the check-in date
   * @param checkOutDate the check-out date
   */
  public void cancelReserveDates(int checkInDate, int checkOutDate) {
    for (int i = checkInDate - 1; i < checkOutDate; i++) {
      availabilityDates.get(i).setAvailable(true); // availability[i] = true;
    }
  }

  /**
   * Gets the total earnings from all reservations for the room.
   *
   * @return the total earnings
   */
  public double getTotalEarnings() {
    double totalEarnings = 0;
    for (Reservation reservation : reservations) {
      totalEarnings += reservation.getTotalPrice();
    }
    return totalEarnings;
  }

  /**
   * Gets the total number of reservations for the room.
   *
   * @return the total number of reservations
   */
  public ArrayList<AvailabilityDate> getAvailabilityDates() {
    return this.availabilityDates;
  }

  /**
   * Gets the availability date for the specified date number.
   *
   * @param dateNumber the date number
   * @return the availability date, otherwise null
   */
  public AvailabilityDate getAvailabilityDate(int dateNumber) {
    for (AvailabilityDate date : getAvailabilityDates()) {
      if (date.getDateNumber() == dateNumber)
        return date;
    }

    return null;
  }

  /**
   * Gets the list of reserved dates for the room.
   *
   * @return the list of reserved dates
   */
  public ArrayList<AvailabilityDate> getReservedDates() {
    ArrayList<AvailabilityDate> reservedDates = new ArrayList<>();
    for (AvailabilityDate date : getAvailabilityDates()) {
      if (!date.isAvailable())
        reservedDates.add(date);
    }

    return reservedDates;
  }

  /**
   * Gets the index of the reserved date in the list of availability dates.
   *
   * @param date the date number
   * @return the index of the reserved date, otherwise 0
   */
  public int getIndexOfReservedDate(int date) {
    for (int i = date - 1; i < getAvailabilityDates().size(); i++) {
      if (!getAvailabilityDates().get(i).isAvailable())
        return i;
    }
    return 0;
  }

  /**
   * Gets the price for the specified date.
   *
   * @param selectedDate the selected date
   * @return the price for the date
   */
  public double getPriceOnDate(int selectedDate) {
    double priceResult = 0;
    for (AvailabilityDate date : getAvailabilityDates()) {
      if (selectedDate == date.getDateNumber()) {
        priceResult = date.getModifiedPrice();
        break;
      }
    }

    return priceResult;
  }
}
