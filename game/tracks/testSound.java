package game.tracks;

import jaco.mp3.player.MP3Player;

public class testSound {

    public static void main(String[] args) throws Exception {

        MP3Player player = Playlist.getMP3Player();

        player.play();
        Thread.sleep(10000);

        player.stop();
        Thread.sleep(5000);

        player.skipForward();
        Thread.sleep(10000);

        player.stop();
        Thread.sleep(5000);

        player.skipForward();
        Thread.sleep(10000);

        player.stop();
    }
}