<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="ISO-8859-1">
    <title>Registration Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <title>Insert title here</title>

    <style>
        body {
            background-color: #f8f9fa;
            padding-top: 50px;
        }
        .form-registration {
            max-width: 330px;
            padding: 15px;
            margin: auto;
        }
        .form-registration .form-control {
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
    <!-- Login Form -->
    <form class="form-registration" method="post" action="/sportgroup/register">
        <h2 class="mb-3">Register</h2>
        <c:if test="${not empty someMessage}">
            <div class="alert alert-danger" role="alert">
                    ${someMessage} <a href="login">Login</a> here.
            </div>
        </c:if>

        <div class="mb-3">
            <label for="name" class="form-label">Last Name</label>
            <input type="text" id="name" name="name" class="form-control" required>
        </div>

        <button class="btn btn-primary" type="submit">Register new sportgroup</button>
    </form>


</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>