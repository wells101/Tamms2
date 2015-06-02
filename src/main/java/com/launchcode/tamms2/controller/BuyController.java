package com.launchcode.tamms2.controller;

import com.launchcode.tamms2.models.BuyTransaction;
import com.launchcode.tamms2.view.BuyView;

/**
 * Created by Gaming on 6/1/2015.
 */
public class BuyController {
    private final BuyTransaction model;
    private final BuyView view;

    public BuyController(BuyView view, BuyTransaction model) {
        this.view = view;
        this.model = model;

        view.UPCField.addActionListener(e -> handleUPCEntry());
    }

    public void show(){
        view.setVisible(true);
    }

    private void handleUPCEntry() {
        if(!model.checkUPC(view.getUpcEntryText())){
            view.setErrorText("You need to add info to " + view.getUpcEntryText());
        }
        else{
            view.setErrorText("Item Found!");
        }
    }
}
