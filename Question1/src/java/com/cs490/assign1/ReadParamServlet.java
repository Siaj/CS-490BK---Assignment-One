/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs490.assign1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.*;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
public class ReadParamServlet extends HttpServlet {

    protected void returnResults(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Reading All Request Parameters</title>");
            out.println("<style>table, th, td {\n"
                    + "  border: 1px solid black;\n"
                    + "}table, th, td {\n"
                    + "  border: 1px solid black;\n"
                    + "}</style>");
            out.println("</head>");
            out.println("<body style = \"background: none\">");
            out.println("<h1>Reading All Request Parameters</h1>");
            out.println("<table><tr style = \"background: gold\"><th>Parameter Name</th><th>Parameter Value(s)</th></tr>");

            Enumeration<String> e = request.getParameterNames();
            while (e.hasMoreElements()) {
                String paramName = e.nextElement();
                String paramValue = request.getParameter(paramName);
                if (paramValue.equals("")) {
                    paramValue = "<i>No Value</i>";
                }

                if (paramName.equals("cardNum")) {
                    String[] output = request.getParameterValues(paramName);
                    paramValue = "<ul><li>" + output[0] + "</li><li>" + output[1] + "</li></ul>";
                }
                out.println("<tr><td>" + paramName + "</td><td>" + paramValue + "</td></tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        returnResults(request, response);
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
