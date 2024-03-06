package persistence;

import model.Account;
import model.Earning;
import model.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    Earning earning;
    Expense expense1;
    Expense expense2;
    Account acc;

    @BeforeEach
    public void setup() {
        acc = new Account();
        earning = new Earning(2500, "salary", "january salary", "01-01-2024");
        expense1 = new Expense(10.99, "milk", "2 cartons", "01-15-2024", "Food & Grocery");
        expense2 = new Expense(1500, "rent", "", "01-01-2024", "Bills & Utilities");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }



    @Test
    void testWriterEmptyAccount() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAccount.json");
            writer.open();
            writer.write(acc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAccount.json");
            acc = reader.read();
            assertEquals(0, acc.getAllEarningsList().size());
            assertEquals(0, acc.getAllExpensesList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterNonEmptyAccount() {
        try {
            acc.addEarning(earning);
            acc.addExpense(expense1);
            acc.addExpense(expense2);
            JsonWriter writer = new JsonWriter("./data/testWriterNonEmptyAccount.json");
            writer.open();
            writer.write(acc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNonEmptyAccount.json");

            acc = reader.read();

            assertEquals(2500, acc.getTotalEarnings());
            assertEquals(10.99 + 1500, acc.getTotalExpenses());
            assertEquals(2, acc.getAllExpensesList().size());
            assertEquals(1, acc.getAllEarningsList().size());
            assertEquals(989.01, acc.getBalance());

            assertEquals(10.99, acc.getExpensesByCategory().get("Food & Grocery").getTotal());
            assertEquals(1500, acc.getExpensesByCategory().get("Bills & Utilities").getTotal());
            assertEquals(0, acc.getExpensesByCategory().get("Entertainment & Leisure").getTotal());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
