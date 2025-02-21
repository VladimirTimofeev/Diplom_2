import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

public class CreateUserTest {

    private PostApi postApi = new PostApi();
    private DeleteApi deleteApi = new DeleteApi();
    private CheckStatusCode checkStatusCode = new CheckStatusCode();
    private CheckBodyResponse checkBodyResponse = new CheckBodyResponse();
    private Response response;

    private String accsessToken;
    private String refreshToken;

    @Step("Отправка запроса на создание пользователя и проверка его создания")
    public void createUser() {
        response = postApi.postCreateUser(UsersData.expectedUser());
        checkStatusCode.checkStatusCode200(response);
        checkBodyResponse.checkBodyTegSuccessTrue(response);
        checkBodyResponse.checkBodyUserEmail(response, UsersData.expectedUser());
        checkBodyResponse.checkBoduUserName(response, UsersData.expectedUser());
    }

    @Step("Отправка запроса на создание имеющегося пользователя и проверка ответа")
    public void creatingExistingUser() {
        response = postApi.postCreateUser(UsersData.expectedUser());
        checkStatusCode.checkStatusCode403(response);
        checkBodyResponse.checkBodyTegSuccessFalse(response);
        checkBodyResponse.checkMessageAboutCreatedUser(response);
    }

    @Step("Отправка запроса на удаление пользователя и проверка ответа на его удачное исполнение")
    public void deleteUser(String accsessToken) {
        response = deleteApi.deleteUser(accsessToken);
        checkStatusCode.checkStatusCode202(response);
        checkBodyResponse.checkBodyTegSuccessTrue(response);
        checkBodyResponse.checkMessageDeleteUser(response);
    }

    @Step("Авторизация пользователя")
    public Response authorizationUser() {
        return response = postApi.authorizationUser(UsersData.expectedUser());
    }

    @Step("Извлечение accessToken и refreshToken")
    public String getAccessToken(Response response) {
        accsessToken = response.jsonPath().getString("accessToken");
        return accsessToken.substring(7);
//        refreshToken = response.jsonPath().getString("refreshToken");
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
    public void authorizationAndDeleteUser() {
        response = authorizationUser();
        accsessToken = getAccessToken(response);
        deleteUser(accsessToken);
    }
}