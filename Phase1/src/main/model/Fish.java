package model;

import org.json.JSONObject;
import persistence.Writable;

//class for the Fish. Every Fish has a species common name, a size in inches, an aggression level,
//and preferred water temp (cold or Tropic). Implements Writable
public class Fish implements Writable {
    private String name; //Species common name not Scientific
    private double size; // size in inches
    private String aggressionLevel; //aggression levels being: Low,Mid,High
    private String waterTemp;  // water temperature : C or T

    //EFFECTS: Represent a fish that has a name, size, aggression, and waterTemp and if it's been fed
    public Fish(String name, double size, String aggressionLevel, String waterTemp) {
        this.name = name;
        this.size = size;
        this.aggressionLevel = aggressionLevel;
        this.waterTemp = waterTemp;
    }

    //EFFECTS: returns string name of fish
    public String getName() {
        return name;
    }

    //EFFECTS: returns size of fish
    public double getSize() {
        return size;
    }

    //EFFECTS: returns string of aggression level of fish
    public String getAggressionLevel() {
        return aggressionLevel;
    }

    //EFFECTS: returns the water temperature preferred by fish, "C" or "T"
    public String getWaterTemp() {
        return waterTemp;
    }

    @Override
    public JSONObject toJson() {
        JSONObject fishJson = new JSONObject();
        fishJson.put("name", name);
        fishJson.put("size", size);
        fishJson.put("aggression level", aggressionLevel);
        fishJson.put("water temp", waterTemp);
        return fishJson;
    }
}


