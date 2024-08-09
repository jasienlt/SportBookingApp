<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="ISO-8859-1">
    <title>Login and Logout Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../resource/css/style.css" rel="stylesheet" />
    <script
            src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
            crossorigin="anonymous"
    ></script>
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
            crossorigin="anonymous"
    ></script>

    <title>Insert title here</title>

    <style>
        body {
            background-color: #f8f9fa;
            padding-top: 50px;
            color: black;
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
        <form class="form-login" method="post" action="<c:url value='/perform_login'/>">
            <h2 class="mb-3"><spring:message key="login.login"/></h2>
            <% if (request.getParameter("error") != null) { %>
                <div class="alert alert-danger" role="alert">
                    <spring:message key="login.invalidAccount"/>
                </div>
            <% } %>
            <div class="mb-3">
                <label for="username" class="form-label"><spring:message key="login.username"/></label>
                <input type="text" id="username" name="username" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label"><spring:message key="login.password"/></label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>
            <div class="form-footer">
                <p class="form-footer"><spring:message key="login.askForAccount"/> <a href="registration"><spring:message key="login.register"/></a> <spring:message key="login.here"/> </p>
            </div>


            <button class="btn btn-primary" type="submit"><spring:message key="login.signIn"/></button>
        </form>

        <% } else { %>
        <!-- Logout Form -->
        <form class="form-login" method="post" action="./home">
            <h2 class="mb-3">Welcome, <%= firstName %>!</h2>
            <button class="btn btn-primary" type="submit"><spring:message key="login.logout"/></button>
        </form>
        <% } %>

        <div class="language-selector">
            <label for="locales"><spring:message key="lang.change"/></label>
            <select id="locales">
                <option value=""></option>
                <option value="en">English</option>
                <option value="vi">Tiếng Việt</option>
                <!-- Add more language options as needed -->
            </select>
        </div>
    </div>
    <script type="module" src="../scripts/locale.js"></script>
</body>
</html>