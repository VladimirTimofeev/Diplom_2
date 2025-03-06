import io.restassured.response.Response;

public class DeleteApi extends BaseHttpClient{

    public Response deleteUser(String accessToken) {
        return doDeleteRequest(URL.DELETEPATH, accessToken);
    }
}
