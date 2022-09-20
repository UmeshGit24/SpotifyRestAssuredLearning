package com.spotify.oauthApi.test;

import com.spotify.configUtils.OAuthConfigLoder;
import com.spotify.constants.RouteConstants;
import com.spotify.constants.StatusCode;
import com.spotify.error.Errors;
import com.spotify.error.OAuthApiError;
import com.spotify.oauthApi.dataBuilder.OAuthDataBuilder;
import com.spotify.oauthApi.pojo.OauthToken;
import com.spotify.oauthApi.renewtoken.RenewAccessToken;
import com.spotify.oauthApi.specBuilder.OAuthSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class AccessTokenApi {


    String clientID=OAuthConfigLoder.getInstance().getClientId();
    String clientSecret=OAuthConfigLoder.getInstance().getClientSecret();
    String redirectUri=OAuthConfigLoder.getInstance().getRedirectUri();
    String oAuthCode=OAuthConfigLoder.getInstance().getCode();



    @Test(description = "Get Access Token and Refresh Token through OAuth Code" ,priority = 0)

    public void getAccessTokenAndRefreshToken(){

        OauthToken response=given()
                .spec(OAuthSpecBuilder.getRequestSpec())
                .contentType(ContentType.URLENC)
                .formParams(OAuthDataBuilder.getRefreshAndAccessTokenDataBuilder())
                .post(RouteConstants.OAUTH_API_ENDPOINT)
                .then()
                .spec(OAuthSpecBuilder.getReponseSpec()).statusCode(StatusCode.STATUS_CODE_200.code)
                .extract().as(OauthToken.class);

        assertThat(response.getAccess_token(),notNullValue());
        assertThat(response.getRefresh_token(),notNullValue());
        assertThat(response.getExpires_in(),notNullValue());

        OAuthConfigLoder.getInstance().setAccessToken(response.getAccess_token());
        OAuthConfigLoder.getInstance().setRefreshToken(response.getRefresh_token());
        OAuthConfigLoder.getInstance().setTokenExpiresTime(response.getExpires_in());


    }

    @Test(description = "Renew Access token through refresh token" ,priority = 1,dependsOnMethods = "getAccessTokenAndRefreshToken")
    public void renewAccessTokenAfterTokenExpire(){
        RenewAccessToken.renewAccessToken();

    }


    @Test(description = "Should not get access token and refresh when OAuth code is invalid")
    public void shoutNotGetAccessTokenAndRefreshTokenThroughInvalidOAuthCode(){

        OAuthApiError response=given()
                .spec(OAuthSpecBuilder.getRequestSpec())
                .contentType(ContentType.URLENC)
                .formParams(OAuthDataBuilder.invalidOAuthRequestDataBuilder(oAuthCode+"KJDJ",clientID,clientSecret,redirectUri))
                .post(RouteConstants.OAUTH_API_ENDPOINT)
                .then()
                .spec(OAuthSpecBuilder.getReponseSpec()).statusCode(StatusCode.STATUS_INVALID_OAUTH_CODE_400.code)
                .extract().as(OAuthApiError.class);

        assertThat(response.getError(),equalTo(StatusCode.STATUS_INVALID_OAUTH_CODE_400.message));
        assertThat(response.getErrorDescription(),equalTo(StatusCode.STATUS_INVALID_OAUTH_CODE_400.errorDescription));


    }


    @Test(description = "Should not get access token and refresh when OAuth code is expired")

    public void shouldNotGetAccessTokenAndRefreshTokenThroughExpiredOAuthCode(){

        OAuthApiError response=given()
                .spec(OAuthSpecBuilder.getRequestSpec())
                .contentType(ContentType.URLENC)
                .formParams(OAuthDataBuilder.invalidOAuthRequestDataBuilder(oAuthCode,clientID,clientSecret,redirectUri))
                .post(RouteConstants.OAUTH_API_ENDPOINT)
                .then()
                .spec(OAuthSpecBuilder.getReponseSpec())
                .statusCode(StatusCode.STATUS_EXPIRED_OAUTH_CODE_400.code)
                .extract().as(OAuthApiError.class);

        assertThat(response.getError(),equalTo(StatusCode.STATUS_EXPIRED_OAUTH_CODE_400.message));
        assertThat(response.getErrorDescription(),equalTo(StatusCode.STATUS_EXPIRED_OAUTH_CODE_400.errorDescription));



    }


    @Test(description = "Should not get access token and refresh when Client Secret is Invalid")
    public void shouldNotGetAccessTokenAndRefreshTokenThroughInvalidClientSecret(){

        OAuthApiError response=given()
                .spec(OAuthSpecBuilder.getRequestSpec())
                .contentType(ContentType.URLENC)
                .formParams(OAuthDataBuilder.invalidOAuthRequestDataBuilder(oAuthCode,clientID,clientSecret+"HSS",redirectUri))
                .post(RouteConstants.OAUTH_API_ENDPOINT)
                .then()
                .spec(OAuthSpecBuilder.getReponseSpec())
                .statusCode(StatusCode.STATUS_INVALID_CLIENT_SECRET_400.code)
                .extract().as(OAuthApiError.class);

        assertThat(response.getError(),equalTo(StatusCode.STATUS_INVALID_CLIENT_SECRET_400.message));
        assertThat(response.getErrorDescription(),equalTo(StatusCode.STATUS_INVALID_CLIENT_SECRET_400.errorDescription));
    }

    @Test(description = "Should not get access token and refresh when Client ID is Invalid")
    public void shouldNotGetAccessTokenAndRefreshTokenThroughInvalidClientID(){

        OAuthApiError response=given()
                .spec(OAuthSpecBuilder.getRequestSpec())
                .contentType(ContentType.URLENC)
                .formParams(OAuthDataBuilder.invalidOAuthRequestDataBuilder(oAuthCode,clientID+"SJS",clientSecret,redirectUri))
                .post(RouteConstants.OAUTH_API_ENDPOINT)
                .then()
                .spec(OAuthSpecBuilder.getReponseSpec())
                .statusCode(StatusCode.STATUS_INVALID_CLIENT_ID_400.code)
                .extract().as(OAuthApiError.class);

        assertThat(response.getError(),equalTo(StatusCode.STATUS_INVALID_CLIENT_ID_400.message));
        assertThat(response.getErrorDescription(),equalTo(StatusCode.STATUS_INVALID_CLIENT_ID_400.errorDescription));

    }


}

