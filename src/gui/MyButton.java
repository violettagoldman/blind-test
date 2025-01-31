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


    public static MyButton createBSend(JTextArea write){
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

    public static MyButton createBSaveChannel(JTextField title){
        MyButton bSaveChannel = new MyButton("Create new channel");
        bSaveChannel.setPreferredSize(new Dimension(100,20));
        bSaveChannel.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                gui.Service.errorNewChannel();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bSaveChannel;
    }


    public static MyButton createBLogin(JTextField nickname, Login login){ // tu m'envoies pas le bon avatar ici
        MyButton bLogin = new MyButton("Connect");
        bLogin.setPreferredSize(new Dimension(100,20));
        bLogin.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
               network.Client.getInstance().sendConnection(nickname.getText(), login.getAvatar());
               GUI.getCardWindow().show(GUI.getWindow(), "new channel");}
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
                network.Client.getInstance().sendGo();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bGo;
    }

    public static MyButton createBQuit(){
        MyButton bGo = new MyButton("Quit");
        bGo.setPreferredSize(new Dimension(200,30));
        bGo.addMouseListener(new java.awt.event.MouseAdapter (){
            public void mouseEntered(java.awt.event.MouseEvent evt) { }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                GUI.getCardWindow().show(GUI.getWindow(), "new channel");
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { }
        });
        return bGo;
    }



}
