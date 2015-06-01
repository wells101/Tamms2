package com.launchcode.tamms2.ConsoleTest;

import com.launchcode.tamms2.models.SKUManager;

/**
 * Created by Gaming on 5/25/2015.
 */
public class Tamms2 {

    public static void main(String[] args){
        TammsConsole app = new TammsConsole();
//        app.addItemManual();
        SKUManager manager = new SKUManager();
        System.out.println(manager.generateSKU());
    }
}
