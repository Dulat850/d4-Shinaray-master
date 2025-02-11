package repositories;

import data.interfaces.IDB;
import models.Book;
import models.Cart;
import models.users.User;
import repositories.interfaces.ICartRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartRepository implements ICartRepository {

    // Метод для получения подключения к базе данных
    private final IDB db;

    public CartRepository(IDB db) {
        this.db = db;
    }

    // Метод для добавления корзины в базу данных
    @Override
    public void addCart(Cart cart) {
        String query = "INSERT INTO carts (user_id, book_name, price) VALUES (?, ?, ?)";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cart.getUserId());
            stmt.setString(2, cart.getBookName());
            stmt.setDouble(3, cart.getPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для добавления книги в корзину
    @Override
    public void addBookToCart(Cart cart, Book book) {
        String query = "UPDATE carts SET book_name = ?, price = ? WHERE cart_id = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setDouble(2, book.getPrice());
            stmt.setInt(3, cart.getCartId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для удаления книги из корзины
    @Override
    public void removeBookFromCart(Cart cart, Book book) {
        String query = "DELETE FROM carts WHERE user_id = ? AND book_name = ?";

        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cart.getUserId());
            stmt.setString(2, book.getTitle());
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Book successfully removed from cart!");
            } else {
                System.out.println("Book not found in cart.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Метод для вычисления общей стоимости корзины
    @Override
    public double calculateTotalCost(Cart cart) {
        String query = "SELECT SUM(price) FROM carts WHERE user_id = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cart.getUserId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    // Метод для очистки корзины
    @Override
    public void clearCart(int userId) {
        String query = "DELETE FROM carts WHERE user_id = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            int affectedRows = stmt.executeUpdate();

            System.out.println("Cart cleared! Rows affected: " + affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Метод для обновления книги в корзине
    @Override
    public void updateBookInCart(Cart cart, Book book) {
        String query = "UPDATE carts SET book_name = ?, price = ? WHERE cart_id = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setDouble(2, book.getPrice());
            stmt.setInt(3, cart.getCartId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для получения книги по ID
    @Override
    public Book getBookById(int id) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT * FROM books WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Book(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }

    // Метод для получения пользователя по ID
    @Override
    public User getUserById(int id) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql ="SELECT * FROM users WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()){
                return new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"));
            }
        }catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }

    // Метод для получения всех корзин
    public List<Book> getBooksInCart(int userId) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT b.book_id, b.name, b.price FROM books b " +
                "JOIN carts c ON b.name = c.book_name " +
                "WHERE c.user_id = ?";

        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                books.add(new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }



    @Override
    public void saveCart(Cart cart) {
        String query = "INSERT INTO carts (user_id, book_name, price) VALUES (?, ?, ?)";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, cart.getUserId());
            stmt.setString(2, cart.getBookName());  // Проверить, что book_name не NULL
            stmt.setDouble(3, cart.getPrice());

            System.out.println("Saving book to cart: " + cart.getBookName());  // Debug
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public Cart getCartByUserId(int userId) {
        String query = "SELECT * FROM carts WHERE user_id = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cart(
                        rs.getInt("user_id"),
                        rs.getString("book_name"),
                        rs.getDouble("price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Cart> getCartItems(int userId) {
        List<Cart> cartItems = new ArrayList<>();
        String query = "SELECT * FROM carts WHERE user_id = ?";

        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cart cart = new Cart(
                        rs.getInt("user_id"),
                        rs.getString("book_name"),
                        rs.getDouble("price")
                );
                cartItems.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItems;
    }


}
