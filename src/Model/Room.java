package Model;

public class Room {
  private String name;
  private double pricePerNight;
  private boolean available;
  
  public Room(String name, double pricePerNight) {
    this.name = name; // must be unique
    this.pricePerNight = pricePerNight;
    this.available = true;
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

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }
}
