package persistence;

import model.Fish;

import static org.junit.jupiter.api.Assertions.assertEquals;

//The methods in this class are derived/inspired by the sample starter, "JsonSerializationDemo", provided
// by the course and can be found here:
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonTest {
    protected void checkFish(String name, double size, String aggressionLevel, String waterTemp, Fish fishelle) {
        assertEquals(name, fishelle.getName());
        assertEquals(size, fishelle.getSize());
        assertEquals(aggressionLevel, fishelle.getAggressionLevel());
        assertEquals(waterTemp, fishelle.getWaterTemp());
    }
}