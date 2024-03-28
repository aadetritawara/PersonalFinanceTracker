package ui;

import model.Earning;
import model.ItemToBeLogged;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

// This class is a pop-up window that appears when the user clicks the "Add new earning" button
// It prompts the user to input data related their earning, and adds it to the account.
public class EarningPopUpWindow extends JFrame implements ActionListener {

    private GuiPersonalFinanceTracker parentClass;
    private JButton submit;
    private JButton cancel;
    private JTextField name = new JTextField();
    private JTextField note = new JTextField();
    private JTextField date = new JTextField();
    private JTextField amount = new JTextField();
    ArrayList<JTextField> inputs = new ArrayList<>(Arrays.asList(amount, name, note, date));

    // EFFECT: a pop-up window that prompts the user to input details of the earning.
    public EarningPopUpWindow(GuiPersonalFinanceTracker guiPersonalFinanceTracker) {
        this.parentClass = guiPersonalFinanceTracker;
        this.setTitle("Add New Earning");
        this.setResizable(false);
        this.setSize(600, 250);
        this.getContentPane().setBackground(new Color(18, 18, 18));
        this.setLocationRelativeTo(null);

        cancel = new JButton("Cancel");
        submit = new JButton("Submit Expense");
        submit.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new GridLayout(6, 2));

        for (JTextField input : inputs) {
            input.setBackground(Color.darkGray);
            input.setForeground(Color.white);
            input.setCaretColor(Color.lightGray);
        }

        formatFrame();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    // EFFECTS: a helper function of the constructor that adds graphical components to the frame to get user input
    private void formatFrame() {
        JPanel container = new JPanel();
        container.setBackground(new Color(18,18,18));

        ArrayList<String> names = new ArrayList<>(Arrays.asList("amount ($): ", "name: ", "note (optional): ",
                "date (MM-DD-YYYY): "));

        for (int i = 0; i < 4; i++) {
            JLabel text = new JLabel();
            text.setText(names.get(i));
            text.setForeground(new Color(211, 211, 211, 211));

            add(text);
            add(inputs.get(i));
        }
        add(new JLabel());
        add(new JLabel());
        add(submit);
        add(cancel);
    }

    @Override
    // MODIFIES: this
    // EFFECT: Gets the values entered upon click of submit button
    // Adds the submitted earning to the account
    // updates gui and earning table with a new row
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            double input1 = Double.parseDouble(amount.getText());
            String input2 = name.getText();
            String input3 = note.getText();
            String input4 = date.getText();

            Earning earning = new Earning(input1, input2, input3, input4);
            ItemToBeLogged itemEarning = earning;
            parentClass.getAccount().addEarning(earning);
            parentClass.update(earning);
            parentClass.getEarningModel().addItem(itemEarning);
            this.dispose();
        } else if (e.getSource() == cancel) {
            this.dispose();
        }
    }
}
