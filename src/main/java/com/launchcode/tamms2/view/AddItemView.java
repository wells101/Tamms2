package com.launchcode.tamms2.view;

import com.launchcode.tamms2.dao.TammsDAO;
import com.launchcode.tamms2.dataobjects.InventoryItem;
import com.launchcode.tamms2.dataobjects.SKUManager;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Gaming on 6/1/2015.
 */
public class AddItemView extends JFrame{

    public JTextField UPCField = new JTextField(20);
    private JLabel label = new JLabel("Enter UPC:");
    private JTextField SKUField = new JTextField(20);
    private JTextField titleField = new JTextField(20);
    private JTextField artistField = new JTextField(20);
    private JComboBox<String> genreBox;
    private JComboBox<String> formatBox;
    public JButton addItemButton = new JButton("Add Item");
    private List<String> formats = TammsDAO.getInstance().getFormats();
    private List<String> genres = TammsDAO.getInstance().getGenres();
    private List<String> formCodes = TammsDAO.getInstance().getFormCodes();
    private List<String> genreCodes = TammsDAO.getInstance().getGenreCodes();
    private PriceFrame pFrame = new PriceFrame();

    public AddItemView(){
        super("AddItem");
        setUpLists();
        initView();
    }

    private void setUpLists() {
        java.util.Collections.sort(formats);
        java.util.Collections.sort(genres);
        java.util.Collections.sort(formCodes);
        java.util.Collections.sort(genreCodes);
    }

    private void initView(){
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2));
        this.add(label);
        this.add(UPCField);
        JLabel skuLabel = new JLabel("SKU: ");
        JLabel titleLabel = new JLabel("Title: ");
        JLabel artistLabel = new JLabel("Artist/Author/System: ");
        JLabel formatLabel = new JLabel("Format: ");
        JLabel genreLabel = new JLabel("Genre: ");

        this.add(skuLabel);
        SKUField.setEditable(false);
        this.add(SKUField);
        this.add(titleLabel);
        this.add(titleField);
        this.add(artistLabel);
        this.add(artistField);
        formatBox = new JComboBox<>(new Vector(formats));
        this.add(formatLabel);
        this.add(formatBox);
        genreBox = new JComboBox<>(new Vector(genres));
        this.add(genreLabel);
        this.add(genreBox);
        this.add(pFrame);
        addItemButton.setEnabled(false);
        this.add(addItemButton);
        this.pack();
    }

    public String getUpcEntryText() {
        return UPCField.getText();
    }

    public void setErrorText(String errorText) {
        JOptionPane.showMessageDialog(this, errorText);
    }

    public void updateInformation(InventoryItem foundItem) {
        SKUField.setText(foundItem.getSKU());
        titleField.setText(foundItem.getTITLE_1());
        artistField.setText(foundItem.getTITLE_2());
        formatBox.setSelectedIndex(this.lookUpFormCode(foundItem.getFORM_CODE()));
        genreBox.setSelectedIndex(this.lookUpGenreCode(foundItem.getGENRE_CODE()));
        pFrame.setNewCost(String.format("%.2f", foundItem.getNEW_COST()));
        pFrame.setUsedCost(String.format("%.2f", foundItem.getUSED_COST()));
        pFrame.setNewPrice(String.format("%.2f", foundItem.getNEW_PRICE()));
        pFrame.setUsedPrice(String.format("%.2f", foundItem.getUSED_PRICE()));
    }

    private int lookUpGenreCode(String genre_code) {
        List<String> genreCodes = TammsDAO.getInstance().getGenreCodes();
        Collections.sort(genreCodes);
        return Collections.binarySearch(genreCodes, genre_code);
    }

    private String lookUpGenreCode(int i){
        return genreCodes.get(i);
    }

    private int lookUpFormCode(String form_code) {
        return Collections.binarySearch(formCodes, form_code);
    }

    private String lookUpFormCode(int i){
        return formCodes.get(i);
    }

    public InventoryItem generateItem() {
        InventoryItem item = new InventoryItem();
        item.setTITLE_1(titleField.getText());
        item.setTITLE_2(artistField.getText());
        item.setSKU(SKUField.getText());
        item.setFORM_CODE(lookUpFormCode(formatBox.getSelectedIndex()));
        item.setGENRE_CODE(lookUpGenreCode(genreBox.getSelectedIndex()));
        item.setUSED_PRICE(pFrame.getUsedPrice());
        item.setNEW_PRICE(pFrame.getNewPrice());
        item.setUSED_COST(pFrame.getUsedCost());
        item.setNEW_PRICE(pFrame.getNewPrice());
        return item;
    }

    public void assignSKU() {
        SKUField.setText(Integer.toString(SKUManager.getInstance().generateSKU()));
    }
}
