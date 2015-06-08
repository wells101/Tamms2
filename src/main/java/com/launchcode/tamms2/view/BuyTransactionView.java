package com.launchcode.tamms2.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Gaming on 6/5/2015.
 */
public class BuyTransactionView extends JFrame {
    private JTable buyTable;

    public BuyTransactionView(){
        super("Buy Transaction");

    }

    public void display() {
        this.setVisible(true);
    }

    public void initView() {
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2));
        buyTable = new JTable();

        this.add(buyTable);
    }
}
