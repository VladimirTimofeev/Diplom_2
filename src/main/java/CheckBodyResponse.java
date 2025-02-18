import io.restassured.response.Response;
import static org.hamcrest.Matchers.notNullValue;

public class CheckBodyResponse {

    public void checkBody_id(Response response) {
        response.then().assertThat()
                .body("data.__id", notNullValue());
    }
}
