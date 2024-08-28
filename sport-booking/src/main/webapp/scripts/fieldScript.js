import {selectedHour, setDefaultExcept} from "./hourScript.js";
import {selectedDays} from "./dayScripts.js";
import {dayOfWeek} from "./utils.js";

const selectedDay = document.getElementById("bookingDate");
const fieldsBtn = document.getElementsByClassName('fieldBtn');
const submitBookingBtn = document.getElementById("submitBtn");
const customerName = document.getElementById("customerNameInput").value;
const customerEmail = document.getElementById("customerEmail").value;
let reservedFieldTimeslots = [];
let pendingFieldTimeslots = [];
export let selectedFields = new Set();
var dateInput = document.getElementById('bookingDate');

function retrieveBookedFieldsFromDb() {
    var selectedDate = dateInput.value;

    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/booking', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            if (response.reservedFieldTimeslots) {
                reservedFieldTimeslots = response.reservedFieldTimeslots;
                pendingFieldTimeslots = response.pendingFieldTimeslots;
                console.log(reservedFieldTimeslots);
                console.log(pendingFieldTimeslots);
                setDefaultExcept(-1);
                disableFieldSelected();
            } else {
                console.error('Invalid response');
            }

        }
    };
    const data = {
        type: "date",
        date: selectedDate
    }
    xhr.send(JSON.stringify(data));
}
document.addEventListener('DOMContentLoaded', function() {
    dateInput.addEventListener('change', retrieveBookedFieldsFromDb);
    window.addEventListener('pageshow', retrieveBookedFieldsFromDb);
});

function getAllBookedAndPendingFields() {
    const bookedFields = new Set();
    const pendingFields = new Set();
    console.log(selectedDays);
    reservedFieldTimeslots.forEach(reservedFieldTimeslot => {
       if(selectedDays.has(dayOfWeek.indexOf(reservedFieldTimeslot.fieldTimeslot.day) + 1) && selectedHour.has(reservedFieldTimeslot.fieldTimeslot.timeslot.id)) {
           bookedFields.add(reservedFieldTimeslot.fieldTimeslot.field.id)
       }
    });

    pendingFieldTimeslots.forEach(pendingFieldTimeslot => {
        if(selectedDays.has(dayOfWeek.indexOf(pendingFieldTimeslot.fieldTimeslot.day) + 1) && selectedHour.has(pendingFieldTimeslot.fieldTimeslot.timeslot.id)) {
            pendingFields.add(pendingFieldTimeslot.fieldTimeslot.field.id)
        }
    });

    return [bookedFields, pendingFields]
}



export function enableFieldSelected() {
    const [bookedFields, pendingFields] = getAllBookedAndPendingFields();

    console.log(bookedFields);

    for (let i = 0; i < fieldsBtn.length; i++) {
        if(!bookedFields.has(i + 1) && !pendingFields.has(i + 1)) {
            fieldsBtn[i].disabled = false;
        }
        else if(bookedFields.has(i + 1)){
            fieldsBtn[i].style.backgroundColor = '#ff8c8c';
            fieldsBtn[i].style.color = 'white';
            const imgElement = document.querySelector(`img[alt="${(i + 1).toString()}"]`);
            imgElement.style.opacity = "0.5";
        }
        else {
            fieldsBtn[i].style.backgroundColor = '#ade0ff';
            fieldsBtn[i].style.color = 'white';
            const imgElement = document.querySelector(`img[alt="${(i + 1).toString()}"]`);
            imgElement.style.opacity = "0.5";
        }
    }
}

export function disableFieldSelected() {
  for (let i = 0; i < fieldsBtn.length; i++) {
    fieldsBtn[i].disabled = true;
    fieldsBtn[i].style.backgroundColor = 'white';
    selectedFields.clear();
    submitBookingBtn.disabled = true;
    const imgElement = document.querySelector(`img[alt="${(i + 1).toString()}"]`);
    imgElement.style.opacity = "1";
    fieldsBtn[i].style.color = 'black';
  }
}

for (let i = 0; i < fieldsBtn.length; i++) {
  fieldsBtn[i].addEventListener('click', function onClick() {
    fieldsBtn[i].style.backgroundColor =
      fieldsBtn[i].style.backgroundColor === 'salmon' ? 'white' : 'salmon';
    fieldsBtn[i].style.backgroundColor === 'salmon' ? selectedFields.add(i) : selectedFields.delete(i)
    submitBookingBtn.disabled = !(selectedHour.size === 2 && selectedDays.size > 0 && selectedFields.size > 0 && selectedDay.value !== "" && customerName !== null && customerEmail !== null);
  });
}

submitBookingBtn.addEventListener('click', function onClick() {
  selectedFields.forEach(key => document.getElementById("selectedFields").value += `${key + 1} `)
})

