package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
// Classe principale de l'interface
public class GUI extends JFrame {
    private static JPanel window;
    private static MyPanel login;
    private static NewChannel newChannel;
    private static CardLayout cardWindow;

    public static CardLayout getCardWindow() {
        return cardWindow;
    }

    public static JPanel getWindow() {
        return window;
    }

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
        cardWindow = new CardLayout();
        window.setLayout(cardWindow);

        //nouveau jeu
        newChannel = new NewChannel(cardWindow, window);
        window.add(MyScroll.createBlack(newChannel), "new channel");

        //connection
        login = new Login(cardWindow , window);
        window.add(MyScroll.createBlack(login), "login");

        //page de jeu
        window.add(new ChannelsPanel(), "channels");

        cardWindow.show(window, "login");

    }

    public static NewChannel getNewChannel() {
        return newChannel;
    }
}

//try {
//        URL url = new URL("https://i.scdn.co/image/ab67616d0000b273efc6988972cb04105f002cd4");
//        image = new ImageIcon(url);
//        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(50, 55, Image.SCALE_DEFAULT));
//        JLabel jlabel = new JLabel(image2, JLabel.CENTER);
//        north.add(jlabel, BorderLayout.CENTER);
//        }catch (Exception e){
//        }