package game.tracks;

import authorization.client_credentials.ClientCredentialsExample;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetPlaylist {
    private static final String accessToken = ClientCredentialsExample.getToken();
    private static final String playlistId = "7oBeEkujcRybm7dCAUAIhG";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();
    private static final GetPlaylistRequest getPlaylistRequest = spotifyApi.getPlaylist(playlistId)
            .build();

    public static void getPlaylist_Sync() {
        try {
            final Playlist playlist = getPlaylistRequest.execute();

            System.out.println("Name: " + playlist.getName());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void getPlaylist_Async() {
        try {
            final CompletableFuture<Playlist> playlistFuture = getPlaylistRequest.executeAsync();

            final Playlist playlist = playlistFuture.join();

            System.out.println("Name: " + playlist.getName());
            System.out.println("Tracks: " + playlist.getTracks());

        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }

    public static void main(String[] args) {
        getPlaylist_Sync();
        getPlaylist_Async();
    }
}