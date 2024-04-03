package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// class modeled on JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads account from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads account from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Account read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccount(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses account from JSON object and returns it
    private Account parseAccount(JSONObject jsonObject) {
        Account acc = new Account();
        addThingies(acc, jsonObject);
        EventLog.getInstance().logEvent(new Event("Account Information Successfully Loaded."));
        return acc;
    }

    // MODIFIES: acc
    // EFFECTS: parses items to be logged from JSON object and adds them to account
    private void addThingies(Account acc, JSONObject jsonObject) {
        JSONArray jsonArrayExpenses = jsonObject.getJSONArray("all expenses list");
        for (Object json : jsonArrayExpenses) {
            JSONObject nextExpense = (JSONObject) json;
            addExpense(acc, nextExpense);
        }

        JSONArray jsonArrayEarnings = jsonObject.getJSONArray("all earnings list");
        for (Object json : jsonArrayEarnings) {
            JSONObject nextEarning = (JSONObject) json;
            addEarning(acc, nextEarning);
        }
    }

    // MODIFIES: acc
    // EFFECTS: parses expense from JSON object and adds it to account
    private void addExpense(Account acc, JSONObject jsonObject) {

        double amount = jsonObject.getDouble("amount");
        String name = jsonObject.getString("name");
        String note = jsonObject.getString("note");
        String date = jsonObject.getString("date");
        String category = jsonObject.getString("category");

        Expense expense = new Expense(amount, name, note, date, category);
        acc.addExpense(expense);
    }

    // MODIFIES: acc
    // EFFECTS: parses earning from JSON object and adds it to account
    private void addEarning(Account acc, JSONObject jsonObject) {

        double amount = jsonObject.getDouble("amount");
        String name = jsonObject.getString("name");
        String note = jsonObject.getString("note");
        String date = jsonObject.getString("date");

        Earning earning = new Earning(amount, name, note, date);
        acc.addEarning(earning);
    }
}

