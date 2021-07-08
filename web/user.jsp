<%-- 
    Document   : user
    Created on : Jun 29, 2021, 1:44:55 PM
    Author     : Tuan
--%>

<%@page import="dto.BookDTO"%>
<%@page import="java.util.List"%>
<%@page import="dto.UsersDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <%
            UsersDTO user = (UsersDTO) session.getAttribute("LOGIN_USER");
            List<BookDTO> list = (List<BookDTO>) request.getAttribute("LIST_BOOK");
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
            if (user == null || !"US".equals(user.getRoleID())) {
                response.sendRedirect("login.html");
                return;
            }
        %>
        <h1>Welcome <%= user.getFullName()%>(<%= user.getRoleID()%>).</h1>
        <a href="MainController?action=Logout">Logout</a>
        <form action="MainController">
            Search <input type="text" name="search" value="<%= search%>"/>
            <input type="submit" name="action" value="Search"/>
        </form>
        <%
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Book ID</th>
                    <th>Book Name</th>
                    <th>Quantity</th>
                    <th>Category Name</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (BookDTO dto : list) {
                %>
                <tr>
                    <td><%= count++%></td>
                    <td><%= dto.getBookID()%></td>
                    <td><%= dto.getBookName()%></td>
                    <td><%= dto.getQuantity()%></td>
                    <td><%= dto.getCategoryName()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
                }
            }
        %>

    </body>
</html>
