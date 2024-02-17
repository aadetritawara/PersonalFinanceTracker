package model;

public class Expense extends ItemToBeLogged {
    // this class represents a single expense

    private String category;

    // REQUIRES: amount > 0
    // EFFECT: constructs an expense with given amount, name, and category
    public Expense(double amount, String name, String note, String date, String category) {
        super(amount, name, note, date);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

}
