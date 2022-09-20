package com.spotify.playlistApi.pojo.Track;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class Track {
    public Track(String uri) {
        this.uri = uri;
    }

    @JsonProperty("uri")
    private String uri;
}
