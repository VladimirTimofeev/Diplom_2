import io.restassured.response.Response;

public class GetApi extends BaseHttpClient {

    private final String apiPathRequestUserData = "/api/auth/user";
    private final String apiPathRequestAllOrders = "/api/orders/all";
    private final String apiPathRequestSpecificUser = "/api/orders";

    //Запрос получения данных о пользователе
    public Response getRequestUserData(String accessToken) {
        return doGetRequest(accessToken, apiPathRequestUserData);
    }

    //Получение всех заказов
    public Response getRequestAllOrders() {
        return doGetRequest(apiPathRequestAllOrders);
    }

    //Получение заказа конкретного пользователя
    public Response getRequestSpecificUser() {
        return doGetRequest(apiPathRequestSpecificUser);
    }

    public Response getRequestSpecificUser(String token) {
        return doGetRequest(token, apiPathRequestSpecificUser);
    }
}