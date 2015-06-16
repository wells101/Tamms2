package com.launchcode.tamms2.controller;

import com.launchcode.tamms2.dao.TammsDAO;
import com.launchcode.tamms2.dataobjects.InventoryItem;
import com.launchcode.tamms2.dataobjects.Invoice;
import com.launchcode.tamms2.view.BuyTransactionView;

/**
 * Created by Gaming on 6/5/2015.
 */
public class BuyTransactionController {
    BuyTransactionView view;
    Invoice invoice;


    public BuyTransactionController(BuyTransactionView view){
        this.view = view;
//        view.inputField.addActionListener(e->executeAddListItem());
    }

    public void show() {
        view.initView();
        view.display();
    }
}
