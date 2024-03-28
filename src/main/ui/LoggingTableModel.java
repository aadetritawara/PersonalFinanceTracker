package ui;

import model.ItemToBeLogged;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class LoggingTableModel extends AbstractTableModel {

    private ArrayList<ItemToBeLogged> itemsList;
    private final String[] columnNames = {"Name", "Note", "Date", "Amount"};

    public LoggingTableModel(ArrayList<ItemToBeLogged> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public int getRowCount() {
        return itemsList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
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
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void addItem(ItemToBeLogged item) {
        this.itemsList.add(item);
        fireTableDataChanged();
    }
}
