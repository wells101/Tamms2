package com.launchcode.tamms2.dao;

/**
 * Created by Gaming on 5/22/2015.
 */
import com.launchcode.tamms2.dataobjects.InventoryItem;
import com.launchcode.tamms2.dataobjects.Invoice;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TammsDAO {

    public static final String JDBC_SQLITE_INVENTORY_DB = "jdbc:sqlite:inventory.db";
    public static final String ORG_SQLITE_JDBC = "org.sqlite.JDBC";
    private static TammsDAO dao;

    /**
     * Returns the instance of the DAO.
     * @return the instnace of the DAO.
     */
    public static TammsDAO getInstance(){
        if(dao == null){
            dao = new TammsDAO();
        }
        return dao;
    }

    /**
     * Sets up a connection to the database for the DAO.
     * @return Connection to the Inventory.db file.
     * @throws SQLException
     */
    protected Connection getConnection() throws SQLException {
        try {
            Class.forName(ORG_SQLITE_JDBC);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to find class for loading the database", e);
        }
        return DriverManager.getConnection(JDBC_SQLITE_INVENTORY_DB);
    }

    /**
     *
     * @param sku
     * @return Title_1 from the items Table.
     */
    public String getTitle_1BySKU(String sku) {
        try(Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT title_1 FROM items WHERE sku LIKE '" + sku + "'");
            ResultSet myResults = statement.executeQuery();
            return myResults.getString("title_1");
        }catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unable to find Database", e);
        }
    }

    public String getTitle_2BySKU(String sku) {
        try(Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT title_2 FROM items WHERE sku LIKE '" + sku + "'");
            ResultSet myResults = statement.executeQuery();
            return myResults.getString("title_2");
        }catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unable to find Database", e);
        }
    }

    public String getFORM_CODEBySKU(String sku){
        try(Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT form_code FROM items WHERE sku LIKE '" + sku + "'");
            ResultSet myResults = statement.executeQuery();
            return myResults.getString("form_code");
        }catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unable to find Database", e);
        }
    }

    public String getGENRE_CODEBySKU(String sku){
        try(Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT genre_code FROM items WHERE sku LIKE '" + sku + "'");
            ResultSet myResults = statement.executeQuery();
            return myResults.getString("genre_code");
        }catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unable to find Database", e);
        }
    }

    public String getUPCBySKU(String sku){
        try(Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT upc FROM UPC WHERE sku LIKE '" + sku + "'");
            ResultSet myResults = statement.executeQuery();
            return myResults.getString("upc");
        }catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unable to find Database", e);
        }
    }

    public String getSKUByUPC(String UPC) {
        try(Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT sku FROM UPC WHERE upc LIKE '" + UPC + "'");
            ResultSet myResults = statement.executeQuery();
            return myResults.getString("sku");
        }catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unable to find Database", e);
        }
    }

    public void addItemWithSKU(InventoryItem item) {
        try(Connection conn = getConnection()){
            PreparedStatement statement = conn.prepareStatement("INSERT INTO items VALUES ('" + item.getSKU() + "', '" + item.getTITLE_1() + "', '" + item.getTITLE_2() + "', '" + item.getFORM_CODE() +"', '" + item.getGENRE_CODE() + "')");
                int d = statement.executeUpdate();
                addToQTYTable(item);//Logic for updating QTY/Method call
                addToUPCTable(item);//Logic for updating UPC table
                addToPriceTable(item);//Logic for updating Prices table
                addToCostsTable(item);//Logic for updating Costs table
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Unable to Connect, addItemWithSKU()", e);
        }
    }

    private void addToUPCTable(InventoryItem item) {
        try(Connection conn = getConnection()){
            PreparedStatement statement = conn.prepareStatement("INSERT INTO UPC VALUES('" + item.getUPC() + "'," + item.getSKU() + ")");
            int d = statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error Occurred in: addToUPCTable", e);
        }
    }

    public boolean itemInDatabase(String sku){
        try(Connection conn = getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT sku FROM items WHERE sku LIKE '" + sku + "'");
            ResultSet resultSet = statement.executeQuery();
            List<String> results = new ArrayList<>();

            while(resultSet.next()){
                results.add(resultSet.getString("sku"));
            }

            if(results.size() > 0){
                return true;
            }

            else{
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failure to Verify in itemInDatabase(), connection error", e);
        }
    }

    public boolean upcInDatabase(String UPC){
        try(Connection conn = getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT upc FROM UPC WHERE upc LIKE '" + UPC + "'");
            ResultSet resultSet = statement.executeQuery();
            List<String> results = new ArrayList<>();

            while(resultSet.next()){
                results.add(resultSet.getString("upc"));
            }

            if(results.size() > 0){
                return true;
            }

            else{
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failure to Verify in upcInDatabase(), connection error", e);
        }
    }

    public boolean itemInTable(String sku, String tableName){
        try(Connection conn = getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT sku FROM " + tableName + " WHERE sku LIKE '" + sku + "'");
            ResultSet resultSet = statement.executeQuery();
            List<String> results = new ArrayList<>();

            while(resultSet.next()){
                results.add(resultSet.getString("sku"));
            }

            if(results.size() > 0){
                return true;
            }

            else{
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failure to Verify in itemInTable(), connection error", e);
        }
    }

    public List<String> getFormats() {
        try(Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT form_code, form_desc FROM formats");
            ResultSet myResults = statement.executeQuery();
            List<String> results = new ArrayList<>();
            while(myResults.next()){
                results.add(myResults.getString("form_code") + " - " + myResults.getString("form_desc"));
//                results.add(myResults.getString("form_code"));
//                results.add(myResults.getString("form_desc"));
            }
            return results;
        }catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unable to find Database", e);
        }
    }

    public List<String> getGenres() {
        try(Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT genre_code, genre_desc FROM genre");
            ResultSet myResults = statement.executeQuery();
            List<String> results = new ArrayList<>();
            while(myResults.next()){
                results.add(myResults.getString("genre_code") + " - " + myResults.getString("genre_desc"));
//                results.add(myResults.getString("genre_code"));
//                results.add(myResults.getString("genre_desc"));
            }
            return results;
        }catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unable to find Database", e);
        }
    }

    /**
     * Add a new Item to the QTY table, defaults to no items in inventory.
     * Use the appropriate DAO's adjustQTY(String SKU, int QTY) method to add inventory.
     * @param item
     */
    public void addToQTYTable(InventoryItem item){
        PreparedStatement statement;
        try(Connection conn = getConnection()){
            if(item.isNEW_ITEM()){
                statement = conn.prepareStatement("INSERT INTO qty VALUES('" + item.getSKU() + "',0,0)");
            }
            else {
                statement = conn.prepareStatement("INSERT INTO qty VALUES('" + item.getSKU() + "',0,0)");
            }
            int d = statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error Occurred in: addToQTYTable", e);
        }
    }

    public void adjustQTY(InventoryItem item, int qty){
        try(Connection conn = getConnection()){
            PreparedStatement statement;
            int currentQTY = 0;
            if(item.isNEW_ITEM()){
                currentQTY = getNewQTY(item.getSKU());
                int newQTY = currentQTY + qty;
                statement = conn.prepareStatement("UPDATE qty SET qty_new = " + newQTY + " WHERE sku = '" + item.getSKU() + "'");
            }
            else{
                currentQTY = getUsedQTY(item.getSKU());
                int newQTY = currentQTY + qty;
                statement = conn.prepareStatement("UPDATE qty SET qty_used = " + newQTY + " WHERE sku = '" + item.getSKU() + "'");
            }
            int d = statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error Occurred in: addToQTYTable", e);
        }
    }
    /**
     * Add a new Item to the PRICES table, defaults to 0.01 New Price and 0.01 Used Price
     * @param item - an InventoryItem.
     */
    public void addToPriceTable(InventoryItem item){
        try(Connection conn = getConnection()){
            PreparedStatement statement = conn.prepareStatement("INSERT INTO price VALUES('" + item.getSKU() + "'," + item.getNEW_PRICE() + "," + item.getUSED_PRICE() + ")");
            int d = statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error Occurred in: addToPriceTable", e);
        }
    }

    /**
     * Add a new Item to the COST table, defaults to 0.01 New Price and 0.01 Used Price
     * @param item an InventoryItem
     */
    public void addToCostsTable(InventoryItem item) {
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO costs VALUES('" + item.getSKU() + "'," + item.getNEW_COST()+ "," + item.getUSED_COST()+ ")");
            int d = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error Occurred in: addToCostTable", e);
        }
    }

    public List<String> getFormCodes() {
        try(Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT form_code FROM formats");
            ResultSet myResults = statement.executeQuery();
            List<String> results = new ArrayList<>();
            while(myResults.next()){
                results.add(myResults.getString("form_code"));
//                results.add(myResults.getString("genre_code"));
//                results.add(myResults.getString("genre_desc"));
            }
            return results;
        }catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unable to find Database", e);
        }
    }

    public List<String> getGenreCodes() {
        try(Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT genre_code FROM genre");
            ResultSet myResults = statement.executeQuery();
            List<String> results = new ArrayList<>();
            while(myResults.next()){
                results.add(myResults.getString("genre_code"));
            }
            return results;
        }catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unable to find Database", e);
        }
    }

    public int getUsedQTY(String sku) {
        try(Connection conn = this.getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT qty_used FROM qty WHERE sku = '" + sku + "'");
            ResultSet results = statement.executeQuery();
            return results.getInt("qty_used");
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error Occurred in: getQTY", e);
        }
    }

    public void setUsedPrice(String sku, double price) {
        try(Connection conn = this.getConnection()){
            PreparedStatement statement = conn.prepareStatement("UPDATE price SET price_used = " + price + " WHERE sku = '" + sku + "'");
            int d = statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error Occurred in: addToQTYTable", e);
        }
    }

    public double getUsedPrice(String sku) {
        try(Connection conn = this.getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT price_used FROM price WHERE sku = '" + sku + "'");
            ResultSet results = statement.executeQuery();
            return results.getDouble("price_used");
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error Occurred in: addToQTYTable", e);
        }
    }

    public void setUsedCost(String sku, double price) {

        try (Connection conn = this.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("UPDATE costs SET cost_used = " + price + " WHERE sku = '" + sku + "'");
            int d = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error Occurred in: setCost", e);
        }
    }

    public double getUsedCost(String sku) {
        try(Connection conn = this.getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT cost_used FROM costs WHERE sku = '" + sku + "'");
            ResultSet results = statement.executeQuery();
            return results.getDouble("cost_used");
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error Occurred in: getCost", e);
        }
    }

    public int getNewQTY(String sku) {
        try(Connection conn = this.getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT qty_new FROM qty WHERE sku = '" + sku + "'");
            ResultSet results = statement.executeQuery();
            return results.getInt("qty_new");
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error Occurred in: addToQTYTable", e);
        }
    }

    public void setNewPrice(String sku, double price) {
        try(Connection conn = this.getConnection()){
            PreparedStatement statement = conn.prepareStatement("UPDATE price SET price_new = " + price + " WHERE sku = '" + sku + "'");
            int d = statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error Occurred in: setPrice", e);
        }
    }

    public double getNewPrice(String sku) {
        try(Connection conn = this.getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT price_new FROM price WHERE sku = '" + sku + "'");
            ResultSet results = statement.executeQuery();
            return results.getDouble("price_new");
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error Occurred in: getPrice", e);
        }
    }

    public void setNewCost(String sku, double price) {

        try (Connection conn = this.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("UPDATE costs SET cost_new = " + price + " WHERE sku = '" + sku + "'");
            int d = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error Occurred in: setCost", e);
        }
    }

    public double getNewCost(String sku) {
        try(Connection conn = this.getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT cost_new FROM costs WHERE sku = '" + sku + "'");
            ResultSet results = statement.executeQuery();
            return results.getDouble("cost_new");
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error Occurred in: getCost", e);
        }
    }

    public void processInvoice(Invoice invoice) {
        if (invoice.IS_BUY()){
            for(int i = 0; i < invoice.getMyItems().size(); i++) {
                this.adjustQTY(invoice.getMyItems().get(i), 1);
            }
        }
        else{
            for(int i = 0; i < invoice.getMyItems().size(); i++){
                this.adjustQTY(invoice.getMyItems().get(i), -1);
            }
        }
    }

    public String[] getBulkSkus() {
        try(Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT title_1 FROM items WHERE title_1 LIKE '%BULK%'");
            ResultSet myResults = statement.executeQuery();
            List<String> results = new ArrayList<>();
            while(myResults.next()){
                results.add(myResults.getString("title_1"));
            }
            Collections.sort(results);
            String[] returningArray = new String[results.size()];
            for(int i = 0; i < results.size(); i++) {
                returningArray[i] = results.get(i);
            }

            return returningArray;
        }catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unable to find Database", e);
        }
    }

    public String getSKUByTitle_1(String bulkItem) {
        try(Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT sku FROM items WHERE title_1 = '" + bulkItem + "'");
            ResultSet myResults = statement.executeQuery();
            return myResults.getString("sku");
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Unable to find Database", e);
        }

    }

    //Search methods start here
    public List<InventoryItem> searchByTitle_1(String searchTerm){
        try(Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT sku FROM items WHERE title_1 LIKE '%" + searchTerm + "%'");
            ResultSet myResults = statement.executeQuery();
            List<InventoryItem> results = new ArrayList<>();
            while(myResults.next()){
                results.add(new InventoryItem(myResults.getString("sku"), 'u'));
            }
            return results;
        }catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unable to find Database", e);
        }
    }

    public List<InventoryItem> searchByTitle_2(String searchTerm){
        try(Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT sku FROM items WHERE title_2 LIKE '%" + searchTerm + "%'");
            ResultSet myResults = statement.executeQuery();
            List<InventoryItem> results = new ArrayList<>();
            while(myResults.next()){
                results.add(new InventoryItem(myResults.getString("sku"), 'u'));
            }
            return results;
        }catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unable to find Database", e);
        }
    }
}
