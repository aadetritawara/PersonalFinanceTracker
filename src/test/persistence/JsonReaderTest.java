package persistence;

import model.Account;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderFileDoesNotExist() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Account acc = reader.read();
            fail("No exception was caught, when one was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyAccount() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAccount.json");
        try {
            Account acc = reader.read();
            assertEquals(0, acc.getAllExpensesList().size());
            assertEquals(0, acc.getAllEarningsList().size());
            assertEquals(0, acc.getBalance());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testReaderNonEmptyAccount() {
        JsonReader reader = new JsonReader("./data/testReaderNonEmptyWorkroom.json");
        try {
            Account acc = reader.read();
            assertEquals(2530 - (2*10.99) - 12.99, acc.getBalance());
            assertEquals(2530, acc.getTotalEarnings());
            assertEquals((2*10.99) + 12.99, acc.getTotalExpenses());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
