package com.launchcode.tamms2.models;

import com.launchcode.tamms2.dao.TammsDAO;

/**
 * Created by Gaming on 6/1/2015.
 */
public class BuyTransaction {
    public boolean checkUPC(String upcEntryText) {
        return TammsDAO.getInstance().upcInDatabase(upcEntryText);
    }
}
