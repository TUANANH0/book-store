/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Tuan
 */
public class BookDTO {

    private String bookID;
    private String bookName;
    private int quantity;
    private int categoryID;
    private String categoryName;

    public BookDTO() {
    }

    public BookDTO(String bookID, String bookName, int quantity, int categoryID, String categoryName) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.quantity = quantity;
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

}
