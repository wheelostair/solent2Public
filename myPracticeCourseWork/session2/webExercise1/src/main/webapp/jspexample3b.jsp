<%-- 
    Document   : jspexample3
    Created on : 22-Jul-2021, 11:16:28
    Author     : admin
    THIS EXAMPLE SHOWS HOW OBJECTS CAN BE STORED IN THE SESSION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.solent.ood.webexercise1.model.User" %>
<%
    // retrieve the stored users list from the session
    List<User> users = (List<User>) session.getAttribute("users");
    if (users == null) {
        users = new ArrayList<User>();
        session.setAttribute("users", users);
    }

    String name = request.getParameter("userName");
    String address = request.getParameter("userAddress");
    String index = request.getParameter("index");
    

    // find what action to perform on the page
    String action = request.getParameter("action");

    if ("removeUser".equals(action)) {
        int i = Integer.parseInt(index);
        users.remove(i);
        
    } else if ("addUser".equals(action)) {
        User user = new User();
        user.setName(name);
        user.setAddress(address);
        users.add(user);
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Example 3</title>
    </head>
    <body>
        <h1>JSP Example 3b: User table</h1>

        <h2>user list</h2>

        <table style="width:30%; border: 2px solid black;">
            <tr>
                <th>No</th>
                <th>Name</th>
                <th>Address</th>
            </tr>

            <% for (int idx = 0; idx < users.size(); idx++) {
                    User user = users.get(idx);
            %>

            <tr>
                <td><%=idx + 1%></td>
                <td><%=user.getName()%></td>
                <td><%=user.getAddress()%></td>
                <td>
                    <form action="./jspexample3b.jsp" method="get">
                        <input type="hidden" name="index" value="<%=idx%>">
                        <input type="hidden" name="action" value="removeUser">
                        <input type="submit" value="remove" />
                    </form>
                </td>
            </tr>


            <%
                }
            %>
        </table>
        <h1>add users</h1>
        <form action="./jspexample3b.jsp" method="get">
            <p>user name <input type="text" name="userName" value=""></p>
            <input type="hidden" name="action" value="addUser">
            <p>user address <input type="text" name="userAddress" value=""></p>
            <input type="hidden" name="action" value="addUser">
            <button type="submit" >add name to list</button>
        </form> 
        <br>



    </body>
</html>