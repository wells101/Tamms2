package com.launchcode.tamms2.models;

import com.launchcode.tamms2.dao.TammsDAO;
import com.launchcode.tamms2.dataobjects.InventoryItem;

/**
 * Created by Gaming on 6/1/2015.
 */
public class AddItemTransaction {
    public boolean checkUPC(String upcEntryText) {
        return TammsDAO.getInstance().upcInDatabase(upcEntryText);
    }

    public InventoryItem initItem(String upcEntryText) {
        return new InventoryItem(upcEntryText);
    }

    public void addItem(InventoryItem newItem) {
        TammsDAO.getInstance().addItemWithSKU(newItem);
    }
}
