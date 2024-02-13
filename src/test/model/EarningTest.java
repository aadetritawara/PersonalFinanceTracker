package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EarningTest {

    Earning earning;

    @BeforeEach
    public void setup() {
        earning = new Earning(1000, "salary");
    }

    @Test
    public void testConstructor() {
       assertEquals(1000, earning.getAmount());
       assertEquals("salary", earning.getName());
    }
}
