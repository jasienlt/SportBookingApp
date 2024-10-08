<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="ISO-8859-1">
    <title>Admin Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../resource/css/login.css">
</head>
<body>
<div class="container">
    <!-- Login Form -->
    <form class="form-login" method="post" action="court_login">
        <h2 class="mb-3">Admin Login</h2>
        <% if (request.getParameter("error") != null) { %>
        <div class="alert alert-danger" role="alert">
            Invalid username or password!
        </div>
        <% } %>
        <div class="mb-3">
            <c:if test="${not empty someMessage}">
                ${someMessage}
            </c:if>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Username</label>
            <input type="text" id="email" name="email" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" id="password" name="password" class="form-control" required>
        </div>
        <div class="form-footer">
            <p class="form-footer"><a href="/login">Sign in as customer</a></p>
        </div>


        <button class="btn btn-primary" type="submit">Sign in</button>
    </form>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</div>
</body>
</html>