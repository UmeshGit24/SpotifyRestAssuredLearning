package com.spotify.playlistApi.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Jacksonized
public class AddItems {

    @JsonProperty("uris")
    private List<String> songsUri=new ArrayList<String>();
    @JsonProperty("range_start")
    private int rangeStart;
    @JsonProperty("insert_before")
    private int insertBefore;
    @JsonProperty("range_length")
    private int rangeLength;
    @JsonProperty("snapshot_id")
    private String snapshot_id;







}
