package com.launchcode.tamms2.controller;

import com.launchcode.tamms2.dataobjects.InventoryItem;
import com.launchcode.tamms2.models.AddItemTransaction;
import com.launchcode.tamms2.view.AddItemView;


/**
 * This controller manages adding items to the database.
 * It will initialize an item into the Items and UPC table.
 *
 */
public class AddItemController {
    private final AddItemTransaction model;
    private final AddItemView view;

    public AddItemController(AddItemView view, AddItemTransaction model) {
        this.view = view;
        this.model = model;

        view.UPCField.addActionListener(e -> checkUPCEntry());
        view.addItemButton.addActionListener(e -> executeAddItem());
    }

    private void executeAddItem() {
        InventoryItem newItem = view.generateItem();
        view.setErrorText("Is this correct? \n" + newItem.toString());
        }

    public void show(){
        view.setVisible(true);
    }

    private void checkUPCEntry() {
        if(!model.checkUPC(view.getUpcEntryText())){
            view.setErrorText("You need to add info to UPC:" + view.getUpcEntryText());
            view.assignSKU();
            view.addItemButton.setEnabled(true);
        }
        else{
            view.setErrorText("Item Already Exists!");
            //Set the fields in the view to the proper values
            InventoryItem foundItem = model.initItem(view.getUpcEntryText());
            view.updateInformation(foundItem);
        }
    }
}
