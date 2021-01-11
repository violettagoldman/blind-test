package gui;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {
    public UserPanel(String user, String score, String avatar){
        super();
        this.setBackground(MyColor.white());
        this.setPreferredSize(new Dimension(200,40));
        this.setLayout(new BorderLayout());

        //avatar
        ImageIcon image = new ImageIcon( MyButton.class.getResource(avatar));
        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        JLabel jlabel = new JLabel(image2);
        jlabel.setBackground(MyColor.white());
        this.add(jlabel, BorderLayout.WEST);

        JTextArea userAera = MyTextArea.user(user+" : "+score);
        userAera.setForeground(MyColor.green());
        userAera.setPreferredSize(new Dimension(200,25));

        this.add(userAera, BorderLayout.CENTER);

    }
}
