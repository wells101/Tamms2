package com.launchcode.tamms2;

import com.launchcode.tamms2.dao.NewItemDAO;
import com.launchcode.tamms2.dao.UsedItemDAO;
import com.launchcode.tamms2.models.InventoryItem;

/**
 * Created by Gaming on 5/28/2015.
 */
public class InventoryContext {

    private InventoryStrategy myStrategy;

    public void setStrategy(InventoryStrategy strategy){
        this.myStrategy = strategy;
    }

    public void sellItem(InventoryItem item){
        if(item.isNEW_ITEM()){
            setStrategy(NewItemDAO.getInstance());
        }
        else{
            setStrategy(UsedItemDAO.getInstance());
        }

    }

    public void buyItem(InventoryItem item){
        if(item.isNEW_ITEM()){
            setStrategy(NewItemDAO.getInstance());
        }
        else{
            setStrategy(UsedItemDAO.getInstance());
        }
        //Logic to buy Item
    }

    public void addToInventory(InventoryItem item){
        if(item.isNEW_ITEM()){
            setStrategy(NewItemDAO.getInstance());
        }
        else{
            setStrategy(UsedItemDAO.getInstance());
        }
        //Logic to Add to Inventory
    }

    public double getPrice(InventoryItem item){
        if(item.isNEW_ITEM()){
            setStrategy(NewItemDAO.getInstance());
        }
        else{
            setStrategy(UsedItemDAO.getInstance());
        }

        return myStrategy.getPrice(item.getSKU());
    }

    public void setPrice(InventoryItem item, double price){
        if(item.isNEW_ITEM()){
            setStrategy(NewItemDAO.getInstance());
        }
        else{
            setStrategy(UsedItemDAO.getInstance());
        }
        myStrategy.setPrice(item.getSKU(), price);
    }

    public double getCost(InventoryItem item){
        if(item.isNEW_ITEM()){
            setStrategy(NewItemDAO.getInstance());
        }
        else{
            setStrategy(UsedItemDAO.getInstance());
        }
        return myStrategy.getCost(item.getSKU());
    }

    public void setCost(InventoryItem item, double cost){
        if(item.isNEW_ITEM()){
            setStrategy(NewItemDAO.getInstance());
        }
        else{
            setStrategy(UsedItemDAO.getInstance());
        }
        myStrategy.setCost(item.getSKU(), cost);
    }

    public int getQTY(InventoryItem item){
        if(item.isNEW_ITEM()){
            setStrategy(NewItemDAO.getInstance());
        }
        else{
            setStrategy(UsedItemDAO.getInstance());
        }
        return myStrategy.getQTY(item.getSKU());
    }

    public void adjustQTY(InventoryItem item, int adjustedQTY){
        if(item.isNEW_ITEM()){
            setStrategy(NewItemDAO.getInstance());
        }
        else{
            setStrategy(UsedItemDAO.getInstance());
        }
        myStrategy.adjustQTY(item.getSKU(), adjustedQTY);
    }
}
