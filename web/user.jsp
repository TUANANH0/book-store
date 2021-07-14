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
            String orderMessage = (String) request.getAttribute("ORDER_MESSAGE");
            if (orderMessage == null) {
                orderMessage = "";
            }
            if (search == null) {
                search = "";
            }
            if (user == null || !"US".equals(user.getRoleID())) {
                response.sendRedirect("login.html");
                return;
            }
        %>
        <h1>Welcome <%= user.getFullName()%>(<%= user.getRoleID()%>).</h1>
        <a href="MainController?action=Logout">Logout</a></br>
        <a href="MainController?action=ViewCart&userID=<%= user.getUserID()%>&search=<%= search%>">View Cart</a>
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
                    <th>Price</th>

                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (BookDTO dto : list) {
                %>
            <form action="MainController" method="POST">          
                <tr>
                    <td><%= count++%></td>
                    <td><%= dto.getBookID()%></td>
                    <td><%= dto.getBookName()%></td>
                    <td><%= dto.getQuantity()%></td>
                    <td><%= dto.getCategoryName()%></td>
                    <td><%= dto.getPrice()%></td>
                    <td>
                        <input type="submit" name="action" value="Borrow"/>
                        <input type="hidden" name="orderID" value="<%= user.getUserID() + dto.getBookID()%>"/>
                        <input type="hidden" name="bookID" value="<%= dto.getBookID()%>"/>
                        <input type="hidden" name="bookName" value="<%= dto.getBookName()%>"/>
                        <input type="hidden" name="quantity" value="<%= dto.getQuantity()%>"/>
                        <input type="hidden" name="categoryName" value="<%= dto.getCategoryName()%>"/>
                        <input type="hidden" name="price" value="<%= dto.getPrice()%>"/>
                        <input type="hidden" name="userID" value="<%= user.getUserID()%>"/>
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
    <%= orderMessage%>
</body>
</html>
