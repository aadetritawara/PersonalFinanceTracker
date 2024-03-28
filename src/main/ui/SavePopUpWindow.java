package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// This class represents a pop-up window opened when the user selects "quit" from the application.
// It asks the user if they want to save the current state of the application to the file.
public class SavePopUpWindow extends JFrame implements ActionListener {

    private GuiPersonalFinanceTracker parentClass;
    private JButton yes = new JButton("yes");
    private JButton no = new JButton("no");

    // EFFECT: creates a pop-up window asking user if they want to save to file, with button options to select from
    public SavePopUpWindow(GuiPersonalFinanceTracker guiPersonalFinanceTracker) {
        this.parentClass = guiPersonalFinanceTracker;

        this.setTitle("Quit Options");
        this.setResizable(false);
        this.setSize(450, 200);
        this.getContentPane().setBackground(new Color(18, 18, 18));
        this.setLocationRelativeTo(null);

        yes.addActionListener(this);
        no.addActionListener(this);
        JPanel container = new JPanel();
        container.setBackground(new Color(18, 18, 18));
        container.add(yes);
        container.add(no);

        JLabel question = new JLabel("Do you want to save your data to file?", SwingConstants.CENTER);
        question.setForeground(new Color(211, 211, 211));
        add(question, BorderLayout.CENTER);
        add(container, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: if user wants to save to file, write to the Json file and dispose of current window
    // if user does not want to save to file, dispose of current file
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == yes) {
            try {
                parentClass.getJsonWriter().open();
                parentClass.getJsonWriter().write(parentClass.getAccount());
                parentClass.getJsonWriter().close();
                this.dispose();
            } catch (FileNotFoundException exception) {
                System.out.println("Unable to write to file: " + "./data/FinanceTracker.json");
            }
        } else if (e.getSource() == no) {
            this.dispose();
        }
    }
}
