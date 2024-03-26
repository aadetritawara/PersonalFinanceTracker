package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ExpensePopUpWindow extends JFrame implements ActionListener {

    ArrayList<String> returningValues;
    JButton submit;
    JTextField amount = new JTextField();
    JTextField name = new JTextField();
    JTextField note = new JTextField();
    JTextField date = new JTextField();
    JTextField category = new JTextField();

    public ExpensePopUpWindow() {
        this.setTitle("Add New Expense");
        this.setResizable(false);
        this.setSize(450, 300);
        this.getContentPane().setBackground(new Color(18, 18, 18));
        this.setLocationRelativeTo(null);

        submit = new JButton("Submit Expense");
        submit.addActionListener(this);

        this.setLayout(new GridLayout(6, 1));
        add(amount);
        add(name);
        add(note);
        add(date);
        add(category);
        add(submit);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submit) {
            String input1 = amount.getText();
            String input2 = name.getText();
            String input3 = note.getText();
            String input4 = date.getText();
            String input5 = category.getText();

            returningValues = new ArrayList<>(Arrays.asList(input1, input2, input3, input4, input5));

        }
    }

    public ArrayList<String> returnValues() {
        return returningValues;
    }
}
