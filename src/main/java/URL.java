public class URL {

    public static final String HOST = "https://stellarburgers.nomoreparties.site/";

    //apiDeletePath
    //Удаление пользователя
    public static final String DELETEPATH = "/api/auth/user";

    //apiGetPath
    //Получение и обновление информации о пользователе
    public static final String APIPATHREQUESTUSERDATA = "/api/auth/user";
    //Получить все заказы
    public static final String APIPATHREQUESTALLORDERS = "/api/orders/all";
    //Получить заказы конкретного пользователя
    public static final String APIPATHREQUESTSPECIFICUSER = "/api/orders";

    //apiPathPath
    //Обновление данных о пользователе
    public static final String PATHCPATHREQUESTUSER = "/api/auth/user ";

    //apiPostPath
    //Создание пользователя
    public static final String CREATEUSER = "/api/auth/register";
    //Авторизация пользователя
    public static final String AUTHORIZATIONUSER = "/api/auth/login ";
    //Создание заказа
    public static final String CREATEORDER = "/api/orders";
}