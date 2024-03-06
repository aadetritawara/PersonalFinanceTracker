package model;

import org.json.JSONObject;

public abstract class ItemToBeLogged {

    private double amount;
    private String name;
    private String note;
    private String date;

    // REQUIRES: amount > 0
    // EFFECTS: creates an instance of an item to be logged with given amount and name
    public ItemToBeLogged(double amount, String name, String note, String date) {
        this.amount = amount;
        this.name = name;
        this.note = note;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    public String getDate() {
        return date;
    }

    // EFFECTS: turns this into a Json object
    protected JSONObject itemToJson() {

        JSONObject j = new JSONObject();
        j.put("amount", getAmount());
        j.put("name", getName());
        j.put("note", getNote());
        j.put("date", getDate());
        return j;
    }

}
