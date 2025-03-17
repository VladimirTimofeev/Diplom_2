import io.restassured.response.Response;
import org.apache.http.HttpStatus;

public class CheckStatusCode {

    //Провека кода 200
    public void checkStatusCode200(Response response) {
        response.then().assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    public void checkStatusCode202(Response response) {
        response.then().assertThat()
                .statusCode(HttpStatus.SC_ACCEPTED);
    }

    //400
    public void checkStatusCode400(Response response) {
        response.then().assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    //401
    public void checkStatusCode401(Response response) {
        response.then().assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    //403
    public void checkStatusCode403(Response response) {
        response.then().assertThat()
                .statusCode(HttpStatus.SC_FORBIDDEN);
    }

    //500
    public void checkStatusCode500(Response response) {
        response.then().assertThat()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}