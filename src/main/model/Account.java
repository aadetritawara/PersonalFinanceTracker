package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class Account {
    // This class represents an account to which expenses and earnings may be added.

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
        addToCategory(expense);
        allExpensesList.add(expense);
        updateBalance(expense);
        totalExpenses += expense.getAmount();
    }


    // MODIFIES: this
    // EFFECT: records earning in allEarningsList and the updates balance and totalEarnings values.
    public void addEarning(Earning earning) {
        allEarningsList.add(earning);
        totalEarnings += earning.getAmount();
        updateBalance(earning);
    }

    // MODIFIES: balance
    // EFFECT: updates balance by adding given earning's amount to it,
    // or if it is an expense, decreasing the balance by expense amount
    public void updateBalance(ItemToBeLogged item) {
        if (item instanceof Earning) {
            this.balance += item.getAmount();
        } else {
            this.balance -= item.getAmount();
        }
    }

    // MODIFIES: expensesByCategory
    // EFFECT: adds the expense to appropriate category and updates appropriate category by the expense's amount
    public void addToCategory(Expense expense) {
        for (String key : expensesByCategory.keySet()) {
            Category value = expensesByCategory.get(key);
            if (key.equals(expense.getCategory())) {
                value.getExpenseList().add(expense);
                value.setTotal(expense.getAmount());
            }
        }
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

    // EFFECTS: returns this as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        JSONArray allExpenses = makeJsonExpenseArray(allExpensesList);
        json.put("all expenses list", allExpenses);

        JSONArray allEarnings = makeJsonEarningArray(allEarningsList);
        json.put("all earnings list", allEarnings);

        return json;

    }

    // EFFECTS: returns the Json array associated with the inputted list of type Earning
    private JSONArray makeJsonEarningArray(ArrayList<Earning> list) {
        JSONArray j = new JSONArray();
        for (Earning i: list) {
            JSONObject expense = i.earningToJson();
            j.put(expense);
        }
        return j;
    }

    // EFFECTS: returns the Json array associated with the inputted list of type Expense
    private JSONArray makeJsonExpenseArray(ArrayList<Expense> list) {
        JSONArray j = new JSONArray();
        for (Expense i: list) {
            JSONObject expense = i.expenseToJson();
            j.put(expense);
        }
        return j;
    }
}
