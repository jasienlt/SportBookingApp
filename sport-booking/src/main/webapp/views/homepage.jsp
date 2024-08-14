<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Club Booking</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
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
    <link href="../resource/css/style.css" rel="stylesheet" />
</head>
<body>

<!-- Search Bar -->
<div class="container">
    <div class="search-bar d-flex justify-content-between">
        <input type="text" placeholder="Tìm kiếm">
        <button class="search-button"><i class="bi bi-search"></i></button>
    </div>

    <!-- Main Content -->
    <div class="main-content">
        <!-- Club List -->
        <div class="club-list">
            <div class="club-item" onclick="showClubDetails(1)">
                <img src="resource/image/logo.png" alt="Club Logo 1">
                <strong>CLB Cầu Lông 18B Cộng Hòa</strong><br>
                18B Cộng Hòa, Phường 4, Tân Bình, TP Hồ Chí Minh
            </div>
            <div class="club-item" onclick="showClubDetails(2)">
                <img src="resource/image/logo.png" alt="Club Logo 2">
                <strong>272 Badminton</strong><br>
                Lô A4 Chung Cư Ngô Quyền, TP Đà Lạt, Lâm Đồng
            </div>
        </div>

        <!-- Club Details -->
        <div class="club-details" id="club-details">
            <div class="club-details-content">
                <h3>Club Details</h3>
                <p>Select a club to see details here...</p>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<div class="footer">
    <div class="footer-item" onclick="window.location.href='test'">
        <i class="bi bi-list-task"></i><br>
        Danh sách
    </div>
    <div class="footer-item" onclick="window.location.href='featured.jsp'">
        <i class="bi bi-star-fill"></i><br>
        Nổi bật
    </div>
    <div class="footer-item" onclick="window.location.href='login'">
        <i class="bi bi-person-square"></i><br>
        Tài khoản
    </div>
</div>

<script>
    function showClubDetails(clubId) {
        // Send AJAX request to fetch club details
        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'getClubDetails?clubId=' + clubId, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById('club-details').innerHTML = xhr.responseText;
            }
        };
        xhr.send();
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
