<%-- 
    Document   : jspexample2
    Created on : 6 Oct 2021, 10:32:07
    Author     : Ally Cox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
// any valid java code can go between the escape sequences

// here we are looking for the userName value in the web request which called this page
    String name = request.getParameter("userName");
    String address = request.getParameter("address");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Example 2</title>
    </head>
    <body>
        <h1>JSP Example 2: Form Request Examples</h1>

        Hello I think your name is <%=name %> and your address is <%=address %>

        <!-- starting the href with ./ means you are referring relative to the root of this page -->

        <!-- get uses url encoded parameters -->
        <form action="./jspexample2.jsp" method="get">
            <p>user name <input type="text" name="userName" value=""></p>
            <p>address <input type="text" name="address" value=""></p>
            <button type="submit" >add name and address using GET</button>
        </form> 
        
        <br>

        <!-- post avoids url encoded parameters -->
        <form action="./jspexample2.jsp" method="post">
            <!-- here we are pre filling the value with the name variable -->
            <p>user name <input type="text" name="userName" value="<%=name %>"></p>
            <!-- here we are pre filling the value with the address variable -->
            <p>address <input type="text" name="address" value="<%=address %>"></p>
            <button type="submit" >add name and address using POST</button>
            
            
        </form> 
         
    </body>
</html>
