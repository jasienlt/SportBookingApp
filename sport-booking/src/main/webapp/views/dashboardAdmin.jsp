<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="com.developer.sportbooking.persistence.repository.CustomerRepo" %>
<%@ page import="com.developer.sportbooking.entity.Customer" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    <style>
        #charts { display: flex; justify-content: space-around; }
        .chart-container { width: 45%; }
    </style>

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

<%--    &lt;%&ndash; Check if user is logged in &ndash;%&gt;--%>
<%--    <sec:authorize access = "isAuthenticated()">--%>
<%--        Welcome <sec:authentication property="principal.username" />--%>
<%--        <br>--%>
<%--        <button class="btn btn-primary" type="submit">Logout</button>--%>
<%--    </sec:authorize>--%>
<%--    <sec:authorize access = "!isAuthenticated()"> <p> Please log in to access.  <a href="login">Login</a> </p></sec:authorize>--%>

<h1>Dashboard</h1>
<div id="charts">
    <div class="chart-container">
        <h2>Pie Chart</h2>
        <canvas id="pieChart"></canvas>
    </div>
    <div class="chart-container">
        <h2>Line Chart</h2>
        <canvas id="lineChart"></canvas>
    </div>
</div>
<div>
    <h2>Feedback</h2>
    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Review</th>
        </tr>
        </thead>
        <tbody id="feedbacksTable">
        <!-- Dynamic Content -->
        </tbody>
    </table>
</div>
<div>
    <h2>Bookings</h2>
    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Booking</th>
        </tr>
        </thead>
        <tbody id="bookingsTable">
        <!-- Dynamic Content -->
        </tbody>
    </table>
</div>

<script>
    function fetchData() {
        $.ajax({
            url: 'reviews',
            method: 'GET',
            success: function(data) {
                var feedbacksTable = $('#feedbacksTable');
                feedbacksTable.empty();
                data.forEach(review => {
                    feedbacksTable.append(`<tr><td>${feedback.id}</td>
                                            <td>${feedback.feedback}</td>
                                            <td>${feedback.rating}</td></tr>`);
                });
            }
        });

        $.ajax({
            url: 'bookings',
            method: 'GET',
            success: function(data) {
                var bookingsTable = $('#bookingsTable');
                bookingsTable.empty();
                data.forEach(booking => {
                    bookingsTable.append(`<tr>
                                            <td>${booking.date}</td>
                                            <td>${booking.status}</td></tr>`);
                });
            }
        });

        // Fetch Pie Chart Data
        $.ajax({
            url: 'pieChartData',
            method: 'GET',
            success: function(data) {
                var ctx = document.getElementById('pieChart').getContext('2d');
                new Chart(ctx, {
                    type: 'pie',
                    data: {
                        labels: Object.keys(data),
                        datasets: [{
                            data: Object.values(data),
                            backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56']
                        }]
                    }
                });
            }
        });

        // Fetch Line Chart Data
        $.ajax({
            url: 'lineChartData',
            method: 'GET',
            success: function(data) {
                var ctx = document.getElementById('lineChart').getContext('2d');
                new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: Object.keys(data),
                        datasets: [{
                            data: Object.values(data),
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                    }
                });
            }
        });
    }

    $(document).ready(function() {
        fetchData();
        setInterval(fetchData, 5000); // Refresh every 5 seconds
    });
</script>


</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>