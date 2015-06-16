package com.launchcode.tamms2.view;

/**
 * Created by Gaming on 6/15/2015.
 */
public interface TransactionView {
    void display();
    void initView();
    void executeCheckout();
    void addListItem();
    int setConfirmationText(String confirmationText);
}
