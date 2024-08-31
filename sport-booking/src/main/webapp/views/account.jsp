<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Information</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../resource/css/account.css" rel="stylesheet" />
    <link href="../resource/css/style.css" rel="stylesheet" />
</head>
<body>
<!-- Account Header -->
<div class="account-header">
    <img src="resource/image/logo.png" alt="Profile Picture" class="profile-image"> <!-- Add your image path -->
</div>

<!-- Account Info -->
<div class="account-info">
    <h2>Vuong Tran</h2>
    <p>+61467506761</p>
</div>

<!-- Tabs Navigation -->
<ul class="nav nav-tabs" id="myTab" role="tablist">
    <li class="nav-item" role="presentation">
        <button class="nav-link active" id="schedule-tab" data-bs-toggle="tab" data-bs-target="#schedule" type="button" role="tab" aria-controls="schedule" aria-selected="true">Reserved Schedule</button>
    </li>
    <li class="nav-item" role="presentation">
        <button class="nav-link" id="info-tab" data-bs-toggle="tab" data-bs-target="#info" type="button" role="tab" aria-controls="info" aria-selected="false">Members Information</button>
    </li>
</ul>

<!-- Tabs Content -->
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="schedule" role="tabpanel" aria-labelledby="schedule-tab">
        <p>You don't have any bookings yet!</p>
    </div>
    <div class="tab-pane fade" id="info" role="tabpanel" aria-labelledby="info-tab">
        <p>Member information details go here.</p>
    </div>
</div>

<jsp:include page="homepage_footer.jsp" />

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
</body>
</html>
