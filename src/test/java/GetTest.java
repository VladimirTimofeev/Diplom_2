import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Test;

public class GetTest {

    private GetSteps getSteps = new GetSteps();
    private CheckStatus checkStatus = new CheckStatus();
    private CheckBodyResponse checkBodyResponse = new CheckBodyResponse();

    private Response response;

    @Step("Send get request to /api/ingredients")
    public Response getIngridients() {
        return response = getSteps.responseIngridients();
    }

    @Step("Check response status code 200")
    public void checkStatusCode200(Response response) {
        checkStatus.checkStatusCode200(response);
    }

    @Step("Check body ID")
    public void checkBodyIdNotNulle(Response response) {
        checkBodyResponse.checkBody_id(response);
    }

    @Test
    public void getListIngridients() {
        response = getIngridients();
        checkStatusCode200(response);
        checkBodyIdNotNulle(response);
    }


}
