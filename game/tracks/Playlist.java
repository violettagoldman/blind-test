package game.tracks;

import jaco.mp3.player.MP3Player;

import java.net.MalformedURLException;
import java.net.URL;

public class Playlist {

    public static MP3Player player;

    public static MP3Player getPlayer() {
        return player;
    }

    public static void setPlayer(MP3Player player) {
        Playlist.player = player;
    }

    public static MP3Player getMP3Player() throws MalformedURLException {
        Song.setId("1TfqLAPs4K3s2rJMoCokcS");
        String previewUrl = GetTrack.getPreviewUrl();
        URL testUrl = new URL(previewUrl);

        Song.setId("6pvfDHdFcjiIDXFxvDI879");
        Song.getId();
        String previewUrl2 = GetTrack.getPreviewUrl();
        URL testUrl2 = new URL(previewUrl2);

        Song.setId("2UC5XnHA1Wn9FjQmbjNca9");
        Song.getId();
        String previewUrl3 = GetTrack.getPreviewUrl();
        URL testUrl3 = new URL(previewUrl3);

        MP3Player player = new MP3Player(testUrl, testUrl2, testUrl3);
        return player;
    }
}
