package ui;

import model.Expense;
import model.ItemToBeLogged;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ExpensePopUpWindow extends JFrame implements ActionListener {

    private GuiPersonalFinanceTracker parentClass;
    private JButton submit;
    private JButton cancel;
    private JTextField amount = new JTextField();
    private JTextField name = new JTextField();
    private JTextField note = new JTextField();
    private JTextField date = new JTextField();
    private ArrayList<String> categoryNames = new ArrayList<>(Arrays.asList("Food & Grocery", "Bills & Utilities",
            "Clothing", "Entertainment & Leisure", "Other"));
    private JRadioButton food = new JRadioButton("Food & Grocery");
    private JRadioButton bills = new JRadioButton("Bills & Utilities");
    private JRadioButton clothing = new JRadioButton("Clothing");
    private JRadioButton leisure = new JRadioButton("Entertainment & Leisure");
    private JRadioButton other = new JRadioButton("Other");
    private ArrayList<JRadioButton> rbuttons = new ArrayList<>(Arrays.asList(food, bills, clothing, leisure, other));

    // EFFECT: a pop-up window that prompts the user to input details of the expense.
    public ExpensePopUpWindow(GuiPersonalFinanceTracker guiPersonalFinanceTracker) {
        this.parentClass = guiPersonalFinanceTracker;
        this.setTitle("Add New Expense");
        this.setResizable(false);
        this.setSize(600, 300);
        this.getContentPane().setBackground(new Color(18, 18, 18));
        this.setLocationRelativeTo(null);

        cancel = new JButton("Cancel");
        submit = new JButton("Submit Expense");
        submit.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new GridLayout(8, 2));

        formatFrame();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    // EFFECT: adds text and input gui to get user input
    private void formatFrame() {

        ArrayList<String> names = new ArrayList<>(Arrays.asList("amount ($): ", "name: ", "note (optional): ",
                "date (MM-DD-YYYY): "));
        ArrayList<JTextField> inputs = new ArrayList<>(Arrays.asList(amount, name, note, date));

        for (int i = 0; i < 4; i++) {
            JLabel text = new JLabel();
            text.setText(names.get(i));
            text.setForeground(new Color(211, 211, 211, 211));

            add(text);
            add(inputs.get(i));
        }

        JLabel text = new JLabel();
        text.setText("category: ");
        text.setForeground(new Color(211, 211, 211, 211));
        add(text);

        ButtonGroup group = new ButtonGroup();
        for (JRadioButton b : rbuttons) {
            b.setForeground(new Color(211, 211, 211));
            group.add(b);
            add(b);
        }

        add(submit);
        add(cancel);
    }

    @Override
    // EFFECT: Gets the values entered upon click of submit button
    // Puts the obtained values into an array called returningValues
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submit) {
            double input1 = Double.parseDouble(amount.getText());
            String input2 = name.getText();
            String input3 = note.getText();
            String input4 = date.getText();
            String input5 = null;
            for (int i = 0; i < 5; i++) {
                JRadioButton rb = rbuttons.get(i);
                if (rb.isSelected()) {
                    input5 = categoryNames.get(i);
                }
            }

            Expense expense = new Expense(input1, input2, input3, input4, input5);
            parentClass.getAccount().addExpense(expense);
            parentClass.update(expense);
            this.dispose();
        } else if (e.getSource() == cancel) {
            this.dispose();
        }
    }

}
