package com.spotify.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class Error {

    @JsonProperty("status")
    private int status;
    @JsonProperty("message")
    private String message;
}

