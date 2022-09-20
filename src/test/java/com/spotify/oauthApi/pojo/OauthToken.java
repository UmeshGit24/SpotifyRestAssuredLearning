package com.spotify.oauthApi.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Jacksonized
@Builder
public class OauthToken {
    private String code;
    private String client_id;
    private String client_secret;
    private String redirect_uri;
    private String access_token;
    private String token_type;
    private int expires_in;
    private String refresh_token;
    private String scope;
    private String grant_type;



}
