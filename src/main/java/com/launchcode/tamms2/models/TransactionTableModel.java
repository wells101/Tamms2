package com.launchcode.tamms2.models;

import com.launchcode.tamms2.dataobjects.InventoryItem;
import com.launchcode.tamms2.dataobjects.Invoice;

import javax.swing.table.AbstractTableModel;


/**
 * Created by Gaming on 6/10/2015.
 */
public class TransactionTableModel extends AbstractTableModel{

    private final Invoice invoice;
    private final String[] columns = {"SKU", "Title", "Condition", "Price"};

    public TransactionTableModel(Invoice i){
        this.invoice = i;
    }

    @Override
    public String getColumnName(int col){
        return columns[col].toString();
    }

    @Override
    public int getRowCount() {
        return invoice.getMyItems().size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public boolean isCellEditable(int row, int col){
        if(col < 3){
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InventoryItem item = invoice.getMyItems().get(rowIndex);
        switch(columnIndex){
            case 0: return item.getSKU();
            case 1: return item.getTITLE_1();
            case 2: return item.getCondition();
            case 3:
                if(invoice.IS_BUY()){
                    return Double.toString(item.getUSED_COST());
                }
                else{
                    return Double.toString(item.getPRICE());
                }
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int row, int col){
        String newPrice = (String) value;
        double thePrice = Double.parseDouble(newPrice);
        invoice.getMyItems().get(row).setPrice(thePrice);
        fireTableCellUpdated(row, col);
    }
}
