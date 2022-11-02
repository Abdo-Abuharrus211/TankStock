package ui;

import javax.swing.*;
import java.awt.*;


public class TheViewWindow extends JFrame {

    //Constructs the frame for the window and it's components
    public TheViewWindow() {
        //REFERENCE: the following lines of code are derived from the example in the video from the following
        // https://www.youtube.com/watch?v=FdQX8sUNO-s

        setLayout(new BorderLayout());
        setSize(1000,1000);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
        ImageIcon image1 = new ImageIcon("./data/fishPic2.jpg");
        JLabel lbl1 = new JLabel(image1);
        lbl1.setSize(500,500);
        add(lbl1);
        pack();
    }
}
