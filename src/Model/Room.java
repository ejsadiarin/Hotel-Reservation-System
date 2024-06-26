package Model;

import java.util.ArrayList;

/**
 * The Room class represents a hotel room, including its name, price per night, availability, 
 * and reservations. It provides methods to manage reservations and check availability.
 */
public class Room {
  private String name;
  private double pricePerNight;
  private boolean[] availability;
  private ArrayList<Reservation> reservations;

  /**
   * Constructs a new Room with the specified name and price per night.
   *
   * @param name the name of the room
   * @param pricePerNight the price per night for the room
   */
  public Room(String name, double pricePerNight) {
    this.name = name;
    this.pricePerNight = pricePerNight;
    this.availability = new boolean[31];
    this.reservations = new ArrayList<>();
    // Initialize all days to available
    for (int i = 0; i < 31; i++) {
      this.availability[i] = true;
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
    return pricePerNight;
  }

  /**
   * Sets the price per night for the room.
   *
   * @param price the new price per night
   */
  public void setPricePerNight(double price) {
    this.pricePerNight = price;
  }

  /**
   * Checks if the room is available between the specified check-in and check-out dates.
   *
   * @param checkIn the check-in date
   * @param checkOut the check-out date
   * @return true if the room is available, otherwise false
   */
  public boolean isAvailable(int checkIn, int checkOut) {
    // handle overnight reservations
    if (checkIn == checkOut)
      return availability[checkIn - 1];
    
    for (int i = checkIn - 1; i < checkOut; i++) {
      if (!availability[i])
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
   * Gets the reservation for the specified guest name.
   *
   * @param guestName the name of the guest
   * @return the reservation for the specified guest name, or null if not found
   */
  public Reservation getReservation(String guestName) {
    for (Reservation reservation : reservations) {
      if (reservation.getGuestName().equals(guestName))
        return reservation;
    }
    return null;
  }

  /**
   * Adds a new reservation for the specified guest name, check-in date, and check-out date.
   *
   * @param guestName the name of the guest
   * @param checkInDate the check-in date
   * @param checkOutDate the check-out date
   */
  public void addReservation(String guestName, int checkInDate, int checkOutDate) {
    if (reserveDates(checkInDate, checkOutDate)) {
      reservations.add(new Reservation(guestName, this, checkInDate, checkOutDate));
      System.out.printf("Reservation for '%s' added to room '%s' from day %d to day %d.\n", guestName, name, checkInDate, checkOutDate);
    } else {
      System.out.printf("Room '%s' is not available from day %d to day %d.\n", name, checkInDate, checkOutDate);
    }
  }

  /**
   * Removes the reservation for the specified guest name.
   *
   * @param guestName the name of the guest
   */
  public void removeReservation(String guestName) {
    Reservation reservation = getReservation(guestName);
    if (reservation != null) {
      reservation.getRoom().cancelReserveDates(reservation.getCheckInDate(), reservation.getCheckOutDate());
      reservations.remove(reservation);
      System.out.printf("Reservation for '%s' removed from room '%s'.\n", guestName, reservation.getRoom().getName());
    }
    else
      System.out.printf("Reservation for '%s' not found. Exiting...\n", guestName);
  }

  /**
   * Reserves the room for the specified check-in and check-out dates.
   *
   * @param checkInDate the check-in date
   * @param checkOutDate the check-out date
   * @return true if the reservation is successful, false otherwise
   */
  public boolean reserveDates(int checkInDate, int checkOutDate) {
    if (isAvailable(checkInDate, checkOutDate)) {
      // handle overnight reservations
      if (checkInDate == checkOutDate)
        availability[checkInDate - 1] = false;
      else {
        for (int i = checkInDate - 1; i < checkOutDate; i++)
          availability[i] = false;
      }
      return true;
    }
    return false;
  }

  /**
   * Cancels the reservation for the specified check-in and check-out dates.
   *
   * @param checkInDate the check-in date
   * @param checkOutDate the check-out date
   */
  public void cancelReserveDates(int checkInDate, int checkOutDate) {
    for (int i = checkInDate - 1; i < checkOutDate; i++) {
      availability[i] = true;
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
  
}
