import io.restassured.response.Response;

public class GetSteps {

    private GetApi getApi = new GetApi();
    private Response response;

    //Возврат ответа на запрос списка ингридиентов
    public Response responseIngridients() {
        return response = getApi.getRequestIngidients();
    }

    //Возврат ответа на запрос получения данных о пользователе
    public Response responseUserData() {
        return response = getApi.getRequestUserData();
    }

    //Возврат ответа на запрос всех заказов
    public Response responseAllOrders() {
        return response = getApi.getRequestAllOrders();
    }

    //Возврат ответа на запрос получения заказа конкретного пользователя
    public Response responseSpecificUser() {
        return response = getApi.getRequestSpecificUser();
    }
}