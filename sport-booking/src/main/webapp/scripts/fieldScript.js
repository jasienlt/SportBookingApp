import {selectedHour} from "./hourScript.js";
import {selectedDays} from "./dayScripts.js";

const selectedDay = document.getElementById("bookingDate");
const fieldsBtn = document.getElementsByClassName('fieldBtn');

const submitBookingBtn = document.getElementById("submitBtn");
export let selectedFields = new Set()
export function enableFieldSelected() {
  for (let i = 0; i < fieldsBtn.length; i++) {
    fieldsBtn[i].disabled = false;
  }
}

export function disableFieldSelected() {
  for (let i = 0; i < fieldsBtn.length; i++) {
    fieldsBtn[i].disabled = true;
    fieldsBtn[i].style.backgroundColor = 'white';
    selectedFields.clear();
    submitBookingBtn.disabled = true;
  }
}

for (let i = 0; i < fieldsBtn.length; i++) {
  fieldsBtn[i].addEventListener('click', function onClick() {
    fieldsBtn[i].style.backgroundColor =
      fieldsBtn[i].style.backgroundColor === 'salmon' ? 'white' : 'salmon';
    fieldsBtn[i].style.backgroundColor === 'salmon' ? selectedFields.add(i) : selectedFields.delete(i)
    submitBookingBtn.disabled = !(selectedHour.size === 2 && selectedDays.size > 0 && selectedFields.size > 0 && selectedDay.value !== "");
  });
}

submitBookingBtn.addEventListener('click', function onClick() {
  selectedFields.forEach(key => document.getElementById("selectedFields").value += `${key + 1} `)
})

