import io.restassured.response.Response;

public class GetApi extends BaseHttpClient{

    private final String apiPathRequestOrders = "/api/ingredients";
    private final String apiPathRequestUserData = "https://stellarburgers.nomoreparties.site/api/auth/user";
    private final String apiPathRequestAllOrders = "https://stellarburgers.nomoreparties.site/api/orders/all";
    private final String apiPathRequestSpecificUser = "https://stellarburgers.nomoreparties.site/api/orders";

    //Запрос списка ингридиентов
    public Response getRequestIngidients() {
        return doGetRequest(apiPathRequestOrders);
    }

    //Запрос получения данных о пользователе
    public Response getRequestUserData() {
        return doGetRequest(apiPathRequestUserData);
    }

    //Получение всех заказов
    public Response getRequestAllOrders() {
        return doGetRequest(apiPathRequestAllOrders);
    }

    //Получение заказа конкретного пользователя
    public Response getRequestSpecificUser() {
        return doGetRequest(apiPathRequestSpecificUser);
    }
}
