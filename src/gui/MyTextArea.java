package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MyTextArea extends JTextArea {
    public MyTextArea(String str){
        super(str);
        this.setEditable(false);
        this.setBackground(MyColor.gray());
        this.setBorder(new EmptyBorder(2, 2, 2, 2));
        this.setLineWrap(true);
        this.setFont(new Font("SansSerif", Font.PLAIN, 15));
        this.setForeground(MyColor.black());
    }

    public static MyTextArea user(String message){
        MyTextArea user = new MyTextArea(message);
        user.setFont(new Font("SansSerif", Font.BOLD, 17));
        user.setBorder(new EmptyBorder(2, 2, 0, 2));
        return user;
    }

    public static MyTextArea date(String date){
        MyTextArea user = new MyTextArea(date);
        user.setFont(new Font("SansSerif", Font.PLAIN, 12));
        user.setBorder(new EmptyBorder(0, 35, 0, 2));
        user.setForeground(MyColor.grayWithe());
        return user;
    }

    public static MyTextArea message(String date){
        MyTextArea user = new MyTextArea(date);
        user.setBorder(new EmptyBorder(0, 2, 40, 2));
        return user;
    }
}
