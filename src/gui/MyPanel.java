package gui;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    protected final JPanel panel;
    protected final JPanel south;
    protected final JPanel north;
    protected static String avatarChoose;
    public MyPanel(){
        this.setBackground(MyColor.white());
        this.setLayout(new BorderLayout());
        panel = new JPanel();
        panel.setLayout(new GridLayout(0,1, 60, 60));
        panel.setBackground(MyColor.white());
        this.add(panel, BorderLayout.CENTER);

        JPanel west = new JPanel();
        west.setPreferredSize(new Dimension(100,100));
        this.add(west, BorderLayout.WEST);
        west.setBackground(MyColor.white());

        JPanel east = new JPanel();
        east.setPreferredSize(new Dimension(100,100));
        east.setBackground(MyColor.white());
        this.add(east, BorderLayout.EAST);

        north = new JPanel();
        north.setPreferredSize(new Dimension(100,100));
        north.setBackground(MyColor.white());
        north.setLayout(new BorderLayout());
        ImageIcon image = new ImageIcon( getClass().getResource( "note.png"));
        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(50, 55, Image.SCALE_DEFAULT));
        JLabel jlabel = new JLabel(image2, JLabel.CENTER);
        north.add(jlabel, BorderLayout.CENTER);

        this.add(north, BorderLayout.NORTH);

        south = new JPanel();
        south.setPreferredSize(new Dimension(100,100));
        south.setBackground(MyColor.white());
        this.add(south, BorderLayout.SOUTH);

    }

    public static void setAvatarChoose(String str){
        avatarChoose = str;
    }

    public static MyScroll newChannel(CardLayout cardLayout, JPanel cardPanel){
        MyPanel newChannel = new MyPanel();

        JTextField title = new MyTextField("Name of new channel");
        newChannel.panel.add(title);
        newChannel.south.setPreferredSize(new Dimension(400,400));

        newChannel.panel.add(MyButton.createBSaveChannel(cardLayout,cardPanel, title));

        MyScroll scroll = MyScroll.createBlack(newChannel);
        return scroll;
    }

}
