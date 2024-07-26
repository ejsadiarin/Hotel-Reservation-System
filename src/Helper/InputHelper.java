package Helper;

import javax.swing.*;

public class InputHelper {
  public static String askInputString(String message) {
    return JOptionPane.showInputDialog(null, message, "Question", JOptionPane.QUESTION_MESSAGE);
  }
  
  public static int askConfirmation(String message) {
    return JOptionPane.showConfirmDialog(null, message, "Confirm", JOptionPane.OK_CANCEL_OPTION);
  }
}

