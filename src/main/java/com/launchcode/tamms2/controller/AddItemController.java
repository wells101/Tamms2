package com.launchcode.tamms2.controller;

import com.launchcode.tamms2.dataobjects.InventoryItem;
import com.launchcode.tamms2.dataobjects.SKUManager;
import com.launchcode.tamms2.models.AddItemTransaction;
import com.launchcode.tamms2.view.AddItemView;

import javax.swing.*;



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
        if (newItem.isReadyToAdd()) {
            int confirmation = view.setConfirmText("Is this correct? \n" + newItem.toString());
            if (confirmation == JOptionPane.YES_OPTION) {
                model.addItem(newItem);
                view.setErrorText("Item Added!");
                view.clearEntries();
            } else {
            }
        }else{
            view.setErrorText("Insufficient Information Provided!");
        }
    }

    public void show(){
        view.setVisible(true);
    }

    private void checkUPCEntry() {
        if(validUPC(view.getUpcEntryText())) {
            if (!model.checkUPC(view.getUpcEntryText())) {
                view.setErrorText("You need to add info to UPC:" + view.getUpcEntryText());
                this.assignSKU();
                view.addItemButton.setEnabled(true);
            } else {
                view.setErrorText("Item Already Exists!");
                //Set the fields in the view to the proper values
                InventoryItem foundItem = model.initItem(view.getUpcEntryText());
                view.updateInformation(foundItem);
            }
        }
        else{
            view.setErrorText("Invalid UPC!");
        }
    }
    public boolean validUPC(String upcEntryText) {
        String testCase = upcEntryText;
        int testTotal = 0;
        while(testCase.length() < 14){
            testCase = "0" + testCase;
        }
        for(int i = 0; i < testCase.length(); i++){
            if(i % 2 == 0){
                testTotal += Integer.parseInt(testCase.substring(i,i+1)) * 3;
            }
            else{
                testTotal += Integer.parseInt(testCase.substring(i,i+1));
            }
        }
        return testTotal % 10 == 0;
    }
    public void assignSKU() {
        view.SKUField.setText(Integer.toString(SKUManager.getInstance().generateSKU()));
    }
}
