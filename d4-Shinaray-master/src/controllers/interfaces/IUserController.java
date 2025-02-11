package controllers.interfaces;

public interface IUserController {
    String createUser(String name, String surname, String gender);
    String getUserById(int id);
    String getAllUsers();
    Boolean login(String email, String password);

}