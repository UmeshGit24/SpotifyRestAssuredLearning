package com.spotify.playlistApi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class Tracks {


    @JsonProperty("href")
    private String href;
    @JsonProperty("items")
    @JsonIgnoreProperties
    private List<Item> items;
    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("next")
    private String next;
    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("previous")
    private String previous;
    @JsonProperty("total")
    private Integer total;
}
