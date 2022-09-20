package com.spotify.oauthApi.specBuilder;

import com.spotify.configUtils.OAuthConfigLoder;
import com.spotify.constants.Constants;
import com.spotify.constants.StatusCode;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class OAuthSpecBuilder {

    public static RequestSpecification getRequestSpec(){
        return  new RequestSpecBuilder().
                setBaseUri(OAuthConfigLoder.getInstance().getBaseUri())
                .addHeader(Constants.CONTENT,Constants.CONTENT_TYPE)
                .log(LogDetail.ALL)
                .build();


    }


    public static ResponseSpecification getReponseSpec(){
        return new ResponseSpecBuilder()
                .log(LogDetail.BODY)
                .build();

    }






}

