package ui;

import java.io.FileNotFoundException;

//public class that runs the app
public class Main {



    //EFFECTS:calls on SwimAlongApp to kick things off
    public static void main(String[] args) {

        try {
            new SwimAlongApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run Swim Along application: file not found...");
        }

    }
}
