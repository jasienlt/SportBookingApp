<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="com.developer.sportbooking.repository.CustomerRepo" %>
<%@ page import="com.developer.sportbooking.entity.Customer" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">


    <style>
        body {
            background-color: #f8f9fa;
            padding-top: 50px;
        }
        .container {
            max-width: 600px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="mb-3">Home Page</h2>
    <%-- Check if user is logged in --%>
    <sec:authorize access = "isAuthenticated()"> Welcome ${customerDetail.customer.id} <sec:authentication property="principal.username" />
    </sec:authorize>
    <sec:authorize access = "!isAuthenticated()"> <p> Please log in to access.  <a href="login">Login</a> </p></sec:authorize>



</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>