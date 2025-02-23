import io.restassured.response.Response;

public class Token {

    private String accessToken;

    public String extractionToken(Response response) {
        accessToken = response.jsonPath().getString("accessToken");
        return accessToken.substring(7);
    }
}