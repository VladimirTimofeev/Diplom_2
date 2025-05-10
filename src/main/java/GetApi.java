import io.restassured.response.Response;

public class GetApi extends BaseHttpClient {

    //Запрос получения данных о пользователе
    public Response getRequestUserData(String accessToken) {
        return doGetRequest(accessToken, URL.APIPATHREQUESTUSERDATA);
    }

    //Получение всех заказов
    public Response getRequestAllOrders() {
        return doGetRequest(URL.APIPATHREQUESTALLORDERS);
    }

    //Получение заказа конкретного пользователя
    public Response getRequestSpecificUser() {
        return doGetRequest(URL.APIPATHREQUESTSPECIFICUSER);
    }

    public Response getRequestSpecificUser(String token) {
        return doGetRequest(token, URL.APIPATHREQUESTSPECIFICUSER);
    }
}