package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

import model.Fish;
import model.TankStock;
import persistence.JsonReader;
import persistence.JsonWriter;

//Swim Along application, constructs the console user interface
public class SwimAlongApp {
    private Fish fish1;
    private TankStock tank1;
    private static final String JSON_STORAGE = "./data/tankstock.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private int volumeInput;    //volume in liters, entered by user.
    private Scanner userInput; //field for user's input


    //EFFECTS: Effectively runs the entire app
    public SwimAlongApp() throws FileNotFoundException {
        tank1 = new TankStock("Tank-1",0);
        jsonWriter = new JsonWriter(JSON_STORAGE);
        jsonReader = new JsonReader(JSON_STORAGE);
        runApp();
    }

//Reference/Attribution: This method and some following methods are derived from the TellerApp class in the
    // Teller starter provided in the course. I've derived from it ways to implement the Scanner class and some methods
    // in accordance to the permitted use of in-course code.
    // LINK [https://github.students.cs.ubc.ca/CPSC210/TellerApp]


    //MODIFIES:this
    //EFFECTS: Determines whether app moves forward with user input
    private void runApp() {
        boolean continueEntry = true;
        String command = null;


        initializer();

        while (continueEntry) {
            optionsMenu();
            command = userInput.next();
            command = command.toLowerCase(); //was reluctant to include this line,but found it impractical to not do so.

            if (command.equals("exit")) {
                continueEntry = false;
            } else {
                processUserCommands(command);
            }
        }
        System.out.println("Goodbye and Keep Swimming!");

    }


    //MODIFIES:this
    //EFFECTS: initializes tank1 and scanner
    private void initializer() {
        userInput = new Scanner(System.in);
    }

    //REQUIRES: user input.
    //MODIFIES:this
    //EFFECTS: processes all user inputs (commands)
    private void processUserCommands(String command) {
        if (command.equals("save")) {
            saveTank();
        } else if (command.equals("load")) {
            loadTank();
        } else if (command.equals("tank")) {
            makeTank();
        } else if (command.equals("add")) {
            doAddFish();
        } else if (command.equals("remove")) {
            doRemove();
        } else if (command.equals("capacity")) {
            getTankCapacity();
        } else if (command.equals("view")) {
            viewTank();
        } else if (command.equals("biggest")) {
            getBiggest();
        } else if (command.equals("smallest")) {
            getSmallest();
        } else if (command.equals("feed")) {
            feedFish();
        } else {
            System.out.println("Invalid entry! ");
        }
    }


    // EFFECTS: showcase options available to user
    private void optionsMenu() {
        System.out.println("\nPlease select from:");
        System.out.println("\tSave -> save tank to file");
        System.out.println("\tLoad -> load tank from file");
        System.out.println("\tTank -> To make a tank (start here,because fish need water...)");
        System.out.println("\tAdd -> To add fish to tank");
        System.out.println("\tRemove -> To remove fish from tank. (Don't know fish's index? Type \033[3mView\033[0m)");
        System.out.println("\tCapacity -> To view the length of the tank's stock");
        System.out.println("\tView -> To print list of fish in current tank");
        System.out.println("\tBiggest -> prints biggest fish's length and name");
        System.out.println("\tSmallest -> prints biggest fish's length and name");
        System.out.println("\tFeed -> To feed fish");
        System.out.println("\tExit -> Exit");
    }


    //MODIFIES:this
    //EFFECTS: Adds a new tank and sets its volume, used to determine list size
    private void makeTank() {
        System.out.println("Please enter tank volume in liters...");
        volumePass(volumeInput = userInput.nextInt());

        if (volumeInput >= 10) {
            System.out.println("A tank of " + volumeInput + " liters" + " with a maximum capacity of "
                    + (volumeInput / 10) + " fish has been made... \nNow add some fish");
        } else if (volumeInput < 10) {
            System.out.println("Please select a tank of 10l or more...");
        } else {
            optionsMenu();
        }
    }

    //For the purposes of this program at this stage, we'll assume every fish needs 10L to live healthy.
    //
    //REQUIRES:Tank volume in liters as an integer entered by user's inout
    //EFFECTS: passes the value to maxStock() in TankStock class...
    public int volumePass(int i) {
        return tank1.maxStock(i);

    }

    //MODIFIES:this
    //EFFECTS: adds fish to tank
    private void doAddFish() {
        if (tank1.currentStockLength() == tank1.maxStock(volumeInput) && tank1.currentStockLength() != 0) {
            System.out.println("Can't add, tank is at maximum capacity!");

        } else if (tank1.maxStock(volumeInput) == 0) {
            System.out.println("Make a tank before adding fish...");
        } else {
            System.out.println("please enter fish species's common name or its scientific name...");
            String name = userInput.next();
            System.out.println("Enter fish's size (inches)");
            float size = userInput.nextFloat();
            System.out.println("Enter aggression level(\"Low\" or \"Mid\" or \"High\")");
            String aggressionLevel = userInput.next().toLowerCase();
            System.out.println("Enter preferred water temperature,cold or tropic (\"C\" or \"T\") )");
            String waterTemp = userInput.next().toLowerCase();


            fish1 = new Fish(name, size, aggressionLevel, waterTemp);
            tank1.addFish(fish1);

        }
    }


