package com.launchcode.tamms2.view;

import com.launchcode.tamms2.controller.AddItemController;
import com.launchcode.tamms2.dao.TammsDAO;
import com.launchcode.tamms2.dataobjects.InventoryItem;
import com.launchcode.tamms2.dataobjects.Invoice;
import com.launchcode.tamms2.models.AddItemTransaction;
import com.launchcode.tamms2.models.BuyTransactionTableModel;
import javax.swing.*;
import java.awt.*;


/**
 * Created by Gaming on 6/5/2015.
 */
public class BuyTransactionView extends JFrame implements TransactionView {
    public JTable buyTable;
    private BuyTransactionTableModel tableModel;
    private JButton checkoutButton = new JButton("Checkout");
    private Invoice invoice = new Invoice();
    public JTextField inputField = new JTextField();
    private JScrollPane tablePane;
    int firedTimes = 1;

    public BuyTransactionView(){
        super("Buy Transaction");
        tableModel = new BuyTransactionTableModel(invoice);
        initView();
        this.invoice.setIS_BUY(true);
    }

    public void display() {
        this.setVisible(true);
    }

    public void invisible(){
        this.setVisible(false);
    }

    public void initView() {
        setSize(500, 300);
        setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buyTable = new JTable(tableModel);
        buyTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        buyTable.setFillsViewportHeight(true);
        tablePane = new JScrollPane(buyTable);
        this.add(tablePane, BorderLayout.CENTER);
 //       this.add(button, BorderLayout.SOUTH);
        this.add(inputField, BorderLayout.NORTH);
        this.add(checkoutButton, BorderLayout.SOUTH);
        checkoutButton.addActionListener(e -> executeCheckout());
        inputField.addActionListener(e -> addListItem());
    }

    private void executeCheckout() {
        firedTimes++;
        if(firedTimes % 2 == 0) {
            invoice.updateTotal();
            this.setErrorText("Customer's Total is $" + String.format("%.2f", invoice.getTotal()));
            TammsDAO.getInstance().processInvoice(invoice);
        }
    }

    private void setErrorText(String s) {
        JOptionPane.showMessageDialog(null, s);
    }

    public void addListItem() {
        System.out.println(firedTimes);
        firedTimes++;
        if(firedTimes%2 == 0) {
            String itemNumber = inputField.getText();
            if (TammsDAO.getInstance().upcInDatabase(itemNumber)) {
                invoice.addItem(new InventoryItem(itemNumber));
                System.out.println("Success! I should only print once!");
                inputField.setText("");
                tableModel.fireTableDataChanged();
            } else {
                if (this.setConfirmationText("UPC " + itemNumber + " not found.  Add Item?") == JOptionPane.YES_OPTION) {
                    AddItemView addView = new AddItemView();
                    AddItemTransaction addTrans = new AddItemTransaction();
                    AddItemController addCont = new AddItemController(addView, addTrans);//Call up AddItemView to add item.
                    addCont.show();
                    addView.UPCField.setText(inputField.getText());
                    addCont.setInTransaction(true);
                }
                else{
//                    Logic for selecting a bulk SKU to add.
//                     Cancel does nothing. Use
//                     JOptionPane.showInputDialog(frame, "Select Bulk SKU: ", "Bulk Option", JOptionPane.QUESTION_MESSAGE, null, bulkSkus, bulkSKus[0]);
                }

            }
        }
    }

    public int setConfirmationText(String confirmationText) {
        return JOptionPane.showConfirmDialog(null, confirmationText);
    }
}
