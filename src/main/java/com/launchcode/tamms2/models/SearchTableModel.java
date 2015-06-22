package com.launchcode.tamms2.models;

import com.launchcode.tamms2.dao.TammsDAO;
import com.launchcode.tamms2.dataobjects.InventoryItem;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gaming on 6/22/2015.
 */
public class SearchTableModel extends AbstractTableModel {

    private List<InventoryItem> searchResults;
    private final String[] columns = {"SKU", "Title", "Author/System/Artist", "New QTY", "New Price", "New Cost", "Used QTY", "Used Price", "Used Cost"};

    public SearchTableModel(){
        searchResults = new ArrayList<>();
    }
    public SearchTableModel(List<InventoryItem> searchResults){
        this.searchResults = searchResults;
    }

    @Override
    public int getRowCount() {
        return searchResults.size();
    }

    @Override
    public String getColumnName(int col){
        return columns[col].toString();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InventoryItem item = searchResults.get(rowIndex);
        switch(columnIndex){
            case 0: return item.getSKU();
            case 1: return item.getTITLE_1();
            case 2: return item.getTITLE_2();
            case 3: return TammsDAO.getInstance().getNewQTY(item.getSKU());
            case 4: return item.getNEW_PRICE();
            case 5: return item.getNEW_COST();
            case 6: return TammsDAO.getInstance().getUsedQTY(item.getSKU());
            case 7: return item.getUSED_PRICE();
            case 8: return item.getUSED_COST();
        }
        return null;
    }

    public void setSearchResults(List<InventoryItem> newResults){
        searchResults = newResults;
        fireTableDataChanged();
    }
}
