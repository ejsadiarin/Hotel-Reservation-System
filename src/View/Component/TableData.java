package View.Component;

import Controller.HRSController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

public class TableData {
  public static void reservationTableComponent(HRSController controller, JTable table, DefaultTableModel tableData, String selectedHotelName, String selectedRoomName) {
    tableData = (DefaultTableModel) table.getModel(); // reservations (in selected room) in a table
    for (HashMap<String, String> reservationInfo : controller.getAllReservationInfoOnRoom(selectedHotelName, selectedRoomName)) {
      Object[] row = new Object[] {
          reservationInfo.get("Id"),
          reservationInfo.get("Guest Name"),
          reservationInfo.get("Check In Date"),
          reservationInfo.get("Check Out Date"),
          reservationInfo.get("Total Price"),
      };
      tableData.addRow(row);
    }
  }

  public static void datePriceTableComponent(HRSController controller, JTable table, DefaultTableModel tableData, String selectedHotelName, String selectedRoomName) {
    tableData = (DefaultTableModel) table.getModel(); // reservations (in selected room) in a table
    for (HashMap<String, String> datePriceInfo : controller.getAllDatesOnRoom(selectedHotelName, selectedRoomName)) {
      Object[] row = new Object[] {
          datePriceInfo.get("Date Number"),
          datePriceInfo.get("Is Available"),
          datePriceInfo.get("Base Price"),
          datePriceInfo.get("Modified Price"),
      };
      tableData.addRow(row);
    }
  }
  
  public static void allRoomsTableComponent(HRSController controller, JTable table, DefaultTableModel tableData, String selectedHotelName) {
    tableData = (DefaultTableModel) table.getModel();
    for (HashMap<String, String> roomInfo : controller.getAllRoomInfoOnHotel(selectedHotelName)) {
      Object[] row = new Object[] {
          roomInfo.get("Room Name"),
          roomInfo.get("Room Type"),
          roomInfo.get("Price Per Night"),
          roomInfo.get("Number of Reservations"),
          roomInfo.get("Estimated Earnings")
      };
      tableData.addRow(row);
    }
  }
  
  public static void roomsByTypeTableComponent(HRSController controller, JTable table, DefaultTableModel tableData, String selectedHotelName, String roomType) {
    String[] columnNames = { "Room Name", "Room Type", "Price Per Night", "Booked Reservations" };
    tableData = new DefaultTableModel(columnNames, 0);
    table.setModel(tableData);
    
    for (HashMap<String, String> roomInfo : controller.getAllRoomInfoOnHotel(selectedHotelName)) {
      if (roomInfo.get("Room Type").equals(roomType)) {
        Object[] row = new Object[]{
            roomInfo.get("Room Name"),
            roomInfo.get("Room Type"),
            roomInfo.get("Price Per Night"),
            roomInfo.get("Number of Reservations")
        };
        tableData.addRow(row);
      }
    }
  }
  
  public static void availableRoomsByDateTableComponent(HRSController controller, JTable table, DefaultTableModel tableData, String selectedHotelName, int checkInDate, int checkOutDate) {
    String[] columnNames = { "Room Name", "Room Type", "Price Per Night", "Booked Reservations" };
    tableData = new DefaultTableModel(columnNames, 0);
    table.setModel(tableData);

    for (HashMap<String, String> roomInfo : controller.getAllRoomsAvailableOnDate(selectedHotelName, checkInDate, checkOutDate)) {
      Object[] row = new Object[]{
          roomInfo.get("Room Name"),
          roomInfo.get("Room Type"),
          roomInfo.get("Price Per Night"),
          roomInfo.get("Number of Reservations")
      };
      tableData.addRow(row);
    }
  }
}
