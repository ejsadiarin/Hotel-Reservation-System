import java.util.Scanner;

public class Driver {
  public static void main(String[] args) {
    HRSController hrsController = new HRSController();
    Scanner scanner = new Scanner(System.in);
    
    while (true) {
      // display manager here
      System.out.printf("\n==============================================\n");
      System.out.printf("1 - Create a new hotel\n");
      System.out.printf("2 - View all hotels\n");
      System.out.printf("3 - Manage a hotel\n");
      System.out.printf("4 - Simulate booking\n");
      System.out.printf("0 - Exit the program\n");
      System.out.printf("==============================================\n");
      
      System.out.printf("Choose your action (Enter 0 to exit): ");
      int choice = scanner.nextInt();
      scanner.nextLine();
      
      switch (choice) {
        case 0: // exit
          System.out.printf("Exiting...\n");
          scanner.close();
          return;
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
          if (!hrsController.getHotels().isEmpty()) {
            System.out.printf("\nEnter the name of the hotel you want to VIEW (Enter 0 to exit): ");
            hotelName = scanner.nextLine();
            if (hotelName.equals("0"))
              System.out.printf("Going back...\n");
            else
              hrsController.viewSpecificHotel(hotelName);
          }
          break;
        case 3: // manage hotel
          System.out.printf("\n============Manage Hotel============k\n");
          hrsController.viewAllHotels();
          System.out.println();
          System.out.printf("Enter the name of the hotel you want to MANAGE (Enter 0 to exit): ");
          hotelName = scanner.nextLine();
          if (hotelName.equals("0"))
            System.out.printf("Going back...\n");
          else
            hrsController.manageHotel(hotelName);
          break;
        case 4: // simulate booking
          System.out.printf("\nSimulate Booking\n");
          break;
        default:
          System.out.printf("Invalid choice. Please try again.");
      }
      
    }
    
  }
}
