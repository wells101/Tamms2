package com.launchcode.tamms2;

import com.launchcode.tamms2.controller.AddItemController;

import com.launchcode.tamms2.controller.BuyTransactionController;
import com.launchcode.tamms2.models.AddItemTransaction;
import com.launchcode.tamms2.view.AddItemView;
import com.launchcode.tamms2.view.BuyTransactionView;
import com.launchcode.tamms2.view.TammsMain;

/**
 * Created by Gaming on 5/25/2015.
 */
public class Tamms2 {

    public static void main(String[] args){
//
//        AddItemView view = new AddItemView();
//        AddItemTransaction model = new AddItemTransaction();
//        AddItemController controller = new AddItemController(view, model);
//        controller.show();

        BuyTransactionView buyview = new BuyTransactionView();
        BuyTransactionController controller = new BuyTransactionController(buyview);

        controller.show();

//
//        TammsMain main = new TammsMain();
//
//        main.start();
    }
}
