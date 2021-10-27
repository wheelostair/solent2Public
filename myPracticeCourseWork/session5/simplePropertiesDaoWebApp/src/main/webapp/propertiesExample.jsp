<%-- 
    Document   : propertiesExampleJsp
    Created on : 27 Oct 2021, 01:41:23
    Author     : cgallen
--%>

<%@page import="org.solent.ood.simplepropertiesdaowebapp.dao.WebObjectFactory"%>
<%@page import="org.solent.ood.simplepropertiesdaowebapp.dao.PropertiesDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    PropertiesDao propertiesDao = WebObjectFactory.getPropertiesDao();

    String url = propertiesDao.getProperty("org.solent.ood.simplepropertiesdaowebapp.url");
    String username = propertiesDao.getProperty("org.solent.ood.simplepropertiesdaowebapp.username");
    String password = propertiesDao.getProperty("org.solent.ood.simplepropertiesdaowebapp.password");
    String cardName = propertiesDao.getProperty("org.solent.ood.simplepropertiesdaowebapp.cardName");
    String creditCard = propertiesDao.getProperty("org.solent.ood.simplepropertiesdaowebapp.creditCard");
    String expiry = propertiesDao.getProperty("org.solent.ood.simplepropertiesdaowebapp.expiry");
    String cvv = propertiesDao.getProperty("org.solent.ood.simplepropertiesdaowebapp.cvv");
    String issueNumber = propertiesDao.getProperty("org.solent.ood.simplepropertiesdaowebapp.issueNumber");  
    String message = "";
    
    String action = (String) request.getParameter("action");
    if ("updateProperties".equals(action)) {
        message = "updating properties";
        url = (String) request.getParameter("url");
        username = (String) request.getParameter("username");
        password = (String) request.getParameter("password");
        cardName = (String) request.getParameter("cardName");
        creditCard = (String) request.getParameter("creditCard");
        expiry = (String) request.getParameter("expiry");
        cvv = (String) request.getParameter("cvv");
        issueNumber = (String) request.getParameter("issueNumber");

        propertiesDao.setProperty("org.solent.ood.simplepropertiesdaowebapp.url", url);
        propertiesDao.setProperty("org.solent.ood.simplepropertiesdaowebapp.username", username);
        propertiesDao.setProperty("org.solent.ood.simplepropertiesdaowebapp.password", password);
        propertiesDao.setProperty("org.solent.ood.simplepropertiesdaowebapp.cardName", cardName);
        propertiesDao.setProperty("org.solent.ood.simplepropertiesdaowebapp.creditCard", creditCard);
        propertiesDao.setProperty("org.solent.ood.simplepropertiesdaowebapp.expiry", expiry);
        propertiesDao.setProperty("org.solent.ood.simplepropertiesdaowebapp.cvv", cvv);
        propertiesDao.setProperty("org.solent.ood.simplepropertiesdaowebapp.issueNumber", issueNumber);

    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Properties Example Jsp</title>
    </head>
    <body>
        <h1>Properties Example Jsp</h1>
        <p><%=message %></p>
        <form action="./propertiesExample.jsp" method="POST">
            <p>URL Property <input type="text" name="url" value="<%=url%>"></p>
            <p>Username Property <input type="text" name="username" value="<%=username%>"></p>
            <p>Password Property <input type="text" name="password" value="<%=password%>"></p>
            <p>Name on Card <input type="text" name="cardName" value="<%=cardName%>"></p>
            <p>Card Number Property <input type="text" name="creditCard" value="<%=creditCard%>"></p>
            <p>Expiry Property <input type="text" name="expiry" value="<%=expiry%>"></p>
            <p>Cvv Property <input type="text" name="cvv" value="<%=cvv%>"></p>
            <p>Issue Number Property <input type="text" name="issueNumber" value="<%=issueNumber%>"></p>
            <!-- comment -->
            <input type="hidden" name="action" value="updateProperties">

            <button class="btn" type="submit" >Update Properties</button>
        </form> 
    </body>
</html>
