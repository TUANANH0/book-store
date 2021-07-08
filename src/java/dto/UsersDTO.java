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
public class UsersDTO {
    private String userID;
    private String password;
    private String fullName;
    private String roleID;

    public UsersDTO() {
    }

    public UsersDTO(String userID, String password, String fullName, String roleID) {
        this.userID = userID;
        this.password = password;
        this.fullName = fullName;
        this.roleID = roleID;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
    
    
}
