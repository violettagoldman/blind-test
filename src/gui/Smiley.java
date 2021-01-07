package src.gui;

import javax.swing.*;
import java.awt.*;

public class Smiley extends JPanel {
    public Smiley(JPanel messageZone, String title){
        this.setBackground(MyColor.gray());
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(0,50));

        for(int i = 0; i<12 ; i++){
            this.add(MyButton.createBSmile(title, "smileybutton/smiley"+i+".png"));
            this.validate();
        }

    }
}
