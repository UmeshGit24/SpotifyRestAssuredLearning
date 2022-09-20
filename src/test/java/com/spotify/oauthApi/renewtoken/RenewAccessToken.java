package com.spotify.oauthApi.renewtoken;

import com.spotify.configUtils.OAuthConfigLoder;
import com.spotify.constants.RouteConstants;
import com.spotify.oauthApi.dataBuilder.OAuthDataBuilder;
import com.spotify.oauthApi.pojo.OauthToken;
import com.spotify.oauthApi.specBuilder.OAuthSpecBuilder;
import io.restassured.http.ContentType;

import java.time.Instant;


import static io.restassured.RestAssured.given;

    public class RenewAccessToken {

        private static Instant expiryTime;
        private static String acessToken;


        public static String getAccessToken() {

            try {
                if (acessToken == null || expiryTime==null || Instant.now().isAfter(expiryTime)) {
                    renewAccessToken();
                    expiryTime = expiryTime.plusSeconds(OAuthConfigLoder.getInstance().getTokenExpiresTime());

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            acessToken=OAuthConfigLoder.getInstance().getAccessToken();
            return acessToken;

        }


        public static void renewAccessToken(){

            OauthToken reponse=given()
                    .spec(OAuthSpecBuilder.getRequestSpec()).contentType(ContentType.URLENC)
                    .formParams(OAuthDataBuilder.renewAccessTokenDataBuilder())
                    .post(RouteConstants.OAUTH_API_ENDPOINT)
                    .then()
                    .extract()
                    .response()
                    .as(OauthToken.class);

            OAuthConfigLoder.getInstance().setAccessToken(reponse.getAccess_token());




        }


    }
