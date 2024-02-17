package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Account {
    // This class represents an account to which expenses, earnings, and savings may be added

    private HashMap<String, ArrayList<Expense>> expensesListedByCategory;
    private HashMap<String, Integer> totalExpensesByCategory;
    private ArrayList<Expense> allExpensesList;
    private ArrayList<Earning> allEarningsList;
    private int totalEarnings;
    private int totalExpenses;
    private int balance;

    // REQUIRES: income > 0 and savingsGoal > 0
    // EFFECTS: creates an account given an income and savings goal.
    // Initially, expenses in each budget category are empty lists, totalExpensesByCategory have 0 for each
    // category, and balance, total earnings, savings, and expenses are all 0.
    public Account() {

        this.expensesListedByCategory = createEmptyExpenseList();
        this.totalExpensesByCategory = createZeroExpenseList();
        this.allExpensesList = new ArrayList<>();
        this.allEarningsList = new ArrayList<>();
        this.totalExpenses = 0;
        this.totalEarnings = 0;
        this.balance = 0;
    }

    // EFFECTS: creates a key for each budget category. Each key's value is 0.
    public HashMap<String, Integer> createZeroExpenseList() {
        return null; // stub
    }

    // EFFECTS: creates a key for each budget category. Each key's value is an empty array list.
    public HashMap<String, ArrayList<Expense>> createEmptyExpenseList() {
        return null; // stub
    }


    // MODIFIES: this
    // EFFECT: records expense in appropriate category's list in expensesListByCategory,
    // and updates balance, totalExpenses, and totalExpensesByCategory values based on expense's amount.
    public void addExpense() {
        //stub
    }

    // MODIFIES: this
    // EFFECT: updates balance by adding given earning's amount to it,
    // or if it is an expense, decreasing the balance by expense amount


}
