import io.restassured.response.Response;

public class CheckStatus {

//    private Response response;

    //Провека кода 200
    public void checkStatusCode200(Response response) {
        response.then().assertThat()
                .statusCode(200);
    }

    //400
    public void checkStatusCode400(Response response) {
        response.then().assertThat()
                .statusCode(400);
    }

    //401
    public void checkStatusCode401(Response response) {
        response.then().assertThat()
                .statusCode(401);
    }

    //403
    public void checkStatusCode403(Response response) {
        response.then().assertThat()
                .statusCode(403);
    }

    //500
    public void checkStatusCode500(Response response) {
        response.then().assertThat()
                .statusCode(500);
    }
}