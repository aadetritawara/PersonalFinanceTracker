package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Category {
    // this class represents a category with a list of expenses in the
    // category and the total of that category

    private ArrayList<Expense> expenseList;
    private double total;

    // EFFECTS: creates a category with inputted list and total value
    public Category(ArrayList<Expense> expenseList, double total) {
        this.expenseList = expenseList;
        this.total = total;
    }

    public ArrayList<Expense> getExpenseList() {
        return expenseList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total += total;
    }

}
