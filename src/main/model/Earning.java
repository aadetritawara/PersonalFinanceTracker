package model;

public class Earning {
    // this class represents a single earning

    private int amount;
    private String name;

    // REQUIRES: amount > 0
    // EFFECTS: creates an instance of an earning with given amount and name
    public Earning(int amount, String name) {
        this.amount = amount;
        this.name = name;
    }

    // EFFECT: returns the earning's dollar amount
    public int getAmount() {
        return amount;
    }

    // EFFECT: returns the earning's name
    public String getName() {
        return name;
    }
}
