package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Account {
    // This class represents an account to which expenses, earnings, and savings may be added

    private int income;
    private int savingsGoal;
    private HashMap<String, ArrayList<Expense>> expensesListByCategory;
    private HashMap<String, Integer> totalExpensesByCategory;
    private int savingsGoalProgress;
    private int totalEarnings;
    private int totalSavings;
    private int totalExpenses;
    private int balance;

    // REQUIRES: income > 0 and savingsGoal > 0
    // EFFECTS: creates an account given an income and savings goal.
    // Initially, expenses in each budget category are empty lists, totalExpensesByCategory have 0 for each
    // category, and balance, total earnings, savings, and expenses are all 0.
    public Account(int income, int savingsGoal) {

        this.income = income;
        this.savingsGoal = savingsGoal;
        this.expensesListByCategory = createEmptyExpenseList();
        this.totalExpensesByCategory = createZeroExpenseList();
        this.totalExpenses = 0;
        this.totalEarnings = 0;
        this.totalSavings = 0;
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


    // MODIFIES: this, balance, totalExpenses, totalExpensesByCategory, and expensesListByCategory
    // EFFECT: records expense in appropriate category's list in expensesListByCategory,
    // and updates balance, totalExpenses, and totalExpensesByCategory values based on expense's amount.
    public void addExpense() {
        //stub
    }

    // MODIFIES: this, balance, totalEarnings
    // EFFECT: updates balance by adding given earning's amount to it,
    //
}
