package repositories.interfaces;

import models.Book;
import models.Cart;
import models.users.User;

import java.util.List;

public interface ICartRepository {
    void addCart(Cart cart);
    void addBookToCart(Cart cart, Book book);
    void removeBookFromCart(Cart cart, Book book);
    double calculateTotalCost(Cart cart);
    void clearCart(int user_id);
    void updateBookInCart(Cart cart, Book book);
    Book getBookById(int bookId);
    User getUserById(int userId);
    Cart getCartByUserId(int userId);
    void saveCart(Cart cart) ;
    List<Cart> getCartItems(int userId);
    List<Book> getBooksInCart(int userId);
}