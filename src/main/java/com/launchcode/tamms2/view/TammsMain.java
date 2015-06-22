package com.launchcode.tamms2.view;

import com.launchcode.tamms2.controller.AddItemController;
import com.launchcode.tamms2.controller.BuyTransactionController;
import com.launchcode.tamms2.controller.SearchController;
import com.launchcode.tamms2.controller.SellTransactionController;
import com.launchcode.tamms2.dataobjects.Invoice;
import com.launchcode.tamms2.models.AddItemTransaction;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Gaming on 6/10/2015.
 */
public class TammsMain extends JFrame{
    private JButton sellButton = new JButton("Sell");
    private JButton buyButton = new JButton("Buy");
    private JButton addButton = new JButton("Add Items");
    private JButton searchButton = new JButton("Search Items");
    private JPanel navPanel = new JPanel();

    public TammsMain(){
        super("Tamms2 Ver 0.1.0");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(500, 300);
        navPanel.setLayout(new GridLayout(4, 1));
        navPanel.add(buyButton);
        navPanel.add(sellButton);
        navPanel.add(searchButton);
        navPanel.add(addButton);
        this.add(navPanel, BorderLayout.CENTER);


        buyButton.addActionListener(e -> openBuyPanel());
        sellButton.addActionListener(e -> openSellPanel());
        searchButton.addActionListener(e -> openSearchPanel());
        addButton.addActionListener(e -> openAddPanel());
    }

    private void openSearchPanel() {
        SearchController search = new SearchController();
        search.show();
    }

    private void openSellPanel() {
        Invoice invoice = new Invoice();
        invoice.setIS_BUY(false);
        TransactionView sellView = new TransactionView(invoice);
        SellTransactionController sellController = new SellTransactionController(sellView, invoice);
        sellController.show();
    }

    private void openBuyPanel() {
        Invoice invoice = new Invoice();
        invoice.setIS_BUY(true);
        TransactionView buyView = new TransactionView(invoice);
        BuyTransactionController buyCont = new BuyTransactionController(buyView, invoice);
        buyCont.show();
    }

    private void openAddPanel() {
        AddItemView addView = new AddItemView();
        AddItemTransaction addTrans = new AddItemTransaction();
        AddItemController addCont = new AddItemController(addView, addTrans);
        addCont.show();
    }

    public void start(){
        this.setVisible(true);
    }



}
