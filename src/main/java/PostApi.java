import io.restassured.response.Response;

public class PostApi extends BaseHttpClient {

    private final String createUser = "/api/auth/register";
    private final String authorizationUser = "/api/auth/login ";
    private  final String createOrder = "/api/orders";

    //Создание пользователя
    public Response postCreateUser(User user) {
        return doPostRequest(createUser, user);
    }

    //Авторизация пользователя
    public Response authorizationUser(User user) {
        return doPostRequest(authorizationUser, user);
    }

    //Создание заказа
    public Response postCreateOrder(Order order) {
        return doPostRequest(createOrder, order);
    }
}