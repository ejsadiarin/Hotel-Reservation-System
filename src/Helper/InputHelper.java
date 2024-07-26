package Helper;

import javax.swing.*;

public class InputHelper {
  public static String askInputString(String message) {
    return JOptionPane.showInputDialog(null, message, "Question", JOptionPane.QUESTION_MESSAGE);
  }
  
  /**
   * @return 0 if user selected YES, otherwise 1 if NO
   * */
  public static int askConfirmation(String message) {
    return JOptionPane.showConfirmDialog(null, message, "Confirm", JOptionPane.YES_NO_OPTION);
  }
}

