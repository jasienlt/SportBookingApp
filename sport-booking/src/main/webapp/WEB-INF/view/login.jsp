<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="ISO-8859-1">
    <title>Login and Logout Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <title>Insert title here</title>

    <style>
        body {
            background-color: #f8f9fa;
            padding-top: 50px;
        }
        .form-login {
            max-width: 330px;
            padding: 15px;
            margin: auto;
        }
        .form-login .form-control {
            position: relative;
            box-sizing: border-box;
            height: auto;
            padding: 10px;
            font-size: 16px;
        }
        .form-footer {
            text-align: center;
            margin-top: 20px;
            color: #888;
        }
    </style>
</head>
<body>
<div class="container">
    <%-- Check if user is logged in --%>
    <%
        String firstName = (String) session.getAttribute("firstName");
        if (firstName == null) {
    %>
    <!-- Login Form -->
    <form class="form-login" method="post" action="login">
        <h2 class="mb-3">Login</h2>
        <% if (request.getParameter("error") != null) { %>
        <div class="alert alert-danger" role="alert">
            Invalid username or password!
        </div>
        <% } %>
        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" id="username" name="username" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" id="password" name="password" class="form-control" required>
        </div>
        <div class="form-footer">
            <p class="form-footer">Don't have an account? <a href="registration">Register</a> here.</p>
        </div>


        <button class="btn btn-primary" type="submit">Sign in</button>
    </form>

    <% } else { %>
    <!-- Logout Form -->
    <form class="form-login" method="post" action="home">
        <h2 class="mb-3">Welcome, <%= firstName %>!</h2>
        <button class="btn btn-primary" type="submit">Logout</button>
    </form>
    <% } %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</div>
</body>
</html>