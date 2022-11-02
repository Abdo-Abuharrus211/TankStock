package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import exception.LogException;
import model.EventLog;
import model.TankStock;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import model.Event;
import model.EventLog;

//Sets up and configures the exit window..
public class ExitPromptWindow extends JFrame implements LogPrinter {

    private TankStock tank;
    private JsonWriter jsonWriter;
    private static final String JSON_STORAGE = "./data/tankstock.json";
    JPanel panel1;
    JButton btn1;
    JButton btn2;
    JLabel lbl1;
    JLabel lbl2;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public ExitPromptWindow(TankStock t) {
        this.tank = t;
        jsonWriter = new JsonWriter(JSON_STORAGE);
        //frame config
        setTitle("Save or discard tank?");
        setSize(450, 350);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //panel config
        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setBackground(new Color(22, 163, 157));
        panel1.setSize(300, 300);
        panel1.setBorder(new BevelBorder(BevelBorder.RAISED));
        add(panel1);

        lbl1 = new JLabel("would you like to save or discard your tank?");
        lbl1.setFont(new Font("Arial", Font.BOLD, 16));
        lbl1.setBounds(50, 50, 350, 50);
        panel1.add(lbl1);

        lbl2 = new JLabel("");
        lbl2.setFont(new Font("Arial", Font.BOLD, 16));
        lbl2.setBounds(50, 200, 350, 50);
        panel1.add(lbl2);

        btn1 = new JButton("Save & Exit");
        btn1.setFont(new Font("Arial", Font.BOLD, 20));
        btn1.setBackground(Color.CYAN);
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(tank);
                    jsonWriter.close();
                    System.out.println("saved " + tank.getTankName() + " to " + JSON_STORAGE);
                    lbl2.setText("saved " + tank.getTankName() + " to " + JSON_STORAGE);
                } catch (FileNotFoundException e) {
                    System.out.println("Unable to save file " + JSON_STORAGE);
                    lbl2.setText("Unable to save file ");
                }
                try {
                    printLog(EventLog.getInstance());
                } catch (LogException e) {
                    System.out.println("Error printing log");
                }
                System.exit(0);
            }
        });
        panel1.add(btn1);

        btn2 = new JButton("Discard");
        btn2.setBackground(Color.CYAN);
        btn2.setFont(new Font("Arial", Font.BOLD, 20));
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                try {
                    printLog(EventLog.getInstance());
                } catch (LogException e) {
                    System.out.println("Error printing log");
                }
                System.exit(0); //terminates entire program
            }
        });
        panel1.add(btn2);
        repaint();
        pack();

    }


    @Override
    public void printLog(EventLog el) throws LogException {
        for (Event next : el) {
            System.out.println(next.toString() +  "\n\n");
        }
    }
}
