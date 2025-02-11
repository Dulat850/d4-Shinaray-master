package models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static int nextId = 0;
    private int cartId;
    private int userId;
    private String bookName;
    private double price;


    public Cart() {
        nextId++;
        cartId = nextId;
    }

    public Cart(int userId, String bookname, double price) {
        setPrice(price);
        setUserId(userId);
        setBookName(bookname);
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookname) {
        this.bookName = bookname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public int getCartId() {
        return cartId;
    }


    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", userId=" + userId +
                ", bookName=" + bookName +
                ", price=" + price +
                '}';
    }
}