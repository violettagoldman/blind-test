package src.gui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class MyScroll extends JScrollPane {
    public MyScroll(Component c){
        super(c);
        this.setBorder(null);
        this.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        this.getVerticalScrollBar().setUnitIncrement(16);
    }

    public static MyScroll createGray(Component c){
        MyScroll scrollGray = new MyScroll(c);
        scrollGray.getVerticalScrollBar().setBackground(MyColor.gray());
        scrollGray.getHorizontalScrollBar().setBackground(MyColor.gray());

        scrollGray.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = new JButton();
                button.setBackground(MyColor.gray());
                button.setBorder(null);
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = new JButton();
                button.setBackground(MyColor.gray());
                button.setBorder(null);
                return button;
            }

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.GRAY;
            }
        });


        scrollGray.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = new JButton();
                button.setBackground(MyColor.gray());
                button.setBorder(null);
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = new JButton();
                button.setBackground(MyColor.gray());
                button.setBorder(null);
                return button;
            }

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.GRAY;
            }
        });


        return scrollGray;
    }

    public static MyScroll createBlack(Component c) {
        MyScroll scrollBlack = new MyScroll(c);
        scrollBlack.getVerticalScrollBar().setBackground(MyColor.white());

        scrollBlack.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = new JButton();
                button.setBackground(MyColor.white());
                button.setBorder(null);
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = new JButton();
                button.setBackground(MyColor.white());
                button.setBorder(null);
                return button;
            }

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.GRAY;
            }
        });
        return scrollBlack;
    }

}
