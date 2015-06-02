package com.launchcode.tamms2;

import com.launchcode.tamms2.dao.NewItemDAO;
import com.launchcode.tamms2.dao.UsedItemDAO;
import com.launchcode.tamms2.dataobjects.InventoryItem;

/**
 * Created by Gaming on 5/28/2015.
 */
public class InventoryContext {

    private InventoryStrategy myStrategy;

    public void setStrategy(InventoryStrategy strategy){
        this.myStrategy = strategy;
    }

    private void determineStrategy(InventoryItem item){
        if(item.isNEW_ITEM()){
            setStrategy(NewItemDAO.getInstance());
        }
        else{
            setStrategy(UsedItemDAO.getInstance());
        }
    }

    public void addToInventory(InventoryItem item){
        determineStrategy(item);
        //Logic to Add to Inventory
    }

    public double getPrice(InventoryItem item){
        determineStrategy(item);
        return myStrategy.getPrice(item.getSKU());
    }

    public void setPrice(InventoryItem item, double price){
        determineStrategy(item);
        myStrategy.setPrice(item.getSKU(), price);
    }

    public double getCost(InventoryItem item){
        determineStrategy(item);
        return myStrategy.getCost(item.getSKU());
    }

    public void setCost(InventoryItem item, double cost){
        determineStrategy(item);
        myStrategy.setCost(item.getSKU(), cost);
    }

    public int getQTY(InventoryItem item){
        determineStrategy(item);
        return myStrategy.getQTY(item.getSKU());
    }

    public void adjustQTY(InventoryItem item, int adjustedQTY){
        determineStrategy(item);
        myStrategy.adjustQTY(item.getSKU(), adjustedQTY);
    }
}
