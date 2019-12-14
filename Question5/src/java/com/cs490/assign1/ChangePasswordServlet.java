/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs490.assign1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
public class ChangePasswordServlet extends HttpServlet {

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
        doPost(request, response);
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

        HttpSession httpSession = request.getSession(true);

        Hashtable users = (Hashtable) httpSession.getAttribute("users");

        if (users == null) {
            System.out.println("User List in Empty, add to session");
            users = new Hashtable();
            users.put("ann", "aaa");
            users.put("john", "jjj");
            users.put("mark", "mmm");

            httpSession.setAttribute("users", users);
        }

        // Get all parameter values from html form
        String userid = request.getParameter("userid");
        String oldPassword = request.getParameter("oldPassword");
        String newPass = request.getParameter("newPass");
        String confirmNewPass = request.getParameter("confirmNewPass");

        //Get the response PrintWriter to send html back to the user's browser
        PrintWriter printWriter = response.getWriter();

        if (userid != null && oldPassword != null && oldPassword.equals(users.get(userid))) {
            // user at least exists and can change password
            if (newPass != null && confirmNewPass != null && newPass.equals(confirmNewPass)) {
                // new password entries not empty and they match as well
                users.remove(userid); // Remove the old data about user
                users.put(userid, newPass); // Insert new data about user
                httpSession.setAttribute("users", users); //Add to the httpSession

                printWriter.println("<h4>Password Changed Successful</h4>");
                request.getRequestDispatcher("/index.html").include(request, response);
            } else {
                printWriter.println("<h4>Password Changed Unsuccessful</h4>");
                request.getRequestDispatcher("/index.html").include(request, response);
                System.out.println("New Passwords are empty or donot match!");
            }

        } else {
            printWriter.println("<h4>Password Changed Unsuccessful</h4>");
            request.getRequestDispatcher("/index.html").include(request, response);
            System.out.println("User doesn't exist in our records");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
