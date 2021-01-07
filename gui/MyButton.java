package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MyButton extends JButton {
    public MyButton(String str){
        super(str);
        this.setBackground(MyColor.grayWithe());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setBorder(null);
        this.setPreferredSize(new Dimension(60,0));
        this.setFont(new Font("Nirmala UI Semilight", 0, 15));

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(MyColor.green());
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                setBackground(MyColor.grayWithe());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(MyColor.grayWithe());
            }
        });
    }

    public MyButton(Icon i){
        super(i);
        this.setBackground(MyColor.grayWithe());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setBorder(null);
        this.setPreferredSize(new Dimension(60,0));
        this.setFont(new Font("Nirmala UI Semilight", 0, 15));

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(MyColor.green());
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                setBackground(MyColor.grayWithe());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(MyColor.grayWithe());
            }
        });
    }


    public static MyButton createBSend(JTextArea write, JPanel messagesZone, String title){
        MyButton send = new MyButton("Send");
        send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (!write.getText().equals("")) {
                    String str = write.getText();
                   network.Client.getInstance().sendMessage(str, false);
                    write.setText("");
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return send;
    }

    public static MyButton createBSmile( String title, String smiley){
        ImageIcon image = new ImageIcon( MyButton.class.getResource(smiley));
        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        MyButton send = new MyButton(image2);
        send.setPreferredSize(new Dimension(40,40));
        send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
               network.Client.getInstance().sendMessage(smiley, true);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return send;
    }

    public static MyButton createBSeeSmile(JPanel smileyPanel, String smiley){
        ImageIcon image = new ImageIcon( MyButton.class.getResource(smiley));
        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        MyButton send = new MyButton(image2);
        send.setPreferredSize(new Dimension(40,40));
        send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if(smileyPanel.isVisible()){
                    smileyPanel.setVisible(false);
                }else {
                    smileyPanel.setVisible(true);
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return send;
    }

    public static MyButton createBNewChannel(CardLayout cardLayout, JPanel cardPanel){
        MyButton bNewChannel = new MyButton("New channel");
        bNewChannel.setPreferredSize(new Dimension(100,20));
        bNewChannel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cardLayout.show(cardPanel, "new channel");
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bNewChannel;
    }


    public static MyButton createBChannels(CardLayout cardLayout, JPanel cardPanel){
        MyButton bProfile = new MyButton("Channels");
        bProfile.setPreferredSize(new Dimension(100,20));
        bProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cardLayout.show(cardPanel, "channels");
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bProfile;
    }

    public static MyButton createBDeconnect(CardLayout cardLayout, JPanel cardPanel){
        MyButton bSignUp = new MyButton("Deconnect");
        bSignUp.setPreferredSize(new Dimension(100,20));
        bSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) { cardLayout.show(cardPanel, "login"); }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bSignUp;
    }

    public static MyButton createBSaveChannel(CardLayout cardLayout, JPanel cardPanel, JTextField title){
        MyButton bSaveChannel = new MyButton("Create new channel");
        bSaveChannel.setPreferredSize(new Dimension(100,20));
        bSaveChannel.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Service.addChannel(title.getText(), "user");
                network.Client.getInstance().sendChannel(title.getText());
                title.setText("Name of new channel");
                cardLayout.show(cardPanel, "channels");
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bSaveChannel;
    }


    public static MyButton createBLogin(CardLayout cardLayout, JPanel cardPanel, JTextField nickname, Login login){ // tu m'envoies pas le bon avatar ici
        MyButton bLogin = new MyButton("Connect");
        bLogin.setPreferredSize(new Dimension(100,20));
        bLogin.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
               network.Client.getInstance().sendConnection(nickname.getText(), login.getAvatar());
                cardLayout.show(cardPanel, "new channel");}
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bLogin;
    }


    public static MyButton createBNameChannel( String title){
        MyButton bNameChannel = new MyButton(title);
        bNameChannel.setPreferredSize(new Dimension(200,30));
        bNameChannel.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                //network.Client.getInstance().sendChannel(title);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bNameChannel;
    }

    public static MyButton createBChooseAvatar(String avatar, JPanel north, Login login){
        ImageIcon image = new ImageIcon( MyButton.class.getResource(avatar));
        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        MyButton chooseAvatar = new MyButton(image2);
        chooseAvatar.setPreferredSize(new Dimension(80,80));
        chooseAvatar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                MyPanel.setAvatarChoose(avatar);
                north.removeAll();
                north.validate();
                ImageIcon image = new ImageIcon( chooseAvatar.getClass().getResource(avatar));
                ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                JLabel jlabel = new JLabel(image2, JLabel.CENTER);
                north.add(jlabel, BorderLayout.CENTER);
                login.setAvatar(avatar);
                north.validate();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return chooseAvatar;
    }

    public static MyButton createBGo(){
        MyButton bGo = new MyButton("GO");
        bGo.setPreferredSize(new Dimension(200,30));
        bGo.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bGo.setVisible(false);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bGo;
    }



}
