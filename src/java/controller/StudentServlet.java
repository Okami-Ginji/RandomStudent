/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myPack.Student;

/**
 *
 * @author Administrator
 */
public class StudentServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet StudentServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         String action = request.getParameter("action");

        if ("generate".equals(action)) {
            generateStudents(request, response);
        } else{
            updateStudent(request, response);
        }
    }

    private void generateStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int numberOfStudents = Integer.parseInt(request.getParameter("numberOfStudents"));
        List<Student> students = new ArrayList<>();

        // Generate random students and add them to the list
        for (int i = 0; i < numberOfStudents; i++) {
            Student student = createRandomStudent(students);
            students.add(student);
        }

        request.setAttribute("students", students);
        request.getRequestDispatcher("student.jsp").forward(request, response);
    }
    
    
    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int rowIndex = Integer.parseInt(request.getParameter("in"));
        Student newStudent = createRandomUpdateStudent(rowIndex);

        List<Student> students = (List<Student>) request.getSession().getAttribute("students");

        students.set(rowIndex-1, newStudent);

        request.setAttribute("students", students);
        request.getRequestDispatcher("student.jsp").forward(request, response);
    }
    private Student createRandomStudent(List<Student> list_o) {
        Random random = new Random();

        int randomId = generateIDOrder(list_o); // Generating a random ID between 1 and 1000
        String randomName = "Student" + random.nextInt(100); // Generating a random name
        boolean randomGender = random.nextBoolean(); // Generating a random gender
        Date randomDOB = new Date(System.currentTimeMillis()- random.nextInt(1000000000)); // Generating a random date of birth
        return new Student(randomId, randomName, randomGender, randomDOB);
    }
    
    private Student createRandomUpdateStudent(int number) {
        Random random = new Random();

        int randomId = number; // Generating a random ID between 1 and 1000
        String randomName = "Student" + random.nextInt(100); // Generating a random name
        boolean randomGender = random.nextBoolean(); // Generating a random gender
        Date randomDOB = new Date(random.nextInt(1000000000)); // Generating a random date of birth
        return new Student(randomId, randomName, randomGender, randomDOB);
    }
    
    public int generateIDOrder(List<Student> list_o) {
        int id = 0;
        if (list_o.isEmpty()) {
            return 1;
        } else {
            for (Student s : list_o) {
                if (s.getId() == list_o.size()) {
                    id = s.getId() + 1;
                }
            }
        }
        return id;
    }
    
    
   
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
