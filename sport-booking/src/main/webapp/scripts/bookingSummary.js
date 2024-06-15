import {selectedHour} from "./hourScript.js";
import {selectedDays} from "./dayScripts.js";
import {selectedFields} from "./fieldScript.js";
import {dayOfWeek} from "./utils.js"

const selectedFieldsSummary = document.getElementById("selectedFieldsSummary");
const priceSummary = document.getElementById("priceSummary");
const selectedDatesText = document.getElementById("bookingDates");
const bookingTimeText = document.getElementById("bookingTime");

let selectedFieldsText = "";

document
    .getElementById('bookingForm')
    .addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent default form submission

        const fields = document.getElementById("selectedFields").value;
        const startBookingTime = document.getElementById("selectedStartTimeslot").value;
        const endBookingTime = document.getElementById("selectedEndTimeslot").value;
        const dates = document.querySelectorAll('input[name="date"]:checked');
        const selectedDates = Array.from(dates).map(date => date.value);

        const xhr = new XMLHttpRequest();
        xhr.open('POST', '/booking', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

        // Handle the response
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    // Parse the JSON response
                    const response = JSON.parse(xhr.responseText);

                    let sortedSelectedFields = new Set([...selectedFields].sort())
                    sortedSelectedFields.forEach(key => selectedFieldsText += `Field ${key + 1} - `);
                    selectedFieldsSummary.textContent += selectedFieldsText.substring(0, selectedFieldsText.length - 3);

                    selectedDates.forEach(date => {
                        if (date !== '8') {
                            selectedDatesText.textContent += `${dayOfWeek[date - 1]} `
                        }
                    });
                    priceSummary.textContent += response.price.toString();

                    // Display summary in popup
                    document.getElementById('overlay').style.display = 'block';
                    document.getElementById('popup').style.display = 'block';
                } else {
                    alert('An error occurred. Please try again.');
                }
            }
        };
        const data = `fields=${encodeURIComponent(fields)}&startBookingTime=${encodeURIComponent(startBookingTime)}
                            &endBookingTime=${encodeURIComponent(endBookingTime)}
                            &dates=${encodeURIComponent(selectedDates.join(" "))}`
        xhr.send(data);
    });

function resetBookingSummary() {
    //document.getElementById('bookingForm').reset();
    document.getElementById("selectedFields").value = "";
    selectedFieldsSummary.textContent = "Fields: ";
    priceSummary.textContent = "Price: ";
    selectedFieldsText = "";
    selectedDatesText.textContent = "Dates: ";
}

// Close popup when the close button is clicked
document
    .getElementById('closePopup')
    .addEventListener('click', function () {
        resetBookingSummary();
        document.getElementById('overlay').style.display = 'none';
        document.getElementById('popup').style.display = 'none';
    });

// Close popup when clicking outside of the popup
document.getElementById('overlay').addEventListener('click', function () {
    resetBookingSummary();
    document.getElementById('overlay').style.display = 'none';
    document.getElementById('popup').style.display = 'none';
});

document.getElementById("checkout").addEventListener('click', function () {
    document.getElementById("bookingForm").submit();
})

window.addEventListener('pageshow', function(event) {
    const navEntries = performance.getEntriesByType('navigation');
    if (navEntries.length > 0 && (navEntries[0].type === 'back_forward' || event.persisted)) {
        // Page was restored from the Back-Forward cache or navigated back
        resetBookingSummary();
    }
});