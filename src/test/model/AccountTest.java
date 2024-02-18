package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    // tests for account class in model package

    Account account;
    Expense expense1;
    Expense expense2;
    Expense expense3;
    Expense expense4;
    Expense expense5;
    Expense expense6;
    Expense expense7;
    Earning earning1;
    Earning earning2;
    Earning earning3;
    ArrayList<String> categoryList;

    @BeforeEach
    public void setup() {
        account = new Account();

        expense1 = new Expense(1.99, "Gum", "", "01-29-2024", "Food & Grocery");
        expense2 = new Expense(9.97, "Milk", "2 cartons", "01-29-2024",
                "Food & Grocery");
        expense3 = new Expense(50.99, "Jacket", "gym jacket", "02-17-2024",
                "Clothing");
        expense4 = new Expense(1200, "Rent" , "Jan rent", "01-01-2024",
                "Bills & Utilities");
        expense5 = new Expense(10.99, "Gym", "biweekly 1st installment", "01-01-2024",
                "Entertainment & Leisure");
        expense6 = new Expense(10.99, "Spotify", "premium", "01-03-2024",
                "Entertainment & Leisure");
        expense7 = new Expense(60.00, "Textbook", "History textbook", "02-16-2024",
                "Other");

        earning1 = new Earning(2000, "Salary", "January salary", "01-01-2024");
        earning2 = new Earning(50, "Paid Back", "Irene paid me back", "01-05-2024");
        earning3 = new Earning(2500, "February salary", "", "02-01-2024");

        categoryList = new ArrayList<>(Arrays.asList("Food & Grocery", "Bills & Utilities", "Clothing",
                "Entertainment & Leisure", "Other"));
    }

    @Test
    public void testConstructor() {
        testMakeEmptyCategoryMap();
        assertEquals(0, account.getBalance());
        assertEquals(0, account.getTotalEarnings());
        assertEquals(0, account.getTotalExpenses());
        assertTrue(account.getAllEarningsList().isEmpty());
        assertTrue(account.getAllExpensesList().isEmpty());
    }

    @Test
    public void testMakeEmptyCategoryMap() {
        assertEquals(5, account.getExpensesByCategory().size());
        // checking if all expense lists are empty
        // checking if all totals are 0
        for (String category : categoryList) {
            assertTrue(account.getExpensesByCategory().get(category).getExpenseList().isEmpty());
            assertEquals(0, account.getExpensesByCategory().get(category).getTotal());
        }
    }

    @Test
    public void testAddExpenseOnce() {
        account.addExpense(expense1);
        assertEquals(1, account.getExpensesByCategory().get("Food & Grocery").getExpenseList().size());
        assertTrue(account.getExpensesByCategory().get("Other").getExpenseList().isEmpty());
        account.addExpense(expense3);
        account.addExpense(expense4);
        account.addExpense(expense5);
        account.addExpense(expense7);

        assertEquals(-1*(1.99 + 50.99 + 1200 + 10.99 + 60.00), account.getBalance());
        assertEquals(1.99 + 50.99 + 1200 + 10.99 + 60.00, account.getTotalExpenses());
        assertEquals( 5, account.getAllExpensesList().size());

        //checking if all expenses were added in order
        ArrayList<Expense> exp = new ArrayList<>(Arrays.asList(expense1, expense3, expense4, expense5, expense7));
        for (int i = 0; i < 5; i++) {
            assertEquals(exp.get(i), account.getAllExpensesList().get(i));
        }

        //checking if all expense lists for all categories are sized 1 since we added 1 item to each list
        for (String category : categoryList) {
            assertEquals(1, account.getExpensesByCategory().get(category).getExpenseList().size());
        }

        // checking values of each category's list and total
        assertEquals(expense1, account.getExpensesByCategory().get("Food & Grocery").getExpenseList().get(0));
        assertEquals(1.99, account.getExpensesByCategory().get("Food & Grocery").getTotal());

        assertEquals(expense3, account.getExpensesByCategory().get("Clothing").getExpenseList().get(0));
        assertEquals(50.99, account.getExpensesByCategory().get("Clothing").getTotal());

        assertEquals(expense4, account.getExpensesByCategory().get("Bills & Utilities").getExpenseList().get(0));
        assertEquals(1200, account.getExpensesByCategory().get("Bills & Utilities").getTotal());

        assertEquals(expense5, account.getExpensesByCategory().get("Entertainment & Leisure").getExpenseList().get(0));
        assertEquals(10.99, account.getExpensesByCategory().get("Entertainment & Leisure").getTotal());

        assertEquals(expense7, account.getExpensesByCategory().get("Other").getExpenseList().get(0));
        assertEquals(60.00, account.getExpensesByCategory().get("Other").getTotal());

    }

    @Test
    public void testAddExpenseTwice() {
        account.addExpense(expense1);
        account.addExpense(expense2);
        account.addExpense(expense5);
        account.addExpense(expense6);

        // checking values of each category's list and total
        assertEquals(2, account.getExpensesByCategory().get("Food & Grocery").getExpenseList().size());
        assertEquals(expense1, account.getExpensesByCategory().get("Food & Grocery").getExpenseList().get(0));
        assertEquals(expense2, account.getExpensesByCategory().get("Food & Grocery").getExpenseList().get(1));
        assertEquals(1.99 + 9.97, account.getExpensesByCategory().get("Food & Grocery").getTotal());

        assertEquals(2, account.getExpensesByCategory().get("Entertainment & Leisure").getExpenseList().size());
        assertEquals(expense5, account.getExpensesByCategory().get("Entertainment & Leisure").getExpenseList().get(0));
        assertEquals(expense6, account.getExpensesByCategory().get("Entertainment & Leisure").getExpenseList().get(1));
        assertEquals(10.99 + 10.99, account.getExpensesByCategory().get("Entertainment & Leisure").getTotal());
    }

    @Test
    public void testAddEarningOnce() {
        account.addEarning(earning1);
        assertEquals(2000, account.getBalance());
        assertEquals(2000, account.getTotalEarnings());
        assertEquals(earning1, account.getAllEarningsList().get(0));
        assertEquals(1, account.getAllEarningsList().size());
    }


    @Test
    public void testAddEarningMultipleTimes() {
        account.addEarning(earning1);
        account.addEarning(earning2);
        account.addEarning(earning3);
        assertEquals(2000 + 50 + 2500, account.getBalance());
        assertEquals(2000 + 50 + 2500, account.getTotalEarnings());

        assertEquals(earning1, account.getAllEarningsList().get(0));
        assertEquals(earning2, account.getAllEarningsList().get(1));
        assertEquals(earning3, account.getAllEarningsList().get(2));
        assertEquals(3, account.getAllEarningsList().size());
    }

    @Test
    public void testUpdateBalance() {
        account.updateBalance(earning1);
        assertEquals(2000, account.getBalance());
        account.updateBalance(expense1);
        assertEquals(2000 - 1.99, account.getBalance());
        account.updateBalance(expense5);
        account.updateBalance(earning2);
        account.updateBalance(earning3);
        account.updateBalance(expense7);
        assertEquals(2000 - 1.99 - 10.99 + 50 + 2500 - 60.00, account.getBalance());
    }

    @Test
    public void testAddToCategory() {
        account.addToCategory(expense1);
        assertEquals(1, account.getExpensesByCategory().get("Food & Grocery").getExpenseList().size());
        assertTrue(account.getExpensesByCategory().get("Other").getExpenseList().isEmpty());
        assertEquals(0, account.getExpensesByCategory().get("Other").getTotal());

        account.addToCategory(expense2);
        assertEquals(2, account.getExpensesByCategory().get("Food & Grocery").getExpenseList().size());
        assertEquals(expense1, account.getExpensesByCategory().get("Food & Grocery").getExpenseList().get(0));
        assertEquals(expense2, account.getExpensesByCategory().get("Food & Grocery").getExpenseList().get(1));
        assertEquals(1.99 + 9.97, account.getExpensesByCategory().get("Food & Grocery").getTotal());
        assertTrue(account.getExpensesByCategory().get("Other").getExpenseList().isEmpty());
        assertEquals(0, account.getExpensesByCategory().get("Other").getTotal());

    }


}
