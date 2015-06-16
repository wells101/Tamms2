package com.launchcode.tamms2.consoletest;

import com.launchcode.tamms2.controller.AddItemController;

import com.launchcode.tamms2.controller.BuyTransactionController;
import com.launchcode.tamms2.models.AddItemTransaction;

import com.launchcode.tamms2.view.AddItemView;
import com.launchcode.tamms2.view.BuyTransactionView;

/**
 * Created by Gaming on 5/25/2015.
 */
public class Tamms2 {

    public static void main(String[] args){

        BuyTransactionView view = new BuyTransactionView();
        BuyTransactionController controller = new BuyTransactionController(view);
        controller.show();
//        AddItemView view = new AddItemView();
//        AddItemTransaction model = new AddItemTransaction();
//        AddItemController controller = new AddItemController(view, model);
//        controller.show();
    }
}
