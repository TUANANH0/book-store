<%-- 
    Document   : insert
    Created on : Jul 6, 2021, 2:50:24 AM
    Author     : Tuan
--%>

<%@page import="dto.BookErrorDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Book</title>
    </head>
    <body>
        <%
            BookErrorDTO bookError = (BookErrorDTO) request.getAttribute("INSERT_ERROR");
            if(bookError == null){
                bookError = new BookErrorDTO();
            }
        %>
        <form action="MainController">
            Book ID<input type="text" name="bookID" required=""/></br>
            <%= bookError.getBookIDError() %></br>
            Book Name<input type="text" name="bookName" required=""/></br>
            </br>
            Quantity<input type="text" name="quantity" required=""/></br>
            </br>
            Category ID<input type="text" name="categoryID" required=""/></br>
            <%= bookError.getCategoryIDError() %></br>
            <input type="submit" name="action" value="Insert"/>
            <input type="reset" value="Reset"/>
            <%= bookError.getMessageError()%>
        </form>
        <a href="admin.jsp">Back Page</a>
    </body>
</html>
