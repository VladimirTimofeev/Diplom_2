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
    public void checkSatusCode401(Response response) {
        response.then().assertThat()
                .statusCode(401);
    }

    //403
    public void checkSatusCode403(Response response) {
        response.then().assertThat()
                .statusCode(403);
    }

    //500
    public void checkSatusCode500(Response response) {
        response.then().assertThat()
                .statusCode(500);
    }
}