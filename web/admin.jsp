<%-- 
    Document   : admin
    Created on : Jun 24, 2021, 3:14:11 PM
    Author     : Tuan
--%>

<%@page import="java.util.List"%>
<%@page import="dto.BookDTO"%>
<%@page import="dto.UsersDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <%
            UsersDTO user = (UsersDTO) session.getAttribute("LOGIN_USER");
            List<BookDTO> list = (List<BookDTO>) request.getAttribute("LIST_BOOK");
            String search = request.getParameter("search");
            String errorMessage = (String) request.getAttribute("ERROR_MESSAGE");
            if (search == null) {
                search = "";
            }
            if(errorMessage == null){
                errorMessage = "";
            }
            if (user == null || !"AD".equals(user.getRoleID())) {
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
                    <th>Category ID</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (BookDTO dto : list) {
                %>
            <form action="MainController">
                <tr>
                    <td><%= count++%></td>
                    <td>
                        <%= dto.getBookID()%>
                    </td>
                    <td>
                        <input type="text" name="bookName" value="<%= dto.getBookName()%>"
                    </td>
                    <td>
                        <input type="text" name="quantity" value="<%= dto.getQuantity()%>"/>
                    </td>
                    <td>
                        <input type="text" name="categoryID" value="<%= dto.getCategoryID()%>"/>
                    </td>
                    <td>
                        <a href="MainController?action=Delete&bookID=<%= dto.getBookID()%>&search=<%= search%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" name="action" value="Update"/>
                        <input type="hidden" name="bookID" value="<%= dto.getBookID()%>"/>
                        <input type="hidden" name="search" value="<%= search%>"/>
                    </td>
                </tr>
            </form>
            <%
                }
            %>
        </tbody>
    </table>
    <%
            }
        }
    %>
    <%= errorMessage %>
</body>
</html>
