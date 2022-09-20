package com.spotify.playlistApi.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spotify.playlistApi.pojo.Track.Track;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class PlaylistItems {

    @JsonProperty("tracks")
    private List<Track> tracks = null;
    @JsonProperty("snapshot_id")
    private String snapshotId;
}
