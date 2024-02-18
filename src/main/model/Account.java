package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Account {
    // This class represents an account to which expenses, earnings, and savings may be added

    private HashMap<String, Category> expensesByCategory;
    private ArrayList<Expense> allExpensesList;
    private ArrayList<Earning> allEarningsList;
    private double totalEarnings;
    private double totalExpenses;
    private double balance;

    // EFFECTS: creates an account.
    // Initially, expenses in each budget category are empty lists with a $0 total for each category,
    // and balance, total earnings, and total expenses are all 0. All expenses and all earnings lists are empty.
    public Account() {

        makeEmptyCategoryMap();
        this.allExpensesList = new ArrayList<>();
        this.allEarningsList = new ArrayList<>();
        this.totalExpenses = 0;
        this.totalEarnings = 0;
        this.balance = 0;
    }


    // MODIFIES: expensesByCategory
    // EFFECT: creates a hash map of 5 category keys with values representing a category class which has an empty
    // expense list and a 0 total value.
    public void makeEmptyCategoryMap() {
        this.expensesByCategory = new HashMap<>();
        ArrayList<String> categoryList = new ArrayList<>(Arrays.asList("Food & Grocery", "Bills & Utilities",
                "Clothing", "Entertainment & Leisure", "Other"));
        for (String category : categoryList) {
            this.expensesByCategory.put(category, new Category(new ArrayList<>(), 0));
        }
    }


    // MODIFIES: this
    // EFFECT: records expense in allExpensesList and appropriate category's expenses list in expensesByCategory.
    // Also updates numerical values of balance, totalExpenses, & expensesByCategory's total field with expense's amount
    public void addExpense(Expense expense) {
        //stub
    }


    // MODIFIES: this
    // EFFECT: records earning in allEarningsList and the updates balance and totalEarnings values.
    public void addEarning(Earning earning) {
        //stub
    }

    // MODIFIES: balance
    // EFFECT: updates balance by adding given earning's amount to it,
    // or if it is an expense, decreasing the balance by expense amount
    public void updateBalance(ItemToBeLogged item) {
        if (item instanceof Earning) {
            this.balance = balance += item.getAmount();
        } else {
            this.balance = balance -= item.getAmount();
        }
    }

    // MODIFIES: expensesByCategory
    // EFFECT: adds the expense to appropriate category and updates appropriate category by the expense's amount
    public void addToCategory(Expense expense) {
        //stub
    }

    public HashMap<String, Category> getExpensesByCategory() {
        return expensesByCategory;
    }

    public ArrayList<Expense> getAllExpensesList() {
        return allExpensesList;
    }

    public ArrayList<Earning> getAllEarningsList() {
        return allEarningsList;
    }

    public double getBalance() {
        return balance;
    }

    public double getTotalEarnings() {
        return totalEarnings;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }
}
