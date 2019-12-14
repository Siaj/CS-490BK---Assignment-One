/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs490.assign1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
public class FamousQuoteServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FamousQuoteServlet</title>");
            out.println("</head>");
            out.println("<body>");

//            Get the servlets' contexts so as to get their initialized parameter values 
            ServletContext context = getServletConfig().getServletContext();
            System.out.println(getInitParameter("famousQuote"));
            System.out.println(getInitParameter("greetings"));

            Enumeration e = getInitParameterNames();
            Enumeration e1 = context.getInitParameterNames();

            //<editor-fold defaultstate="collapsed" desc="For development testing(s)">
            while (e.hasMoreElements()) {
                System.out.println("Servlet: " + e.nextElement());
            }
            while (e1.hasMoreElements()) {
                System.out.println("Context: " + e1.nextElement());
            }//</editor-fold>

            String famousQuote = getInitParameter("famousQuote");
            String greetings = getInitParameter("greetings");
            if (greetings == null) {
                greetings = "no greetings";
            }
            
            String accessContextParam = context.getInitParameter("forAll");

            out.println("<h3>" + famousQuote + "</h3>");
            out.println("<h3>" + greetings + "</h3>");
            out.println("<h3>" + accessContextParam + "</h3>");

            out.println("</body>");
            out.println("</html>");
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
