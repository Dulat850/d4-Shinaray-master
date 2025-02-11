package start;

import data.interfaces.IDB;
import RoleManagement.Role;
import models.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Start {
    private final Scanner scanner = new Scanner(System.in);
    private IDB db;
    public Role role;
    public boolean enter;

    public Start(IDB db) {
        this.db = db;
    }

    public void start() {
        System.out.println("---Welcome to Online Books Shop---");
        System.out.println("Choose an option:");
        System.out.println("1. Enter by User");
        System.out.println("2. Enter by Sorter");
        System.out.println("3. Enter by Manager");
        System.out.println("4. Enter by Admin");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                enterUser();
                role = Role.USER;
                break;
            case 2:
                enter();
                role = Role.SORTER;
                break;

            case 3:
                enter();
                role = Role.MANAGER;
                break;

            case 4:
                enter();
                role = Role.ADMIN;
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void enterUser(){
        System.out.println("Choose an option:");
        System.out.println("1. Sign up");
        System.out.println("2. Login");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                createUserMenu();
                enter = true;
                break;

                case 2:
                    enter = loginUser();
        }
    }

    private void enter() {
        System.out.println("Choose an option:");
        System.out.println("1. Login");
        System.out.println("2. Exist");

        switch (scanner.nextInt()) {
            case 1:
                loginUser();
                break;
            case 2:
                return;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }


    private boolean loginUser(){
        System.out.print("Please enter email: ");
        String email = scanner.nextLine();
        System.out.print("Please enter password: ");
        String password = scanner.nextLine();

        if (loginMenu(email, password)) {
            System.out.println("Login Successful!");
            return true;
            }
        else{
            System.out.println("Login Failed!");
            return false;
        }
    }

    Boolean loginMenu(String email, String password) {
        Boolean response = login(email, password);
        return response;
    }

    public Boolean login(String email, String password) {
        User user = getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful. Welcome, " + user.getName() + "!");
            return true;
        } else {
            System.out.println("Login Failed!");
            return false;
        }
    }
    public User getUserByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try (Connection connection = db.getConnection();  // Создаем соединение в блоке try-with-resources
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")  // Предполагаем, что пароль хранится в базе в хэшированном виде
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }

        return null;
    }




    private void createUserMenu() {
        System.out.println("Please enter name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter email: ");
        String email = scanner.nextLine();
        System.out.println("Please enter password: ");
        String password = scanner.nextLine();

        String response = createUser(name, email, password);
        System.out.println(response);
    }

    private String createUser(String name, String email, String password) {
        User user = new User(name, email, password);
        boolean created = createUser(user);
        return (created) ? "User was created" : "User creation failed";
    }

    public boolean createUser(User user) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql ="INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPassword());

            st.execute();

            return true;
        } catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
        return false;
    }

}
