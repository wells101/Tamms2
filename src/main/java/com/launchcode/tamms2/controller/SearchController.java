package com.launchcode.tamms2.controller;

import com.launchcode.tamms2.dao.TammsDAO;
import com.launchcode.tamms2.models.SearchTableModel;
import com.launchcode.tamms2.view.SearchView;

import javax.swing.*;

/**
 * Created by Gaming on 6/22/2015.
 */
public class SearchController{
    private SearchView view;
    private SearchTableModel model = new SearchTableModel();

    public SearchController(){
        view = new SearchView(model);
        view.searchPanel.searchButton.addActionListener(e -> executeSearch());
    }

    private void executeSearch() {
        switch(view.searchPanel.getSearchType()){
            case 0:
                executeTitleSearch();
                break;
            case 1:
                executeAuthorSearch();
                break;
        }
    }

    private void executeTitleSearch() {
        view.searchResults.setSearchResults(TammsDAO.getInstance().searchByTitle_1(view.searchPanel.getSearchTerm()));
    }

    private void executeAuthorSearch() {
        view.searchResults.setSearchResults(TammsDAO.getInstance().searchByTitle_2(view.searchPanel.getSearchTerm()));
    }

    public void show(){
        view.setVisible(true);
    }

}
