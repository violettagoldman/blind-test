package gui;

import javax.swing.*;
import java.awt.*;

public class NewChannel extends MyPanel {
    private JTextField error;
    private JTextField title;
    public NewChannel(CardLayout cardLayout, JPanel cardPanel){
        title = new MyTextField("Name of new channel");
        this.panel.add(title);
        error = MyTextField.BorderEmpty("This channel is already in game");
        this.panel.add(error);
        error.setVisible(false);
        this.south.setPreferredSize(new Dimension(400,400));

        this.panel.add(MyButton.createBSaveChannel(title));
    }

    public void errorVisible(String [] channels) {
        for(String channel : channels){
            if(channel.equals(title.getText())){
                error.setVisible(true);
                return;
            }
        }
        network.Client.getInstance().sendChannel(title.getText());
        Service.addChannel(title.getText(), "user");
        title.setText("Name of new channel");
        GUI.getCardWindow().show(GUI.getWindow(), "channels");
    }
}
