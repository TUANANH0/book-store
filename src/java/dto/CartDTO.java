/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;

/**
 *
 * @author Tuan
 */
public class CartDTO {

    private String orderID;
    private String bookName;
    private int quantity;
    private int total;
    private Date borrowDate;
    private Date returnDate;
    private String userID;
    private String bookID;

    public CartDTO() {
    }

    public CartDTO(String orderID, String bookName, int quantity, int total, Date borrowDate, Date returnDate, String userID, String BookID) {
        this.orderID = orderID;
        this.bookName = bookName;
        this.quantity = quantity;
        this.total = total;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.userID = userID;
        this.bookID = BookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

}
