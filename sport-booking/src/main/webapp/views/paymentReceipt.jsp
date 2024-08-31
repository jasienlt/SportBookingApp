<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Booking Summary Site</title>
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
<form action="./finishPayment" method="post" enctype="multipart/form-data">
    <h2>Customer Name: ${custName}</h2>
    <input type="hidden" name="custName" value=${custName}>
    <h2>Total Fee: ${totalFee}</h2>
    <input type="hidden" name="totalFee" value=${totalFee}>
    <h2>Scan here to transfer:</h2><br>
    <img src="data:image/png;base64,${imgAsBase64}" class="align-items-md-center" /><br>

    <label for="receiptImg" class="form-label">Payment Screnshot:</label><br>
    <input type="file" accept="image/x-png,image/gif,image/jpeg,image/jpg" id="receiptImg" name="receiptImg" required /><br>

    <input type="hidden" name="custEmail" value="${custEmail}">
    <input type="hidden" name="selectedStartTimeslot" value="${selectedStartTimeslot}">
    <input type="hidden" name="selectedEndTimeslot" value="${selectedEndTimeslot}">
    <input type="hidden" name="date" value="${dates}">
    <input type="hidden" name="selectedFields" value="${selectedFields}">
    <input type="hidden" name="bookingPeriod" value="${bookingPeriod}">
    <input type="hidden" name="courtId" value="${courtId}">


    <div>
        <button type="submit" id="submitBtn" class="btn btn-dark btn-lg submitBtn my-2">
            <span>Submit</span>
        </button>
    </div>

</form>
</body>
</html>
