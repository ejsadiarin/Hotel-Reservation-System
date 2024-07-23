package Model;

public class AvailabilityDate {
  private int dateNumber;
  private boolean available;
  private double basePrice;
  private double modifiedPrice;
  
  public AvailabilityDate(int dateNumber, double basePrice) {
    this.dateNumber = dateNumber;
    this.available = true;
    this.basePrice = basePrice;
  }

  public int getDateNumber() {
    return this.dateNumber;
  }

  public boolean isAvailable() {
    return this.available;
  }
  
  public void setAvailable(boolean available) {
    this.available = available;
  }

  public double getBasePrice() {
    return this.basePrice;
  }
  
  public void setBasePrice(double basePrice) {
    this.basePrice = basePrice;
  }

  public double getModifiedPrice() {
    return this.modifiedPrice;
  }
  
  public void setModifiedPrice(double modifier) {
    if (modifier >= 0.5 && modifier <= 1.5) {
      this.modifiedPrice = this.basePrice * modifier;
    }
  }
}
