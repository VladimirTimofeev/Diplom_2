import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

public class GetTest {

    private GetApi getApi = new GetApi();
    private CheckStatusCode checkStatusCode = new CheckStatusCode();
    private CheckBodyResponse checkBodyResponse = new CheckBodyResponse();

    private Response response;

    @Step("Отправка запроса заказа конкретного пользователя без токена")
    public Response getOrderSpecificUserWithoutToken() {
        return response = getApi.getRequestSpecificUser();
    }

    @Step("Запрос всех заказов")
    public void getAndCheckAllOrders() {
        response = getApi.getRequestAllOrders();
        checkStatusCode.checkStatusCode200(response);
        checkBodyResponse.checkBodyTegSuccessTrue(response);
    }
    @Step("Check response status code 401")
    public void checkStatusCode401(Response response) {
        checkStatusCode.checkStatusCode401(response);
    }

    @Step("Проверка сообщения, что пользователь не авторизован")
    public void checkBodyMessageNotAuthorizationUser(Response response) {
        checkBodyResponse.checkMessageYouShouldBeAuthorised(response);
    }

    @Test
    @DisplayName("Получение всех заказов")
    public void getAllOrders() {
        getAndCheckAllOrders();
    }

    @Test
    @DisplayName("Получение списка ингридиентов неавторизованного пользователя")
    public void getListIngridientsSpecUserWithoutToken() {
        response = getOrderSpecificUserWithoutToken();
        checkStatusCode401(response);
        checkBodyMessageNotAuthorizationUser(response);
    }
}
