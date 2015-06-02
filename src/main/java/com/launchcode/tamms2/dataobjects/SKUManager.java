package com.launchcode.tamms2.dataobjects;

import com.launchcode.tamms2.dao.TammsDAO;

import java.util.Random;

/**
 * Created by Gaming on 5/31/2015.
 */
public class SKUManager {

    private static SKUManager manager;
    private Random random = new Random();

    /**
     * Returns the instance of the DAO.
     * @return the instnace of the DAO.
     */
    public static SKUManager getInstance(){
        if(manager == null){
            manager = new SKUManager();
        }
        return manager;
    }

    public int generateSKU(){
        int targetSKU;
        int high = 1000000000;
        int low = 100000000;
        do {
            targetSKU = random.nextInt(high - low) + low;
        }while(TammsDAO.getInstance().itemInDatabase(Integer.toString(targetSKU)));
        return targetSKU;
    }
}
