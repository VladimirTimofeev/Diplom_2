import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

public class UserTest {

    private PostApi postApi = new PostApi();
    private CheckStatus checkStatus = new CheckStatus();
    private CheckBodyResponse checkBodyResponse = new CheckBodyResponse();
    private Response response;

    private String accsessToken;
    private String refreshToken;

    @Step("Отправка запроса на создание пользователя")
    public Response createUser() {
        return response = postApi.postCreateUser(UsersData.expectedUser());
    }

    @Step("Check response status code 200")
    public void checkStatusCode200(Response response) {
        checkStatus.checkStatusCode200(response);
    }

    @Step("Проверка тега success - true")
    public void chectTegSuccessTrue(Response response) {
        checkBodyResponse.checkDodyTegSuccessTrue(response);
    }

    @Step("Проверка корректности email созданного пользователя")
    public void checkUserEmail(Response response, User user) {
        checkBodyResponse.checkBodyUserEmail(response, user);
    }

    @Step("Проверка корректности имени созданного пользователя")
    public void checkUserName(Response response, User user) {
        checkBodyResponse.checkBoduUserName(response, user);
    }

    @Step("Извлечение accessToken и refreshToken")
    public void getAccessToken(Response response) {
        accsessToken = response.jsonPath().getString("accessToken");
        refreshToken = response.jsonPath().getString("refreshToken");
    }

    @Step("Преобразование токена")
    public String substringToken(String accsessToken) {
        return accsessToken.substring(7);
    }

    @Test
    @DisplayName("Создание нового пользователя")
    public void createNewUser() {
        response = createUser();
        checkStatusCode200(response);
        chectTegSuccessTrue(response);
        checkUserEmail(response, UsersData.expectedUser());
        checkUserName(response, UsersData.expectedUser());

        getAccessToken(response);
        System.out.println(accsessToken);
        System.out.println(refreshToken);
        accsessToken = substringToken(accsessToken);
        System.out.println(accsessToken);
    }


}
