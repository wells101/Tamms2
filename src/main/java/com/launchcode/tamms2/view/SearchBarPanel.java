package com.launchcode.tamms2.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Gaming on 6/22/2015.
 */
public class SearchBarPanel extends JPanel {
    public JButton searchButton;
    private JTextField searchField;
    private JComboBox<String> searchTypeComboBox;
    private List<String> searchTerms = this.prepareList();

    private List<String> prepareList() {
        List<String> searchTerms = new ArrayList<>();
        searchTerms.add("Title");
        searchTerms.add("Artist/Author/System");
        return searchTerms;
    }

    public SearchBarPanel(){
        searchField = new JTextField(25);
        searchTypeComboBox = new JComboBox<>(new Vector(searchTerms));
        searchButton = new JButton("Search");
        this.setLayout(new GridLayout(2, 2));
        this.add(searchField);
        this.add(searchTypeComboBox);
        this.add(new JPanel());
        this.add(searchButton);
    }

    public int getSearchType(){
        return searchTypeComboBox.getSelectedIndex();
    }

    public String getSearchTerm(){
        return searchField.getText();
    }
}
