package com.silikonm.utilities;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Harsha
 * Date: 10/18/13
 * Time: 9:42 PM
 */
public class Alert extends JOptionPane {

    public static void error(String message) {
        showMessageDialog(null, "Error: " + message, "Error", ERROR_MESSAGE);
    }

    public static void message(String message) {
        showMessageDialog(null, message, "Message", INFORMATION_MESSAGE);
    }

    public static void warning(String message) {
        showMessageDialog(null, message, "Warning", WARNING_MESSAGE);
    }

    public static int confirmYesNoDialog(String message) {
        return JOptionPane.showConfirmDialog(null, message, null, JOptionPane.YES_NO_OPTION);
    }
}
