package com.launchcode.tamms2.controller;

import com.launchcode.tamms2.dao.TammsDAO;
import com.launchcode.tamms2.dataobjects.InventoryItem;
import com.launchcode.tamms2.dataobjects.Invoice;
import com.launchcode.tamms2.models.AddItemTransaction;
import com.launchcode.tamms2.view.AddItemView;
import com.launchcode.tamms2.view.TransactionView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Gaming on 6/5/2015.
 */
public class BuyTransactionController implements TransactionController{
    TransactionView view;
    Invoice model;
    String[] bulkSkus = TammsDAO.getInstance().getBulkSkus();

    public BuyTransactionController(TransactionView view, Invoice model){
        this.view = view;
        this.model = model;
        view.checkoutButton.addActionListener(e->executeCheckout());
        view.inputField.addActionListener(e->addListItem());
    }

    public void show() {
        view.initView();
        view.display();
    }

    @Override
    public void executeCheckout() {
            model.updateTotal();
            if(view.setConfirmationText("Customer's Total is $" + String.format("%.2f", model.getTotal()) + "\n Is this correct?") == JOptionPane.YES_OPTION){
                int result = view.setConfirmationText("Store Credit Value: $" + String.format("%.2f", model.getTotal() * 1.5) +
                        "\n Cash Value: $" + model.getTotal() + "\n Does customer accept Store Credit offer?");
                if(result == JOptionPane.YES_OPTION){
                    view.setErrorText("Issue CTC Card.");
                    TammsDAO.getInstance().processInvoice(model);
                    view.setVisible(false);
                }
                else if(result == JOptionPane.NO_OPTION){
                    view.setErrorText("Give cash to Customer.");
                    TammsDAO.getInstance().processInvoice(model);
                    view.setVisible(false);
                }
                else{
                    //Do nothing.
                }
            }

    }

    @Override
    public void addListItem() {
            String itemNumber = view.inputField.getText();
        if (TammsDAO.getInstance().upcInDatabase(itemNumber)) {
            model.addItem(new InventoryItem(itemNumber));
            System.out.println("Success! I should only print once!");
            view.inputField.setText("");
            view.tableModel.fireTableDataChanged();
        }
        else if(TammsDAO.getInstance().itemInDatabase(itemNumber)){
            model.addItem(new InventoryItem(TammsDAO.getInstance().getUPCBySKU(itemNumber)));
            view.inputField.setText("");
            view.tableModel.fireTableDataChanged();
        }
        else {
            if (view.setConfirmationText("UPC " + itemNumber + " not found.  Add Item?") == JOptionPane.YES_OPTION) {
                startAddItem();
            }
            else if(view.setConfirmationText("Use a bulk SKU?") == JOptionPane.YES_OPTION){
                startAddBulkItem();
            }

        }
        view.totalPane.updateTotal(model);
    }

    private void startAddBulkItem() {
        String bulkItem = (String) JOptionPane.showInputDialog(view, "Select Bulk SKU: ", "Bulk Option", JOptionPane.QUESTION_MESSAGE, null, bulkSkus, bulkSkus[0]);
        model.addItem(new InventoryItem(TammsDAO.getInstance().getSKUByTitle_1(bulkItem), 'u'));
        view.tableModel.fireTableDataChanged();
    }

    private void startAddItem() {
        AddItemView addView = new AddItemView();
        AddItemTransaction addTrans = new AddItemTransaction();
        AddItemController addCont = new AddItemController(addView, addTrans);//Call up AddItemView to add item.
        addCont.show();
        addView.UPCField.setText(view.inputField.getText());
        addCont.setInTransaction(true);
    }
}
