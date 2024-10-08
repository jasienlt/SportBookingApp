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
<section>
    <p>
        Thanks ${customerName} <br/>
        We appreciate your business! If you have any questions, please email
        <a href="mailto:orders@example.com">orders@example.com</a>.
    </p>
</section>

<div id="map"></div>

<script>
    let map;

    function initMap() {
        // The location of Uluru
        const position = { lat: -25.344, lng: 131.031 };

        var map = new google.maps.Map(
            document.getElementById('map'), { zoom: 4, center: position });
        var marker = new google.maps.Marker({ position: position, map: map });
        // Request needed libraries.
        //@ts-ignore
        // const { Map } = google.maps.importLibrary("maps");
        // const { AdvancedMarkerElement } = google.maps.importLibrary("marker");
        //
        // // The map, centered at Uluru
        // map = new Map(document.getElementById("map"), {
        //     zoom: 4,
        //     center: position,
        //     mapId: "DEMO_MAP_ID",
        // });
        //
        // // The marker, positioned at Uluru
        // const marker = new AdvancedMarkerElement({
        //     map: map,
        //     position: position,
        //     title: "Uluru",
        // });
    }
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCCKURs_tLLjghrsjLMcoh-xqtaA2KDmlQ&loading=async&callback=initMap" defer></script>

</body>
</html>
