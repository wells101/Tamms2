package com.launchcode.tamms2;

import java.util.List;
import java.util.Scanner;

import com.launchcode.tamms2.dao.TammsDAO;
import com.launchcode.tamms2.models.InventoryItem;

/**
 * Created by Gaming on 5/25/2015.
 */
public class TammsConsole {

    private List<String> formats = TammsDAO.getInstance().getFormats();
    private List<String> genres = TammsDAO.getInstance().getGenres();

    public void addItemManual() {
        InventoryItem newItem = new InventoryItem();
        Scanner myScanner = new Scanner(System.in);
        //prompt for SKU
        System.out.println("Enter new SKU for Item. Exclude N/U at end of SKU: ");
        newItem.setSKU(myScanner.nextLine());
        //prompt for Title_1, remind of being Title of product in all cases
        System.out.println("Enter Title of Item: ");
        newItem.setTITLE_1(myScanner.nextLine());
        //prompt for Title_2, remind of being Author, System, Lead Actor.  null for pass.
        System.out.println("Enter Author, System, or Lead Actor of product, or Null to pass: ");
        String title_2 = myScanner.nextLine();
        if(title_2.equals("null")){
            newItem.setTITLE_2(null);
        }
        else{
            newItem.setTITLE_2(title_2);
        }
        //prompt for Format Code, list Format Codes
        String format;
        do {
            System.out.println("Enter Format Code: ");
            printFormats();
            format = myScanner.nextLine();
        }while(formats.contains(format) == false);
        newItem.setFORM_CODE(format);
        //prompt for Genre Code, list Genre Codes
        String genre;
        do {
            System.out.println("Enter Genre Code: ");
            printGenres();
            genre = myScanner.nextLine();
        }while(genres.contains(genre) == false);
        newItem.setGENRE_CODE(genre);
        //Verify all non-nulls are not null.
        if(newItem.isReadyToAdd()){
//            TammsDAO.getInstance().addItemWithSku(newItem);
            System.out.println("Item is Ready to Add! Printing Information: ");
            System.out.println(newItem.toString());
        }
        else{
            System.out.println("Item is missing information, please retry!!");
            this.addItemManual();
        }
        //Connect and Add to database
    }

    public void printFormats() {
        for(int i = 0; i < formats.size(); i = i + 2){
            System.out.println(formats.get(i) + " - " + formats.get(i+1));
        }
        System.out.println();
    }

    public void printGenres() {
        for(int i = 0; i < genres.size(); i = i + 2){
            System.out.println(genres.get(i) + " - " + genres.get(i + 1));
        }
        System.out.println();
    }
}
