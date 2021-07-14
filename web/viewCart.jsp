<%-- 
    Document   : viewCart
    Created on : Jul 15, 2021, 12:30:54 AM
    Author     : Tuan
--%>

<%@page import="dto.UsersDTO"%>
<%@page import="dto.CartDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
    </head>
    <body>
        <%
            String message = (String)request.getAttribute("GIVEBACK_MESSAGE");
            if(message == null){
                message = "";
            }
            List<CartDTO> list = (List<CartDTO>) session.getAttribute("LIST_CART");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Book Name</th>
                    <th>Quantity</th>
                    <th>Borrow Date</th>
                    <th>Return Date</th>
                    <th>Total</th>
                    <th>Modify</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (CartDTO dto : list) {
                %>
            <form action="MainController">
                <tr>
                    <td><%=count++%></td>
                    <td><%= dto.getBookName()%></td>
                    <td>
                        <input type="text" name="quantity" value="<%= dto.getQuantity()%>"
                    </td>
                    <td><%= dto.getBorrowDate()%></td>
                    <td><%= dto.getReturnDate()%></td>
                    <td><%= dto.getTotal()%></td>
                    <td>
                        <input type="submit" name="action" value="Modify"/>
                        <input type="hidden" name="orderID" value="<%= dto.getOrderID()%>"/>
                        <input type="hidden" name="userID"  value="<%= dto.getUserID()%>"/>
                        <input type="hidden" name="bookID" value="<%= dto.getBookID()%>"
                    </td>
                    <td>
                        <a href="MainController?action=GiveBack&orderID=<%= dto.getOrderID()%>&userID=<%= dto.getUserID()%>">Give Back</a>
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
    <%=message%>
    <a href="user.jsp">Back User Page</a>
</body>
</html>
