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

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
public class AccountServlet extends HttpServlet {

    Hashtable data = new Hashtable();

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

        //get the userid set by LoginServlet
        String userid = (String) request.getAttribute("userid");

        if (userid != null) {

            // userid available. retrieve the data and generate the page.
            String[] records = (String[]) data.get(userid);

            PrintWriter pw = response.getWriter();
            pw.println("<html>");
            pw.println("<head>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<h3>Account Status for " + userid + " at the start of previous three months...</h3><p>");
            for (String record : records) {
                pw.println(record + "<br>");
            }

            pw.println("<br><a href = \"index.html\" >Main Page</a> for the application");
            pw.println("</body>");
            pw.println("</html>");

        } else {

            //No userid. Send login.html to the user.
            //observe the use of relative path.
            request.getRequestDispatcher("/login.html").forward(request, response);
        }
    }

    @Override
    public void init() {
        data.put("ann", new String[]{"01/01/2001 : 1000.00", "01/02/2001 : 1300.00", "01/03/2001 : 900.00"});
        data.put("john", new String[]{"01/01/2001 : 4500.00", "01/02/2001 : 2100.00", "01/03/2001 : 2600.00"});
        data.put("mark", new String[]{"01/01/2001 : 7800.00", "01/02/2001 : 5200.00", "01/03/2001 : 1900.00"});
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
