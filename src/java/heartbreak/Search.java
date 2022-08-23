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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Search extends HttpServlet {

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

        ArrayList<User> users = UserModel.getUsers();

        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("search")) {
            String username = request.getParameter("user-search");

            if (username == null || username.equals("")) { // this code works, class lecture code == null still accepts blank value...                
                //return to page requested from, code source: https://stackoverflow.com/questions/12013707/servlet-forward-response-to-caller-previous-page
                //anser by timmo Aug 17, 2012
                response.sendRedirect(request.getHeader("referer"));
            } else if (!username.equals("")) {
                for (User user : users) {
                    if (username.equals(user.getUsername())) {
                        ArrayList<Tweet> tweets = TweetModel.getTweetsFromSingleUser(username);
                        request.setAttribute("tweets", tweets);
                        String url = "/Profile?username=" + username;
                        getServletContext().getRequestDispatcher(url).forward(request, response);
                    }
                }

                String noUser = username;
                request.setAttribute("noUser", noUser);
                String url = "/userNotFound.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
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
