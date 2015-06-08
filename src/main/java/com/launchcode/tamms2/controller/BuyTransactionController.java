package com.launchcode.tamms2.controller;

import com.launchcode.tamms2.models.BuyTransaction;
import com.launchcode.tamms2.view.BuyTransactionView;

/**
 * Created by Gaming on 6/5/2015.
 */
public class BuyTransactionController {
    BuyTransaction model;
    BuyTransactionView view;

    public BuyTransactionController(BuyTransaction model, BuyTransactionView view){
        this.model = model;
        this.view = view;
    }

    public void show() {
        view.initView();
        view.display();
    }
}
