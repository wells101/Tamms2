package com.launchcode.tamms2.view;

import com.launchcode.tamms2.dao.TammsDAO;
import com.launchcode.tamms2.dataobjects.InventoryItem;
import com.launchcode.tamms2.dataobjects.Invoice;
import com.launchcode.tamms2.models.SellTransactionTableModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Gaming on 6/17/2015.
 */
public class SellTransactionView extends JFrame {
    public JTable sellTable;
    private SellTransactionTableModel tableModel;
    private JButton checkoutButton = new JButton("Checkout");
    private Invoice invoice = new Invoice();
    public JTextField inputField = new JTextField();
    private JScrollPane tablePane;
    int firedTimes = 1;

    public SellTransactionView(){
        super("Sell Transaction");
        tableModel = new SellTransactionTableModel(invoice);
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
        sellTable = new JTable(tableModel);
        sellTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        sellTable.setFillsViewportHeight(true);
        tablePane = new JScrollPane(sellTable);
        this.add(tablePane, BorderLayout.CENTER);
        //       this.add(button, BorderLayout.SOUTH);
        this.add(inputField, BorderLayout.NORTH);
        this.add(checkoutButton, BorderLayout.SOUTH);
        checkoutButton.addActionListener(e -> executeCheckout());
        inputField.addActionListener(e -> addListItem());
    }

    public void executeCheckout() {
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
            if (TammsDAO.getInstance().itemInDatabase(itemNumber.substring(0, itemNumber.length() - 1))) {
                invoice.addItem(new InventoryItem(itemNumber.substring(0, itemNumber.length() - 1), itemNumber.charAt(itemNumber.length() - 1)));
                System.out.println("Success! I should only print once!");
                inputField.setText("");
                tableModel.fireTableDataChanged();
            } else {
                this.setErrorText("SKU not found");
            }
        }
    }

    public int setConfirmationText(String confirmationText) {
        return JOptionPane.showConfirmDialog(null, confirmationText);
    }
}
