package game.tracks;

import authorization.client_credentials.ClientCredentialsExample;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetTrack {

    private static final String accessToken = ClientCredentialsExample.getToken();
    private static String id = Song.getId();

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();
    private static final GetTrackRequest getTrackRequest = spotifyApi.getTrack(Song.getId())
            .market(CountryCode.FR)
            .build();

    public static void getTrack_Sync() {
        try {
            final Track track = getTrackRequest.execute();

            System.out.println("Name: " + track.getName());
            System.out.println("Url: " + track.getPreviewUrl());

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void getTrack_Async() {
        try {
            final CompletableFuture<Track> trackFuture = getTrackRequest.executeAsync();

            final Track track = trackFuture.join();
            System.out.println("Name: " + track.getName());
            System.out.println("Url: " + track.getPreviewUrl());

        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }

    public static String getPreviewUrl(){

        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(accessToken)
                .build();
        GetTrackRequest getTrackRequest = spotifyApi.getTrack(Song.getId())
                .market(CountryCode.FR)
                .build();

        try {
            final Track track = getTrackRequest.execute();

            String previewUrl = track.getPreviewUrl();

            return previewUrl;

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        getTrack_Sync();
        getTrack_Async();
    }
}