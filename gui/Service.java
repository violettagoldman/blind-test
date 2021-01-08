package gui;

import java.util.HashMap;


public class Service {
    private static final HashMap<String, ChannelPanel> channelsMap = new HashMap<>();

    public static void addChannel(String title, String user){
        channelsMap.put(title, ChannelsPanel.addChannels(title, user));
    }

    public static void addMessage(String message, String user, String title, String avatar){
        System.out.println(title);
        System.out.println(channelsMap.keySet());
        channelsMap.get(title).messages(message, user, avatar);
        channelsMap.get(title).getMessagesZone().validate();
    }

    public static void addSmiley(String smiley, String user, String title, String avatar){
        channelsMap.get(title).smiley(smiley, user, avatar);
    }

    public static void addImage(String title, String image, String text){
        channelsMap.get(title).image(image, text);
    }

    public static void addMusic(String title, String music, String text){
        channelsMap.get(title).music(music, text);
    }

    public static void updateUsersConnected( String [] users, String [] scores, String [] avatars,  String title){
        channelsMap.get(title).updateLisUser(users, scores, avatars);
    }

    public static void errorNewChannel(){
        GUI.getNewChannel().errorVisible();
    }

    public static void startGame(String title){
        channelsMap.get(title).goNotVisible();
    }

    public static void endGame(String title){
        channelsMap.get(title).quitVisible();
    }

    public static void returnNewChanel(String title){
        GUI.getCardWindow().show(GUI.getWindow(), "new channel");
        GUI.getCardWindow().removeLayoutComponent(channelsMap.get(title));
    }

}
