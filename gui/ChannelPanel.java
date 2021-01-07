package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChannelPanel extends JPanel {
    private final String title;

    public String getTitle() { return title; }

    public String getId() { return id; }

    public JPanel getMessagesZone() { return messagesZone; }

    public JPanel getListUser() {return listUser; }

    public HashMap<String, UserPanel> getUsersMap() {return usersMap; }

    private final String id;
    private final JPanel messagesZone;
    private final JPanel listUser;
    private final HashMap<String, UserPanel> usersMap = new HashMap<>();
    private final  JScrollPane scrollMessages;

    public ChannelPanel(String title, String id, String user){
        this.title = title;
        this.id = id;
        this.setLayout(new BorderLayout());

        //Zone menu et titre
        JPanel menu = new JPanel();
        menu.setLayout(new FlowLayout(FlowLayout.LEFT));
        menu.add(MyButton.createBNameChannel(title));
        menu.add(MyButton.createBGo());
        menu.setBackground(MyColor.white());
        menu.setPreferredSize(new Dimension(0,40));
        this.add(menu, BorderLayout.NORTH);

        //zone de liste des utilisateurs
        listUser = new JPanel();
        listUser.setPreferredSize(new Dimension(200,0));
        listUser.setBackground(MyColor.grayGreen());
        listUser.setLayout(new FlowLayout(FlowLayout.CENTER));
        listUser.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2,MyColor.green()));
        this.add(listUser , BorderLayout.WEST );


        //Zone des messages
        JPanel containerMessage = new JPanel();
        containerMessage.setLayout(new BorderLayout());
        messagesZone = new JPanel();
        messagesZone.setLayout(new BoxLayout(messagesZone, BoxLayout.PAGE_AXIS));
        messagesZone.setBackground(MyColor.white());
        messagesZone.add(new MyTextArea("\n\n Welcome to the blind_test : \n\n**************************************\n\n "
                +title+
                " \n\n**************************************\n\n Send news messages !! "+
                "\n\n**************************************\n\n See the other users on the left \n\n"
        ));
        containerMessage.add(messagesZone, BorderLayout.CENTER);
        JPanel smiley = new Smiley(messagesZone,title );
        smiley.setVisible(false);
        containerMessage.add(smiley, BorderLayout.SOUTH);
        scrollMessages = MyScroll.createGray(containerMessage);
        this.add(scrollMessages , BorderLayout.CENTER );

        //Zone de saisie des messages
        JPanel write = new JPanel();
        write.setPreferredSize(new Dimension(0,60));
        write.setLayout(new BorderLayout());
        JTextArea writeScript = new JTextArea();
        writeScript.setLineWrap(true);
        JScrollPane scrollWrite = MyScroll.createBlack(writeScript);
        write.add(scrollWrite, BorderLayout.CENTER);
        writeScript.setBackground(MyColor.white());
        writeScript.setFont(new Font("SansSerif", Font.PLAIN, 15));
        writeScript.setForeground(MyColor.black());
        write.add(MyButton.createBSend(writeScript, messagesZone, title), BorderLayout.EAST );
        write.add(MyButton.createBSeeSmile(smiley,"smileybutton/smile.png"), BorderLayout.WEST );
        this.add(write, BorderLayout.SOUTH );
    }

    public JPanel messagesStructure(String nickname, String avatar){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());


        //utilisateur et avatar
        JPanel userPlace = new JPanel();
        userPlace.setLayout(new BorderLayout());
        userPlace.setBackground(MyColor.gray());

        JTextArea user = MyTextArea.user(nickname+" :");
        user.setForeground(MyColor.green());
        user.setPreferredSize(new Dimension(0,25));
        ImageIcon image = new ImageIcon( MyButton.class.getResource(avatar));
        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        JLabel jlabel = new JLabel(image2);
        userPlace.add(jlabel, BorderLayout.WEST);
        userPlace.add(user, BorderLayout.CENTER);

        //date
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date myDate = new Date();
        JTextArea date = MyTextArea.date(format.format(myDate));

        //zone information
        JPanel north = new JPanel();
        north.setLayout(new GridLayout(0,1));
        north.add(userPlace);
        north.add(date);
        north.setBackground(MyColor.gray());
        north.setPreferredSize(new Dimension(0, 50));
        panel.add(north, BorderLayout.NORTH);

        panel.setBackground(MyColor.gray());
        panel.setBorder(new EmptyBorder(2, 10, 0, 0));

        return panel;
    }

    public void messages(String str, String nickname, String avatar){
        JPanel messageStruct = messagesStructure(nickname, avatar);
        JTextArea message = MyTextArea.message(str);
        message.setForeground(MyColor.black());
        messageStruct.add(message, BorderLayout.CENTER);
        messagesZone.add(messageStruct);
        messagesZone.validate();
        scrollMessages.validate();
        downVerticalScroll();
    }

    public void smiley(String smiley, String nickname, String avatar){
        JPanel messageStruct = messagesStructure(nickname, avatar);
        ImageIcon image = new ImageIcon( MyButton.class.getResource(smiley));
        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JLabel jlabel = new JLabel(image2);
        jlabel.setPreferredSize(new Dimension(30,30));
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(jlabel, BorderLayout.WEST);
        panel.setBackground(MyColor.gray());
        messageStruct.add(panel, BorderLayout.CENTER);
        messagesZone.add(messageStruct);

        JPanel south = new JPanel();
        south.setPreferredSize(new Dimension(0,30));
        south.setBackground(MyColor.gray());
        messageStruct.add(south, BorderLayout.SOUTH);

        messagesZone.validate();
        scrollMessages.validate();
        downVerticalScroll();
    }

    public void updateLisUser(String [] users, String [] scores, String [] avatars){
        for (Map.Entry mapentry : usersMap.entrySet()) {
             listUser.remove((Component) mapentry.getValue());
             System.out.println(mapentry.getKey());
             this.validate();
        }
        usersMap.clear();
        for (int i = 0 ; i<users.length ; i++) {
            UserPanel userPanel = new UserPanel(users[i], scores[i], avatars[i]);
            System.out.println(users[i]+ scores[i]+ avatars[i]);
            usersMap.put(users[i], userPanel);
            listUser.add(userPanel);
            listUser.validate();
        }
        this.listUser.validate();
        this.validate();
    }

    public void downVerticalScroll(){
        scrollMessages.getVerticalScrollBar().setValue(scrollMessages.getVerticalScrollBar().getMaximum());
    }
}
