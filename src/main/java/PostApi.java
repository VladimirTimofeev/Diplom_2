import io.restassured.response.Response;

public class PostApi extends BaseHttpClient {

    //Создание пользователя
    public Response postCreateUser(User user) {
        return doPostRequest(URL.CREATEUSER, user);
    }

    //Авторизация пользователя
    public Response authorizationUser(User user) {
        return doPostRequest(URL.AUTHORIZATIONUSER, user);
    }

    //Создание заказа
    public Response postCreateOrder(Order order) {
        return doPostRequest(URL.CREATEORDER, order);
    }
}