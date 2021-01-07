package src.gui;

import javax.swing.*;
import java.awt.*;

public class ToolsBars extends JPanel {
    public ToolsBars(CardLayout cardLayout, JPanel cardPanel, CardLayout cardLayout2, JPanel cardPanel2){
        this.setLayout(new FlowLayout());
        this.setBackground(MyColor.white());
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,MyColor.green()));
        this.add( MyButton.createBChannels(cardLayout, cardPanel) );
        this.add( MyButton.createBNewChannel(cardLayout, cardPanel));
        this.add( MyButton.createBDeconnect(cardLayout2, cardPanel2) );
    }

}
