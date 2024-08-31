<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP List Users Records</title>
</head>
<body>

<div align="center">
    <form id = "processPayment" action="./approvePayment" method="post">
        <table border="1" cellpadding="5">
            <caption><h2>Pending Payments</h2></caption>
            <tr>
                <th>ID</th>
                <th>Created Date</th>
                <th>Payment Type</th>
                <th>Booking ID</th>
            </tr>
            <c:forEach var="payment" items="${listPayment}">
                <tr>
                    <td><c:out value="${payment.id}" /></td>
                    <td><c:out value="${payment.createdDate}" /></td>
                    <td><c:out value="${payment.paymentType}" /></td>
                    <td><c:out value="${payment.bookingId}" /></td>
                    <input type="hidden" name="id" value="${payment.id}"/>
                    <td><button type="submit" name="action" value="success" onclick="return confirm('Do you really want to approve the payment?');">SUCCESSFUL</button></td>
                    <td><button type="submit" name="action" value="cancelled" onclick="return confirm('Do you really want to cancel the payment?');">CANCELLED</button></td>
                </tr>
            </c:forEach>
        </table>

    </form>
</div>

<script type="text/javascript">
    function checkForNewData() {
        $.ajax({
            url: "/checkNewData",  // The endpoint to check for new data
            method: "GET",
            success: function (data) {
                if (data.hasNewData) {  // Assuming the server returns a JSON object with a boolean flag
                    alert("New booking data has been added! Please confirm the payment.");
                    setTimeout(function() {
                        location.reload();  // Refresh the page after 2 seconds
                    }, 2000);
                }
            },
            error: function (error) {
                console.error("Error checking for new data: ", error);
            }
        });
    }

    // Check for new data every 20 seconds (20000 milliseconds)
    setInterval(checkForNewData, 20000);
</script>

</body>
</html>