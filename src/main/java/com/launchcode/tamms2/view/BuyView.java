package com.launchcode.tamms2.view;

import com.launchcode.tamms2.dao.TammsDAO;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Gaming on 6/1/2015.
 */
public class BuyView extends JFrame{

    public JTextField UPCField = new JTextField(20);
    private JLabel label = new JLabel("Enter UPC:");
    private JTextField SKUField = new JTextField(20);
    private JTextField titleField = new JTextField(20);
    private JTextField artistField = new JTextField(20);
    private JComboBox<String> genreBox;
    private JComboBox<String> formatBox;


    public BuyView(){
        super("Buy View");
        initView();
    }

    private void initView(){
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2));
        this.add(label);
        this.add(UPCField);
        JLabel skuLabel = new JLabel("SKU: ");
        JLabel titleLabel = new JLabel("Title: ");
        JLabel artistLabel = new JLabel("Artist/Author/System: ");
        JLabel formatLabel = new JLabel("Format: ");
        JLabel genreLabel = new JLabel("Genre: ");
        this.add(skuLabel);
        this.add(SKUField);
        this.add(titleLabel);
        this.add(titleField);
        this.add(artistLabel);
        this.add(artistField);
        List<String> formats = TammsDAO.getInstance().getFormats();
        java.util.Collections.sort(formats);
        formatBox = new JComboBox<>(new Vector(formats));
        this.add(formatLabel);
        this.add(formatBox);
        List<String> genres = TammsDAO.getInstance().getGenres();
        java.util.Collections.sort(genres);
        genreBox = new JComboBox<>(new Vector(genres));
        this.add(genreLabel);
        this.add(genreBox);
        this.pack();
    }

    public String getUpcEntryText() {
        return UPCField.getText();
    }

    public void setErrorText(String errorText) {
        JOptionPane.showMessageDialog(this, errorText);
    }
}
