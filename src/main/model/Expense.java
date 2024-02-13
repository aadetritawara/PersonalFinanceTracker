package model;

public class Expense {
    // this class represents a single expense

    private int amount;
    private String name;
    private String category;

    // REQUIRES: amount > 0
    // EFFECT: constructs an expense with given amount, name, and category
    public Expense(int amount, String name, String category) {
        this.amount = amount;
        this.name = name;
        this.category = category;
    }

    // EFFECT: returns the expense's dollar amount
    public int getAmount() {
        return amount;
    }

    // EFFECT: returns the expense's category
    public String getCategory() {
        return category;
    }

    // EFFECT: returns the expense's name
    public String getName() {
        return name;
    }
}
