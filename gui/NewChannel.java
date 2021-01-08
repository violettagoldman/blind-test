package gui;

import javax.swing.*;
import java.awt.*;

public class NewChannel extends MyPanel {
    private JTextField error;
    public NewChannel(CardLayout cardLayout, JPanel cardPanel){
        JTextField title = new MyTextField("Name of new channel");
        this.panel.add(title);
        error = MyTextField.BorderEmpty("This channel is already in game");
        this.panel.add(error);
        error.setVisible(false);
        this.south.setPreferredSize(new Dimension(400,400));

        this.panel.add(MyButton.createBSaveChannel(title));
    }

    public void errorVisible() {
//        for(String channel : channels){
//            if(channel)
//        }
        error.setVisible(true);
    }
}