    //MODIFIES:this
    //EFFECTS: removes the fish at the index entered by user's input
    private void doRemove() {
        if (!tank1.isTankEmpty()) {
            System.out.println("Please enter list index of fish you'd like to remove");
            viewTank();
            int index = userInput.nextInt();
            tank1.removeFish(index);
            //System.out.println("You have these fish left in your tank:");
            viewTank();
        } else if (tank1.isTankEmpty()) {
            System.out.println("Can't remove fish from empty tank. Please add fish...");
        }
    }

    //REQUIRES:non-empty tank
    //EFFECTS: prints out the list of all fish in the current tank
    private void viewTank() {
        if (tank1.maxStock(volumeInput) == 0) {
            System.out.println("Please make a tank first.");
        } else if (!tank1.isTankEmpty()) {
            System.out.println("These are your fish:");
            for (int g = 0; g < tank1.currentStockLength(); g++) {
                System.out.println(tank1.getFish(g).getName());
            }
        } else if (tank1.isTankEmpty()) {
            System.out.println("List is empty,please add fish to your tank");
        }
    } //Reference/Attribution:Please note some of this method was inspired from code written by
    // user:Crazenezz on stackoverflow. link below
    //https://stackoverflow.com/questions/10168066/how-to-print-out-all-the-elements-of-a-list-in-java



    //REQUIRES:non-empty tank list
    //EFFECTS: prints out the length of the biggest fish in tank
    private void getBiggest() {
        if (!tank1.isTankEmpty()) {
            DecimalFormat df = new DecimalFormat("#.##");  //Formatting double to get 2 decimals
            System.out.println("The biggest fish is the " + tank1.biggestFish().getName() + ", it's "
                    + df.format(tank1.biggestFish().getSize()) + "\"" + " long");
        } else {
            System.out.println("Either there's no tank or tank is empty");
        }
    } //Reference/Attribution: Got the DecimalFormat method form from
    // https://stackoverflow.com/questions/12806278/double-decimal-formatting-in-java

    //REQUIRES:non-empty tank list
    //EFFECTS: prints out the length name of the smallest fish in tank
    private void getSmallest() {
        if (!tank1.isTankEmpty()) {
            DecimalFormat df = new DecimalFormat("#.##");
            System.out.println("The smallest fish is the " + tank1.smallestFish().getName() + ", it's "
                    + df.format(tank1.smallestFish().getSize()) + "\"" + " long");
        } else {
            System.out.println("Either there's no tank or tank is empty");
        }
    }

    //REQUIRES: tank volume must not equal < 10.
    //EFFECTS:prints out the maxFish or "capacity" of a tank, i,e the list's user determined length.
    private void getTankCapacity() {
        System.out.println(tank1.maxStock(volumeInput));
    }

    //EFFECTS:Simply "feeds" the fish and prints out a statement
    private void feedFish() {
        if (tank1.isTankEmpty()) {
            System.out.println("Oh No, there's no fish to feed :( \n Try Adding some:)");
        } else if (!tank1.isTankEmpty()) {
            System.out.println("Enter index of fish you'd like to feed or enter (tank's capacity + 1)\n"
                    + " to feed them all. Current capacity: " + tank1.maxStock(volumeInput));
            viewTank();
            System.out.println("-----------------");
            int index = userInput.nextInt();
            if (index == (tank1.maxStock(volumeInput) + 1)) {
                System.out.println("All fish have been fed and are now swimming happily :)");
            } else {
                String fishyName = tank1.getFish(index).getName();
                System.out.println(fishyName + " has been fed and is now full and happy:)");
            }
        } else {
            System.out.println("All fish have been fed and are now swimming happily :)");
        }
    }

    //The 2 following methods are based on similar methods found in the sample starter, "JsonSerializationDemo",
    // provided by the course and can be found here:
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    //EFFECTS: saves tank to file
    private void saveTank() {
        try {
            jsonWriter.open();
            jsonWriter.write(tank1);
            jsonWriter.close();
            System.out.println("saved " + tank1.getTankName() + " to " + JSON_STORAGE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save file " + JSON_STORAGE);
        }
    }

    //MODIFIES: this.
    //EFFECTS: loads tankstock from file
    public void loadTank() {
        try {
            tank1 = jsonReader.read();
            volumeInput = 10 * jsonReader.getCapacity();
            System.out.println("Loaded " + tank1.getTankName() + " from " + JSON_STORAGE);
        } catch (IOException e) {
            System.out.println("Can't load from file " + JSON_STORAGE);
        }
    }


}



