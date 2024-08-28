function showClubDetails(clubId) {
    // Send AJAX request to fetch club details
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'getCourtDetails?clubId=' + clubId, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById('club-details').innerHTML = xhr.responseText;
        }
    };
    xhr.send();
}

const bookingBtn = document.getElementById("club-details");