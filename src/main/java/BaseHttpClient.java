
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.net.Authenticator;

import static io.restassured.RestAssured.given;


public class BaseHttpClient {



    private RequestSpecification baseRequestSpec = new RequestSpecBuilder()
            .setBaseUri(URL.HOST)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY3YjVkOTEyOWVkMjgwMDAxYjU3Y2RiYSIsImlhdCI6MTczOTk3MDg1MywiZXhwIjoxNzM5OTcyMDUzfQ.j_WT3wdr4xSN0j1ISBDFTU05AriHdGtRH-DhqDa2Tl0")
            .setRelaxedHTTPSValidation()
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .addFilter(new ErrorLoggingFilter())
            .build();

    protected Response doGetRequest(String path) {
        return given()
                .spec(baseRequestSpec)
                .get(path)
                .thenReturn();
    }

    protected Response doGetRequest(String token, String path) {
        return given()
                .spec(baseRequestSpec)
                .auth().oauth2(token)
                .get(path)
                .thenReturn();
    }

    protected Response doPostRequest(String path, Object body) {
        return given()
                .spec(baseRequestSpec)
                .body(body)
                .post(path)
                .thenReturn();
    }

    protected Response doDelete(String path) {
        return given()
                .spec(baseRequestSpec)
                .delete(path)
                .thenReturn();
    }

}
