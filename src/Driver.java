import javax.swing.*;

import Controller.HRSController;
import View.MainView;

public class Driver {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      MainView view = new MainView();
      HRSController controller = new HRSController(view);
      controller.showMainView();
    });
  }
}
