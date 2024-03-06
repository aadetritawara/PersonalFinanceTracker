package ui;

import java.io.FileNotFoundException;

// runs the finance tracker application
public class Main {
    public static void main(String[] args) {
        try {
            new PersonalFinanceTracker();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found.");
        }
    }
}
