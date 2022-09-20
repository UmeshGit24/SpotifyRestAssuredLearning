package com.spotify.playlistApi.dataBuilder;

import com.github.javafaker.Faker;
import com.spotify.playlistApi.pojo.CreatePlaylist;

public class CreatePlaylistDataBuilder {


    static Faker fakeData=new Faker();

    public static CreatePlaylist playlistDataBuilder(){
        return CreatePlaylist.builder()
                .name(String.valueOf(fakeData.artist().name()))
                .description(fakeData.company().industry())
                .playlistScope(false).build();
    }




}
