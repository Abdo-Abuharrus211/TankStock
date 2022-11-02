package persistence;

import model.Fish;
import model.TankStock;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

//The methods in this class may be derived/inspired by the sample starter, "JsonSerializationDemo", provided
// by the course and can be found here:
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo


// Represents a reader that reads tankstock from JSON data stored in file
public class JsonReader {
    private int capacity;

    private String source;
    private TankStock tank1;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TankStock read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTankStock(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses tankstock from JSON object and returns it
    private TankStock parseTankStock(JSONObject jsonObject) {
        String name = jsonObject.getString("tank-name");
        capacity = jsonObject.getInt("tank-capacity");
        TankStock ts = new TankStock(name, capacity);
        ts.maxStock(10 * capacity);
        addTankStock(ts, jsonObject);
        return ts;
    }

    // MODIFIES: ts
    // EFFECTS: parses tankstock from JSON object and adds them to tankstock
    private void addTankStock(TankStock ts, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tankStock");
        for (Object json : jsonArray) {
            JSONObject nextFish = (JSONObject) json;
            addFish(ts, nextFish);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses fish from JSON object and adds it to tankstock
    private void addFish(TankStock ts, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double size = jsonObject.getDouble("size");
        String aggressionLevel = jsonObject.getString("aggression level");
        String waterTemp = jsonObject.getString("water temp");
        Fish fish1 = new Fish(name, size, aggressionLevel, waterTemp);
        ts.addFish(fish1);
    }

    public int getCapacity() {
        return capacity;
    }
}