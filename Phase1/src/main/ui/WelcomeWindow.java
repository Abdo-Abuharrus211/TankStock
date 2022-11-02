package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

import model.TankStock;
import persistence.JsonReader;
import persistence.JsonWriter;

//Window that greets user upon launch and prompts 2 options
public class WelcomeWindow extends JFrame {
    private MainPanel mp;
    JPanel panel1;
    JButton btn1;
    JButton btn2;
    JTextField inputBox;
    JLabel lbl1;
    JLabel lbl2;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public WelcomeWindow() {
        //frame config
        setTitle("Welcome...");
        setSize(450, 350);
        setVisible(true);
        setResizable(false);
        //panel config
        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setBackground(new Color(22, 163, 157));
        panel1.setSize(450, 350);
        panel1.setBorder(new BevelBorder(BevelBorder.RAISED));
        add(panel1);

        JLabel welcome = new JLabel("Welcome,how would you like to start?");
        welcome.setBounds(30, 20, 300, 30);
        welcome.setFont(new Font("Arial", Font.BOLD, 17));

        panel1.add(welcome);

        btn1 = new JButton("Load");
        btn1.setBounds(250, 100, 100, 50);
        btn1.setBackground(new Color(139, 228, 90));
        panel1.add(btn1);
        btn1.addActionListener(new ActionListener() {
            //REFERENCE placement of method derived from TrafficLights starter
            public void actionPerformed(ActionEvent a) {
                mp = new MainPanel();
                mp.loadTheTank();
                dispose();
            }
        });

        btn2 = new JButton("New");
        btn2.setBounds(250, 180, 100, 50);
        btn2.setBackground(new Color(139, 228, 90));
        panel1.add(btn2);
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mp = new MainPanel();
                String userInput = inputBox.getText().toString();
                mp.makeTheTank(userInput);
                dispose();
            }
        });

        lbl1 = new JLabel("To load previous tank:");
        lbl1.setBounds(10, 100, 200, 40);
        lbl1.setFont(new Font("Arial", Font.BOLD, 17));
        panel1.add(lbl1);

        lbl2 = new JLabel("Enter volume to make new tank:");
        lbl2.setBounds(10, 150, 200, 40);
        lbl2.setFont(new Font("Arial", Font.BOLD, 17));
        panel1.add(lbl2);

        inputBox = new JTextField(10);
        inputBox.setBounds(10, 180, 200, 50);
        inputBox.setBackground(new Color(255, 255, 255));
        inputBox.setFont(new Font("Arial", Font.BOLD, 20));
        panel1.add(inputBox);
        repaint();
    }


}


