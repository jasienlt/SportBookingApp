<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="ISO-8859-1">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../resource/css/login.css" rel="stylesheet" />
</head>
<body>
<div class="form-login">
    <!-- Login Form -->
    <form method="post" action="<c:url value='/perform_login'/>">
        <h2 class="mb-3"><spring:message key="login.login"/></h2>
        <% if (request.getParameter("error") != null) { %>
        <div class="alert alert-danger" role="alert">
            <spring:message key="login.invalidAccount"/>
        </div>
        <% } %>
        <input type="text" id="username" name="username" class="form-control" placeholder="<spring:message key="login.username"/>" required>
        <input type="password" id="password" name="password" class="form-control" placeholder="<spring:message key="login.password"/>" required>
        <button class="btn btn-primary" type="submit"><spring:message key="login.signIn"/></button>
    </form>

    <div class="form-footer">
        <p><spring:message key="login.askForAccount"/> <a href="register"><spring:message key="login.register"/></a> <spring:message key="login.here"/> </p>
    </div>

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

<jsp:include page="homepage_footer.jsp" />

<script type="module" src="../scripts/locale.js"></script>
</body>
</html>
