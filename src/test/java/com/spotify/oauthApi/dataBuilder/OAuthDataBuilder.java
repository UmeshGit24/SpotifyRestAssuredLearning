package com.spotify.oauthApi.dataBuilder;

import com.spotify.configUtils.OAuthConfigLoder;
import com.spotify.oauthApi.pojo.OauthToken;

import java.util.HashMap;
import java.util.Map;

public class OAuthDataBuilder {


    private static final String CLIENT_ID= OAuthConfigLoder.getInstance().getClientId();
    private static final String CLIENT_SECRET=OAuthConfigLoder.getInstance().getClientSecret();

    public static Map<String,String> getRefreshAndAccessTokenDataBuilder(){
        Map<String,String> dataBuilderMap=new HashMap<>();

        dataBuilderMap.put("grant_type","authorization_code");
        dataBuilderMap.put("code",OAuthConfigLoder.getInstance().getCode());
        dataBuilderMap.put("redirect_uri",OAuthConfigLoder.getInstance().getRedirectUri());
        dataBuilderMap.put("client_id",CLIENT_ID);
        dataBuilderMap.put("client_secret",CLIENT_SECRET);

        return dataBuilderMap;

    }

    public static  Map<String,String> renewAccessTokenDataBuilder(){

        Map<String ,String > dataBuilderMap=new HashMap<>();
        dataBuilderMap.put("grant_type","refresh_token");
        dataBuilderMap.put("refresh_token",OAuthConfigLoder.getInstance().getRefreshToken());
        dataBuilderMap.put("client_id",CLIENT_ID);
        dataBuilderMap.put("client_secret",CLIENT_SECRET);

        return dataBuilderMap;

    }

    public static Map<String,String> invalidOAuthRequestDataBuilder(String oAuthCode,String clientID,String clientSecret,String redirectUri){
        Map<String,String> dataBuilderMap=new HashMap<>();

        dataBuilderMap.put("grant_type","authorization_code");
        dataBuilderMap.put("code",oAuthCode);
        dataBuilderMap.put("redirect_uri",redirectUri);
        dataBuilderMap.put("client_id",clientID);
        dataBuilderMap.put("client_secret",clientSecret);

        return dataBuilderMap;

    }


}
