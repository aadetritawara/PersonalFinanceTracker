package model;

import java.util.ArrayList;

public class Category {
    // this class creates a category with a list of expenses in the
    // category and the total of that category

    private ArrayList<Expense> expenseList;
    private double total;

    public Category(ArrayList<Expense> expenseList, double total) {
        this.expenseList = expenseList;
        this.total = total;
    }

    public ArrayList<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(ArrayList<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total += total;
    }
}
