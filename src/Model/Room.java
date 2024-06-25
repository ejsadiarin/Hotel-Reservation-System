package Model;

public class Room {
  private String name;
  private double pricePerNight;
  private boolean[] availability;

  public Room(String name, double pricePerNight) {
    this.name = name;
    this.pricePerNight = pricePerNight;
    this.availability = new boolean[31];
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

  public boolean isAvailable(int day) {
    return availability[day - 1];
  }

  public boolean isAvailable(int checkIn, int checkOut) {
    for (int i = checkIn - 1; i < checkOut - 1; i++) {
      if (!availability[i]) {
        return false;
      }
    }
    return true;
  }

  public boolean reserve(int checkIn, int checkOut) {
    if (isAvailable(checkIn, checkOut)) {
      for (int i = checkIn - 1; i < checkOut - 1; i++) {
        availability[i] = false;
      }
      return true;
    }
    return false;
  }

  public void cancelReservation(int checkIn, int checkOut) {
    for (int i = checkIn - 1; i < checkOut - 1; i++) {
      availability[i] = true;
    }
  }
}
