import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CheckBodyResponse {

    public void checkBody_id(Response response) {
        response.then().assertThat()
                .body("data._id", notNullValue());
    }

    public void checkMessageNotAuthorizationUser(Response response) {
        response.then().assertThat()
                .body("message", equalTo("You should be authorised"));
    }

    public void checkDodyTegSuccessTrue(Response response) {
        response.then().assertThat()
                .body("success", equalTo(true));
    }

    public void checkBodyUserEmail(Response response, User user) {
        response.then().assertThat()
                .body("user.email", equalTo(user.getEmail()));
    }

    public void checkBoduUserName(Response response, User user) {
        response.then().assertThat()
                .body("user.name", equalTo(user.getName()));
    }
}