package com.spotify.playlistApi.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spotify.userProfileApi.pojo.ExternalUrls;
import com.spotify.userProfileApi.pojo.Followers;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class Owner {

    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;
    @JsonProperty("followers")
    private Followers followers;
    @JsonProperty("href")
    private String href;
    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("display_name")
    private String displayName;
}

