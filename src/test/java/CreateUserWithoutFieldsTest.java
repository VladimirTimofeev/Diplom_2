import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CreateUserWithoutFieldsTest {

    private final String email;
    private final String password;
    private final String name;

    private PostApi postApi = new PostApi();
    private CheckStatusCode checkStatusCode = new CheckStatusCode();
    private CheckBodyResponse checkBodyResponse = new CheckBodyResponse();

    public CreateUserWithoutFieldsTest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Parameterized.Parameters
    public static Object[][] createUserWithoutRequiresField() {
        return new Object[][]{
                {"rfv@rfv.re", "456654", ""},
                {"tgb@bgt.re", "", "Viktor"},
                {"", "yhn@yhn.re", "Petr"}
        };
    }

    @Test
    @DisplayName("Попытка создания пользователя без одного обязательного поля")
    public void createdUser() {
        User user = new User(email, password, name);
        Response responce = postApi.postCreateUser(user);
        checkStatusCode.checkStatusCode403(responce);
        checkBodyResponse.checkBodyTegSuccessFalse(responce);
        checkBodyResponse.checkMessageAbsenceOfARrequiredFieldForRegistration(responce);
    }
}