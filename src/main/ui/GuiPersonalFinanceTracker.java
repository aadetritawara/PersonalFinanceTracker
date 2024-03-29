package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

// this class represents the graphical user interface for the personal finance tracker application
public class GuiPersonalFinanceTracker extends JFrame implements ActionListener {

    // creates and rescales an image icon for earning
    private ImageIcon earning = new ImageIcon(new ImageIcon("src/main/ui/Images/earning.png"
    ).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
    // creates and rescales an image icon for expense
    private ImageIcon expense = new ImageIcon(new ImageIcon("src/main/ui/Images/expense.png"
    ).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));

    private ArrayList<ImageIcon> icons = new ArrayList<>();

    private JButton earningButton = new JButton("Add New Earning");
    private JButton quitButton = new JButton("Quit");
    private JButton expenseButton = new JButton("Add New Expense");
    private Account acc;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private JPanel rightContainer;
    private DecimalFormat df = new DecimalFormat("#0.00");
    private LoggingTableModel model;
    private JTable table;
    private TableRowSorter<LoggingTableModel> sorter;
    private JButton expensesFilterButton = new JButton("Filter Expenses");
    private JButton earningsFilterButton = new JButton("Filter Earnings");
    private JButton showAllButton = new JButton("Show All Items");


    // EFFECT: creates a graphical interface for the personal finance tracker application
    // if the inputted boolean is true, the data from the file is loaded, otherwise present an empty tracker
    public GuiPersonalFinanceTracker(Boolean b) {

        jsonReader = new JsonReader("./data/FinanceTracker.json");
        jsonWriter = new JsonWriter("./data/FinanceTracker.json");
        acc = new Account();

        if (b == true) {
            load();
        }

        this.setTitle("Personal Finance Tracker");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.getContentPane().setBackground(new Color(18, 18, 18));
        this. setLocationRelativeTo(null);
        this.getContentPane().setLayout(new BorderLayout());
        initializeGuiComponents();
        add(createCenter(), BorderLayout.CENTER); // center of screen/table

        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECT: adds components to an empty JFrame in their correct area
    private void initializeGuiComponents() {
        add(setNorth(), BorderLayout.NORTH); // top

        JPanel container = new JPanel();
        container.setBackground(new Color(18, 18, 18));
        container.setLayout(new GridLayout(4, 1));
        container.add(new JLabel(""));
        container.add(earningButton());
        container.add(expenseButton());
        container.add(quitButton());

        add(container, BorderLayout.EAST); // right of screen
    }

    // EFFECT: returns a component needed to be placed in the center of the application's graphical user interface
    private Component createCenter() {

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(new Color(18, 18,18));
        JLabel title = new JLabel("All Logged Earnings & Expenses", SwingConstants.CENTER);
        title.setForeground(Color.lightGray);
        title.setBackground(new Color(89, 70, 178));
        title.setOpaque(true);

        JPanel northContainer = new JPanel(new GridLayout(2,1));
        northContainer.setBackground(new Color(18,18,18));
        northContainer.add(new JLabel(""));
        northContainer.add(title);

        container.add(northContainer, BorderLayout.NORTH);

        container.add(returnTable(), BorderLayout.CENTER);

        container.add(filteringAction(), BorderLayout.SOUTH);

        return container;
    }

    // EFFECTS: returns the component that contains all the buttons needed for filtering
    private Component filteringAction() {

        JPanel container = new JPanel(new GridLayout(1,3));
        container.setBackground(new Color(18,18,18));

        earningsFilterButton.addActionListener(this);
        expensesFilterButton.addActionListener(this);
        showAllButton.addActionListener(this);

        container.add(earningsFilterButton);
        container.add(expensesFilterButton);
        container.add(showAllButton);
        return container;
    }

    // EFFECTS: helper function for createCenter() that creates the table displaying earnings & expenses together
    private Component returnTable() {
        ArrayList<ItemToBeLogged> itemList = new ArrayList<>();

        for (ItemToBeLogged item : acc.getAllEarningsList()) {
            ItemToBeLogged i = item;
            itemList.add(i);
        }

        for (ItemToBeLogged item : acc.getAllExpensesList()) {
            ItemToBeLogged i = item;
            itemList.add(i);
        }

        JScrollPane js = new JScrollPane();
        model = new LoggingTableModel(itemList);
        table = new JTable(model);
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        table.setBackground(Color.darkGray);
        table.setForeground(new Color(230,230,250));
        js.setViewportView(table);
        js.getViewport().setBackground(table.getBackground());

        return js;
    }

    // EFFECTS: formats quit button and sets it up as an action listener object
    private Component quitButton() {
        JPanel container = new JPanel();
        container.setBackground(new Color(18, 18, 18));

        quitButton.setForeground(new Color(18, 18, 18));
        quitButton.addActionListener(this);

        container.add(quitButton);

        return container;
    }

    // EFFECT: adds balance and spending breakdown in the north area of the JFrame
    private Component setNorth() {
        JPanel container = new JPanel();
        container.setBackground(new Color(18, 18, 18));

        container.setLayout(new GridLayout(1,2));

        container.add(leftContainer());
        container.add(rightContainer());

        return container;
    }

    // EFFECTS: helper function for setNorth() that creates the spending by category visual
    private Component rightContainer() {
        rightContainer = new JPanel();
        rightContainer.setBackground(new Color(18, 18, 18));
        rightContainer.setLayout(new GridLayout(2,1));

        JLabel title = new JLabel();
        title.setText("Spending By Category");
        title.setForeground(new Color(211, 211, 211));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        rightContainer.add(title);
        rightContainer.add(secondRow());
        return rightContainer;
    }

    // EFFECT: creates an array list of resized icons
    private ArrayList<String> resizeIcons() {

        ArrayList<String> filePaths = new ArrayList<>(Arrays.asList("src/main/ui/Images/shoppingCart.png",
                "src/main/ui/Images/lightBulb.png", "src/main/ui/Images/jacket.png",
                "src/main/ui/Images/controller.png", "src/main/ui/Images/wallet.png"));
        ArrayList<String> categoryNames = new ArrayList<>(Arrays.asList("Food & Grocery", "Bills & Utilities",
                "Clothing", "Entertainment & Leisure", "Other"));

        for (int i = 0; i < 5; i++) {
            ImageIcon icon = new ImageIcon(new ImageIcon(filePaths.get(i)).getImage().getScaledInstance(35,
                    35, Image.SCALE_DEFAULT));
            icons.add(icon);
        }
        return categoryNames;
    }

    // EFFECTS: creates the second row of the north region. Each column has a category icon and the total spending
    // in that category. There are five columns, one for each category of expenses.
    private Component secondRow() {
        JPanel container = new JPanel();
        container.setBackground(new Color(18, 18, 18));
        container.setLayout(new GridLayout(3, 5));

        ArrayList<String> categories = resizeIcons();

        ArrayList<String> shorter = new ArrayList<>(Arrays.asList("Food", "Bills", "Clothing", "Leisure", "Other"));
        for (String str : shorter) {
            JLabel zeroRow = new JLabel();
            zeroRow.setText(str);
            zeroRow.setForeground(new Color(211, 211, 211));
            container.add(zeroRow);
        }

        for (ImageIcon img : icons) {
            JLabel firstRow = new JLabel();
            firstRow.setIcon(img);
            container.add(firstRow);
        }

        for (String c : categories) {
            JLabel secondRow = new JLabel();
            secondRow.setText("$ " + df.format(acc.getExpensesByCategory().get(c).getTotal()));
            secondRow.setForeground(new Color(211, 211, 211));
            container.add(secondRow);
        }

        return container;
    }

    // EFFECTS: constructs the balance, total expense, and total earnings panel
    private Component leftContainer() {
        JPanel leftContainer = new JPanel();
        leftContainer.setBackground(new Color(18, 18, 18));
        leftContainer.setLayout(new GridLayout(3,2));

        ArrayList<Double> amounts = new ArrayList<>(Arrays.asList(acc.getBalance(), acc.getTotalExpenses(),
                acc.getTotalEarnings()));

        ArrayList<JLabel> accountSummaryLabelTitles = new ArrayList<>(Arrays.asList(new JLabel("Balance",
                        SwingConstants.CENTER), new JLabel("Total Expenses", SwingConstants.CENTER),
                new JLabel("Total Earnings", SwingConstants.CENTER)));

        for (int i = 0; i < 3; i++) {
            JLabel amount = new JLabel();
            amount.setText("$" + df.format(amounts.get(i)));
            amount.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel title = accountSummaryLabelTitles.get(i);
            title.setForeground(new Color(211, 211, 211));
            amount.setForeground(new Color(211, 211, 211));

            leftContainer.add(title);
            leftContainer.add(amount);
        }

        return leftContainer;
    }

    // EFFECT: adds a button for adding an earning to the account
    private Component earningButton() {
        JPanel container = new JPanel();
        container.setBackground(new Color(18, 18, 18));

        JLabel label = new JLabel(earning);

        earningButton.setForeground(new Color(18, 18, 18));
        earningButton.setBackground(new Color(89, 70, 178));
        earningButton.addActionListener(this);

        container.add(label);
        container.add(earningButton);

        return container;
    }

    // EFFECT: adds a button for adding an expense to the account
    private Component expenseButton() {
        JPanel container = new JPanel();
        container.setBackground(new Color(18, 18, 18));

        JLabel label = new JLabel(expense);

        expenseButton.setForeground(new Color(18, 18, 18));
        expenseButton.setBackground(new Color(89, 70, 178));
        expenseButton.addActionListener(this);

        container.add(label);
        container.add(expenseButton);

        return container;
    }

    @Override
    // MODIFIES: this
    // EFFECT: opens appropriate windows based on the button clicked : earning/expense/quit
    // (For implementing filtering activity: I got help from this question:
    // https://stackoverflow.com/questions/1107911/how-can-i-filter-rows-in-a-jtable)
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == earningButton) {
            new EarningPopUpWindow(this);
        } else if (e.getSource() == expenseButton) {
            new ExpensePopUpWindow(this);
        } else if (e.getSource() == quitButton) {
            new SavePopUpWindow(this);
            dispose();
        } else if (e.getSource() == showAllButton) {
            sorter.setRowFilter(null);
        } else if (e.getSource() == earningsFilterButton) {
            RowFilter<LoggingTableModel, Integer> earningsFilter = RowFilter.regexFilter("Earning", 4);
            sorter.setRowFilter(earningsFilter);
        } else if (e.getSource() == expensesFilterButton) {
            RowFilter<LoggingTableModel, Integer> expensesFilter = RowFilter.regexFilter("Expense", 4);
            sorter.setRowFilter(expensesFilter);
        }
    }

    // MODIFIES: this
    // EFFECTS: reads data already in the file while updating all necessary features of the GUI
    // (balance, spending by category, & JTable listing earnings/expenses)
    public void load() {
        try {
            acc = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file");
        }
    }

    // MODIFIES: this
    // EFFECT: updates the graphical user interface according to changes made in the accounts class from pop-up windows
    public void update(ItemToBeLogged item) {
        rightContainer = null;
        icons.clear();
        initializeGuiComponents();
        this.setVisible(true);
    }

    public Account getAccount() {
        return this.acc;
    }

    public JsonWriter getJsonWriter() {
        return this.jsonWriter;
    }

    public LoggingTableModel getModel() {
        return model;
    }

}
