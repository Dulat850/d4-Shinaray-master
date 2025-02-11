package repositories.interfaces;

import models.users.User;

import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);
    User getUserById(int id);
    List<User> getAllUsers();
    User getUserByEmail(String email);
}
