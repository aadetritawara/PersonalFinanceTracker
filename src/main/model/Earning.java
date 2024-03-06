package model;

import org.json.JSONObject;

public class Earning extends ItemToBeLogged {
    // this class represents a single earning

    // REQUIRES: amount > 0
    // EFFECTS: creates an instance of an earning with given amount and name
    public Earning(double amount, String name, String note, String date) {
        super(amount, name, note, date);
    }

    // EFFECTS: turns this into a Json object
    public JSONObject earningToJson() {
        JSONObject j = super.itemToJson();
        return j;
    }
}
