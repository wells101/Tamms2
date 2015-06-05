package com.launchcode.tamms2.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;

/**
 * Created by Gaming on 6/3/2015.
 */
public class PriceFrame extends JPanel {

    private JTextField priceNewField, priceUsedField, costNewField, costUsedField;
    private JLabel blankspace, newHeader, usedHeader, costHeader, priceHeader;
    public PriceFrame(){
        super(new GridLayout(3, 3));
        this.initiatePanel();
    }

    private void initiatePanel() {
        blankspace = new JLabel("");
        newHeader = new JLabel("New");
        usedHeader = new JLabel("Used");
        costHeader = new JLabel("Cost");
        priceHeader = new JLabel("Sale Price");
        priceNewField = new JTextField("0.01", 8);
        priceUsedField = new JTextField("0.01", 8);
        costNewField = new JTextField("0.01", 8);
        costUsedField = new JTextField("0.01", 8);
        this.add(blankspace);
        this.add(priceHeader);
        this.add(costHeader);
        this.add(newHeader);
        this.add(priceNewField);
        this.add(costNewField);
        this.add(usedHeader);
        this.add(priceUsedField);
        this.add(costUsedField);
    }

    public double getNewPrice(){
        return Double.parseDouble(priceNewField.getText());
    }

    public double getUsedPrice(){
        return Double.parseDouble(priceUsedField.getText());
    }

    public double getNewCost(){
        return Double.parseDouble(costNewField.getText());
    }

    public double getUsedCost(){
        return Double.parseDouble(costUsedField.getText());
    }

    public void setNewPrice(String d){
        priceNewField.setText(d);
    }

    public void setUsedPrice(String d){
        priceUsedField.setText(d);
    }

    public void setNewCost(String d){
        costNewField.setText(d);
    }

    public void setUsedCost(String d){
        costUsedField.setText(d);
    }

    public void clearEntries() {
        priceNewField.setText("");
        priceUsedField.setText("");
        costNewField.setText("");
        costUsedField.setText("");
    }
}
