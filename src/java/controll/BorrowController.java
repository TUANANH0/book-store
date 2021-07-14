/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controll;

import dao.BookDAO;
import dao.OrderDTO;
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
public class BorrowController extends HttpServlet {

    private final String SUCCESS = "SearchController";
    private final String ERROR = "SearchController";

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
        String url = ERROR;
        try {
            String orderID = request.getParameter("orderID");
            String userID = request.getParameter("userID");
            String bookID = request.getParameter("bookID");
            int total = Integer.parseInt(request.getParameter("price"));
            BookDAO dao = new BookDAO();
            OrderDTO dto = new OrderDTO(orderID, userID, bookID, total);
            boolean checkOrderID = dao.checkInsertOrder(orderID);
            if (!checkOrderID) {
                boolean check = dao.insertOrder(dto);
                if (check) {
                    url = SUCCESS;
                    request.setAttribute("ORDER_MESSAGE", "Order success!");
                } else {
                    request.setAttribute("ORDER_MESSAGE", "Order don't success!");
                }
            } else {
                boolean checkQuantity = dao.updateQuantity(orderID, bookID);
                if (checkQuantity) {
                    url = SUCCESS;
                    request.setAttribute("ORDER_MESSAGE", "Order success!");
                }

            }
        } catch (Exception e) {
            log("Error at BorrowController " + e.toString());
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
