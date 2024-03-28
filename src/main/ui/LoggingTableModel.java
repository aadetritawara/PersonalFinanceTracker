package ui;

import model.ItemToBeLogged;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

// This class represents a modified table model. It contains items needing to be logged (either
// an expense or an earning)
public class LoggingTableModel extends AbstractTableModel {

    private ArrayList<ItemToBeLogged> itemsList;
    private final String[] columnNames = {"Name", "Note", "Date", "Amount"};

    // EFFECTS: sets the given array list of items to the field value
    public LoggingTableModel(ArrayList<ItemToBeLogged> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    // EFFECTS: gets the total number of rows
    public int getRowCount() {
        return itemsList.size();
    }

    @Override
    // EFFECTS: gets the count of the number of columns
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    // EFFECTS: gets the value of the given array of items at the given row and column
    public Object getValueAt(int rowIndex, int columnIndex) {
        ItemToBeLogged item = itemsList.get(rowIndex);
        if (columnIndex == 0) {
            return item.getName();
        } else if (columnIndex == 1) {
            return item.getNote();
        } else if (columnIndex == 2) {
            return item.getDate();
        } else {
            return item.getAmount();
        }
    }

    @Override
    // EFFECTS: returns the name of the column at the given index
    public String getColumnName(int column) {
        return columnNames[column];
    }

    // MODIFIES: this
    // EFFECTS: adds an item to items list, and updates the table with this new item
    public void addItem(ItemToBeLogged item) {
        this.itemsList.add(item);
        fireTableDataChanged();
    }
}
