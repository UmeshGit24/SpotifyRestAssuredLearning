package com.spotify.playlistApi.dataBuilder;

import com.github.javafaker.Faker;
import com.spotify.playlistApi.pojo.AddItems;
import com.spotify.playlistApi.pojo.PlaylistItems;
import com.spotify.playlistApi.pojo.Track.Track;

import java.util.ArrayList;
import java.util.List;

public class PlaylistItemsDataBuilder {

    static Faker faker=new Faker();



    public static AddItems addPlaylistItemDataBuilder(List<String> itemListToBeAdded, String snapshot_id){
        return  AddItems.builder()
                .songsUri(itemListToBeAdded)
                .rangeStart(faker.number().numberBetween(1,1))
                .insertBefore(faker.number().numberBetween(1,1))
                .rangeLength(faker.number().numberBetween(1,1))
                .snapshot_id(snapshot_id)
                .build();


    }

    public static PlaylistItems removePlaylistItemDataBuilder(){

        List<Track> trackList=new ArrayList<>();
        trackList.add(new Track("spotify:track:0rXRBxYh5OFjXLRVnqisao"));
        trackList.add(new Track("spotify:track:3JbAc6kG0otlXHso9uDucj"));

        String snapshot_id="MjUsMjU3ZmUzMTMwZjIxZWU0ZjhkM2M1OTcyZmM4NDZlY2FiNmZjNGM1MQ";

        return  PlaylistItems
                .builder()
                .tracks(trackList)
                .snapshotId(snapshot_id)
                .build();


    }




}
