package com.spotify.playlistApi.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class CreatePlaylist {

    public String name;
    @JsonProperty("public")
    public boolean playlistScope;
    public String description;


}

