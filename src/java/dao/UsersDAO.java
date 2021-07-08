/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.BookDTO;
import dto.UsersDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.spi.DirStateFactory;
import util.DBUtil;

/**
 *
 * @author Tuan
 */
public class UsersDAO {

    public UsersDTO checkLogin(String userID, String password) throws SQLException {
        UsersDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnetion();
            if (conn != null) {
                String sql = "SELECT U.fullName, R.roleName"
                        + " FROM tblUsers U JOIN tblRole R ON U.roleID = R.roleID"
                        + " WHERE userID=? AND password=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleName");
                    user = new UsersDTO(userID, password, fullName, roleID);
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
        return user;
    }

    
}
