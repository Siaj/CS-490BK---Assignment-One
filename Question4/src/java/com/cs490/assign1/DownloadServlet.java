/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs490.assign1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
public class DownloadServlet extends HttpServlet {

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

        // Get pdf path defined in the web.xml file
        String pdfPath = getServletContext().getInitParameter("pdf-files"); // "/WEB-INF/pdf-files/"
        System.out.println(pdfPath);

        String path = "/index.html";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);

        // Get file name entered by user from the html page
        String filename = request.getParameter("filename");
        System.out.println(filename.trim());
        String convertPdfName;

//            File folder = new File("D:/U of R/05. Spring-Summer 2019/CS 490BK - Server-side Web Application/02. Assignments/assign1/Solution/Question4/web/pdf-files/");

        /*1. The below code gets the complete path of where the pdf files are located in the project
          2. It gets an array of all the file names and directories (if they exist)*/
        File folder = new File(this.getServletContext().getRealPath(pdfPath));
        File[] listOfFiles = folder.listFiles();
        System.out.println("Path: " + this.getServletContext().getRealPath(pdfPath) + "\\");

        // The trim() function removes all spacings in filename and hence can correctly verify name entered by user
        if (filename.trim().equals("") || filename.trim().length() == 0) {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Blank Filename</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Please enter a valid filename</h1>");
            out.println("</body>");
            out.println("</html>");
            dispatcher.include(request, response);
        } else { // When it is verified that the filename entered is not empty or just spaces

            // Allows for the user to enter a file with the .pdf extention or not and still be able to get the file as long as it exists!
            if (filename.contains(".pdf")) {
                convertPdfName = filename.split("\\.")[0];
                System.out.println(filename.split("\\.")[1]);
            } else {
                convertPdfName = filename;
            }

            boolean found = false;
            // Loop through the array of files in directory to find a match with what user entered in form
            for (File listOfFile : listOfFiles) {
                //Condition to check if the item in folder is a file or directory and also checks that the pdf name entered exists 
                if (listOfFile.isFile() && (listOfFile.getName()).contains(convertPdfName + ".pdf")) {
                    found = true;
                    System.out.println("File exists here, HURRAY!!! " + convertPdfName + ".pdf");
                    break;

                    // If the file entered is found, set found = true and then exit the loop
                }
            }

            if (found) {
                // If file found, then download to the user browser
                String filePath = this.getServletContext().getRealPath(pdfPath) + "\\" + convertPdfName + ".pdf";
                File downloadFile = new File(filePath);
                FileInputStream inputStream = new FileInputStream(downloadFile);

                String mimeType = getServletContext().getMimeType(filePath);
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }
                System.out.println("MIME type: " + mimeType);

                response.setContentType(mimeType);
                response.setContentLength((int) downloadFile.length());

                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                response.setHeader(headerKey, headerValue);

                try (OutputStream outStream = response.getOutputStream()) {
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outStream.write(buffer, 0, bytesRead);
                    }

                    inputStream.close();
                    outStream.flush();
                    outStream.close();
                }
            } else {
                // The pdf does not exist and an error message need to be sent to the user
                PrintWriter out = response.getWriter();
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Invalid Filename</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Filename: " + convertPdfName + ".pdf not valid</h1>");
                out.println("</body>");
                out.println("</html>");
                dispatcher.include(request, response);
            }

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
//        processRequest(request, response);
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
