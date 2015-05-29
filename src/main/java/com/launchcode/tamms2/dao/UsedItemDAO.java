package com.launchcode.tamms2.dao;

import com.launchcode.tamms2.InventoryStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Gaming on 5/26/2015.
 */
public class UsedItemDAO extends TammsDAO implements InventoryStrategy {

    private static UsedItemDAO dao;

    public static UsedItemDAO getInstance(){
        if(dao == null){
            dao = new UsedItemDAO();
        }
        return dao;
    }

    @Override
    public void adjustQTY(String sku, int adjustedQTY) {
        try(Connection conn = this.getConnection()){
            int currentQTY = 0;
            currentQTY = adjustedQTY + this.getQTY(sku);
            PreparedStatement statement = conn.prepareStatement("UPDATE qty SET qty_used = " + currentQTY + " WHERE sku = '" + sku + "'");
            int d = statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error Occurred in: addToQTYTable", e);
        }
    }

    @Override
    public int getQTY(String sku) {
        try(Connection conn = this.getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT qty_used FROM qty WHERE sku = '" + sku + "'");
            ResultSet results = statement.executeQuery();
            return results.getInt("qty_used");
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error Occurred in: getQTY", e);
        }
    }

    @Override
    public void setPrice(String sku, double price) {
        try(Connection conn = this.getConnection()){
            PreparedStatement statement = conn.prepareStatement("UPDATE price SET price_used = " + price + " WHERE sku = '" + sku + "'");
            int d = statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error Occurred in: addToQTYTable", e);
        }
    }

    @Override
    public double getPrice(String sku) {
        try(Connection conn = this.getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT price_used FROM price WHERE sku = '" + sku + "'");
            ResultSet results = statement.executeQuery();
            return results.getDouble("price_used");
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error Occurred in: addToQTYTable", e);
        }
    }

    @Override
    public void setCost(String sku, double price) {

        try (Connection conn = this.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("UPDATE costs SET cost_used = " + price + " WHERE sku = '" + sku + "'");
            int d = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error Occurred in: setCost", e);
        }
    }

        @Override
        public double getCost(String sku) {
            try(Connection conn = this.getConnection()){
                PreparedStatement statement = conn.prepareStatement("SELECT cost_used FROM costs WHERE sku = '" + sku + "'");
                ResultSet results = statement.executeQuery();
                return results.getDouble("cost_used");
            }catch(SQLException e){
                e.printStackTrace();
                throw new RuntimeException("Error Occurred in: getCost", e);
            }
        }
}
