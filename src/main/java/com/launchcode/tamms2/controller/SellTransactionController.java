package com.launchcode.tamms2.controller;

import com.launchcode.tamms2.dataobjects.Invoice;
import com.launchcode.tamms2.view.BuyTransactionView;
import com.launchcode.tamms2.view.SellTransactionView;

/**
 * Created by Gaming on 6/17/2015.
 */
public class SellTransactionController {
    SellTransactionView view;
    Invoice invoice;


    public SellTransactionController(SellTransactionView view){
        this.view = view;
//        view.inputField.addActionListener(e->executeAddListItem());
    }

    public void show() {
        view.initView();
        view.display();
    }
}
