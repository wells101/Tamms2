package com.launchcode.tamms2.view;

import com.launchcode.tamms2.controller.AddItemController;
import com.launchcode.tamms2.controller.BuyTransactionController;
import com.launchcode.tamms2.controller.SellTransactionController;
import com.launchcode.tamms2.models.AddItemTransaction;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Gaming on 6/10/2015.
 */
public class TammsMain extends JFrame{
    private JButton sellButton = new JButton("Sell");
    private JButton buyButton = new JButton("Buy");
    private JButton addButton = new JButton("Add Items");
    private JPanel navPanel = new JPanel();

    public TammsMain(){
        super("Tamms2 Ver 0.1.0");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(500, 300);
        navPanel.setLayout(new GridLayout(3, 1));
        navPanel.add(buyButton);
        navPanel.add(sellButton);
        navPanel.add(addButton);
        this.add(navPanel, BorderLayout.CENTER);

        addButton.addActionListener(e -> openAddPanel());
        buyButton.addActionListener(e -> openBuyPanel());
        sellButton.addActionListener(e -> openSellPanel());
    }

    private void openSellPanel() {
        SellTransactionView sellView = new SellTransactionView();
        SellTransactionController sellCont = new SellTransactionController(sellView);
        sellCont.show();
    }

    private void openBuyPanel() {
        BuyTransactionView buyView = new BuyTransactionView();
        BuyTransactionController buyCont = new BuyTransactionController(buyView);
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
