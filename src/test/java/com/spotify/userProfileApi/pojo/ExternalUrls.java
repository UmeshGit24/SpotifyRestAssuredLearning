package com.spotify.userProfileApi.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class ExternalUrls {

    @JsonProperty("spotify")
    private String spotify;
}

