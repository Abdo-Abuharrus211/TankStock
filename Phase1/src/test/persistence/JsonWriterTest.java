package persistence;

import model.Fish;
import model.TankStock;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //The methods in this class are derived/inspired by the sample starter, "JsonSerializationDemo", provided
    // by the course and can be found here:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    @Test
    void testWriterInvalidFile() {
        try {
            TankStock ts = new TankStock("Laser Dolphins69", 0);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            TankStock ts = new TankStock("Tank#1", 0);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTankStock.json");
            writer.open();
            writer.write(ts);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTankStock.json");
            ts = reader.read();
            assertEquals("Tank#1", ts.getTankName());
            assertEquals(0, ts.currentStockLength());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            TankStock ts = new TankStock("Tank#1", 2);
            ts.addFish(new Fish("betta", 3.2, "low", "t"));
            ts.addFish(new Fish("ram", 5.4, "low", "t"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTankStock.json");
            writer.open();
            writer.write(ts);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTankStock.json");
            ts = reader.read();
            assertEquals("Tank#1", ts.getTankName());
            List<Fish> fishList = ts.getTankStock();
            assertEquals(2, fishList.size());
            checkFish("betta",3.2, "low", "t", fishList.get(0));
            checkFish("ram",5.4, "low", "t", fishList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}