package com.spotify.playlistApi.playlistApiTests;

import com.spotify.constants.Constants;
import com.spotify.constants.RouteConstants;
import com.spotify.constants.StatusCode;
import com.spotify.playlistApi.dataBuilder.PlaylistItemsDataBuilder;
import com.spotify.playlistApi.dataBuilder.CreatePlaylistDataBuilder;
import com.spotify.playlistApi.pojo.Image;
import com.spotify.playlistApi.pojo.Playlist;
import com.spotify.playlistApi.pojo.PlaylistItems;
import com.spotify.playlistApi.pojo.Tracks;
import com.spotify.playlistApi.specBuilder.SpecBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PlaylistApi {


    private CreatePlaylistDataBuilder createPlaylistDataBuilder=new CreatePlaylistDataBuilder();


    @Test(description = "Create a playlist for a Spotify user")
    public void createPlayList(){
        Playlist cretaedPlaylist=given()
                .spec(SpecBuilder.getRequest())
                .body(createPlaylistDataBuilder.playlistDataBuilder())
                .post(RouteConstants.USERS + Constants.USER_ID +RouteConstants.SEPRATOR+RouteConstants.PLAYLIST)
                .then()
                .spec(SpecBuilder.getResponse()).statusCode(StatusCode.STATUS_CODE_201.code)
                .extract().response()
                .as(Playlist.class);

        assertThat(cretaedPlaylist.getDescription(),notNullValue());
        assertThat(cretaedPlaylist.getId(),notNullValue());
        assertThat(cretaedPlaylist.getName(),notNullValue());
        assertThat(cretaedPlaylist.get_public(),equalTo(false));
        assertThat(cretaedPlaylist.getOwner().getDisplayName(),equalTo(Constants.USER_DISPLAY_NAME));
        assertThat(cretaedPlaylist.getOwner().getId(),equalTo(Constants.USER_ID));
        assertThat(cretaedPlaylist.getOwner().getType(),equalTo(Constants.TYPE));
        assertThat(cretaedPlaylist.getOwner().getUri(),equalTo(Constants.USER_URI));
        assertThat(cretaedPlaylist.getType(),equalTo("playlist"));


    }

    @Test(description = "Get a list of the playlists owned or followed by the current Spotify user.")
    public void getCurrentUserAllPlaylist(){
        Playlist getAllPlaylist=given()
                .spec(SpecBuilder.getRequest())
                .get(RouteConstants.ME+RouteConstants.SEPRATOR+RouteConstants.PLAYLIST)
                .then().spec(SpecBuilder.getResponse())
                .statusCode(StatusCode.STATUS_CODE_200.code)
                .extract()
                .response().as(Playlist.class);


    }

    @Test(description = "Get a playlist owned by a Spotify user.")
    public void getPlaylist(){
        Playlist getPlaylist=given()
                .spec(SpecBuilder.getRequest())
                .get(RouteConstants.PLAYLIST+RouteConstants.SEPRATOR+Constants.PLAYLIST_ID)
                .then().spec(SpecBuilder.getResponse())
                .statusCode(StatusCode.STATUS_CODE_200.code)
                .extract()
                .response().as(Playlist.class);

        assertThat(getPlaylist.getId(),equalTo(Constants.PLAYLIST_ID));
        assertThat(getPlaylist.getName(),equalTo(Constants.PLAYLIST_NAME));
        assertThat(getPlaylist.getType(),equalTo(Constants.PLAYLIST_TYPE));
        assertThat(getPlaylist.getUri(),equalTo(Constants.PLAYLIST_URI));
        assertThat(getPlaylist.get_public(),equalTo(false));

        assertThat(getPlaylist.getOwner().getType(),equalTo(Constants.TYPE));
        assertThat(getPlaylist.getOwner().getDisplayName(),equalTo(Constants.USER_DISPLAY_NAME));
        assertThat(getPlaylist.getOwner().getId(),equalTo(Constants.USER_ID));
        assertThat(getPlaylist.getOwner().getUri(),equalTo(Constants.USER_URI));

    }

    @Test(description = "Get full details of the items of a playlist owned by a Spotify user.")
    public void getPlaylistItems(){

        Tracks response=given()
                .spec(SpecBuilder.getRequest())
                .get(RouteConstants.PLAYLIST+RouteConstants.SEPRATOR+Constants.PLAYLIST_ID+RouteConstants.TRACKS)
                .then()
                .spec(SpecBuilder.getResponse())
                .statusCode(StatusCode.STATUS_CODE_200.code)
                .extract().response()
                .as(Tracks.class);

        assertThat(response.getHref(),notNullValue());



    }


    @Test(description = "Change a playlist's name and public/private state.")
    public void updatePlaylistDetails(){
        Response response =given()
                .spec(SpecBuilder.getRequest())
                .body(CreatePlaylistDataBuilder.playlistDataBuilder())
                .put(RouteConstants.PLAYLIST+RouteConstants.SEPRATOR+Constants.PLAYLIST_ID)
                .then()
                .spec(SpecBuilder.getResponse())
                .extract()
                .response();

        assertThat(response.getStatusCode(),equalTo(StatusCode.STATUS_CODE_200.code));
    }

    @Test(description = "Either reorder or replace items in a playlist depending on the request's parameters.")
    public void updatePlaylistItems(){

        List<String> itemList=new ArrayList<String>();
//        itemList.add("spotify:track:0rXRBxYh5OFjXLRVnqisao");
        itemList.add("spotify:track:3JbAc6kG0otlXHso9uDucj");
        itemList.add("spotify:track:000RDCYioLteXcutOjeweY");

        String snapshotId="NSwyNTIyYTlkNWZjNTcxMjIyMWFiNTAwZjIwYzZmM2ViOTI3MmM1NDlm";

        Playlist updatePlaylistItems=given()
                .spec(SpecBuilder.getRequest())
                .body(PlaylistItemsDataBuilder.addPlaylistItemDataBuilder(itemList,snapshotId))
                .put(RouteConstants.PLAYLIST+RouteConstants.SEPRATOR+Constants.PLAYLIST_ID+RouteConstants.TRACKS)
                .then()
                .spec(SpecBuilder.getResponse()).statusCode(StatusCode.STATUS_CODE_200.code)
                .extract()
                .response()
                .as(Playlist.class);

    }


    @Test(description = "Update the image used to represent a specific playlist.")
    public void uploadPlaylistImage() throws IOException {
        File file=new File(Constants.PLAYLIST_IMAGE_FILE_PATH);
        String encodedImageString=fileToBase64ConversionString(file);

        Response response=given()
                .spec(SpecBuilder.getRequest())
                .body(encodedImageString)
                .put(RouteConstants.PLAYLIST+RouteConstants.SEPRATOR+Constants.PLAYLIST_ID+RouteConstants.PLAYLIST_IMAGES)
                .then()
                .spec(SpecBuilder.getResponse()).extract().response();

        assertThat(response.getStatusCode(),equalTo(StatusCode.STATUS_CODE_202.code));


    }

    @Test(description = "Get the current image associated with a specific playlist.")
    public void getPlayListCoverImage(){

        Image response= given()
                .spec(SpecBuilder.getRequest())
                .get(RouteConstants.PLAYLIST+RouteConstants.SEPRATOR+Constants.PLAYLIST_ID+RouteConstants.PLAYLIST_IMAGES)
                .then()
                .spec(SpecBuilder.getResponse()).statusCode(StatusCode.STATUS_CODE_200.code)
                .extract().response().as(Image.class);

        assertThat(response.getUrl(),notNullValue());



    }
    @Test(description = "Remove one or more items from a user's playlist.")
    public void deletePlaylistItem(){
        PlaylistItems removePlaylistItems=given()
                .spec(SpecBuilder.getRequest())
                .body(PlaylistItemsDataBuilder.removePlaylistItemDataBuilder())
                .delete(RouteConstants.PLAYLIST+RouteConstants.SEPRATOR+Constants.PLAYLIST_ID+RouteConstants.TRACKS)
                .then()
                .spec(SpecBuilder.getResponse())
                .statusCode(StatusCode.STATUS_CODE_200.code)
                .extract()
                .response()
                .as(PlaylistItems.class);


    }

    private static String fileToBase64ConversionString(File filePath) throws IOException {
        byte[] fileContent=Files.readAllBytes(filePath.toPath());
        String encodedImageString= Base64.getEncoder().encodeToString(fileContent);
        return encodedImageString;

    }







}