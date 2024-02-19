package ui;

import model.Account;
import model.Earning;
import model.Expense;

import java.text.NumberFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

// The code structure found in the Teller App helped me write this class:
// source: https://github.students.cs.ubc.ca/CPSC210/TellerApp
public class PersonalFinanceTracker {
    // represents a personal finance tracker

    private Scanner scanner = new Scanner(System.in);
    private Account account = new Account();

    // EFFECTS: runs the personal finance tracker application
    public PersonalFinanceTracker() {
        runTracker();
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTracker() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayMenu();
            command = scanner.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");

    }


    // MODIFIES: this
    // EFFECTS: precesses the command entered by the user to call appropriate method
    public void processCommand(String command) {
        if (command.equals("1")) {
            newExpense();
        } else if (command.equals("2")) {
            newEarning();
        } else if (command.equals("s")) {
            viewAccountStats();
        } else {
            System.out.println("Please enter a valid item from the menu: ");
        }
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

        double doubleAmount = getAmount();

        System.out.println("Please enter a name for the expense: ");
        String name = scanner.nextLine();
        System.out.println("Add a longer note if necessary: ");
        String note = scanner.nextLine();
        String date = getDate();

        displayCategories();
        String answer = scanner.nextLine();
        while (getCategoryFromAnswerChoice(answer).equals("Incorrect Input")) {
            System.out.println("Please select a valid category: ");
            displayCategories();
            answer = scanner.nextLine();
        }
        String category = getCategoryFromAnswerChoice(answer);

        Expense expense = new Expense(doubleAmount, name, note, date, category);
        account.addExpense(expense);
        System.out.println("Expense added to account.\nReturning to main menu.\n");
    }


    // EFFECT: checks if user inputted value is a valid double and returns the valid value
    // once it is obtained from the user
    public double getAmount() {
        double doubleAmount = 0.0;
        boolean validAmountEntered = false;

        do {
            System.out.println("Please enter the amount: ");
            String amount = scanner.nextLine();
            try {
                doubleAmount = Double.parseDouble(amount);
                validAmountEntered = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a valid amount");
            }
        } while (!validAmountEntered);
        return doubleAmount;
    }


    // EFFECTS: gets date from user, checks if inputted date is of MM-DD-YYYY format.
    // if it is, it returns the date otherwise it keeps prompting user to input valid date
    public String getDate() {
        String answer;
        boolean dateValidForm = false;
        do {
            System.out.println("Please enter a date (MM-DD-YYYY)");
            answer = scanner.nextLine();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                LocalDate date = LocalDate.parse(answer, formatter);
                dateValidForm = true;
            } catch (DateTimeException dte) {
                System.out.println("Please enter a valid date:");
            }
        } while (!dateValidForm);
        return answer;
    }


    // EFFECTS: gets the category chose based on the user's answer
    public String getCategoryFromAnswerChoice(String answer) {
        if (answer.equals("1")) {
            return "Food & Grocery";
        } else if (answer.equals("2")) {
            return "Bills & Utilities";
        } else if (answer.equals("3")) {
            return "Clothing";
        } else if (answer.equals("4")) {
            return "Entertainment & Leisure";
        } else if (answer.equals("5")) {
            return "Other";
        } else {
            return "Incorrect Input";
        }
    }


    // EFFECTS: displays a list of categories to choose from
    public void displayCategories() {
        System.out.println("Choose a category: ");
        System.out.println("\t1 -> Food & Grocery");
        System.out.println("\t2 -> Bills & Utilities");
        System.out.println("\t3 -> Clothing");
        System.out.println("\t4 -> Entertainment & Leisure");
        System.out.println("\t5 -> Other");
    }


    // MODIFIES: this
    // EFFECTS: adds an earning to the account
    public void newEarning() {

        double doubleAmount = getAmount();
        System.out.println("Please enter a name for the expense: ");
        String name = scanner.nextLine();
        System.out.println("Add a longer note if necessary: ");
        String note = scanner.nextLine();
        String date = getDate();

        Earning earning = new Earning(doubleAmount, name, note, date);
        account.addEarning(earning);
        System.out.println("Earning added to account.\nReturning to main menu.\n");
    }


    // EFFECTS: displays stats:
    // balance, expense total, earning total, expense list, earning list
    public void displayGeneralStats() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        System.out.println("\n\nTotal Account Balance : " + account.getBalance());
        System.out.println("Total Expenses : " + account.getTotalExpenses());
        System.out.println("Total Earnings : " + account.getTotalEarnings());

        System.out.println("\nAll your recorded earnings: ");
        for (Earning earning : account.getAllEarningsList()) {
            System.out.printf("%-20s %-40s %-20s %-20s%n", earning.getName(), earning.getNote(),
                    earning.getDate(), formatter.format(earning.getAmount()));
        }

        System.out.println("\nAll your recorded expenses: ");
        for (Expense expense : account.getAllExpensesList()) {
            System.out.printf("%-25s %-35s %-30s %-20s %-20s%n", expense.getName(), expense.getNote(),
                    expense.getCategory(), expense.getDate(), formatter.format(expense.getAmount()));
        }
    }


    // EFFECTS: displays total expenses by category
    public void displaySpendingByCategory() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        System.out.println("\n\nTotal Spending By Category: \n");
        for (String key : account.getExpensesByCategory().keySet()) {
            double total = account.getExpensesByCategory().get(key).getTotal();
            System.out.println("\t" + key + ": " + formatter.format(total));
        }
    }


    // EFFECTS: displays both overall spending and spending broken down by category.
    // Also shows separate lists of expenses and earnings made by user.
    public void viewAccountStats() {
        displayGeneralStats();
        displaySpendingByCategory();
    }


}
