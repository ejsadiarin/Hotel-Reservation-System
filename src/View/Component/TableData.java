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
}
