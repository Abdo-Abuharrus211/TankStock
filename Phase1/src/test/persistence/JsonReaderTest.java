package persistence;

import model.Fish;
import model.TankStock;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//The methods in this class are derived/inspired by the sample starter, "JsonSerializationDemo", provided
// by the course and can be found here:
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TankStock ts = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTankStock.json");
        try {
            TankStock ts = reader.read();
            assertEquals("Tank#1", ts.getTankName());
            assertEquals(0,ts.currentStockLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTankStock.json");
        try {
            TankStock ts = reader.read();
            assertEquals("Tank#1", ts.getTankName());
            List<Fish> fishList = ts.getTankStock();
            assertEquals(2, fishList.size());
            checkFish("betta",3.200000047683716, "low", "t", fishList.get(0));
            checkFish("ram",5.400000095367432, "low", "t", fishList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testGetCapacity() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTankStock.json");
        try {
            TankStock ts = reader.read();
            assertEquals(0,reader.getCapacity());
            assertEquals("Tank#1", ts.getTankName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}