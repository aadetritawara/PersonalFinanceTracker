package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This class is a pop-up window that redirects the user to the graphical user interface for the application.
// If the user wants to load their data from the file, it will be reflected in redirected page.
public class LoadPopUpWindow extends JFrame implements ActionListener {

    JFrame frame = new JFrame();
    JButton load = new JButton("Yes");
    JButton doNotLoad = new JButton("No");

    // EFFECT: pop up window asks user if they want to load previous session data with use of buttons
    public LoadPopUpWindow() {
        frame.setSize(450,200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel question = new JLabel();
        question.setBackground(new Color(18, 18, 18));
        question.setOpaque(true);
        question.setText("Do you want to load previous session data from file?");
        question.setHorizontalAlignment(SwingConstants.CENTER);
        question.setForeground(Color.lightGray);

        load.addActionListener(this);
        doNotLoad.addActionListener(this);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(18, 18, 18));
        buttonPanel.add(load);
        buttonPanel.add(doNotLoad);

        frame.add(question, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setBackground(new Color(18, 18, 18));

        frame.setVisible(true);
    }


    @Override
    // MODIFIES: this
    // EFFECTS: if user chooses yes, close current frame and open gui frame with data loaded
    // if user chooses no, close current frame and open gui frame without data loaded
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == load) {
            frame.dispose();
            new GuiPersonalFinanceTracker(true);
        } else if (e.getSource() == doNotLoad) {
            frame.dispose();
            new GuiPersonalFinanceTracker(false);
        }
    }

}
