package com.launchcode.tamms2.view;

import com.launchcode.tamms2.dataobjects.InventoryItem;
import com.launchcode.tamms2.models.SearchTableModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Gaming on 6/22/2015.
 */
public class SearchView extends JFrame {

    public SearchBarPanel searchPanel;
    public JTable resultsTable;
    public SearchTableModel searchResults;
    private JScrollPane tablePane;


    public SearchView(SearchTableModel model){
        super("Search");
        searchResults = model;
        this.initView();
    }

    private void initView() {
        this.setSize(800, 400);
        this.setLayout(new BorderLayout());
        searchPanel = new SearchBarPanel();
        this.add(searchPanel, BorderLayout.NORTH);
        searchResults = new SearchTableModel();
        resultsTable = new JTable(searchResults);
        tablePane = new JScrollPane(resultsTable);
        this.add(tablePane, BorderLayout.CENTER);
    }
}
