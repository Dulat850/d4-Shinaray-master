package start;

import controllers.UserController;
import RoleManagement.Role;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final UserController userController;
    private final Scanner scanner = new Scanner(System.in);

    public MyApplication(UserController userController) {
        this.userController = userController;
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to User Management");
        System.out.println("Select one of the following options:");
        System.out.println("1. Get all users");
        System.out.println("2. Get user by id");
        System.out.println("3. Create new user");
        System.out.println("4. Login");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Select an option (0-3): ");
    }

    public void start(Role role) {
        boolean op = true;
        while (op) {
            mainMenu();
            try {
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1 :
                        if(role == Role.ADMIN) {
                            getAllUsersMenu();
                        } else {
                            System.out.println("Access denied! Only admins can get all users.");
                        }
                        break;
                    case 2 :
                        if(role == Role.ADMIN) {
                            getUserByIdMenu();
                        } else{
                            System.out.println("Access denied! Only admins can get user.");
                        }
                        break;
                    case 3 :
                        if(role == Role.ADMIN) {
                            createUserMenu();
                        }else {
                            System.out.println("Access denied! Only admins can create user.");
                        }
                        break;
                    case 0 : {
                        System.out.println("Exiting User Management...");
                        return;
                    }
                    default : System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid option! " + e);
                scanner.nextLine(); // Ignore invalid input
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println("----------------------------------------");
        }
    }

    private void createUserMenu() {
        System.out.println("Please enter name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter email: ");
        String email = scanner.nextLine();
        System.out.println("Please enter password: ");
        String password = scanner.nextLine();

        String response = userController.createUser(name, email, password);
        System.out.println(response);
    }

    private void getUserByIdMenu() {
        System.out.println("Please enter a user id: ");
        int id = scanner.nextInt();

        String response = userController.getUserById(id);
        System.out.println(response);
    }

    private void getAllUsersMenu() {
        String response = userController.getAllUsers();
        System.out.println(response);
    }

    Boolean loginMenu(String email, String password) {
        Boolean response = userController.login(email, password);
        return response;
    }
}
