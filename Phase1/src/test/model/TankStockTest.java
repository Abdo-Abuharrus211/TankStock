package model;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//Test class for the TankStock class and its methods
class TankStockTest {

    private final TankStock tankStock = new TankStock("tank 1", 5);
    //calls to TankStock to construct new tankStock list

    private final Fish f1 = new Fish("Ramhorn", 3, "Low", "T");
    //example fish to be used in tests...

    private final int volume = 50;


    @BeforeEach
    //simply tests TankStock constructor
    public void tankStockTest() {
        List<Fish> tank = new LinkedList<>();
        tankStock.maxStock(volume);
    }


    @Test
    public void isCompatibleTest() {

        Fish f2 = new Fish("Angelfish", 3, "Low", "T");
        Fish f3 = new Fish("Koi", 3, "Low", "C");
        Fish f4 = new Fish("Cichlid", 3, "High", "T");
        Fish f5 = new Fish("Oscar", 6, "High", "T");
        tankStock.addFish(f1);

        assertTrue(tankStock.isCompatible(f2));
        assertFalse(tankStock.isCompatible(f3));
        assertFalse(tankStock.isCompatible(f4));
        assertFalse(tankStock.isCompatible(f5));
    }

    @Test
    public void addFishTest() {
        Fish f2 = new Fish("Angelfish", 3, "Low", "T");
        Fish f3 = new Fish("Koi", 3, "Low", "C");
        Fish f4 = new Fish("Cichlid", 3, "High", "T");
        Fish f5 = new Fish("Oscar", 6, "Mid", "T");
        Fish f6 = new Fish("Tetra", 1, "Low", "T");
        Fish f7 = new Fish("Bichir", 15, "Mid", "T");
        Fish f8 = new Fish("Danios", 1, "Low", "T");
        Fish f9 = new Fish("Guppy", 1, "Low", "T");
        Fish f10 = new Fish("Trout", 7, "Low", "C");

        assertTrue(tankStock.addFish(f1));
        assertTrue(tankStock.addFish(f2));
        assertFalse(tankStock.addFish(f3));
        assertFalse(tankStock.addFish(f4));
        assertFalse(tankStock.addFish(f5));
        assertTrue(tankStock.addFish(f6));
        assertFalse(tankStock.addFish(f7));
        assertTrue(tankStock.addFish(f8));
        assertTrue(tankStock.addFish(f9));
        assertFalse(tankStock.addFish(f10));
    }

    @Test
    public void removeFishTest() {
        assertFalse(tankStock.removeFish(0)); //test when list is empty.

        Fish f2 = new Fish("Angelfish", 3, "Low", "T");
        Fish f6 = new Fish("Tetra", 1, "Low", "T");
        Fish f8 = new Fish("Danios", 1, "Low", "T");

        tankStock.addFish(f1);
        tankStock.addFish(f2);
        tankStock.addFish(f6);
        tankStock.addFish(f8);

        assertTrue(tankStock.removeFish(0));
        assertTrue(tankStock.removeFish(2));
        assertEquals(2, tankStock.currentStockLength());
        assertNotEquals(4, tankStock.currentStockLength());
    }

    @Test
    public void currentStockTest() {
        Fish f2 = new Fish("Angelfish", 3, "Low", "T");
        Fish f3 = new Fish("Tetra", 1, "Low", "T");

        tankStock.addFish(f1);
        tankStock.addFish(f2);
        tankStock.addFish(f3);

        assertEquals(3, tankStock.currentStockLength());
    }

    @Test
    public void isTankEmptyTest() {
        tankStock.addFish(f1);

        assertFalse(tankStock.isTankEmpty());
        tankStock.removeFish(0);
        assertTrue(tankStock.isTankEmpty());

    }

    @Test
    public void isTankFullTest() {
        Fish f2 = new Fish("Angelfish", 3, "Low", "T");

        tankStock.addFish(f1);
        tankStock.addFish(f2);
        tankStock.addFish(f1);
        tankStock.addFish(f2);
        tankStock.addFish(f1);
        tankStock.addFish(f2);
        tankStock.addFish(f1);
        tankStock.addFish(f2);
        tankStock.addFish(f1);
        tankStock.addFish(f2);

        assertTrue(tankStock.isTankFull());
    }

    @Test
    public void getFishTest() {
        Fish f2 = new Fish("Angelfish", 3, "Low", "T");
        Fish f3 = new Fish("Tetra", 1, "Low", "T");

        tankStock.addFish(f1);
        tankStock.addFish(f2);

        assertEquals(f1, tankStock.getFish(0));
        assertNotEquals(f3, tankStock.getFish(1));
        assertEquals(f2, tankStock.getFish(1));
    }

    @Test
    public void smallestFishTest() {
        Fish f2 = new Fish("Angelfish", 3, "Low", "T");
        Fish f3 = new Fish("Tetra", 1, "Low", "T");
        Fish f4 = new Fish("Ropefish", 18, "Low", "T");

        tankStock.addFish(f1);
        tankStock.addFish(f2);
        tankStock.addFish(f3);
        tankStock.addFish(f4);

        assertEquals(f3, tankStock.smallestFish());
        assertNotEquals(f4, tankStock.smallestFish());

    }

    @Test
    public void biggestFishTest() {
        Fish f2 = new Fish("Angelfish", 3, "Low", "T");
        Fish f3 = new Fish("Tetra", 2, "Low", "T");
        Fish f4 = new Fish("Ropefish", 18, "Low", "T");

        tankStock.addFish(f1);
        tankStock.addFish(f2);
        tankStock.addFish(f3);
        tankStock.addFish(f4);

        assertEquals(f4, tankStock.biggestFish());
        assertNotEquals(f2, tankStock.biggestFish());
    }

    @Test
    public void getTankNameTest() {
        assertEquals("tank 1", tankStock.getTankName());
    }

    @Test
    public void getTankStockTest() {
        tankStock.addFish(f1);
        assertEquals(1, tankStock.getTankStock().size());
        assertFalse(tankStock.getTankStock().isEmpty());
        assertTrue(tankStock.getTankStock().contains(f1));

    }

    @Test ////This tests toJson that's found in TankStock class
    public void toJsonTSTest(){
        Fish f2 = new Fish("Angelfish", 3, "Low", "T");
        tankStock.addFish(f1);
        tankStock.addFish(f2);

        JSONObject jsonTs = new JSONObject();
        jsonTs = tankStock.toJson();

        assertEquals("tank 1",jsonTs.getString("tank-name"));
        assertEquals(5,jsonTs.getInt("tank-capacity"));
    }

    @Test
    public void tankStockToJsonTest() {
        Fish f2 = new Fish("Angelfish", 3, "Low", "T");
        tankStock.addFish(f1);
        tankStock.addFish(f2);

        JSONArray jsonArray = new JSONArray();
        jsonArray = tankStock.toJson().getJSONArray("tankStock");

        assertFalse(jsonArray.isEmpty());
        assertNotEquals(f2, jsonArray.get(0));
        assertEquals(2, jsonArray.length());


    }

    @Test  //This test toJson that's found in Fish class
    public void toJsonFishTest() {
        Fish f2 = new Fish("Angelfish", 3, "Low", "T");
        tankStock.addFish(f1);
        tankStock.addFish(f2);

        JSONObject jsonFish = new JSONObject();
        jsonFish = f2.toJson();

        assertEquals("Angelfish", jsonFish.getString("name"));
        assertEquals(3, jsonFish.getInt("size"));
        assertEquals("Low", jsonFish.getString("aggression level"));
        assertEquals("T", jsonFish.getString("water temp"));

    }

}
