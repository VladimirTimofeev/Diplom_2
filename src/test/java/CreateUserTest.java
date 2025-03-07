import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateUserTest {

    private PostApi postApi = new PostApi();
    private DeleteApi deleteApi = new DeleteApi();
    private CheckStatusCode checkStatusCode = new CheckStatusCode();
    private CheckBodyResponse checkBodyResponse = new CheckBodyResponse();
    private Token token = new Token();
    private Response response;

    private String accsessToken;

    @Step("Отправка запроса на создание пользователя и проверка его создания")
    public void createUser() {
        response = postApi.postCreateUser(UsersData.expectedCreateUser());
        checkStatusCode.checkStatusCode200(response);
        checkBodyResponse.checkBodyTegSuccessTrue(response);
        checkBodyResponse.checkBodyUserEmail(response, UsersData.expectedCreateUser());
        checkBodyResponse.checkBoduUserName(response, UsersData.expectedCreateUser());
    }

    @Step("Отправка запроса на создание имеющегося пользователя и проверка ответа")
    public void creatingExistingUser() {
        response = postApi.postCreateUser(UsersData.expectedCreateUser());
        checkStatusCode.checkStatusCode403(response);
        checkBodyResponse.checkBodyTegSuccessFalse(response);
        checkBodyResponse.checkMessageAboutCreatedUser(response);
    }

    @Step("Удаление пользователя и проверка ответа на его удачное исполнение")
    public void deleteUser(String accsessToken) {
        response = deleteApi.deleteUser(accsessToken);
        checkStatusCode.checkStatusCode202(response);
        checkBodyResponse.checkBodyTegSuccessTrue(response);
        checkBodyResponse.checkMessageDeleteUser(response);
    }

    @Step("Авторизация пользователя")
    public Response authorizationUser() {
        return response = postApi.authorizationUser(UsersData.expectedRegistrationUser());
    }

    @Step("Извлечение accessToken и refreshToken")
    public String getAccessToken(Response response) {
        return accsessToken = token.extractionToken(response);
    }

    @Step
    public boolean findUser() {
        response = postApi.authorizationUser(UsersData.expectedCreateUser());
        return checkBodyResponse.getBodyTegSuccessTrue(response);
    }

    @Before
    public void checkUser() {
        if (findUser()) {
            deleteUser(getAccessToken(authorizationUser()));
        }
    }


    @Test
    @DisplayName("Создание нового пользователя")
    public void createNewUser() {
        createUser();
    }

    @Test
    @DisplayName("Попытка создания зарегестрированного пользователя")
    public void creatingARegisteredUser() {
        createUser();
        creatingExistingUser();
    }

    @After
    @DisplayName("Удаление пользователя")
    public void authorizationAndDeleteUser() {
        deleteUser(getAccessToken(authorizationUser()));
    }
}