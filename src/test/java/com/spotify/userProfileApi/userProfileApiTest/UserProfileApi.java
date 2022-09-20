package com.spotify.userProfileApi.userProfileApiTest;


import com.spotify.configUtils.OAuthConfigLoder;
import com.spotify.constants.Constants;
import com.spotify.constants.RouteConstants;
import com.spotify.constants.StatusCode;
import com.spotify.error.Error;
import com.spotify.error.Errors;
import com.spotify.oauthApi.renewtoken.RenewAccessToken;
import com.spotify.playlistApi.specBuilder.SpecBuilder;
import com.spotify.userProfileApi.pojo.UserProfile;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserProfileApi {



    @Test(description = "Get detailed profile information about the current user")
    public void getCurrentUser(){
        UserProfile currentUser=given()
                .spec(SpecBuilder.getRequest())
                .get(RouteConstants.ME)
                .then()
                .spec(SpecBuilder.getResponse())
                .statusCode(StatusCode.STATUS_CODE_200.code)
                .extract()
                .response()
                .as(UserProfile.class);

        assertThat(currentUser.getDisplayName(),equalTo(Constants.USER_DISPLAY_NAME));
        assertThat(currentUser.getType(),equalTo(Constants.TYPE));

        assertThat(currentUser.getUri(),equalTo(Constants.USER_URI));
        assertThat(currentUser.getId(),equalTo(Constants.USER_ID));

    }



    @Test(description = "Get public profile information about a Spotify user.")
    public void getUserProfile(){
        UserProfile userProfile=given()
                .spec(SpecBuilder.getRequest())
                .get(RouteConstants.USERS+Constants.USER_ID)
                .then()
                .spec(SpecBuilder.getResponse())
                .statusCode(StatusCode.STATUS_CODE_200.code)
                .extract()
                .response().as(UserProfile.class);

        assertThat(userProfile.getDisplayName(),equalTo(Constants.USER_DISPLAY_NAME));
        assertThat(userProfile.getType(),equalTo(Constants.TYPE));

        assertThat(userProfile.getDisplayName(),equalTo(Constants.USER_DISPLAY_NAME));
        assertThat(userProfile.getType(),equalTo(Constants.TYPE));
        assertThat(userProfile.getId(),equalTo(Constants.USER_ID));
        assertThat(userProfile.getUri(),equalTo(Constants.USER_URI));


    }


    @Test(description = "Accessing Api by passing wrong access token ")
    public void shouldNotGetUserProfileThroughWrongAccessToken(){
        Errors response=given()
                .spec(SpecBuilder.getRequestSpecForWrongAccessToken(RouteConstants.SPOTIFY_BASE_URI,RouteConstants.SPOTIFY_BASE_PATH, RenewAccessToken.getAccessToken()+"sdkjs"))
                .get(RouteConstants.USERS+Constants.USER_ID)
                .then()
                .spec(SpecBuilder.getResponse())
                .extract().response().as(Errors.class);

        assertThat(response.getError().getStatus(),equalTo(StatusCode.STATUS_INVALID_CODE_401.code));
        assertThat(response.getError().getMessage(),equalTo(StatusCode.STATUS_INVALID_CODE_401.message));

    }

    @Test(description = "Accessing Api by passing expired token")
    public void shouldNotGetUserProfileAfterAccessTokenExpire(){
        Errors response=given()
                .spec(SpecBuilder.getRequestSpecForWrongAccessToken(RouteConstants.SPOTIFY_BASE_URI,RouteConstants.SPOTIFY_BASE_PATH, OAuthConfigLoder.getInstance().getAccessToken()))
                .get(RouteConstants.USERS+Constants.USER_ID)
                .then()
                .spec(SpecBuilder.getResponse())
                .extract().response().as(Errors.class);

        assertThat(response.getError().getStatus(),equalTo(StatusCode.STATUS_EXPIRE_CODE_401.code));
        assertThat(response.getError().getMessage(),equalTo(StatusCode.STATUS_EXPIRE_CODE_401.message));

    }



}
