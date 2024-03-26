package ui;

import javax.swing.*;
import java.io.FileNotFoundException;

// runs the finance tracker application
public class Main {
    public static void main(String[] args) {

        new LoadPopUpWindow();
        // SwingUtilities.invokeLater(() -> new GuiPersonalFinanceTracker());

//        try {
//            new PersonalFinanceTracker();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run application: file not found.");
//        }
    }
}
