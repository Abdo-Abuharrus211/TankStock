package ui;

import model.Fish;
import model.TankStock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddfishWindow extends JFrame {
    private MainPanel mp;
    private TankStock tank;
    private int volume;
    private Fish fish1;
    private String name;
    private Float size;
    private String aggression;
    private String temp;
    JPanel panel1;
    JTextField field1;
    JTextField field2;
    JTextField field3;
    JTextField field4;
    JLabel introLabel;
    JLabel lbl1;
    JLabel lbl2;
    JLabel lbl3;
    JLabel lbl4;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public AddfishWindow(TankStock t, int c, MainPanel m) {
        this.tank = t;
        this.volume = c;
        this.mp = m;
        //
        setTitle("Add fish to your tank");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        //paenl config
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setSize(500, 500);
        panel1.setBackground(new Color(22, 163, 157));
        add(panel1);

        introLabel = labelMaker("Enter fish parameters:", 20, 50, 350, 50);
        introLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel1.add(introLabel);

        lbl1 = labelMaker("Name", 20, 100, 150, 35);
        lbl2 = labelMaker("Size", 20, 150, 150, 35);
        lbl3 = labelMaker("Aggression(\"low\",\"mid\",\"high\")", 20, 200, 250, 35);
        lbl4 = labelMaker("Temp(\"c\" or \"t\")", 20, 250, 150, 35);
        panel1.add(lbl1);
        panel1.add(lbl2);
        panel1.add(lbl3);
        panel1.add(lbl4);
        field1 = fieldMaker(300, 100, 150, 35);
        field2 = fieldMaker(300, 150, 150, 35);
        field3 = fieldMaker(300, 200, 150, 35);
        field4 = fieldMaker(300, 250, 150, 35);
        panel1.add(field1);
        panel1.add(field2);
        panel1.add(field3);
        panel1.add(field4);

        JButton enterButton = new JButton("Enter");
        enterButton.setBounds(300, 300, 100, 35);
        enterButton.setBackground((new Color(139, 228, 90)));
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doAddFish();
            }
        });
        panel1.add(enterButton);
        repaint();

    }

    //REQUIRES: this
    //EFFECTS: adds fish to tank
    public void doAddFish() {
        mp.volumePass(volume);
        name = field1.getText();
        size = Float.valueOf(field2.getText());
        aggression = field3.getText();
        temp = field4.getText();
        fish1 = new Fish(name, size, aggression, temp);
        tank.addFish(fish1);
        mp.updatePrint();
        dispose();
    }

    //EFFECTS: constructs a label with given parameters
    public JLabel labelMaker(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setBounds(x, y, width, height);
        return label;
    }

    //EFFECTS: constructs a JTextField with given parameters
    public JTextField fieldMaker(int x, int y, int width, int height) {
        JTextField field = new JTextField(20);
        field.setFont(new Font("Arial", Font.PLAIN,16));
        field.setBounds(x, y, width, height);
        field.setVisible(true);
        return field;
    }

}
