package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
// Classe principale de l'interface
public class GUI extends JFrame {
    private static JPanel window;
    private static MyPanel profile;
    private static MyPanel signUp;
    private static MyPanel login;


    public GUI() {
        super( "Gorythmic" );

        //layout selon system
        if(System.getProperty("os.name").equals("Windows 10")){
            try {
                UIManager.setLookAndFeel( "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
                SwingUtilities.updateComponentTreeUI( this);
            } catch( Exception exception ) {
                exception.printStackTrace();
            }
        }else {
            try {
                UIManager.setLookAndFeel( "javax.swing.plaf.nimbus.NimbusLookAndFeel" );
                SwingUtilities.updateComponentTreeUI( this);
            } catch( Exception exception ) {
                exception.printStackTrace();
            }
        }

        //Paramètre de la fenêtre
        this.setUndecorated(false);
        this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        this.setSize( 500, 500 );
        this.setLocationRelativeTo( null );
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("note.png")));

        //fermeture du programme à la fermeture de la fenetre
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e ){
                System.exit(1);
            };
        });

        //Ajout des éléments à la fenetre
        window = (JPanel) this.getContentPane();
        final CardLayout cardWindow = new CardLayout();
        window.setLayout(cardWindow);

        //nouveau jeu
        window.add(MyPanel.newChannel(cardWindow, window), "new channel");

        //connection
        login = new Login(cardWindow , window);
        window.add(MyScroll.createBlack(login), "login");

        //page de jeu
        window.add(new ChannelsPanel(), "channels");

        cardWindow.show(window, "login");

    }

    public static MyPanel getProfile() {
        return profile;
    }
}
