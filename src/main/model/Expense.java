package model;

import org.json.JSONArray;
import org.json.JSONObject;

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

    // EFFECTS: turns this into a Json object
    public JSONObject expenseToJson() {
        JSONObject j = super.itemToJson();
        j.put("category", getCategory());
        return j;
    }

    @Override
    public boolean isExpense() {
        return true;
    }

    @Override
    public boolean isEarning() {
        return false;
    }
}
