package Model;

public class AvailabilityDate {
  private int dateRange1;
  private int dateRange2;
  private boolean available;
  private double basePrice;
  private double modifiedPrice;
  
  public AvailabilityDate(double basePrice) {
    this.available = true;
    this.basePrice = basePrice;
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
    else
      throw new IllegalArgumentException("Modifier must be between 0.5 and 1.5");
  }
}
