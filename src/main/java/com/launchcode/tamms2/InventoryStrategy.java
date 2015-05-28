package com.launchcode.tamms2;

/**
 * Details strategies for different inputs.
 *
 * Items in inventory have a SKU with N, U, or C at the end, differentiating new, used, and coupon items respectively.
 * Logic exists in InventoryContext to determine the strategy to be used, and additionally in TransactionStrategy to
 * manage logic for Buy and Sell transactions.
 */

public interface InventoryStrategy {
    void adjustQTY(String sku, int adjustedQTY);
    int getQTY(String sku);
    void setPrice(String sku, double price);
    double getPrice(String sku);
    void setCost(String sku, double price);
    double getCost(String sku);
}
