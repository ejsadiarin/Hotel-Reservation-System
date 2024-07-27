package Model;

public class AvailabilityDate {
  private int dateNumber;
  private boolean available;
  private double basePrice;
  private double modifiedPrice;
  private boolean isModified;
  private double modifier;
  
  public AvailabilityDate(int dateNumber, double basePrice) {
    this.dateNumber = dateNumber;
    this.available = true;
    this.basePrice = basePrice;
    setModifiedPrice(1.0); // by default this is equal to basePrice
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
  
  public void setModifiedPrice(double newModifier) {
    if (newModifier == 1.0) {
      setIsModified(false);
    }
    if (newModifier >= 0.5 && newModifier <= 1.5 && newModifier != 1.0) {
      setIsModified(true);
    }
    this.modifiedPrice = getBasePrice() * newModifier;
    setModifier(newModifier);
  }

  public boolean getIsModified() {
    return this.isModified;
  }

  public void setIsModified(boolean modified) {
    this.isModified = modified;
  }

  public double getModifier() {
    return this.modifier;
  }
  
  public void setModifier(double newModifier) {
    this.modifier = newModifier;
  }
}
