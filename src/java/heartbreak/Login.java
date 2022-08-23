/*
Devin Strom
Prof. Charnesky
CIS 2454
Final Project
 */

 /*
This project is based heavily on the sample project from CIS 2454 Summer 2022 lectures weeks 12 & 13 by Prof. Charnesky
 */

package heartbreak;

import static heartbreak.Heartbreak.getSHA;
import static heartbreak.Heartbreak.toHexString;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Login extends HttpServlet {

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

        String action = request.getParameter("action");

        //below login partial actions (login register etc) used from CIS 2454 Summer 2022 Weeks 12 and 13 lectures Prof. Charnesky
        if (action == null) {
            String url = "/login.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
        } else if (action.equalsIgnoreCase("login")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            if (username == null || password == null) {
                String error = "username or password is missing";
                request.setAttribute("error", error);
                String url = "/error.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }

            try {
                String hashedPassword = toHexString(getSHA(password));
                User user = new User(0, username, hashedPassword);

                if (UserModel.login(user)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    response.sendRedirect("Homepage");
                } else {
                    String error = "invalid username or password";
                    request.setAttribute("error", error);
                    String url = "/error.jsp";
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                }

            } catch (Exception ex) {
                exceptionPage(ex, request, response);
            }
        } else if (action.equalsIgnoreCase("register")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            //for some reason, registration will accept blank fields from jsp page without .equals("")
            if (username == null || password == null || username.equals("") || password.equals("")) {
                String error = "username or password missing";
                request.setAttribute("error", error);
                String url = "/error.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }

            try {
                //https://www.geeksforgeeks.org/sha-256-hash-in-java/ from week 12 CIS 2454, Prof. Charnesky
                String hashedPassword = toHexString(getSHA(password));

                User user = new User(0, username, hashedPassword);
                UserModel.addUser(user);
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("Homepage");
            } catch (Exception ex) {
                exceptionPage(ex, request, response);
            }
        }
    }

    //CIS 2454 Summer 2022 weeks 12 and 13 lectures Prof. Charnesky
    private void exceptionPage(Exception ex, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = ex.toString();
        request.setAttribute("error", error);
        String url = "/error.jsp";
        getServletContext().getRequestDispatcher(url).forward(request, response);
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
