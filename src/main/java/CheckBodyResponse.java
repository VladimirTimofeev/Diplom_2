import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CheckBodyResponse {

    //Проверка сообщения, что пользователь не выторизован
    public void checkMessageNotAuthorizationUser(Response response) {
        response.then().assertThat()
                .body("message", equalTo("You should be authorised"));
    }

    //Проверка сообщения об удачном удалении пользователя
    public void checkMessageDeleteUser(Response response) {
        response.then().assertThat()
                .body("message", equalTo("User successfully removed"));
    }

    //Проверка сообщения уже созданном пользователе
    public void checkMessageAboutCreatedUser(Response response) {
        response.then().assertThat()
                .body("message", equalTo("User already exists"));
    }

    //Проверка ответа отсутствия обязательного поля для регистрации
    public void checkMessageAbsenceOfARrequiredFieldForRegistration(Response response) {
        response.then().assertThat()
                .body("message", equalTo("Email, password and name are required fields"));
    }

    //Проверка ответа с неверными данными клиента при логировании
    public void chechMessageLoginOrPasswordIncorrect(Response response) {
        response.then().assertThat()
                .body("message", equalTo("email or password are incorrect"));
    }


    //Проверка тега ответа TRUE
    public void checkBodyTegSuccessTrue(Response response) {
        response.then().assertThat()
                .body("success", equalTo(true));
    }

    //Проверка тега ответа FALSE
    public void checkBodyTegSuccessFalse(Response response) {
        response.then().assertThat()
                .body("success", equalTo(false));
    }

    public void checkBodyUserEmail(Response response, User user) {
        response.then().assertThat()
                .body("user.email", equalTo(user.getEmail()));
    }

    public void checkBoduUserName(Response response, User user) {
        response.then().assertThat()
                .body("user.name", equalTo(user.getName()));
    }
}