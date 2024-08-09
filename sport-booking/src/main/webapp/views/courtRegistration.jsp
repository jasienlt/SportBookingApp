<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
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
    <form class="form-registration" method="post" action="/court/register" enctype = "multipart/form-data">
        <h2 class="mb-3">Register</h2>
        <c:if test="${not empty someMessage}">
            <div class="alert alert-danger" role="alert">
                    ${someMessage} <a href="login">Login</a> here.
            </div>
        </c:if>

        <div class="mb-3">
            <label for="name" class="form-label">Court Name</label>
            <input type="text" id="name" name="name" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="address" class="form-label">Address</label>
            <input type="text" id="address" name="address" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Phone Number</label>
            <input type="text" id="phone" name="phone" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="sportgroup" class="form-label">Sportgroup</label>

            <select id="sportgroup" name="sportgroup" class="form-select" required>
                <c:forEach var="sportgroup" items="${listSportgroup}">
                    <option value="${sportgroup.name}"></option>
                </c:forEach>

            </select>
        </div>
        <div class="mb-3">
            <label for="paymentImg" class="form-label">Payment QR Code:</label>
            <p> Please name the file under this format: CourtName. E.g: DungCauLong, VuongBongRo...</p>
            <input type="file" id="paymentImg" name="paymentImg" required />
        </div>

        <button class="btn btn-primary" type="submit">Register new court</button>
    </form>


</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>