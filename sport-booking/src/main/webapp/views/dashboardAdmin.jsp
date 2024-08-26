<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="com.developer.sportbooking.repository.CustomerRepo" %>
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
    <style>
        body {
            background-color: #f8f9fa;
            padding-top: 50px;
        }
        .container {
            max-width: 600px;
        }
    </style>

    <script type="text/javascript">
        window.onload = function() {

            var dataPoints = [];
            var chart = new CanvasJS.Chart("chartContainer", {
                title: {
                    text: "Number of booking based on Status within 7 days"
                },
                axisY: {
                    title: "Number of bookings",
                    minimum: 0
                },
                data: [
                    {
                        type: "stackedBar",
                        name: "Completed",
                        showInLegend: "true",
                        dataPoints: [] // Will be populated with AJAX call
                    },
                    {
                        type: "stackedBar",
                        name: "Pending",
                        showInLegend: "true",
                        dataPoints: [] // Will be populated with AJAX call
                    },
                    {
                        type: "stackedBar",
                        name: "Canceled",
                        showInLegend: "true",
                        dataPoints: [] // Will be populated with AJAX call
                    }
                ]
            });

            var yValue;
            var xValue;

            <c:forEach items="${dataPointsList}" var="dataPoints" varStatus="loop">
                <c:forEach items="${dataPoints}" var="dataPoint">
                yValue = parseFloat("${dataPoint.y}");
                xValue = parseString("${dataPoint.x}");
                dataPoints.push({
                    x : xValue,
                    y : yValue,
                });
            </c:forEach>
            </c:forEach>

            chart.render();

            function updateChart() {
                $.ajax({
                    url: "/dashboardAdmin/updateChartData",
                    method: "GET",
                    success: function (data) {
                        chart.options.data[0].dataPoints = data[0]; // Update Completed
                        chart.options.data[1].dataPoints = data[1]; // Update Pending
                        chart.options.data[2].dataPoints = data[2]; // Update Canceled
                        chart.render();
                    },
                    error: function (error) {
                        console.log("Error fetching data: ", error);
                    }
                });
            }

            updateChart(); // Initial chart load

            // Refresh the chart every 30 minutes (1800000 milliseconds)
            setInterval(updateChart, 1800000);

        }
    </script>
</head>
<body>

<div id="chartContainer" style="height: 370px; width: 100%;"></div>

<%--<div class="container">

<%--    &lt;%&ndash; Check if user is logged in &ndash;%&gt;--%>
<%--    <sec:authorize access = "isAuthenticated()">--%>
<%--        Welcome <sec:authentication property="principal.username" />--%>
<%--        <br>--%>
<%--        <button class="btn btn-primary" type="submit">Logout</button>--%>
<%--    </sec:authorize>--%>
<%--    <sec:authorize access = "!isAuthenticated()"> <p> Please log in to access.  <a href="login">Login</a> </p></sec:authorize>--%>

    <!-- Tab links -->
<%--    <div class="tab">--%>
<%--        <button class="tablinks" onclick="openTab("c", 'London')">London</button>--%>
<%--        <button class="tablinks" onclick="openTab(event, 'Paris')">Paris</button>--%>
<%--        <button class="tablinks" onclick="openTab(event, 'Tokyo')">Tokyo</button>--%>
<%--    </div>--%>

<%--    <!-- Tab content -->--%>
<%--    <div id="London" class="tabcontent">--%>
<%--        <h3>London</h3>--%>
<%--        <p>London is the capital city of England.</p>--%>
<%--    </div>--%>

<%--    <div id="Paris" class="tabcontent">--%>
<%--        <h3>Paris</h3>--%>
<%--        <p>Paris is the capital of France.</p>--%>
<%--    </div>--%>

<%--    <div id="Tokyo" class="tabcontent">--%>
<%--        <h3>Tokyo</h3>--%>
<%--        <p>Tokyo is the capital of Japan.</p>--%>
<%--    </div>--%>


</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdn.canvasjs.com/canvasjs.min.js"></script>
</body>
</html>