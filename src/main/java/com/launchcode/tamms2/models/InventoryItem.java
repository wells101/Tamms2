package com.launchcode.tamms2.models;

/**
 * Implements an Inventory Item in the Tamms2 Database.  Defaults Price to 0.01 and Used (NEW_ITEM = false), as most items
 * will enter the inventory as such.
 */
public class InventoryItem{
    private String SKU; //Primary Key in inventory
    private String UPC;
    private String TITLE_1;
    private String TITLE_2;
    private String FORM_CODE;
    private String GENRE_CODE;
    private Double PRICE = 0.01;
    private boolean NEW_ITEM = false;

    public InventoryItem(String SKU, String TITLE_1, String TITLE_2, String FORM_CODE, String GENRE_CODE) {
        this.SKU = SKU;
        this.TITLE_1 = TITLE_1;
        this.TITLE_2 = TITLE_2;
        this.FORM_CODE = FORM_CODE;
        this.GENRE_CODE = GENRE_CODE;
    }

    public InventoryItem() {
    }

    public InventoryItem(String SKU, String TITLE_1, String FORM_CODE, String GENRE_CODE, Double PRICE, boolean NEW_ITEM) {
        this.SKU = SKU;
        this.TITLE_1 = TITLE_1;
        this.TITLE_2 = null;
        this.FORM_CODE = FORM_CODE;
        this.GENRE_CODE = GENRE_CODE;
        this.PRICE = PRICE;
        this.NEW_ITEM = NEW_ITEM;
    }

    public InventoryItem(String SKU, String TITLE_1, String TITLE_2, String FORM_CODE, String GENRE_CODE, Double PRICE, boolean NEW_ITEM) {
        this.SKU = SKU;
        this.TITLE_1 = TITLE_1;
        this.TITLE_2 = TITLE_2;
        this.FORM_CODE = FORM_CODE;
        this.GENRE_CODE = GENRE_CODE;
        this.PRICE = PRICE;
        this.NEW_ITEM = NEW_ITEM;
    }

    /**
     * Tests to see if an item is ready to add to the database.  Usable in conjunction with the addItemManual() and addItem()
     * methods in TammsConsole class.
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
        return PRICE;
    }

    public void setPRICE(Double PRICE) {
        this.PRICE = PRICE;
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

    @Override
    public String toString(){
        String result = "SKU: " + getSKU() + " Title: " + getTITLE_1() + " Author/System: " + getTITLE_2() + " Format Code: " + getFORM_CODE() + " Genre Code: " + getGENRE_CODE() + " Price: " + getPRICE();
        return result;
    }
}
