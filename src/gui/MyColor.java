package src.gui;

import java.awt.*;

public class MyColor extends Color {
    public MyColor(int r, int g, int b) {
        super(r, g, b);
    }

    public static MyColor green(){
        MyColor blue = new MyColor(54, 204, 94);
        return blue;
    }

    public static MyColor grayGreen(){
        MyColor grayBlue = new MyColor(174, 255, 195);
        return grayBlue;
    }

    public static MyColor white(){
        MyColor black = new MyColor(230,230,230);
        return black;
    }
    public static MyColor gray(){
        MyColor gray = new MyColor(215,215,215);
        return gray;
    }

    public static MyColor grayWithe(){
        MyColor grayWith = new MyColor(180,180,180);
        return grayWith;
    }

    public static MyColor grayBlack(){
        MyColor grayBlack = new MyColor(30,30,30);
        return grayBlack;
    }

    public static MyColor black(){
        MyColor white = new MyColor(30,30,30);
        return white;
    }

    public static MyColor red(){
        MyColor red = new MyColor(186,0,0);
        return red;
    }


}
