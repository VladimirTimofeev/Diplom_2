import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetUsersOrder {

    private GetApi getApi = new GetApi();
    private PostApi postApi = new PostApi();
    private DeleteApi deleteApi = new DeleteApi();
    private CheckStatusCode checkStatusCode = new CheckStatusCode();
    private CheckBodyResponse checkBodyResponse = new CheckBodyResponse();
    private Token token = new Token();
    private Response response;

    private String accsessToken;

    @Step("Отправка запроса на создание пользователя и проверка его создания")
    public void createNewUser() {
        response = postApi.postCreateUser(UsersData.expectedCreateUser());
        checkStatusCode.checkStatusCode200(response);
        checkBodyResponse.checkBodyTegSuccessTrue(response);
        checkBodyResponse.checkBodyUserEmail(response, UsersData.expectedCreateUser());
        checkBodyResponse.checkBoduUserName(response, UsersData.expectedCreateUser());
    }

    @Step("Авторизация пользователя")
    public String authorizationUser() {
        response = postApi.authorizationUser(UsersData.expectedRegistrationUser());
        return accsessToken = token.extractionToken(response);
    }

    @Step("Отправка запроса на получение списка заказов конкретного пользователя с токеном")
    public void getOrderSpecificUserWithToken(String accsessToken) {
        response = getApi.getRequestSpecificUser(accsessToken);
        checkStatusCode.checkStatusCode200(response);
        checkBodyResponse.checkBodyTegSuccessTrue(response);
    }

    @Step("Создание заказа c правильным хэшем ингредиентов")
    public void createOrderWithCorrectHash(String accsessToken) {
        response = postApi.postCreateOrder(OrderData.correctHashOrder());
        checkStatusCode.checkStatusCode200(response);
        checkBodyResponse.checkBodyTegSuccessTrue(response);
    }

    @Step("Создание заказа без ингредиентов")
    public void createOrderWithoutHash(String accsessToken) {
        response = postApi.postCreateOrder(OrderData.withoutIngredient());
        checkStatusCode.checkStatusCode400(response);
        checkBodyResponse.checkBodyTegSuccessFalse(response);
    }

    @Step("Создание заказа c неверным хэшем ингредиентов")
    public void createOrderWithIncorrectHash(String accsessToken) {
        response = postApi.postCreateOrder(OrderData.incorrectHashOrder());
        checkStatusCode.checkStatusCode500(response);
    }

    @Step("Удаление пользователя и проверка ответа на его удачное исполнение")
    public void deleteUser(String accsessToken) {
        response = deleteApi.deleteUser(accsessToken);
        checkStatusCode.checkStatusCode202(response);
        checkBodyResponse.checkBodyTegSuccessTrue(response);
        checkBodyResponse.checkMessageDeleteUser(response);
    }

    @Before
    @DisplayName("Создание клинета")
    public void createUser() {
        createNewUser();
    }

    @Test
    @DisplayName("Получение списка заказов авторизованного пользователя")
    public void getOrdersAuthorizationUser() {
        getOrderSpecificUserWithToken(authorizationUser());
    }

    @Test
    @DisplayName("Создание заказа с корректным хэшем ингридиентов")
    public void createOrderCorrectHashAuthorizedUser() {
        accsessToken = authorizationUser();
        createOrderWithCorrectHash(accsessToken);
    }

    @Test
    @DisplayName("Создание заказа с некорректным хэшем ингридиентов")
    public void createOrderIncorrectHashAuthorizedUser() {
        accsessToken = authorizationUser();
        createOrderWithIncorrectHash(accsessToken);
    }

    @Test
    @DisplayName("Создание заказа без ингридиентов")
    public void createOrderWithoutHashAuthorizedUser() {
        accsessToken = authorizationUser();
        createOrderWithoutHash(accsessToken);
    }

    @After
    @DisplayName("Удаление пользователя")
    public void authorizationAndDeleteUser() {
        accsessToken = authorizationUser();
        deleteUser(accsessToken);
    }
}
