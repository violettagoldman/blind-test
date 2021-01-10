package game.tracks;

import authorization.client_credentials.ClientCredentialsExample;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class GetTrack {

    private static final String accessToken = ClientCredentialsExample.getToken();
    private static String id = Song.getId();

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();

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

}