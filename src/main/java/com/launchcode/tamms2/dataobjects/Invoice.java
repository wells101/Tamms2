package com.launchcode.tamms2.dataobjects;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of items in a Invoice.
 * Created by Gaming on 6/1/2015.
 */
public class Invoice {

    private List<InventoryItem> myItems = new ArrayList<>();
    private double total = 0.00;
    private boolean IS_BUY = false;
    private double taxRate = 0.0725;

    public boolean IS_BUY() {
        return IS_BUY;
    }

    public void setIS_BUY(boolean IS_BUY) {
        this.IS_BUY = IS_BUY;
    }

    public List<InventoryItem> getMyItems() {
        return myItems;
    }

    public void setMyItems(List<InventoryItem> myItems) {
        this.myItems = myItems;
    }

    public void updateTotal(){
        if(IS_BUY) {
            total = 0.00;
            for (int i = 0; i < myItems.size(); i++) {
                total += myItems.get(i).getUSED_COST();
            }
        }
        else{
            total = 0.00;
            for (int i = 0; i < myItems.size(); i++){
                total += myItems.get(i).getPRICE();
            }
            total = total * taxRate;
        }
    }

    public void addItem(InventoryItem item){
        myItems.add(item);
    }

    public double getSubTotal(){
        double subTotal = 0.00;
        if(IS_BUY) {
            for (int i = 0; i < myItems.size(); i++) {
                subTotal += myItems.get(i).getUSED_COST();
            }
        }
        else{
            for (int i = 0; i < myItems.size(); i++){
                subTotal += myItems.get(i).getPRICE();
            }
        }
        return subTotal;
    }
    public double getTotal() {
        this.updateTotal();
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTax() {
        return getTotal() * taxRate;
    }
}
