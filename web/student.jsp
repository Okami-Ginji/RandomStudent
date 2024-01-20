<%@page import="java.util.ArrayList"%>
<%@ page import="myPack.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student List</title>
</head>
<body>

<%
    List<Student> students = (List<Student>) request.getAttribute("students");

    // If students is not available in request attribute, try retrieving it from the session
    if (students == null) {
        students = (List<Student>) request.getSession().getAttribute("students");
    }

    // If students is still null, initialize an empty list
    if (students == null) {
        students = new ArrayList<>();
    }

    // Store the updated student list in the session
    request.getSession().setAttribute("students", students);
%>

<h2>Student List</h2>



<form action="StudentServlet" method="post">
    <label for="numberOfStudents">Number of Students:</label>
    <input type="text" id="numberOfStudents" name="numberOfStudents">
    <input type="submit" name="action" value="generate">
</form>

<% 
    if(!students.isEmpty()) {
%>
    <table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Gender</th>
        <th>DOB</th>
    </tr>

    <% for (int i = 0; i < students.size(); i++) { %>
        <tr>
            <td><%= students.get(i).getId() %></td>
            <td><%= students.get(i).getName() %></td>
            <td><%= students.get(i).isGender() %></td>
            <td><%= students.get(i).getDOB() %></td>
            <td><form action="StudentServlet" method="post">
                    <input type="submit" name="action" value="update" >
                    <input name="in" style="display: none" value=<%= students.get(i).getId() %> >
            </form></td>
        </tr>
    <% } %>
    </table>
<%
    }
    
%>


</body>
</html>
