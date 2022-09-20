package com.spotify.configUtils;


import java.util.Properties;

public class OAuthConfigLoder {

    private static OAuthConfigLoder oAuthConfigLoder;
    private static Properties properties;

    private static Properties tokenFileProperties;
    private static final String  FILE_PATH="src/main/resources/oauthconfig.properties";
    private static  final String TOKEN_FILE_PATH="src/main/resources/acesstoken.properties";

    private OAuthConfigLoder(){
        properties=PropertyUtils.propertryLoader(FILE_PATH);
        tokenFileProperties=PropertyUtils.propertryLoader(TOKEN_FILE_PATH);

    }

    public static OAuthConfigLoder getInstance() {
        if (oAuthConfigLoder == null) {
            oAuthConfigLoder = new OAuthConfigLoder();
        }

        return oAuthConfigLoder;

    }
    public String getBaseUri() {
        String baseUri = properties.getProperty("baseUri");
        if (baseUri != null) {
            return baseUri;
        } else {
            throw new RuntimeException("baseUri not found in properties file");
        }

    }


    public String getClientId() {
        String clientId = properties.getProperty("clientId");
        if (clientId != null) {
            return clientId;
        } else {
            throw new RuntimeException("clientId not found in properties file");
        }

    }

    public String getRedirectUri() {
        String redirectUri = properties.getProperty("redirectUri");
        if (redirectUri != null) {
            return redirectUri;
        } else {
            throw new RuntimeException("redirectUri not found in properties file");
        }

    }

    public String getClientSecret() {
        String clientSecret = properties.getProperty("clientSecret");
        if (clientSecret != null) {
            return clientSecret;
        } else {
            throw new RuntimeException("clientSecret not found in properties file");
        }

    }

    public String getCode() {
        String code = properties.getProperty("code");
        if (code != null) {
            return code;
        } else {
            throw new RuntimeException("code not found in properties file");
        }
    }

    public void setAccessToken(String accessToken){
        PropertyUtils.setProperties(TOKEN_FILE_PATH,"access_token",accessToken,false);
    }

    public String getAccessToken(){
        String accessToken = tokenFileProperties.getProperty("access_token");
        if (accessToken != null) {
            return accessToken;
        } else {
            throw new RuntimeException("access_token not found in properties file");
        }
    }

    public void setRefreshToken(String refreshToken){
        PropertyUtils.setProperties(FILE_PATH,"refresh_token",refreshToken,true);

    }

    public String getRefreshToken(){
        String refreshToken = properties.getProperty("refresh_token");
        if (refreshToken != null) {
            return refreshToken;
        } else {
            throw new RuntimeException("refreshToken not found in properties file");
        }
    }

    public void setTokenExpiresTime(int expiresTime){
        PropertyUtils.setProperties(FILE_PATH,"expires_in",String.valueOf(expiresTime),true);
        properties.put("expires_in",expiresTime);

    }

    public int getTokenExpiresTime(){
        String tokenExpireTime = properties.getProperty("expires_in");
        if (tokenExpireTime != null) {
            return Integer.valueOf(tokenExpireTime);
        } else {
            throw new RuntimeException("tokenExpireTime not found in properties file");
        }
    }




}
