public class UsersData {

    public static User expectedCreateUser() {
        return new User("asdqwe@dsa.dsa", "123321", "Viktor");
    }

    public static User modyfiedUser() {
        return new User("asdqwes@dsa.dsa", "1233211", "Viktory");
    }

    public static User expectedRegistrationUser() {
        return new User("asdqwe@dsa.dsa", "123321", "");
    }
}
