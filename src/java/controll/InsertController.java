/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controll;

import dao.BookDAO;
import dto.BookDTO;
import dto.BookErrorDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tuan
 */
public class InsertController extends HttpServlet {

    private final String ERRER = "insert.jsp";
    private final String SUCCESS = "insert.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERRER;
        try {
            String bookID = request.getParameter("bookID");
            String bookName = request.getParameter("bookName");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));
            int price = Integer.parseInt(request.getParameter("price"));
            BookDAO dao = new BookDAO();
            BookDTO book = new BookDTO(bookID, bookName, quantity, categoryID, "", price);
            BookErrorDTO bookError = new BookErrorDTO();
            boolean checkUserID = dao.ckeckBookID(bookID);
            if (checkUserID) {
                bookError.setBookIDError("Duplicate BookID" + bookID + "!");
                bookError.setMessageError("Can not insert");
                request.setAttribute("INSERT_ERROR", bookError);
            } else {
                boolean checkCategoryID = dao.checkCategoryID(categoryID);
                if (checkCategoryID) {
                    boolean checkInsert = dao.insertBook(book);
                    if (checkInsert) {
                        url = SUCCESS;
                        bookError.setMessageError("Insert success");
                    } else {
                        bookError.setMessageError("Can not insert");
                    }
                    request.setAttribute("INSERT_ERROR", bookError);
                } else {
                    bookError.setCategoryIDError("CategoryID does not exist");
                    bookError.setMessageError("Can not insert");
                    request.setAttribute("INSERT_ERROR", bookError);
                }
            }
        } catch (Exception e) {
            log("Error at InsertController " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
