package com.launchcode.tamms2.dataobjects;

import com.launchcode.tamms2.dao.TammsDAO;

/**
 * Implements an Inventory Item in the Tamms2 Database.  Defaults Price to 0.01 and Used (NEW_ITEM = false), as most items
 * will enter the inventory as such.
 */
public class InventoryItem implements Comparable<InventoryItem>{
    private String SKU; //Primary Key in inventory.db
    private String UPC;
    private String TITLE_1;
    private String TITLE_2;
    private String FORM_CODE;
    private String GENRE_CODE;
    private Double USED_PRICE = 0.01;
    private Double NEW_PRICE = 0.01;
    private Double USED_COST = 0.01;
    private Double NEW_COST = 0.01;
    private boolean NEW_ITEM = false;

    public InventoryItem() {
    }

    /**
     * Create a new item by UPC.  Buying an item ALWAYS uses the UPC.
     * This is an external rule enforced by VStock management.
     *
     * Items received into the database as new items (Purchased by the Store from a vendor) use
     * InventoryItem(String UPC, Boolean NEW_ITEM) constructor.
     * @param UPC
     */

    public InventoryItem(String UPC){
        //check to see if UPC is in database first.
        if(TammsDAO.getInstance().upcInDatabase(UPC)){
            //if it is, get the information using the SKU constructors
            this.SKU = TammsDAO.getInstance().getSKUByUPC(UPC);
            this.UPC = UPC;
            this.TITLE_1 = TammsDAO.getInstance().getTitle_1BySKU(this.getSKU());
            this.TITLE_2 = TammsDAO.getInstance().getTitle_2BySKU(this.getSKU());
            this.FORM_CODE = TammsDAO.getInstance().getFORM_CODEBySKU(this.getSKU());
            this.GENRE_CODE = TammsDAO.getInstance().getGENRE_CODEBySKU(this.getSKU());
            this.NEW_PRICE = TammsDAO.getInstance().getNewPrice(this.getSKU());
            this.NEW_COST = TammsDAO.getInstance().getNewCost(this.getSKU());
            this.USED_PRICE = TammsDAO.getInstance().getUsedPrice(this.getSKU());
            this.USED_COST = TammsDAO.getInstance().getUsedCost(this.getSKU());
        }
       else{
            this.SKU = Integer.toString(SKUManager.getInstance().generateSKU());
            this.UPC = UPC;
            //Otherwise, generate a default item with a new SKU.
            //User must be prompted for the remaining information after using this constructor.
            //Suggested to use isReadyToAdd(InventoryItem) method to check.
        }

    }

    public InventoryItem(String SKU, String TITLE_1, String TITLE_2, String FORM_CODE, String GENRE_CODE) {
        this.SKU = SKU;
        this.TITLE_1 = TITLE_1;
        this.TITLE_2 = TITLE_2;
        this.FORM_CODE = FORM_CODE;
        this.GENRE_CODE = GENRE_CODE;
    }


    /**
     * Tests to see if an item is ready to add to the database.
     * @return
     */

    public boolean isReadyToAdd(){
        if(SKU != null && TITLE_1 != null && FORM_CODE != null && GENRE_CODE != null){
            return true;
        }
        else{
            return false;
        }
    }

    public String getTITLE_1() {
        return TITLE_1;
    }

    public void setTITLE_1(String TITLE_1) {
        this.TITLE_1 = TITLE_1;
    }

    public String getTITLE_2() {
        return TITLE_2;
    }

    public void setTITLE_2(String TITLE_2) {
        this.TITLE_2 = TITLE_2;
    }

    public String getFORM_CODE() {
        return FORM_CODE;
    }

    public void setFORM_CODE(String FORM_CODE) {
        this.FORM_CODE = FORM_CODE;
    }

    public String getGENRE_CODE() {
        return GENRE_CODE;
    }

    public void setGENRE_CODE(String GENRE_CODE) {
        this.GENRE_CODE = GENRE_CODE;
    }

    public Double getPRICE() {
        if(NEW_ITEM)
            return NEW_PRICE;
        else
            return USED_PRICE;
    }

    public boolean isNEW_ITEM() {
        return NEW_ITEM;
    }

    public void setNEW_ITEM(boolean NEW_ITEM) {
        this.NEW_ITEM = NEW_ITEM;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getUPC() {
        return UPC;
    }

    public void setUPC(String UPC) {
        this.UPC = UPC;
    }

    public Double getUSED_PRICE() {
        return USED_PRICE;
    }

    public void setUSED_PRICE(Double USED_PRICE) {
        this.USED_PRICE = USED_PRICE;
    }

    public Double getNEW_PRICE() {
        return NEW_PRICE;
    }

    public void setNEW_PRICE(Double NEW_PRICE) {
        this.NEW_PRICE = NEW_PRICE;
    }

    public Double getUSED_COST() {
        return USED_COST;
    }

    public void setUSED_COST(Double USED_COST) {
        this.USED_COST = USED_COST;
    }

    public Double getNEW_COST() {
        return NEW_COST;
    }

    public void setNEW_COST(Double NEW_COST) {
        this.NEW_COST = NEW_COST;
    }

    @Override
    public String toString(){
        String result = "SKU: " + getSKU() + "\n Title: " + getTITLE_1() + "\n Author/System: " + getTITLE_2() + "\n Format Code: " + getFORM_CODE() + "\n Genre Code: " + getGENRE_CODE() + "\n New Price: " + getNEW_PRICE() + "\n Used Price: " + getUSED_PRICE();
        return result;
    }

    public int compareTo(InventoryItem item) {
        if(item.getPRICE() == this.getPRICE()){
            return 0;
        }
        else if(item.getPRICE() > this.getPRICE()){
            return 1;
        }
        else{
            return -1;
        }
    }

    public String getCondition() {
        return this.isNEW_ITEM() ? "New" : "Used";
    }

    public void setPrice(double price) {
        if(this.isNEW_ITEM()){
            this.setNEW_PRICE(price);
        }
        else{
            this.setUSED_PRICE(price);
        }
    }
}
