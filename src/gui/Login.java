package gui;

import javax.swing.*;
import java.awt.*;

public class Login extends MyPanel{
    private JTextField nickname;
    private String avatar;
    public Login(CardLayout cardLayout, JPanel cardPanel){
        super();
        avatar = "avatar/1.png";
        this.panel.setLayout(new BorderLayout());

        JTextField nickname = new MyTextField("Your nickname");
        JPanel north = new JPanel();
        north.setLayout(new FlowLayout(FlowLayout.CENTER));
        north.setBackground(MyColor.white());
        north.add(nickname);
        north.add(MyButton.createBLogin(cardLayout, cardPanel, nickname, this.avatar), BorderLayout.SOUTH);

        this.panel.add(north, BorderLayout.NORTH);

        this.north.remove(0);
        ImageIcon image = new ImageIcon( this.getClass().getResource(avatar));
        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));

        JLabel jlabel = new JLabel(image2, JLabel.CENTER);
        this.north.add(jlabel, BorderLayout.CENTER);
        JPanel avatarPanel = new JPanel();
        avatarPanel.setLayout(new GridLayout(4,6, 30, 30));

        for(int i = 0; i<20 ; i++){
            avatarPanel.add(MyButton.createBChooseAvatar("avatar/"+i+".png", this.north, this));
            avatarPanel.validate();
        }
        this.panel.add(avatarPanel, BorderLayout.CENTER);
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
