import {selectedHour} from "./hourScript.js";
import {selectedDays} from "./dayScripts.js";

const fieldsBtn = document.getElementsByClassName('fieldBtn');

const submitBookingBtn = document.getElementById("submitBtn");
const selectedFields = new Set()
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
  }
}

for (let i = 0; i < fieldsBtn.length; i++) {
  fieldsBtn[i].addEventListener('click', function onClick() {
    fieldsBtn[i].style.backgroundColor =
      fieldsBtn[i].style.backgroundColor === 'salmon' ? 'white' : 'salmon';
    fieldsBtn[i].style.backgroundColor === 'salmon' ? selectedFields.add(i) : selectedFields.delete(i)
    if(selectedHour.size === 2 && selectedDays.size > 0 && selectedFields.size > 0) {
      submitBookingBtn.disabled = false;
    }
    else {
      submitBookingBtn.disabled = true;
    }
  });
}

submitBookingBtn.addEventListener('click', function onClick() {
  selectedFields.forEach(key => document.getElementById("selectedFields").value += key + " ")
})


