package com.launchcode.tamms2.view;

import com.launchcode.tamms2.dataobjects.Invoice;
import com.launchcode.tamms2.models.TransactionTableModel;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Gaming on 6/5/2015.
 */
public class TransactionView extends JFrame {
    public JTable viewTable;
    public TransactionTableModel tableModel;
    public JButton checkoutButton = new JButton("Checkout");
    public JTextField inputField = new JTextField();
    private JScrollPane tablePane;
    public TotalPane totalPane = new TotalPane();
    private Invoice invoice;

    public TransactionView(Invoice i){
        super(determineHeader(i));
        invoice = i;
        tableModel = new TransactionTableModel(i);
    }

    private static String determineHeader(Invoice i) {
        if(i.IS_BUY()){
            return "Buy Transaction";
        }
        else{
            return "Sell Transaction";
        }
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
        viewTable = new JTable(tableModel);
        viewTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        viewTable.setFillsViewportHeight(true);
        tablePane = new JScrollPane(viewTable);
        this.add(tablePane, BorderLayout.CENTER);
        this.add(inputField, BorderLayout.NORTH);
        JPanel southPanel = new JPanel(new GridLayout(1, 2));
        southPanel.add(totalPane);
        southPanel.add(checkoutButton);
        totalPane.updateTotal(invoice);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    public void setErrorText(String s) {
        JOptionPane.showMessageDialog(null, s);
    }

    public int setConfirmationText(String confirmationText) {
        return JOptionPane.showConfirmDialog(null, confirmationText);
    }
}
