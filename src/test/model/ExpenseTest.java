package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseTest {

    Expense expense;

    @BeforeEach
    public void setup() {
        expense = new Expense(10.99, "Bought milk","2 cartons", "2024-02-15", "grocery");
    }

    @Test
    public void testConstructor() {
        assertEquals(10.99, expense.getAmount());
        assertEquals("Bought milk", expense.getName());
        assertEquals("2 cartons", expense.getNote());
        assertEquals("grocery", expense.getCategory());
        assertEquals("2024-02-15", expense.getDate());
    }

    @Test
    public void testGetAmount() {
        assertEquals(10.99, expense.getAmount());
    }

    @Test
    public void testGetName() {
        assertEquals("Bought milk", expense.getName());
    }

    @Test
    public void testGetNote() {
        assertEquals("2 cartons", expense.getNote());
    }

    @Test
    public void testGetCategory() {
        assertEquals("grocery", expense.getCategory());
    }

    @Test
    public void testGetDate() {
        assertEquals("2024-02-15", expense.getDate());
    }
}
