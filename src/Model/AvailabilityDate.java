package Model;

/**
 * The AvailabilityDate class represents a date with availability, price, and modifiers.
 */
public class AvailabilityDate {
  private int dateNumber;
  private boolean available;
  private double basePrice;
  private double modifiedPrice;
  private boolean isModified;
  private double modifier;
  
  /**
   * Constructs a new AvailabilityDate with the specified date number and base price.
   *
   * @param dateNumber the date number
   * @param basePrice  the base price
   */
  public AvailabilityDate(int dateNumber, double basePrice) {
    this.dateNumber = dateNumber;
    this.available = true;
    this.basePrice = basePrice;
    setModifiedPrice(1.0); // by default this is equal to basePrice
  }

  /**
   * Gets the date number.
   *
   * @return the date number
   */
  public int getDateNumber() {
    return this.dateNumber;
  }

  /**
   * Gets the availability of the date.
   *
   * @return true if the date is available, otherwise false
   */
  public boolean isAvailable() {
    return this.available;
  }
  
  /**
   * Sets the availability of the date.
   *
   * @param available true if the date is available, otherwise false
   */
  public void setAvailable(boolean available) {
    this.available = available;
  }

  /**
   * Gets the base price of the date.
   *
   * @return the base price
   */
  public double getBasePrice() {
    return this.basePrice;
  }
  
  /**
   * Sets the base price of the date.
   *
   * @param basePrice the new base price
   */
  public void setBasePrice(double basePrice) {
    this.basePrice = basePrice;
  }

  /**
   * Gets the modified price of the date.
   *
   * @return the modified price
   */
  public double getModifiedPrice() {
    return this.modifiedPrice;
  }
  
  /**
   * Sets the modified price of the date.
   *
   * @param newModifier the new modifier
   */
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

  /**
   * Gets the modified status of the date.
   *
   * @return true if the date is modified, otherwise false
   */
  public boolean getIsModified() {
    return this.isModified;
  }

  /**
   * Sets the modified status of the date.
   *
   * @param modified true if the date is modified, otherwise false
   */
  public void setIsModified(boolean modified) {
    this.isModified = modified;
  }

  /**
   * Gets the modifier of the date.
   *
   * @return the modifier
   */
  public double getModifier() {
    return this.modifier;
  }
  
  /**
   * Sets the modifier of the date.
   *
   * @param newModifier the new modifier
   */
  public void setModifier(double newModifier) {
    this.modifier = newModifier;
  }
}
