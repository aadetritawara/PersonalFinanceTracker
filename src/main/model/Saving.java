package model;

public class Saving {
    // this class represents a single saving

    private int amount;

    // REQUIRES: amount > 0
    // EFFECTS: creates a saving with given amount
    public Saving(int amount) {
        this.amount = amount;
    }

    // EFFECT: returns the saving's dollar amount
    public int getAmount() {
        return amount;
    }
}
