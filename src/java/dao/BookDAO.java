/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.BookDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DBUtil;

/**
 *
 * @author Tuan
 */
public class BookDAO {

    public boolean deleteBook(String bookID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtil.getConnetion();
            String sql = "DELETE tblBooks"
                    + " WHERE BookID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, bookID);
            result = stm.executeUpdate() > 0;
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public boolean updateBook(BookDTO book) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtil.getConnetion();
            String sql = "UPDATE tblBooks"
                    + " SET BookID=?, BookName=?, Quantity=?, CategoryID=?"
                    + " WHERE BookID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, book.getBookID());
            stm.setString(2, book.getBookName());
            stm.setInt(3, book.getQuantity());
            stm.setInt(4, book.getCategoryID());
            stm.setString(5, book.getBookID());
            result = stm.executeUpdate() > 0;
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    
    public List<BookDTO> getListBook(String search) throws SQLException {
        List<BookDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnetion();
            if (conn != null) {
                String sql = "SELECT B.BookID, B.BookName, B.Quantity, B.CategoryID, C.CategoryName"
                        + " FROM tblBooks B JOIN tblCategory C ON B.CategoryID = C.CategoryID"
                        + " WHERE BookName LIKE ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String bookID = rs.getString("bookID");
                    String bookName = rs.getString("bookName");
                    int quantity = rs.getInt("Quantity");
                    int categoryID = rs.getInt("CategoryID");
                    String categoryName = rs.getString("CategoryName");
                    list.add(new BookDTO(bookID, bookName, quantity, categoryID, categoryName));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
}
