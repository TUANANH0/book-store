/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controll;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tuan
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String SEARCH_CONTROLLER = "SearchController";
    private static final String DELETE_CONTROLLER = "DeleteController";
    private static final String UPDATE_CONTROLLER = "UpdateController";
    private static final String INSERT_CONTROLLER = "InsertController";
    private static final String BORROW_CONTROLLER = "BorrowController";
    private static final String View_CART_CONTROLLER = "ViewCartController";
    private static final String MODIFY_CONTROLLER = "ModifyController";
    private static final String GIVE_BACK_CONTROLLER = "GiveBackController";

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
            HttpSession session = request.getSession();
            String action = request.getParameter("action");
            if ("Login".equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if ("Logout".equals(action)) {
                url = LOGOUT_CONTROLLER;
            } else if ("Search".equals(action)) {
                url = SEARCH_CONTROLLER;
            } else if ("Delete".equals(action)) {
                url = DELETE_CONTROLLER;
            } else if ("Update".equals(action)) {
                url = UPDATE_CONTROLLER;
            } else if ("Insert".equals(action)) {
                url = INSERT_CONTROLLER;
            } else if ("Borrow".equals(action)) {
                url = BORROW_CONTROLLER;
            } else if ("ViewCart".equals(action)) {
                url = View_CART_CONTROLLER;
            } else if ("Modify".equals(action)) {
                url = MODIFY_CONTROLLER;
            }else if ("GiveBack".equals(action)) {
                url = GIVE_BACK_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error at MainController " + e.toString());
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
