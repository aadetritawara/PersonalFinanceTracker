package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingTest {

    @Test
    public void testConstructor() {
        Saving saving = new Saving(10);
        assertEquals(10, saving.getAmount());
    }
}
