package ui;

import javax.swing.*;
import java.io.FileNotFoundException;

// runs the finance tracker application
public class Main {
    public static void main(String[] args) {

        new LoadPopUpWindow();

        // This code is for running the console based application:
        //        try {
        //            new PersonalFinanceTracker();
        //        } catch (FileNotFoundException e) {
        //            System.out.println("Unable to run application: file not found.");
        //        }
    }
}
