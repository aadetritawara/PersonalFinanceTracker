package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SavePopUpWindow extends JFrame implements ActionListener {

    private GuiPersonalFinanceTracker parentClass;
    private JButton yes = new JButton("yes");
    private JButton no = new JButton("no");

    public SavePopUpWindow(GuiPersonalFinanceTracker guiPersonalFinanceTracker) {
        this.parentClass = guiPersonalFinanceTracker;


        this.setTitle("Quit Options");
        this.setResizable(false);
        this.setSize(600, 300);
        this.getContentPane().setBackground(new Color(18, 18, 18));
        this.setLocationRelativeTo(null);

        yes.addActionListener(this);
        no.addActionListener(this);
        JPanel container = new JPanel();
        container.add(yes);
        container.add(no);

        JLabel question = new JLabel("Do you want to save your data to file?");
        add(question, BorderLayout.CENTER);
        add(container, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
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
