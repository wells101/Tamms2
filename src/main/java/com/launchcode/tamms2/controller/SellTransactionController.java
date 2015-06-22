package com.launchcode.tamms2.controller;

import com.launchcode.tamms2.dao.TammsDAO;
import com.launchcode.tamms2.dataobjects.InventoryItem;
import com.launchcode.tamms2.dataobjects.Invoice;
import com.launchcode.tamms2.view.TransactionView;

/**
 * Created by Gaming on 6/17/2015.
 */
public class SellTransactionController implements TransactionController {
    TransactionView view;
    Invoice model;
    private int firedTimes = 1;


    public SellTransactionController(TransactionView view, Invoice model){
        this.view = view;
        this.model = model;
        view.inputField.addActionListener(e->addListItem());
        view.checkoutButton.addActionListener(e->executeCheckout());
    }

    public void show() {
        view.initView();
        view.display();
    }

    @Override
    public void executeCheckout() {
            model.updateTotal();
            view.setErrorText("Customer's Total is $" + String.format("%.2f", model.getTotal()));
            TammsDAO.getInstance().processInvoice(model);
    }

    @Override
    public void addListItem() {
        System.out.println(firedTimes);
        String itemNumber = view.inputField.getText();
        if (TammsDAO.getInstance().itemInDatabase(itemNumber.substring(0, itemNumber.length() - 1))) {
            model.addItem(new InventoryItem(itemNumber.substring(0, itemNumber.length() - 1), itemNumber.charAt(itemNumber.length() - 1)));
            System.out.println("Success! I should only print once!");
            view.inputField.setText("");
            view.totalPane.updateTotal(model);
            view.tableModel.fireTableDataChanged();
        } else {
            view.setErrorText("SKU not found");
        }
    }
}
