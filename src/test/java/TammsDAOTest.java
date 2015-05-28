/**
 * Created by Gaming on 5/22/2015.
 */

import com.launchcode.tamms2.TammsConsole;
import com.launchcode.tamms2.dao.NewItemDAO;
import com.launchcode.tamms2.dao.TammsDAO;
import com.launchcode.tamms2.dao.UsedItemDAO;
import com.launchcode.tamms2.models.InventoryItem;
import org.junit.Test;
import org.junit.Before;

import java.rmi.server.UID;

import static org.junit.Assert.assertEquals;

public class TammsDAOTest {

    TammsDAO dao;
    NewItemDAO NIDAO;
    UsedItemDAO UIDAO;
    TammsConsole app;
    InventoryItem testItem;
    @Before
    public void setUp() throws Exception{
        dao = TammsDAO.getInstance();
        NIDAO = NewItemDAO.getInstance();
        UIDAO = UsedItemDAO.getInstance();
        app = new TammsConsole();
        testItem = new InventoryItem("255000334", "Pokemon:Red Version", "Game Boy", "NGB", "ADV");
        testItem.setPRICE(9.99);
    }

    @Test
    public void testGetSku() throws Exception{
        String result = dao.getTitle_1BySKU("100001");
        assertEquals("Rugby 15", result);
    }

    @Test
    public void testPrintFormats() throws Exception{
        app.printFormats();
    }

    @Test
    public void testPrintGenre() throws Exception{
        app.printGenres();
    }

    @Test
    public void testItemInDatabase() throws Exception{
        InventoryItem myItem = new InventoryItem("100007", "Rugby 15", "Playstation 4", "PS4", "SPT");
        boolean testresult = dao.itemInDatabase(myItem.getSKU());
        assertEquals(false, testresult);
    }

    @Test
    public void testAddItemWithSKU() throws Exception{
        dao.addItemWithSKU(testItem);
        boolean testresult = dao.itemInDatabase(testItem.getSKU());
        assertEquals(true, testresult);
    }

    @Test
    public void testItemInTable() throws Exception{
        assertEquals(false, dao.itemInTable(testItem.getSKU(), "qty"));
    }

    @Test
    public void testAddToQTYTable() throws Exception{
        dao.addToQTYTable(testItem);
        assertEquals(true, dao.itemInTable(testItem.getSKU(), "qty"));
    }

    @Test
    public void testGetNewQTY() throws Exception{
        int result = NIDAO.getQTY(testItem.getSKU());
        assertEquals(1,result);
    }

    @Test
    public void testGetUsedQTY() throws Exception{
        int result = UIDAO.getQTY(testItem.getSKU());
        assertEquals(2, result);
    }

    @Test
    public void testAddQTYNew() throws Exception{
        NIDAO.adjustQTY(testItem.getSKU(), 1);
        int result = NIDAO.getQTY(testItem.getSKU());
        NIDAO.adjustQTY(testItem.getSKU(), -1);
        assertEquals(2, result);
    }

    @Test
    public void testAddQTYUsed(){
        UIDAO.adjustQTY(testItem.getSKU(), 2);
        UIDAO.adjustQTY(testItem.getSKU(), -2);
        int result = UIDAO.getQTY(testItem.getSKU());
        assertEquals(8, result);
    }

    @Test
    public void testAddPriceTable(){
        dao.addToPriceTable(testItem);
        assertEquals(true, dao.itemInTable(testItem.getSKU(), "price"));
    }

    @Test
    public void testSetPriceNew(){
        NIDAO.setPrice(testItem.getSKU(), 19.99);
        double result = NIDAO.getPrice(testItem.getSKU());
        assertEquals(19.99, result, 0);
    }

    @Test
    public void testSetPriceUsed(){
        UIDAO.setPrice(testItem.getSKU(), 19.99);
        double result = UIDAO.getPrice(testItem.getSKU());
        assertEquals(19.99, result, 0);
    }
}