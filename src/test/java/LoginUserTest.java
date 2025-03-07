import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginUserTest {

    private PatchApi patchApi = new PatchApi();
    private PostApi postApi = new PostApi();
    private DeleteApi deleteApi = new DeleteApi();
    private CheckStatusCode checkStatusCode = new CheckStatusCode();
    private CheckBodyResponse checkBodyResponse = new CheckBodyResponse();
    private Token token = new Token();

    private String accsessToken;

    private Response response;

    @Step
    public boolean findUser() {
        response = postApi.authorizationUser(UsersData.expectedCreateUser());
        return checkBodyResponse.getBodyTegSuccessTrue(response);
    }

    @Step("Авторизация пользователя")
    public Response authorizationUser() {
        return response = postApi.authorizationUser(UsersData.expectedRegistrationUser());
    }

    @Step("Извлечение accessToken и refreshToken")
    public String getAccessToken(Response response) {
        return accsessToken = token.extractionToken(response);
    }

    @Step("Создание клиента и проверка ответа")
    public void createNewUser() {
        response = postApi.postCreateUser(UsersData.expectedCreateUser());
        checkStatusCode.checkStatusCode200(response);
        checkBodyResponse.checkBodyTegSuccessTrue(response);
    }

    @Step("Авторизация клиента и проверка ответа об успешной авторизации")
    public String checkAuthorizationUser() {
        response = postApi.authorizationUser(UsersData.expectedRegistrationUser());
        checkStatusCode.checkStatusCode200(response);
        checkBodyResponse.checkBodyTegSuccessTrue(response);
        checkBodyResponse.checkBodyUserEmail(response, UsersData.expectedCreateUser());
        checkBodyResponse.checkBoduUserName(response, UsersData.expectedCreateUser());
        return accsessToken = token.extractionToken(response);
    }

    @Step("Обновление данных пользователя")
    public void modyfiUser() {
        response = patchApi.patchRequestModifyUser(UsersData.modyfiedUser());
        checkStatusCode.checkStatusCode401(response);
        checkBodyResponse.checkBodyTegSuccessFalse(response);
        checkBodyResponse.checkMessageYouShouldBeAuthorised(response);
    }

    @Step("Удаление клиента")
    public void deleteUserAndCheckResponse(String accessToken) {
        response = deleteApi.deleteUser(accessToken);
        checkStatusCode.checkStatusCode202(response);
        checkBodyResponse.checkBodyTegSuccessTrue(response);
        checkBodyResponse.checkMessageDeleteUser(response);
    }

    @Before
    @DisplayName("Создание пользователя")
    public void createUser() {
        if (findUser()) {
            deleteUserAndCheckResponse(getAccessToken(authorizationUser()));
        }
        createNewUser();
    }

    @Test
    @DisplayName("Авторизация пользователя")
    public void loginUser() {
        checkAuthorizationUser();
    }

    @Test
    @DisplayName("Обновление данных пользователя без авторизации")
    public void modifyUserWithoutAuthorization() {
        modyfiUser();
    }

    @After
    @DisplayName("Удаление пользователя")
    public void deleteUser() {
        deleteUserAndCheckResponse(checkAuthorizationUser());
    }
}