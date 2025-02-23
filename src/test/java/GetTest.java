import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

public class GetTest {

    private GetApi getApi = new GetApi();
    private PostApi postApi = new PostApi();
    private CheckStatusCode checkStatusCode = new CheckStatusCode();
    private CheckBodyResponse checkBodyResponse = new CheckBodyResponse();

    private Response response;

    private String accsessToken;
    private String refreshToken;

    @Step("Авторизация пользователя")
    public Response authorizationUser() {
        return response = postApi.authorizationUser(UsersData.expectedCreateUser());
    }

    @Step("Send get request to /api/ingredients without token")
    public Response getOrderSpecificUserWithoutToken() {
        return response = getApi.getRequestSpecificUser();
    }

    @Step("Send get request to /api/ingredients with token")
    public Response getOrderSpecificUserWithToken(String accsessToken) {
        return response = getApi.getRequestSpecificUser(accsessToken);
    }

    @Step("Извлечение accessToken и refreshToken")
    public void getAccessToken(Response response) {
        accsessToken = response.jsonPath().getString("accessToken");
        refreshToken = response.jsonPath().getString("refreshToken");
    }

    @Step("Check response status code 200")
    public void checkStatusCode200(Response response) {
        checkStatusCode.checkStatusCode200(response);
    }

    @Step("Check response status code 401")
    public void checkStatusCode401(Response response) {
        checkStatusCode.checkStatusCode401(response);
    }

    @Step("Check body ID")
    public void checkBodyMessageNotAuthorizationUser(Response response) {
        checkBodyResponse.checkMessageNotAuthorizationUser(response);
    }

    @Step("Check teg succsess TRUE")
    public void checkBodyTegSuccessTrue(Response response) {
        checkBodyResponse.checkBodyTegSuccessTrue(response);
    }

    @Test
    @DisplayName("Получение списка ингридиентов неавторизованного пользователя")
    public void getListIngridientsSpecUserWithoutToken() {
        response = getOrderSpecificUserWithoutToken();
        checkStatusCode401(response);
        checkBodyMessageNotAuthorizationUser(response);
    }

//    @Test
//    @DisplayName("Получение списка ингридиентов авторизованного пользователя")
//    public void getListIngridientsSpecUserWithToken() {
//        response = authorizationUser();
//        getAccessToken(response);
//        response = getOrderSpecificUserWithToken(accsessToken.substring(7));
//        System.out.println(accsessToken);
//        System.out.println(refreshToken);
//        checkStatusCode200(response);
//        checkBodyTegSuccessTrue(response);
//    }
}
