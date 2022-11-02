package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;


//DISCLAIMER: this class and some methods may draw inspiration
// the Lab5 starter file. For example,I'll also be using LinkedList to represent tankStock
//LINK [https://github.students.cs.ubc.ca/CPSC210-2021W-T1/lab5_m0a2f]

//Class represents TankStock,a loosely defined term in fish-keeping that refers to a tank's "stock" of fish and
// other creature such as shrimp and snails, an inventory basically. Here TankStock will refer to list length as
//determined by the user's input.
public class TankStock implements Writable {
    private String tankName;
    private  List<Fish> tankStock;

    private int maxFish; //this is the result of dividing the user input (volume) by 10.

    //EFFECTS: constructs an empty list of tankStock (essentially an empty tank)
    public TankStock(String name,int maxFish) {
        this.tankName = name;
        this.maxFish = maxFish;
        tankStock = new LinkedList<>();
    }

    //EFFECTS: getter method for tank name entered by user
    public String getTankName() {
        return tankName;
    }


    //For the purposes of this program at this stage, we'll assume every fish needs 10L to live healthy.
    //REQUIRES:Input from user via scanner
    //EFFECTS: takes tank volume input and calculates maximum amount of fish possible with given tank size,
    // i,e, it sets the length of the list TankStock.
    public int maxStock(int v) {
        return  this.maxFish = v / 10;

    }


    //REQUIRES: non-empty TankStock list
    //EFFECTS: if tank isn't empty, checks aggression levels and water temp if they match then compatible.
    // if tank is empty then automatically true.
    public boolean isCompatible(Fish i) {
        if (!tankStock.isEmpty()) {
            return Objects.equals(i.getAggressionLevel(), tankStock.get(0).getAggressionLevel())
                    && Objects.equals(i.getWaterTemp(), tankStock.get(0).getWaterTemp());
        }
        return true;
    }

    //REQUIRES: Boolean value from isCompatible()
    //MODIFIES: tankStock list by adding to it.
    //EFFECTS:If consumed fish is compatible with
    public boolean addFish(Fish i) {
        if (isTankFull()) {
            System.out.println("Can't add, tank is at maximum capacity!");
        }
        if (isCompatible(i) && (currentStockLength() < maxFish)) { //ALWAYS MUST BE X < Y
            tankStock.add(i);
            System.out.println("\"" + i.getName() + "\"" + " has been added to your tank.");
            EventLog.getInstance().logEvent(new Event(
                    "\"" + i.getName() + "\"" + " has been added to your tank."));
            return true;
        } else if (!isCompatible(i)) {
            System.out.println("This fish is not compatible with the fish already in the tank...");
            EventLog.getInstance().logEvent(new Event(
                    "\"" + i.getName() + "\"" + " is not compatible with the fish already in the tank!"));

        }
        return false;
    }

    //REQUIRES: non-empty tanksStock list
    //MODIFIES: tankStock list
    //EFFECTS: removes a fish from a list by its index in list.
    public boolean removeFish(int f) {
        if (!tankStock.isEmpty()) {
            EventLog.getInstance().logEvent(new Event(tankStock.get(f).getName() + " has been removed"));
            tankStock.remove(f);
            return true;
        } else {
            return false;
        }
    }


    //EFFECTS:returns the size of the list and thus the tank's stock
    public int currentStockLength() {
        return tankStock.size();
    }

    //EFFECTS:returns true if tank is empty and needs restocking
    public boolean isTankEmpty() {
        return tankStock.isEmpty();
    }

    //REQUIRES: Non-empty TankStock list
    //EFFECTS: returns true if the tank has reached its maximum capacity
    public boolean isTankFull() {
        return tankStock.size() == maxFish;
    }

    //REQUIRES: Non-empty TankStock list
    //EFFECTS: retrieves fish at indicated index
    public Fish getFish(int i) {
        return tankStock.get(i);
    }


    // EFFECTS: returns an unmodifiable list of fish in saved tank
    public List<Fish> getTankStock() {
        return Collections.unmodifiableList(tankStock);

    }


    //EFFECTS:Returns the length of the smallest fish
    public Fish smallestFish() {
        Fish smallFish = getFish(0);
        int indexOfShort = 0;
        for (int b = 1; b < tankStock.size(); b++) {
            if (tankStock.get(b).getSize() < tankStock.get(b - 1).getSize()) {
                smallFish = getFish(b);
                indexOfShort = b;
            }
        }
        return smallFish;
    }
    //Reference/Attribution: had an issue with bugs, so I referenced this post on Stackoverflow
    //https://stackoverflow.com/questions/22822204/java-lang-indexoutofboundsexception-index-3-size-3


    //revised previous method to check for biggest instead of the smallest
    //EFFECTS:Returns the biggest fish
    public Fish biggestFish() {
        Fish bigFish = getFish(0);
        int indexOfShort = 0;
        for (int b = 1; b < tankStock.size(); b++) {
            if (tankStock.get(b).getSize() > tankStock.get(b - 1).getSize()) {
                indexOfShort = b;
                bigFish = getFish(b);
            }
        }
        return bigFish;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("tank-name", tankName);
        json.put("tank-capacity", maxFish);
        json.put("tankStock", tankStockToJson());
        return json;
    }

    //EFFECTS: returns Fish in tank as a JSON array
    private JSONArray tankStockToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Fish f : tankStock) {
            jsonArray.put(f.toJson());
        }
        EventLog.getInstance().logEvent(new Event("Tank " + tankName + " has been saved"));
        return jsonArray;
    }

    public void loadTankEntryLogger() {
        EventLog.getInstance().logEvent(new Event("A " + (maxFish * 10) + "L tank has been retrieved."));
    }

    public void newTankEntryLogger() {
        EventLog.getInstance().logEvent(new Event("A new " + (maxFish * 10) + "L tank has been made."));
    }
}
