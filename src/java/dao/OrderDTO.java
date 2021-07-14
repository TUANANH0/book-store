/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Date;



/**
 *
 * @author Tuan
 */
public class OrderDTO {
    private String orderID;
    private String userID;
    private String bookID;
    private int total;

    public OrderDTO() {
    }

    public OrderDTO(String orderID, String userID, String bookID, int total) {
        this.orderID = orderID;
        this.userID = userID;
        this.bookID = bookID;
        this.total = total;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
