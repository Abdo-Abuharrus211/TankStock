package ui;

import model.Fish;
import model.TankStock;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//DISCLAIMER: Big thanks for ALex Lee and his tutorial (from following URL) for introducing the basics of Java Swing
//https://www.youtube.com/watch?v=5o3fMLPY7qY

//Main window for the program's GUI
public class MainPanel extends JFrame {
    private Fish fish1;
    private TankStock tank;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    //
    private static final String JSON_STORAGE = "./data/tankstock.json";
    private String userInput;
    private int volumeInput;    //volume in liters, entered by user.
    private JPanel panel1;
    private JLabel operations;
    private JLabel inputLabel;
    private JLabel coverPic;
    private ImageIcon image1;
    private JTextArea viewArea;
    private JTextField userField;
    private JTextArea resultsText;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public MainPanel() {
        tank = new TankStock("Tank-1", 0);
        jsonWriter = new JsonWriter(JSON_STORAGE);
        jsonReader = new JsonReader(JSON_STORAGE);
        //Frame config
        setTitle("Swim Along?");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        //panel config
        panel1 = new JPanel();
        panel1.setBackground(new Color(22, 163, 157));
        panel1.setBorder(new BevelBorder(BevelBorder.RAISED));
        panel1.setBounds(0, 250, 1000, 750);
        panel1.setLayout(null);
        add(panel1);

        image1 = new ImageIcon("./data/fishPic4.jpg");
        coverPic = new JLabel(image1);
        coverPic.setSize(200, 200);
        coverPic.setBounds(0, 0, 1000, 250);
        add(coverPic);

        operations = new JLabel("Available Operations:");
        operations.setBounds(20, 30, 250, 30);
        operations.setFont(new Font("Arial", Font.BOLD, 20));
        panel1.add(operations);

        btn1 = buttonMaker("Add", 10, 100, 120, 50);
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFish();
            }
        });

        btn2 = buttonMaker("Remove", 150, 100, 120, 50);
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!tank.isTankEmpty()) {
                    resultsText.setText("Please enter list index of fish you'd like to remove");
                } else if (tank.isTankEmpty()) {
                    resultsText.setText("Can't remove fish from empty tank. Please add fish...");
                }
            }
        });

        btn3 = buttonMaker("Capacity", 10, 170, 120, 50);
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(tank.maxStock(volumeInput));
                resultsText.setText("This tank's capacity is " + tank.maxStock(volumeInput) + " fish.");
            }
        });

        btn4 = buttonMaker("View", 150, 170, 120, 50);
        btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TheViewWindow();
            }
        });

        btn5 = buttonMaker("Enter", 230, 530, 80, 35);
        btn5.setFont(new Font("Arial", Font.BOLD, 15));
        btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getInput();
                removeFish();
                userField.setText(null);
            }
        });

        btn6 = buttonMaker("Exit", 230, 570, 80, 50);
        btn6.setBackground(new Color(243, 79, 74));
        btn6.setFont(new Font("Arial", Font.BOLD, 15));
        btn6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ExitPromptWindow(tank);
            }
        });
        panel1.add(btn6).setBackground(new Color(243, 79, 74));

        inputLabel = new JLabel("User input:");
        inputLabel.setBounds(20, 400, 300, 30);
        inputLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel1.add(inputLabel);

        userField = new JTextField(20);
        userField.setBounds(10, 450, 300, 70);
        userField.setFont(new Font("Arial", Font.BOLD, 20));
        panel1.add(userField);

        viewArea = new JTextArea();
        viewArea.setBounds(450, 50, 500, 450);
        viewArea.setBorder((new BevelBorder(BevelBorder.RAISED)));
        viewArea.setFont(new Font("Arial", Font.BOLD, 18));
        viewArea.setEditable(false);
        panel1.add(viewArea);

        resultsText = new JTextArea("");
        resultsText.setBounds(450, 535, 500, 50);
        resultsText.setFont(new Font("Arial", Font.BOLD, 18));
        resultsText.setBorder(new BevelBorder(BevelBorder.RAISED));
        panel1.add(resultsText);
        repaint();
    }

    //REQUIRES:Tank volume in liters as an integer entered by user's inout
    //EFFECTS: passes the value to maxStock() in TankStock class...
    public int volumePass(int i) {
        return tank.maxStock(i);
    }

    //REQUIRES: this
    //EFFECTS: calls the AddFishWindow to enter parameters
    public void addFish() {
        if (tank.currentStockLength() == tank.maxStock(volumeInput) && tank.currentStockLength() != 0) {
            System.out.println("Can't add, tank is at maximum capacity!");
            resultsText.setText("Can't add, tank is at maximum capacity!");
        } else {
            new AddfishWindow(tank, volumeInput, this);
        }
    }

    //MODIFIES:tankStock
    //EFFECTS: removes the fish at the index entered by user's input
    public void removeFish() {
        int index = Integer.parseInt(userInput);
        resultsText.setText(tank.getFish(index).getName() + " has been removed from tank.");
        tank.removeFish(index);
        printTank();
        repaint();
    }

    //MODIFIES:tankStock
    //EFFECTS: loads tank from Json file
    public void loadTheTank() {
        try {
            tank = jsonReader.read();
            volumeInput = 10 * jsonReader.getCapacity();
            System.out.println("Loaded " + tank.getTankName() + " from " + JSON_STORAGE);
            tank.loadTankEntryLogger();
            printTank();
        } catch (IOException e) {
            System.out.println("Can't load from file " + JSON_STORAGE);
        }
    }

    //MODIFIES:tankStock
    //EFFECTS: Adds a new tank and sets its volume, used to determine list size
    public void makeTheTank(String i) {
        volumePass(volumeInput = Integer.parseInt(i));
        if (volumeInput >= 10) {
            System.out.println("A tank of " + volumeInput + " liters" + " with a maximum capacity of "
                    + tank.maxStock(volumeInput) + " fish has been made... \nNow add some fish");
            tank.newTankEntryLogger();
        } else if (volumeInput < 10) {
            System.out.println("Please select a tank of 10l or more...");
        }
    }

    //REFERENCE: Some modifications derived from the examples from the following URL
    //https://stackoverflow.com/questions/29644527/print-list-of-files-in-jframe
    //REQUIRES:non-empty tank
    //EFFECTS: prints out the list of all fish in the current tank
    public void printTank() {
        String name;
        String fishNames = "";
        if (tank.maxStock(volumeInput) == 0) {
            System.out.println("Please make a tank first.");
            viewArea.setText("Please make a tank first.");
        } else if (!tank.isTankEmpty()) {
            System.out.println("These are your fish:");
            for (int g = 0; g < tank.currentStockLength(); g++) {
                name = tank.getFish(g).getName();
                fishNames += "\n" + g + "-" + name;
                System.out.println(tank.getFish(g).getName());
                viewArea.setText(fishNames);
            }
        } else if (tank.isTankEmpty()) {
            System.out.println("List is empty,please add fish to your tank");
            viewArea.setText("List is empty,please add fish to your tank");
        }
    }

    //MODIFIES: userInput
    //EFFECT: gets string from userField and converts to string and sets userInput = userField
    private void getInput() {
        userInput = userField.getText();
    }

    //EFFECTS: updates the viewArea for tank
    public void updatePrint() {
        printTank();
        repaint();
    }

    //EFFECTS: constructs a button with given parameters
    private JButton buttonMaker(String name, int x, int y, int width, int height) {
        JButton butt = new JButton(name);
        butt.setFont(new Font("Arial", Font.BOLD, 20));
        butt.setBackground(new Color(139, 228, 90));
        butt.setBounds(x, y, width, height);
        panel1.add(butt);
        return butt;
    }

}