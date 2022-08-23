/*
Devin Strom
Prof. Charnesky
CIS 2454
Final Project
 */

 /*
This project is based heavily on the sample project from CIS 2454 Summer 2022 lectures weeks 12 & 13 by Prof. Charnesky
 */

//controller

package heartbreak;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Heartbreak extends HttpServlet {

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

        HttpSession session = request.getSession();
        String sessionUsername = (String) session.getAttribute("username");

        if (sessionUsername == null) {
            response.sendRedirect("Login");
            return;
        }
        if (action == null) {
            action = "goToHomePage";
        }

        if (action.equalsIgnoreCase("goToHomePage")) {
            String url = "/Homepage";
            getServletContext().getRequestDispatcher(url).forward(request, response);
        } else if (action.equalsIgnoreCase("updateUser")) {
            String id = request.getParameter("id");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (id == null || username == null || password == null || username.equals("") || password.equals("")) {
                String error = "id or username or password is missing";
                request.setAttribute("error", error);
                String url = "/error.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            } else {
                try {

                    //https://www.geeksforgeeks.org/sha-256-hash-in-java/
                    String hashedPassword = toHexString(getSHA(password));

                    User user = new User(Integer.parseInt(id), username, hashedPassword);

                    UserModel.updateUser(user);

                    //make session username null so that header shows correct name also so user is logged out
                    session.setAttribute("username", null);
                    response.sendRedirect("Login");

                } catch (Exception ex) {
                    exceptionPage(ex, request, response);
                }
            }

        } else if (action.equalsIgnoreCase("deleteUser")) {
            String id = request.getParameter("id");
            if (id == null) {
                String error = "id is missing";
                request.setAttribute("error", error);
                String url = "/error.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }

            try {
                User user = new User(Integer.parseInt(id), "", "");

                UserModel.deleteUser(user);

                response.sendRedirect("Login");   

            } catch (Exception ex) {
                exceptionPage(ex, request, response);
            }
        }
    }

    //below 3 methods copied from CIS 2454 Summer 2022 weeks 12 and 13 lectures by Prof. Charnesky
    private void exceptionPage(Exception ex, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = ex.toString();
        request.setAttribute("error", error);
        String url = "/error.jsp";
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA 
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called 
        // to calculate message digest of an input 
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation 
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value 
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
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
