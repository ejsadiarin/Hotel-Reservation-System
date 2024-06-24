import java.util.Scanner;

public class Driver {
  public static void main(String[] args) {
    HRSController hrsController = new HRSController();
    Scanner scanner = new Scanner(System.in);
    
    while (true) {
      // display manager here
      System.out.printf("\nChoose your action: ");
      int choice = scanner.nextInt();
      scanner.nextLine();
      
      switch (choice) {
        case 1: // create hotel
          System.out.printf("\nCreating hotel...\n");
          System.out.printf("Enter hotel name: ");
          String hotelName = scanner.nextLine();
          hrsController.createHotel(hotelName);
          break;
        case 2: // view hotel
          System.out.printf("\nViewing hotels...\n");
           hrsController.viewAllHotels();
          // add option to view specific Hotel via hotelName
          System.out.printf("\nEnter the name of the hotel you want to view: \n");
          hotelName = scanner.nextLine();
          hrsController.viewSpecificHotel(hotelName);
          break;
        case 3: // manage hotel
          System.out.printf("\nManage Hotel\n");
          break;
        case 4: // simulate booking
          System.out.printf("\nSimulate Booking\n");
          break;
        case 5: // exit
          System.out.printf("Exiting...\n");
          scanner.close();
          return;
        default:
          System.out.printf("Invalid choice. Please try again.");
      }
      
    }
    
  }
}
