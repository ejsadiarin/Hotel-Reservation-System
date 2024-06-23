public class Room {
  private String name;
  private double pricePerNight;
  private boolean available;
  
  public Room(String name, double pricePerNight) {
    
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
