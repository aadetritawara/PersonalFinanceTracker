package ui;

import model.Account;

import java.util.Scanner;

public class PersonalFinanceTracker {
    // represents a personal finance tracker

    private Scanner scanner;
    private Account account;

    // EFFECTS: runs the personal finance tracker application
    public PersonalFinanceTracker() {
        runTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTracker() {
        displayMenu();

    }

    // EFFECTS: displays a menu for user to choose from
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> new expense");
        System.out.println("\t2 -> new earning");
        System.out.println("\ts -> view account stats");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds an expense to the account
    public void newExpense() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds an earning to the account
    public void newEarning() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: displays stats:
    // balance, expense total, earning total, expense list, earning list
    public void displayGeneralStats() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: displays total expenses by category
    public void displaySpendingByCategory() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: displays both overall spending and spending broken down by category.
    // Also shows separate lists of expenses and earnings made by user.
    public void viewAccountStats() {
        displayGeneralStats();
        displaySpendingByCategory();
    }


}
