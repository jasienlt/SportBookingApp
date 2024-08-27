<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Booking Site</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
            crossorigin="anonymous"
    />
    <link href="../resource/css/style.css" rel="stylesheet" />
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
</head>
<body>
<div class="container">
    <div class="row">
        <div class="d-flex flex-row align-items-center justify-content-center">
            <h1 class=""><spring:message key="booking.title"/></h1>
            <a href="./homepage" class="backBtn">
                <button class="btn text-white">Back</button>
            </a>
        </div>

        <h4 class="d-flex p-2"><spring:message key="booking.selectTime"/></h4>

        <form method="post" id="bookingForm">
            <div class="d-flex p-1 col-4 ms-auto">
                <input
                        id="bookingDate"
                        name="bookingPeriod"
                        class="form-control align-self-center"
                        type="month"
                        value="${currentDate}"
                        min="${currentDate}"
                />
            </div>

            <div id="hourGroupBtn">
                <input type="hidden" id="selectedStartTimeslot" name="selectedStartTimeslot">
                <input type="hidden" id="selectedEndTimeslot" name="selectedEndTimeslot">
                <c:forEach items="${timeslotStartTime}" var="timeslot">
                    <input type="button" class="btn btn-primary mx-1 my-1 hourBtn" value="${timeslot}" name="${timeslot}">
                </c:forEach>
            </div>

            <h4 class="d-flex p-2 my-2"><spring:message key="booking.selectDate"/></h4>

            <div id="dateInWeek"></div>

            <div class="d-flex">
                <h5 class="my-2"><spring:message key="booking.selectField"/></h5>
                <a
                        class="align-self-center ms-auto text-white link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover"
                        href="#"
                >Field & Price</a
                >
            </div>

            <div id="fields">
                <input type="hidden" id="selectedFields" name="selectedFields">
                <c:forEach items="${fields}" var="field">
                    <div class="button-overlay mx-2 my-3">
                        <img class="fieldImg" src="../resource/image/field.png" alt="${field.id}"/>
                        <input class="btn btn-light btn-sm fieldBtn" type="button" value="${field.id}" name="fields" disabled>
                        <small>50k/h-70k/h</small>
                    </div>
                </c:forEach>
            </div>
            <input type="hidden" name="totalFee" id="totalFee">

            <div class="form-group">
                <label for="emailInput"><spring:message key="booking.email"/></label>
                <input
                        type="email"
                        class="form-control"
                        id="emailInput"
                        name="customerEmail"
                        aria-describedby="emailHelp"
                        placeholder="<spring:message key="booking.emailPlaceholder"/>"
                        required
                />
            </div>
            <div class="form-group">
                <label for="customerNameInput"><spring:message key="booking.customerName"/></label>
                <input
                        type="text"
                        class="form-control"
                        id="customerNameInput"
                        name="customerName"
                        placeholder="<spring:message key="booking.customerNamePlaceholder"/>"
                        required
                />
            </div>
            <div class="form-group">
                <label for="bookingNoteInput"><spring:message key="booking.note"/></label>
                <textarea
                        class="form-control"
                        id="bookingNoteInput"
                        rows="3"
                        placeholder="<spring:message key="booking.notePlaceHolder"/>"
                ></textarea>
            </div>

            <div>
                <button type="submit" id="submitBtn" class="btn btn-dark btn-lg submitBtn my-2" form="bookingForm" disabled>
                    <span>Submit</span>
                </button>
            </div>
            <div id="overlay"></div>
            <div id="popup">
                <h1>Booking Summary</h1>
                <p id="customerName"><span>Customer Name: </span></p>
                <p id="customerEmail"><span>Customer Email: </span></p>
                <p id="selectedFieldsSummary"><span>Fields: </span></p>
                <p id="bookingTime"><span>Time: </span></p>
                <p id="bookingDates"><span>Dates: </span></p>
                <p id="priceSummary"><span>Price: </span></p>
                <button type="submit" id="checkoutStripe" class="btn btn-dark btn-lg submitBtn my-2">Checkout By Card</button>
                <button type="submit" id="checkoutTransfer" class="btn btn-dark btn-lg submitBtn my-2">Checkout By Transfer</button>
                <button id="closePopup" class="btn btn-dark btn-lg submitBtn my-2">Back to booking</button>
            </div>
        </form>

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
</div>

<script type="module" src="../scripts/locale.js"></script>
<script type="module" src="../scripts/utils.js"></script>
<script type="module" src="../scripts/generateScript.js"></script>
<script type="module" src="../scripts/dayScripts.js"></script>
<script type="module" src="../scripts/hourScript.js"></script>
<script type="module" src="../scripts/fieldScript.js"></script>
<script type="module" src="../scripts/bookingSummary.js"></script>
<script type="module" src="../scripts/setCheckoutFormAction.js"></script>
</body>
</html>
