package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryTest {
    // tests for category class in model package

    Category category;

    @BeforeEach
    public void setup() {
        category = new Category(new ArrayList<>(), 0);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, category.getTotal());
        assertTrue(category.getExpenseList().isEmpty());
    }

    @Test
    public void testGetTotal() {
        assertEquals(0, category.getTotal());
    }

    @Test
    public void testGetExpenseList() {
        assertTrue(category.getExpenseList().isEmpty());
        assertEquals(0, category.getExpenseList().size());
    }

    @Test
    public void testSetTotal() {
        category.setTotal(29.99);
        assertEquals(29.99, category.getTotal());
    }

}
