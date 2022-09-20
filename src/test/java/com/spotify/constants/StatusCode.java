package com.spotify.constants;

public enum StatusCode {

    STATUS_CODE_200(200, "",""),
    STATUS_CODE_201(201, "",""),
    STATUS_CODE_202(202, "",""),
    STATUS_INVALID_CODE_401(401, "Invalid access token", ""),
    STATUS_EXPIRE_CODE_401(401, "The access token expired", ""),
    STATUS_CODE_400(400, "Missing required field: name", ""),
    STATUS_INVALID_OAUTH_CODE_400(400,"invalid_grant","Invalid authorization code"),
    STATUS_EXPIRED_OAUTH_CODE_400(400,"invalid_grant","Authorization code expired"),
    STATUS_INVALID_CLIENT_SECRET_400(400,"invalid_client","Invalid client secret"),
    STATUS_INVALID_CLIENT_ID_400(400,"invalid_client","Invalid client"),
    STATUS_INVALID_REDIRECT_URI_400(400,"invalid_grant","Invalid redirect URI");

    public final int code;
    public final String message;
    public final String errorDescription;



    StatusCode(int code, String message, String errorDescription) {
        this.code = code;
        this.message = message;
        this.errorDescription = errorDescription;
    }


}