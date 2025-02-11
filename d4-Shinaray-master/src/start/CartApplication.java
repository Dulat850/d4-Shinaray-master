package start;

import RoleManagement.Role;
import controllers.interfaces.ICartController;
import models.Book;
import models.Cart;
import models.users.User;

import java.util.List;
import java.util.Scanner;

public class CartApplication {
    private final ICartController cartController;
    private final Scanner scanner;

    public CartApplication(ICartController cartController) {
        this.cartController = cartController;
        this.scanner = new Scanner(System.in);
    }

    public void start(Role role) {
        while (true) {
            System.out.println("Welcome to the Cart Application!");
            System.out.println("Choose an option:");
            System.out.println("1. Add book to cart");
            System.out.println("2. Remove book from cart");
            System.out.println("3. View cart");
            System.out.println("4. View total cost");
            System.out.println("5. Clear cart");
            System.out.println("0. Exit");
            System.out.print("Enter your option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if(role == Role.COSTUMER) {
                        addBookToCart();
                    } else {
                        System.out.println("Invalid option! This option work only available for COSTUMER.");
                    }
                    break;
                case 2:
                    if(role == Role.COSTUMER) {
                        removeBookFromCart();
                    } else {
                        System.out.println("Invalid option! This option work only available for COSTUMER.");
                    }
                    break;
                case 3:
                    if(role == Role.COSTUMER) {
                        viewCart();
                    } else {
                        System.out.println("Invalid option! This option work only available for COSTUMER.");
                    }
                    break;
                case 4:
                    if(role == Role.COSTUMER) {
                        viewTotalCost();
                    } else {
                        System.out.println("Invalid option! This option work only available for COSTUMER.");
                    }
                    break;
                case 5:
                    if(role == Role.COSTUMER) {
                        clearCart();
                    } else {
                        System.out.println("Invalid option! This option work only available for COSTUMER.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addBookToCart() {
        System.out.println("Enter user ID to add to cart:");
        int userId = getUserInputAsInt();
        User user = getUserById(userId);
        if (user == null) {
            System.out.println("User not found! Try again.");
            return;
        }

        System.out.println("Enter book ID to add to cart:");
        int bookId = getUserInputAsInt();

        Book book = getBookById(bookId);
        if (book == null) {
            System.out.println("Book not found! Please enter a valid book ID.");
            return;
        }

        cartController.addBookToCart(book, userId);
        System.out.println("Book added to cart!");
    }

    private void removeBookFromCart() {
        System.out.println("Enter book ID to remove from cart:");

        int bookId = getUserInputAsInt();
        Book book = getBookById(bookId);

        if (book != null) {
            System.out.println("Enter user ID:");
            int userId = getUserInputAsInt(); // Получаем ID пользователя

            cartController.removeBookFromCart(book, userId);
            System.out.println("Book removed from cart!");
        } else {
            System.out.println("Book not found!");
        }
    }


    private void viewCart() {
        System.out.println("Enter user ID to view cart:");
        int userId = getUserInputAsInt();

        List<Cart> cartItems = cartController.getCartItems(userId);

        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Your Cart:");
            for (Cart item : cartItems) {
                System.out.println(item);
            }
        }
    }


    private void viewTotalCost() {
        System.out.println("Enter user ID to view total cost:");
        int userId = getUserInputAsInt();
        double totalCost = cartController.getTotalCost(userId);
        System.out.println("Total Cost for user " + userId + ": " + totalCost);
    }

    private void clearCart() {
        System.out.print("Enter user ID to view cart: ");
        int userId = getUserInputAsInt();
        cartController.clearCart(userId);
        System.out.println("Cart cleared!");
    }

    private Book getBookById(int bookId) {
        return cartController.getBookById(bookId);
    }

    private User getUserById(int userId) {
        return cartController.getUserById(userId);
    }

    private int getUserInputAsInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
}
