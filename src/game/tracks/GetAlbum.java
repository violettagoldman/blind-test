package game.tracks;

import authorization.client_credentials.ClientCredentialsExample;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.albums.GetAlbumRequest;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetAlbum {
    private static final String accessToken = ClientCredentialsExample.getToken();;

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();

    public static String getImageUrl(String albumId){

        GetAlbumRequest getAlbumRequest = spotifyApi.getAlbum(albumId)
                .market(CountryCode.FR)
                .build();

        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(accessToken)
                .build();
        GetAlbumRequest getTrackRequest = spotifyApi.getAlbum(albumId)
                .market(CountryCode.FR)
                .build();

        try {
            final Album album = getAlbumRequest.execute();

            String image = String.valueOf(Arrays.stream(album.getImages()).findAny());
            String imageUrl = image.substring(31, 95);

            System.out.println("URL : " + imageUrl);

            return imageUrl;

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}