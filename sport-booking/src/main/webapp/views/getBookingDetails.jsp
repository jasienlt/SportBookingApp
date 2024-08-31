<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card-body">
    <div class="headerImg">

        <p><strong>Thông tin:</strong> ${booking.id} </p>
        <p>Customer Name:  ${customer.name}</p>
        <p>Price: </i> ${booking.price}</p>
        <p>Payment Method: </i> ${paymentMethod}</p>
        <p>Payment Receipt:</p>
        <img src="data:image/png;base64,${imgAsBase64}" class="align-items-md-center" /><br>

    </div>
</div>