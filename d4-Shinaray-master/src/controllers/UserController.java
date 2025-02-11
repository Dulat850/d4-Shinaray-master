package controllers;

import controllers.interfaces.IUserController;
import models.users.User;
import repositories.interfaces.IUserRepository;

import java.util.List;

public class UserController implements IUserController {
    private final IUserRepository repo;

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createUser(String name, String email, String password) {
        User user = new User(name, email, password);
        boolean created = repo.createUser(user);
        return (created) ? "User was created" : "User creation failed";
    }

    @Override
    public String getUserById(int id) {
        User user = repo.getUserById(id);
        return (user == null) ? "User was not found" : user.toString();
    }

    @Override
    public String getAllUsers() {
        List<User> users = repo.getAllUsers();
        StringBuilder response = new StringBuilder();
        for (User user : users) {
            response.append(user.toString()).append("\n");
        }
        return response.toString();
    }

    public Boolean login(String email, String password) {
        User user = repo.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful. Welcome, " + user.getName() + "!");
            return true;
        } else {
            System.out.println("Login Failed!");
            return false;
        }
    }

}
