package gui;

import java.util.HashMap;


public class Service {
    private static final HashMap<String, ChannelPanel> channelsMap = new HashMap<>();

    public static void addChannel(String title, String user){
        channelsMap.put(title, ChannelsPanel.addChannels(title, user));
    }

    public static void addMessage(String message, String user, String title){
        System.out.println(title);
        System.out.println(channelsMap.keySet());
        channelsMap.get(title).messages(message, user);
        channelsMap.get(title).getMessagesZone().validate();
    }

    public static void addSmiley(String smiley, String user, String title){
        channelsMap.get(title).smiley(smiley, user);
    }


    public static void addUser( String nickname, String title){
        MyButton button = MyButton.createBNameUser(nickname);
        channelsMap.get(title).connected(nickname);
        channelsMap.get(title).getUsersMap().put(nickname, button);
        channelsMap.get(title).getListUser().add(button);
        channelsMap.get(title).validate();
    }

    public static void removeUser( String nickname, String title){
        ChannelPanel channel = channelsMap.get(title);
        channel.getListUser().remove(channel.getUsersMap().get(nickname));
        channel.getUsersMap().remove(nickname);
        channelsMap.get(title).validate();
    }

    public static void updateUsersConnected( String [] users, String [] scores, String [] avatars,  String title){
        channelsMap.get(title).updateLisUser(users, scores, avatars);
    }

}
