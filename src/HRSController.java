public class HRSController {
  private ArrayList<Hotel> hotels;
  
  public HRSController() {
    this.hotels = new ArrayList<>();
  }
  
  public void createHotel(String name) {
    for (Hotel hotel : hotels) {
      if (hotel.getName().equals(name)) {
        System.out.println("Hotel with this name already exists.");
        return;
      }
    }
    hotels.add(new Hotel(name));
  }
  
  public void viewHotel(String hotel) {
  }
  
  public void manageHotel(String hotel) {
  }
  
  public void simulateBooking() {
  }
}
