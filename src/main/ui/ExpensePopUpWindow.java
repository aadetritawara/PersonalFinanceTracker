package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ExpensePopUpWindow extends JFrame implements ActionListener {

    private ArrayList<String> returningValues;
    private JButton submit;
    private JTextField amount = new JTextField();
    private JTextField name = new JTextField();
    private JTextField note = new JTextField();
    private JTextField date = new JTextField();
    private JTextField category = new JTextField();
    private ArrayList<String> categoryNames = new ArrayList<>(Arrays.asList("Food & Grocery", "Bills & Utilities",
            "Clothing", "Entertainment & Leisure", "Other"));

    // EFFECT: a pop-up window that prompts the user to input details of the expense.
    public ExpensePopUpWindow() {
        System.out.println("The expense window method reached.");
        this.setTitle("Add New Expense");
        this.setResizable(false);
        this.setSize(450, 300);
        this.getContentPane().setBackground(new Color(18, 18, 18));
        this.setLocationRelativeTo(null);

        submit = new JButton("Submit Expense");
        submit.addActionListener(this);

        this.setLayout(new GridLayout(6, 2));

        formatFrame();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    // EFFECT: adds text and input gui to get user input
    private void formatFrame() {

        ArrayList<String> names = new ArrayList<>(Arrays.asList("amount ($): ", "name: ", "note (optional): ",
                "date (MM-DD-YYYY): "));
        ArrayList<JTextField> inputs = new ArrayList<>(Arrays.asList(amount, name, note, date));

        for (int i = 0; i < 5; i++) {
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

        add(submit);
    }

    @Override
    // EFFECT: Gets the values entered upon click of submit button
    // Puts the obtained values into an array called returningValues
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submit) {
            this.dispose();
            String input1 = amount.getText();
            String input2 = name.getText();
            String input3 = note.getText();
            String input4 = date.getText();
            String input5 = category.getText();

            returningValues = new ArrayList<>(Arrays.asList(input1, input2, input3, input4, input5));
        }
    }

    // EFFECT: returns the field "returningValues"
    public ArrayList<String> returnValues() {
        return returningValues;
    }
}
