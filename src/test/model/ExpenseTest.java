package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseTest {

    Expense expense;

    @BeforeEach
    public void setup() {
        expense = new Expense(100, "Bought milk","grocery");
    }

    @Test
    public void testConstructor() {
        assertEquals(100, expense.getAmount());
        assertEquals("Bought milk", expense.getName());
        assertEquals("grocery", expense.getCategory());
    }
}
