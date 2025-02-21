import io.restassured.response.Response;

public class DeleteApi extends BaseHttpClient{

    private final String deletePath = "/api/auth/user";

    public Response deleteUser(String accessToken) {
        return doDeleteRequest(deletePath, accessToken);
    }
}
