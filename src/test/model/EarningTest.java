package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EarningTest {
    // tests for earning class in model package

    Earning earning;

    @BeforeEach
    public void setup() {
        earning = new Earning(1000, "salary", "my monthly salary", "2024-02-15");
    }

    @Test
    public void testConstructor() {
       assertEquals(1000, earning.getAmount());
       assertEquals("salary", earning.getName());
       assertEquals("my monthly salary", earning.getNote());
       assertEquals("2024-02-15", earning.getDate());
    }

    @Test
    public void testGetAmount() {
        assertEquals(1000, earning.getAmount());
    }

    @Test
    public void testGetNote() {
        assertEquals("my monthly salary", earning.getNote());
    }

    @Test
    public void testGetName() {
        assertEquals("salary", earning.getName());
    }

    @Test
    public void testGetDate() {
        assertEquals("2024-02-15", earning.getDate());
    }
}
