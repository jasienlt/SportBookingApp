<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="ISO-8859-1">
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resource/css/register.css'/>">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2>Register</h2>
    <p>ALOBO - Sports Court Booking</p>
    <form class="form-registration" method="post" action="register" onsubmit="return validatePasswords()">
        <div class="input-icons">
            <label for="firstName" class="form-label">First name</label>
            <input type="text" id="firstName" name="firstName" class="form-control" placeholder="Enter first name" required>
        </div>
        <div class="input-icons">
            <label for="lastName" class="form-label">Last name</label>
            <input type="text" id="lastName" name="lastName" class="form-control" placeholder="Enter last name" required>
        </div>
        <div class="input-icons">
            <label for="phone" class="form-label">Phone</label>
            <input type="text" id="phone" name="phone" class="form-control" placeholder="Enter phone number" required>
        </div>
        <div class="input-icons">
            <label for="email" class="form-label">Email address</label>
            <input type="email" id="email" name="email" class="form-control" placeholder="Enter email" required>
        </div>
        <div class="input-icons">
            <label for="password" class="form-label">Password</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Enter password" required>
        </div>
        <div class="input-icons">
            <label for="confirmPassword" class="form-label">Confirm Password</label>
            <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" placeholder="Enter confirm password" required>
        </div>
        <div id="passwordError" style="color: red; display: none;">
            Passwords do not match!
        </div>
        <button class="btn btn-primary" type="submit">Register</button>
    </form>
    <div class="back-to-login">
        <span>Already have an account? <a href="<c:url value='/login'/>">Log in</a></span>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
<script>
    function validatePasswords() {
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;
        const passwordError = document.getElementById('passwordError');

        if (password !== confirmPassword) {
            passwordError.style.display = 'block';
            return false; // Prevent form submission
        } else {
            passwordError.style.display = 'none';
            return true; // Allow form submission
        }
    }
</script>
</body>
</html>
