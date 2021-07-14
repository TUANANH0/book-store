/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.BookDTO;
import dto.CartDTO;
import java.sql.Connection;
import java.sql.Date;
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
                    + " SET BookID=?, BookName=?, Quantity=?, CategoryID=?, Price = ?"
                    + " WHERE BookID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, book.getBookID());
            stm.setString(2, book.getBookName());
            stm.setInt(3, book.getQuantity());
            stm.setInt(4, book.getCategoryID());
            stm.setString(5, book.getBookID());
            stm.setInt(6, book.getPrice());
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
                String sql = "SELECT B.BookID, B.BookName, B.Quantity, B.CategoryID, C.CategoryName, B.Price"
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
                    int price = rs.getInt("Price");
                    list.add(new BookDTO(bookID, bookName, quantity, categoryID, categoryName, price));
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

    public boolean insertBook(BookDTO book) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtil.getConnetion();
            if (conn != null) {
                String sql = "INSERT INTO tblBooks(BookID, BookName, Quantity, CategoryID, Price)"
                        + " VALUES(?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, book.getBookID());
                stm.setString(2, book.getBookName());
                stm.setInt(3, book.getQuantity());
                stm.setInt(4, book.getCategoryID());
                stm.setInt(5, book.getPrice());
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean ckeckBookID(String bookID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnetion();
            if (conn != null) {
                String sql = "SELECT BookID"
                        + " FROM tblBooks"
                        + " WHERE BookID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, bookID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public boolean checkCategoryID(int categoryID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnetion();
            if (conn != null) {
                String sql = "SELECT CategoryID"
                        + " FROM tblCategory"
                        + " WHERE CategoryID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, categoryID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public boolean insertOrder(OrderDTO order) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtil.getConnetion();
            if (conn != null) {
                String sql = "INSERT INTO tblOrders(orderID, userID, date, borrowDate, returnDate)"
                        + " VALUES(?,?, GETDATE(), GETDATE(), GETDATE() + DAY(7))"
                        + " INSERT INTO tblOrderDetail(userID, orderID, BookID, quantity, total)"
                        + " VALUES(?,?,?,1,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, order.getOrderID());
                stm.setString(2, order.getUserID());
                stm.setString(3, order.getUserID());
                stm.setString(4, order.getOrderID());
                stm.setString(5, order.getBookID());
                stm.setInt(6, order.getTotal());
                result = stm.executeUpdate() > 0;
            }
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

    public boolean checkInsertOrder(String orderID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnetion();
            if (conn != null) {
                String sql = "SELECT orderID"
                        + " FROM tblOrderDetail"
                        + " WHERE orderID LIKE ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + orderID + "%");
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public boolean updateQuantity(String orderID, String bookID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtil.getConnetion();
            if (conn != null) {
                String sql = "UPDATE tblOrderDetail"
                        + " SET quantity = quantity + 1"
                        + " WHERE orderID LIKE ?"
                        + " UPDATE tblOrderDetail"
                        + " SET total = quantity * (SELECT price FROM tblBooks WHERE bookID = ?)"
                        + " WHERE orderID LIKE ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + orderID + "%");
                stm.setString(2, bookID);
                stm.setString(3, "%" + orderID + "%");
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public List<CartDTO> getListCart(String userID) throws SQLException {
        List<CartDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnetion();
            if (conn != null) {
                String sql = "SELECT B.bookName, OD.quantity, OD.total, O.borrowDate, O.returnDate, OD.orderID, OD.bookID"
                        + " FROM tblBooks B JOIN tblOrderDetail OD ON B.bookID = OD.bookID JOIN tblOrders O ON O.orderID = OD.orderID"
                        + " WHERE OD.userID LIKE ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String bookName = rs.getString("bookName");
                    int quantity = rs.getInt("quantity");
                    int total = rs.getInt("total");
                    Date borrowDate = rs.getDate("borrowDate");
                    Date returnDate = rs.getDate("returnDate");
                    String orderID = rs.getString("orderID");
                    String bookID = rs.getString("bookID");
                    list.add(new CartDTO(orderID, bookName, quantity, total, borrowDate, returnDate, userID, bookID));
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

    public boolean modifyCart(String orderID, int quantity, String bookID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtil.getConnetion();
            if (conn != null) {
                String sql = "UPDATE tblOrderDetail"
                        + " SET quantity = ? , total = ? * (SELECT price FROM tblBooks WHERE bookID = ?)"
                        + " WHERE orderID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setInt(2, quantity);
                stm.setString(3, bookID);
                stm.setString(4, orderID);
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean giveBackBook(String orderID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtil.getConnetion();
            if (conn != null) {
                String sql = "DELETE FROM tblOrderDetail WHERE orderID LIKE ?"
                        + " DELETE FROM tblOrders WHERE orderID LIKE ?";
                        
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + orderID + "%");
                stm.setString(2, "%" + orderID + "%");
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
