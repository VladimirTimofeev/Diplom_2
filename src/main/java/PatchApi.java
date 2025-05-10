import io.restassured.response.Response;

public class PatchApi extends BaseHttpClient {

    public Response patchRequestModifyUser(String accessToken, User user) {
        return doPatchRequest(accessToken, user, URL.PATHCPATHREQUESTUSER);
    }

    public Response patchRequestModifyUser(User user) {
        return doPatchRequest(user, URL.PATHCPATHREQUESTUSER);
    }
}
