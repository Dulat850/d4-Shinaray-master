package controllers.interfaces;

import models.books.Book;
import models.Cart;
import models.users.User;

import java.util.List;

public interface ICartController {

    // Метод для добавления книги в корзину
    void addBookToCart(Book book, int userId);

    // Метод для удаления книги из корзины
    void removeBookFromCart(Book book, int userId);

    // Метод для получения текущей корзины
    Cart getCart();

    // Метод для получения общей стоимости корзины
    double getTotalCost(int userId);

    // Метод для очистки корзины
    void clearCart(int userId);

    // Метод для обновления количества книги в корзине
    void updateBookInCart(Book book, int userId);

    // Метод для получения книги по ID
    Book getBookById(int bookId);

    // Метод для получения пользователя по ID
    User getUserById(int userId);

    List<Cart> getCartItems(int userId);
}
