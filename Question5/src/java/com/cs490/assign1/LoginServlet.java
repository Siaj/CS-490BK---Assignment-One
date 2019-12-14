/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs490.assign1;

import java.io.IOException;
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
public class LoginServlet extends HttpServlet {

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
//        processRequest(request, response);
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

        String userid = request.getParameter("userid");
        String password = request.getParameter("password");

        HttpSession httpSession = request.getSession(true);

        Hashtable users = (Hashtable) httpSession.getAttribute("users");

        if (users == null) {
            users = new Hashtable();
            users.put("ann", "aaa");
            users.put("john", "jjj");
            users.put("mark", "mmm");

            httpSession.setAttribute("users", users);
        }

        if (userid != null && password != null && password.equals(users.get(userid))) {
            //set userid in request to pass it on to AccountServlet
            request.setAttribute("userid", userid);

            //userid,password verified. Forward request to AccountServlet.
            //observe the use of absolute path.
            request.getRequestDispatcher("/AccountServlet").forward(request, response);
        } else {
            //Unable to login. Send login.html to the user.
            //observe the use of relative path.
            request.getRequestDispatcher("/login.html").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
