package Model;

import java.util.ArrayList;

public class Room {
  private String name;
  private double pricePerNight;
  private boolean[] availability;
  private ArrayList<Reservation> reservations;

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

  public String getName() {
    return name;
  }

  public double getPricePerNight() {
    return pricePerNight;
  }

  public void setPricePerNight(double price) {
    this.pricePerNight = price;
  }

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

  public ArrayList<Reservation> getReservations() {
    return reservations;
  }

  public Reservation getReservation(String guestName) {
    for (Reservation reservation : reservations) {
      if (reservation.getGuestName().equals(guestName))
        return reservation;
    }
    return null;
  }

  public void addReservation(String guestName, int checkInDate, int checkOutDate) {
    if (reserveDates(checkInDate, checkOutDate)) {
      reservations.add(new Reservation(guestName, this, checkInDate, checkOutDate));
      System.out.printf("Reservation for '%s' added to room '%s' from day %d to day %d.\n", guestName, name, checkInDate, checkOutDate);
    } else {
      System.out.printf("Room '%s' is not available from day %d to day %d.\n", name, checkInDate, checkOutDate);
    }
  }

  public void removeReservation(String guestName) {
    Reservation reservation = getReservation(guestName);
    if (reservation != null) {
      reservation.getRoom().cancelReserveDates(reservation.getCheckInDate(), reservation.getCheckOutDate());
      reservations.remove(reservation);
      System.out.printf("Reservation for '%s' removed.\n", guestName);
    }
    else
      System.out.printf("Reservation for '%s' not found.\n", guestName);
  }
  

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

  public void cancelReserveDates(int checkInDate, int checkOutDate) {
    for (int i = checkInDate; i < checkOutDate; i++) {
      availability[i] = true;
    }
  }
  
  public double getTotalEarnings() {
    double totalEarnings = 0;
    for (Reservation reservation : reservations) {
      totalEarnings += reservation.getTotalPrice();
    }
    return totalEarnings;
  }
  
}
