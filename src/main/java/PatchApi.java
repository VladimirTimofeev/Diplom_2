import io.restassured.response.Response;

public class PatchApi extends BaseHttpClient {

    private final String patchPathRequestOUser = "/api/auth/user ";

    public Response patchRequestModifyUser(String accessToken, User user) {
        return doPatchRequest(accessToken, user, patchPathRequestOUser);
    }

    public Response patchRequestModifyUser(User user) {
        return doPatchRequest(user, patchPathRequestOUser);
    }
}
