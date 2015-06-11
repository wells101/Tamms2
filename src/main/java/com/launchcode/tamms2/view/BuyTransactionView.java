package com.launchcode.tamms2.view;

import com.launchcode.tamms2.dao.TammsDAO;
import com.launchcode.tamms2.dataobjects.InventoryItem;
import com.launchcode.tamms2.dataobjects.Invoice;
import com.launchcode.tamms2.models.TransactionTableModel;
import javax.swing.*;
import java.awt.*;


/**
 * Created by Gaming on 6/5/2015.
 */
public class BuyTransactionView extends JFrame {
    public JTable buyTable;
    private TransactionTableModel tableModel;
    private JButton button = new JButton("Press me!");
    private Invoice invoice = new Invoice();
    public JTextField input = new JTextField();
    private JScrollPane tablePane;

    public BuyTransactionView(){
        super("Buy Transaction");
        tableModel = new TransactionTableModel(invoice);
        initView();
    }

    public void display() {
        this.setVisible(true);
    }

    public void invisible(){
        this.setVisible(false);
    }

    public void initView() {
        setSize(300, 300);
        setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buyTable = new JTable(tableModel);
        buyTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        buyTable.setFillsViewportHeight(true);
        tablePane = new JScrollPane(buyTable);
        this.add(tablePane, BorderLayout.CENTER);
        this.add(button, BorderLayout.SOUTH);
        this.add(input, BorderLayout.NORTH);
        button.addActionListener(e -> executeUpdate());
        input.addActionListener(e -> addListItem());
    }

    private void addListItem() {
        String itemNumber = input.getText();
        if(TammsDAO.getInstance().upcInDatabase(itemNumber)){
            invoice.addItem(new InventoryItem(itemNumber));
            input.setText("");
        }
        else{
            if((this.setConfirmationText("UPC " + itemNumber + " not found.  Add Item?"))== JOptionPane.YES_OPTION){

            }
        }
    }


    private void executeUpdate() {
        tableModel.fireTableDataChanged();
    }


    public int setConfirmationText(String confirmationText) {
        return JOptionPane.showConfirmDialog(null, confirmationText);
    }
}
