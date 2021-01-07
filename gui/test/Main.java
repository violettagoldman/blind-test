package gui.test;


    import java.awt.FlowLayout;
// w  w  w .  jav a 2  s  .c o  m
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

    public class Main extends JPanel {

        public Main() {
            super(new FlowLayout(FlowLayout.TRAILING, 10, 3));
            add(new JButton("w w w.j a v a 2 s . c o m"));
            add(new JButton("w w w.j a v a 2 s . com"));
            add(new JButton("w w w.java2s.com"));
            add(new JButton("www.j ava 2 s . c o m"));
        }

        public static void main(String[] args) {
            JFrame frame = new JFrame();
            frame.add(new Main());

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(200, 200);
            frame.setVisible(true);
        }
    }

