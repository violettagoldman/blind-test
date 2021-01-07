package src.gui;

import javax.swing.*;
import java.awt.*;

public class ChannelsPanel extends JPanel{
    private static JPanel listChannels;
    private static JPanel channels;
    private static CardLayout cardChannels;

    public ChannelsPanel(){
        this.setLayout( new BorderLayout() );

        channels = new JPanel();
        cardChannels = new CardLayout();
        channels.setLayout(cardChannels);
        this.add( channels, BorderLayout.CENTER );
    }

    public static ChannelPanel addChannels(String title, String user){
        ChannelPanel channel = new ChannelPanel(title, "id", user);
        channels.add(channel,title);
        cardChannels.show(channels, title);
        return channel;
    }

}
