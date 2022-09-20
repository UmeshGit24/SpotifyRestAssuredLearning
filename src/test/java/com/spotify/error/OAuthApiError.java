package com.spotify.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;


@Data
@Builder
@Jacksonized
public class OAuthApiError {

    @JsonProperty("error")
    private String error;
    @JsonProperty("error_description")
    private String errorDescription;
}
