<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
            <h1 class="">BOOKING PAGE</h1>
            <a href="./homepage" class="backBtn">
                <button class="btn text-white">Back</button>
            </a>
        </div>

        <h4 class="d-flex p-2">SELECT BOOKING TIME</h4>

        <div class="d-flex p-1 col-4 ms-auto">
            <input
                    id="startDate"
                    class="form-control align-self-center"
                    type="date"
            />
        </div>

<%--        <div>--%>
<%--&lt;%&ndash;            <c:forEach items="${timeslots}" var="timeslot">&ndash;%&gt;--%>
<%--&lt;%&ndash;                <button class="btn btn-primary mx-1 my-1 hourBtn" type="button" value="1">1</button>&ndash;%&gt;--%>
<%--&lt;%&ndash;            </c:forEach>&ndash;%&gt;--%>
<%--            <p>${name}</p>--%>
<%--        </div>--%>

        <div id="hourGroupBtn">
        </div>

        <h4 class="d-flex p-2 my-2">SELECT BOOKING DATE</h4>

        <div id="dateInWeek"></div>

        <div class="d-flex">
            <h5 class="my-2">SELECT FIELD</h5>
            <a
                    class="align-self-center ms-auto text-white link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover"
                    href="#"
            >Field & Price</a
            >
        </div>

        <div id="fields"></div>

        <form>
            <div class="form-group">
                <label for="emailInput">Email Address</label>
                <input
                        type="email"
                        class="form-control"
                        id="emailInput"
                        aria-describedby="emailHelp"
                        placeholder="Enter email"
                />
            </div>
            <div class="form-group">
                <label for="customerNameInput">Customer Name</label>
                <input
                        type="text"
                        class="form-control"
                        id="customerNameInput"
                        placeholder="Customer Name"
                />
            </div>
            <div class="form-group">
                <label for="bookingNoteInput">Note</label>
                <textarea
                        class="form-control"
                        id="bookingNoteInput"
                        rows="3"
                        placeholder="Please put your note here"
                ></textarea>
            </div>
        </form>
        <div>
            <button type="button" class="btn btn-dark btn-lg submitBtn my-2">
                <span>Submit</span>
            </button>
        </div>
    </div>
</div>
<script src="./scripts/utils.js"></script>
<script src="./scripts/generateScript.js"></script>
<script type="module" src="./scripts/dayScripts.js"></script>
<script type="module" src="./scripts/hourScript.js"></script>
<script src="./scripts/fieldScript.js"></script>
</body>
</html>
