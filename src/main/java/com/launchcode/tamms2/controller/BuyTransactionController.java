package com.launchcode.tamms2.controller;

import com.launchcode.tamms2.dao.TammsDAO;
import com.launchcode.tamms2.dataobjects.InventoryItem;
import com.launchcode.tamms2.dataobjects.Invoice;
import com.launchcode.tamms2.models.BuyTransaction;
import com.launchcode.tamms2.view.BuyTransactionView;

/**
 * Created by Gaming on 6/5/2015.
 */
public class BuyTransactionController {
    BuyTransaction model;
    BuyTransactionView view;
    Invoice invoice;


    public BuyTransactionController(BuyTransaction model, BuyTransactionView view){
        this.model = model;
        this.view = view;
        view.input.addActionListener(e->executeAddListItem());
    }

    public void show() {
        view.initView();
        view.display();
    }
    private void executeAddListItem() {
        String itemNumber = view.input.getText();
        if(TammsDAO.getInstance().upcInDatabase(itemNumber)){
            invoice.addItem(new InventoryItem(itemNumber));
        }
        else{
        }
    }
}
