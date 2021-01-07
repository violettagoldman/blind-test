package gui.test;

import javax.swing.JFrame;
        import javax.swing.JPanel;
        import javax.swing.JLabel;
        import javax.swing.JScrollPane;
        import java.awt.GridLayout;
        import java.awt.GraphicsEnvironment;
        import java.awt.Font;
        import java.awt.BorderLayout;

class ListeFontes extends JFrame {
    public ListeFontes() {
        GraphicsEnvironment gE = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] liste = gE.getAvailableFontFamilyNames();
        JLabel label;
        JLabel label2;
        JPanel panneau = new JPanel();

        panneau.setLayout(new GridLayout(20, 0, 10, 0));
        for (String nom : liste) {
            label = new JLabel();
            label.setFont(new Font(nom, Font.PLAIN, 18));
            label.setText(nom+"\uD83D\uDE00");
            panneau.add(label);
            label2 = new JLabel();
            label2.setText(nom);
            label2.setFont(new Font("SansSerif", 0, 15));
            panneau.add(label2);
        }
        add(new JScrollPane(panneau), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocation(100, 100);
        setVisible(true);
    }
}

class EssaiListeFontes {
    public static void main(String[] arg) {
        new ListeFontes();
    }
}