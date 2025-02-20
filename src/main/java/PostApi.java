import io.restassured.response.Response;

public class PostApi extends BaseHttpClient {

    private final String createUser = "/api/auth/register";

    //Создание пользователя
    public Response postCreateUser(User user) {
        return doPostRequest(createUser, user);
    }
}
