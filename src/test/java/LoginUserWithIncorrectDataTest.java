import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class LoginUserWithIncorrectDataTest {

    private final String email;
    private final String password;

    private PostApi postApi = new PostApi();
    private CheckStatusCode checkStatusCode = new CheckStatusCode();
    private CheckBodyResponse checkBodyResponse = new CheckBodyResponse();
    private DeleteApi deleteApi = new DeleteApi();

    private String accessToken;

    Response response;

    public LoginUserWithIncorrectDataTest(String email, String password) {
        this.email = email;
        this.password = password;
    }


    @Parameterized.Parameters
    public static Object[][] loginUserWithIncorrectLoginOrPassword() {
        return new Object[][] {
                {"tgb@bgt.re", "123321"},
                {"asdqwe@dsa.dsa", "789987"}
        };
    }

    @Step("Создание клиента и проверка ответа")
    public void createNewUser() {
        response = postApi.postCreateUser(UsersData.expectedCreateUser());
        checkStatusCode.checkStatusCode200(response);
        checkBodyResponse.checkBodyTegSuccessTrue(response);
    }

    @Step("Авторизация клиента и проверка ответа об успешной авторизации")
    public String authorizationUser() {
        response = postApi.authorizationUser(UsersData.expectedRegistrationUser());
        accessToken = response.jsonPath().getString("accessToken");
        checkStatusCode.checkStatusCode200(response);
        checkBodyResponse.checkBodyTegSuccessTrue(response);
        checkBodyResponse.checkBodyUserEmail(response, UsersData.expectedCreateUser());
        checkBodyResponse.checkBoduUserName(response, UsersData.expectedCreateUser());
        return accessToken.substring(7);
    }

    @Step("Авторизация пользователя с неверными паролем")
    public void loginUser(User user) {
        response = postApi.authorizationUser(user);
        checkStatusCode.checkStatusCode401(response);
        checkBodyResponse.checkBodyTegSuccessFalse(response);
        checkBodyResponse.chechMessageLoginOrPasswordIncorrect(response);
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
        createNewUser();
    }

    @Test
    @DisplayName("Авторизация пользователя с неверными логином или паролем")
    public void loginUser() {
        User user = new User(email, password);
        loginUser(user);
    }

    @After
    @DisplayName(("Удкление пользователя"))
    public void deleteUser() {
        accessToken = authorizationUser();
        deleteUserAndCheckResponse(accessToken);
    }
}
