package com.spotify.playlistApi.specBuilder;

import com.spotify.constants.Constants;
import com.spotify.constants.RouteConstants;
import com.spotify.oauthApi.renewtoken.RenewAccessToken;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {



    public static RequestSpecification getRequest(){
        return  new RequestSpecBuilder()
                .setBaseUri(RouteConstants.SPOTIFY_BASE_URI)
                .setBasePath(RouteConstants.SPOTIFY_BASE_PATH)
                .addHeader(Constants.AUTHORIZATION,Constants.AUTHORIZATION_TYPE+RenewAccessToken.getAccessToken())
                .log(LogDetail.ALL)
                .build();


    }


    public static ResponseSpecification getResponse(){
        return  new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();

    }

    public static  RequestSpecification getRequestSpecForWrongAccessToken(String baseUri,String basePath,String accessToken){

        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(basePath)
                .addHeader(Constants.AUTHORIZATION,Constants.AUTHORIZATION_TYPE+accessToken)
                .log(LogDetail.ALL)
                .build();

    }


}
