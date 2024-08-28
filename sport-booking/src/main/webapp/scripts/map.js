let map; // Declare the map globally
let geocoder;
let googleMapApi = document.getElementById("googleMapApi").value;

console.log(googleMapApi);

async function showClubDetails(courtId, courtAddress) {
    // Send AJAX request to fetch club details
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'getCourtDetails?courtId=' + courtId, true);
    xhr.onreadystatechange = async function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Replace club details content with the fetched response
            document.getElementById('club-details').innerHTML = xhr.responseText;
            const {AdvancedMarkerElement} = await google.maps.importLibrary("marker");

            geocoder = new google.maps.Geocoder();

            geocoder.geocode( { 'address': courtAddress}, function(results, status) {
                if (status === 'OK') {
                    map = new google.maps.Map(document.getElementById('map'), {
                        center: results[0].geometry.location,
                        zoom: 12,
                        mapId: "DEMO_MAP_ID"
                    });

                    const marker = new AdvancedMarkerElement({
                        map: map,
                        position: results[0].geometry.location,
                    });
                } else {
                    alert('Geocode was not successful for the following reason: ' + status);
                }
            });
        }
    };
    xhr.send();
}

// Load the Google Maps script once
function loadGoogleMapsScript() {
    if (!document.querySelector('script[src*="maps.googleapis.com"]')) {
        var mapsScript = document.createElement('script');
        mapsScript.src = `https://maps.googleapis.com/maps/api/js?key=${googleMapApi}&loading=async&callback=initMap`;
        mapsScript.defer = true;
        document.body.appendChild(mapsScript);
    }
}

function initMap() {
    // This function is automatically called once the Google Maps API script is loaded
    // The map will be initialized only when a club is selected
}

// Load the Google Maps script on page load
window.onload = loadGoogleMapsScript;