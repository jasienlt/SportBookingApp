<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script type="text/javascript">
        window.onload = function() {

            var dps = [[], [], []];
            var chart = new CanvasJS.Chart("chartContainer", {
                animationEnabled: true,
                theme: "light2",
                title: {
                    text: "Number of bookings within 7 days"
                },
                axisX: {
                    valueFormatString: "YYYY-MM-DD",
                    interval: 1,
                    intervalType: "day"
                },
                axisY: {
                    title: "No of bookings",
                    suffix: "bookings"
                },
                legend: {
                    cursor:"pointer",
                    itemclick: toogleDataSeries
                },
                toolTip: {
                    shared: true
                },
                data: [{
                    type: "stackedColumn",
                    name: "CANCELLED",
                    showInLegend: true,
                    xValueType: "dateTime",
                    xValueFormatString: "YYYY-MM-DD",
                    yValueFormatString: "#,###",
                    dataPoints: dps[0]
                },{
                    type: "stackedColumn",
                    name: "PENDING",
                    showInLegend: true,
                    xValueType: "dateTime",
                    xValueFormatString: "YYYY-MM-DD",
                    yValueFormatString: "#,###",
                    dataPoints: dps[1]
                },{
                    type: "stackedColumn",
                    name: "COMPLETED",
                    showInLegend: true,
                    xValueType: "dateTime",
                    xValueFormatString: "YYYY-MM-DD",
                    yValueFormatString: "#,###",
                    dataPoints: dps[2]
                }]
            });

            function toogleDataSeries(e){
                if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
                    e.dataSeries.visible = false;
                } else{
                    e.dataSeries.visible = true;
                }
                chart.render();
            }

            var xValue;
            var yValue;

            <c:forEach items="${dataPointsList}" var="dataPoints" varStatus="loop">
            <c:forEach items="${dataPoints}" var="dataPoint">
            xValue = parseInt("${dataPoint.x}");
            yValue = parseFloat("${dataPoint.y}");
            dps[parseInt("${loop.index}")].push({
                x : xValue,
                y : yValue
            });
            </c:forEach>
            </c:forEach>

            chart.render();

        }
    </script>
</head>
<body>
<div id="chartContainer" style="height: 370px; width: 100%;"></div>
<script src="https://cdn.canvasjs.com/canvasjs.min.js"></script>
</body>
</html>