package com.spotify.userProfileApi.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import java.util.List;

@Data
@Builder
@Jacksonized
public class UserProfile {

    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;
    @JsonProperty("followers")
    private Followers followers;
    @JsonProperty("href")
    private String href;
    @JsonProperty("id")
    private String id;
    @JsonProperty("images")
    private List<Object> images = null;
    @JsonProperty("type")
    private String type;
    @JsonProperty("uri")
    private String uri;

}
