package controllers;

import controllers.interfaces.ICartController;
import models.books.Book;
import models.Cart;
import models.users.User;
import repositories.interfaces.ICartRepository;

import java.util.List;

public class CartController implements ICartController {
    private final ICartRepository cartRepository;

    public CartController(ICartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void addBookToCart(Book book, int userId) {
        if (book == null) {
            System.out.println("Error: Book not found!");
            return;
        }

        System.out.println("Adding book: " + book.getTitle() + " to cart for user ID: " + userId);

        Cart cart = new Cart(userId, book.getTitle(), book.getPrice());
        cartRepository.saveCart(cart);
        System.out.println("Book added to cart successfully!");
    }


    @Override
    public void removeBookFromCart(Book book, int userId) {
        Cart cart = cartRepository.getCartByUserId(userId);

        if (cart == null) {
            System.out.println("Cart not found for user ID: " + userId);
            return;
        }

        // Проверяем, есть ли эта книга в корзине
        if (!cart.getBookName().equals(book.getTitle())) {
            System.out.println("Book not found in cart!");
            return;
        }

        // Вызываем метод удаления из репозитория
        cartRepository.removeBookFromCart(cart, book);
        System.out.println("Book removed from cart!");
    }


    @Override
    public Cart getCart() {
        return null;
    }


    @Override
    public double getTotalCost(int userId) {
        Cart cart = cartRepository.getCartByUserId(userId);
        return (cart != null) ? cartRepository.calculateTotalCost(cart) : 0;
    }

    @Override
    public void clearCart(int userId) {
        Cart cart = cartRepository.getCartByUserId(userId);
        if (cart != null) {
            cartRepository.clearCart(userId);
            System.out.println("Cart cleared!");
        } else {
            System.out.println("Cart not found for user ID: " + userId);
        }
    }

    @Override
    public void updateBookInCart(Book book, int userId) {
        Cart cart = cartRepository.getCartByUserId(userId);
        if (cart == null) {
            System.out.println("Cart not found for user ID: " + userId);
            return;
        }
        cartRepository.updateBookInCart(cart, book);
        cartRepository.saveCart(cart);
        System.out.println("Book updated in cart!");
    }

    @Override
    public List<Cart> getCartItems(int userId) {
        return cartRepository.getCartItems(userId);
    }


    @Override
    public Book getBookById(int bookId) {
        return cartRepository.getBookById(bookId);
    }

    @Override
    public User getUserById(int userId) {
        return cartRepository.getUserById(userId);
    }
}
