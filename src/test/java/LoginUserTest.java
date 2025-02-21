import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginUserTest {

    private PostApi postApi = new PostApi();
    private DeleteApi deleteApi = new DeleteApi();
    private CheckStatusCode checkStatusCode = new CheckStatusCode();
    private CheckBodyResponse checkBodyResponse = new CheckBodyResponse();

    private String accsessToken;

    private Response response;

    @Step("Создание клиента и проверка ответа")
    public void creatrUser() {
        response = postApi.postCreateUser(UsersData.expectedUser());
        checkStatusCode.checkStatusCode200(response);
        checkBodyResponse.checkBodyTegSuccessTrue(response);
    }

    @Step("Авторизация клиента и проверка ответа об успешной авторизации")
    public String authorizationUser() {
        response = postApi.authorizationUser(UsersData.expectedUser());
        accsessToken = response.jsonPath().getString("accessToken");
        checkStatusCode.checkStatusCode200(response);
        checkBodyResponse.checkBodyTegSuccessTrue(response);
        checkBodyResponse.checkBodyUserEmail(response, UsersData.expectedUser());
        checkBodyResponse.checkBoduUserName(response, UsersData.expectedUser());
        return accsessToken.substring(7);
    }

    @Step("Удаление клиента")
    public void deleteUserAndCheckResponse(String accessToken) {
        response = deleteApi.deleteUser(accessToken);
        checkStatusCode.checkStatusCode202(response);
        checkBodyResponse.checkBodyTegSuccessTrue(response);
        checkBodyResponse.checkMessageDeleteUser(response);
    }

    @Before
    public void createUser() {
        creatrUser();
    }

    @Test
    public void loginUser() {
        authorizationUser();
    }

    @After
    public void deleteUser() {
        accsessToken = authorizationUser();
        deleteUserAndCheckResponse(accsessToken);
    }
}
