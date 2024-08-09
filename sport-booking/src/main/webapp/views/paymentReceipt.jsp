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
<form action="./finishPayment" method="post">
    <h1>Booking ID: ${bookingId}</h1>
    <input type="text" name="bookingId" value=${bookingId} hidden=true>
    <h2>Customer Name: ${custName}</h2>
    <input type="text" name="custName" value=${custName} hidden=true>
    <h2>Total Fee: ${totalFee}</h2>
    <input type="text" name="totalFee" value=${totalFee} hidden=true>
    <h2>Scan here to transfer:</h2><br>
    <img src="data:image/png;base64,${imgAsBase64}" />

    <label for="receiptImg" class="form-label">Payment Screnshot:</label>
    <input type="file" id="receiptImg" name="receiptImg" required />

    <div>
        <button type="submit" id="submitBtn" class="btn btn-dark btn-lg submitBtn my-2">
            <span>Submit</span>
        </button>
    </div>

</form>
</body>
</html>
