package com.launchcode.tamms2.view;

import com.launchcode.tamms2.dataobjects.Invoice;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Gaming on 6/21/2015.
 */
public class TotalPane extends JPanel {
    public JTextField subtotalField = new JTextField();
    public JTextField taxField = new JTextField();
    public JTextField totalField = new JTextField();
    private JLabel subLabel = new JLabel("Subtotal :");
    private JLabel taxLabel = new JLabel("Tax :");
    private JLabel grandLabel = new JLabel("Total :");
    private NumberFormat nf = NumberFormat.getInstance();

    public TotalPane(){
        this.setLayout(new GridLayout(3, 2));
        this.add(subLabel);
        this.add(subtotalField);
        subtotalField.setEditable(false);
        this.add(taxLabel);
        this.add(taxField);
        taxField.setEditable(false);
        this.add(grandLabel);
        this.add(totalField);
        totalField.setEditable(false);
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        nf.setGroupingUsed(false);
    }

    public void setSubtotal(Invoice i){
        subtotalField.setText(nf.format(i.getSubTotal()));
    }

    public void setTax(Invoice i){
        if(i.IS_BUY()){
            taxField.setText("0.00");
        }
        else {
            taxField.setText(nf.format(i.getTax()));
        }
    }

    public void updateTotal(Invoice i){
        setSubtotal(i);
        setTax(i);
        totalField.setText(nf.format(i.getTotal()));
        i.setTotal(i.getTotal());
    }
}
