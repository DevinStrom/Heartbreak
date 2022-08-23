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

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Follow extends HttpServlet {

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

        //this is how you check if user is logged in to view site
        HttpSession session = request.getSession();
        String sessionUsername = (String) session.getAttribute("username");
        if (sessionUsername == null) {
            response.sendRedirect("Login");
            return;
        }

        String followingUser = sessionUsername;
        String followedUser = request.getParameter("followedUser");
        String follow = request.getParameter("follow");

        FollowModel test = new FollowModel();
        //test.checkFollowed(username, followedUser);
        if (test.checkFollowed(followingUser, followedUser) == true && follow.equals("follow")) {
            //do nothing
            response.sendRedirect(request.getHeader("referer"));
        } else if (test.checkFollowed(followingUser, followedUser) == true && follow.equals("unfollow")) {
            FollowModel.updateFollow(followingUser, followedUser, follow);
            response.sendRedirect(request.getHeader("referer"));
        } else if (test.checkFollowed(followingUser, followedUser) == false && follow.equals("follow")) {
            FollowModel.updateFollow(followingUser, followedUser, follow);
            response.sendRedirect(request.getHeader("referer"));
        } else if (test.checkFollowed(followingUser, followedUser) == false && follow.equals("unfollow")) {
            //do nothing
            response.sendRedirect(request.getHeader("referer"));
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
