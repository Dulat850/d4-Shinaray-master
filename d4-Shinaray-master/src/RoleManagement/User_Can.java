package RoleManagement;

import models.users.User;

public class User_Can extends User {
    private boolean login = true;
    private boolean getAllBooks = true;
    private boolean getBooksById = true;
    private boolean getBooksByName = true;
    private boolean createBook = true;
    private  boolean addBookToCart = true;
    private boolean deleteBookFromCart = true;
    private boolean updateBookFromCart = true;
    private boolean getTotalCost = true;
    private boolean clearCart = true;
    private boolean Viewcart = true;
}
