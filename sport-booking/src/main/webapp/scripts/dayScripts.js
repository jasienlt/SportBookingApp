import { selectedHour } from './hourScript.js';
import {enableFieldSelected, disableFieldSelected} from './fieldScript.js';

const bookingDayCb = document.getElementsByClassName('bookingDayCb');
const allDaysCb = document.getElementById('day-7');
export const selectedDays = new Set();

function checkAllDayCb() {
  for (let i = 0; i < bookingDayCb.length; i++) {
    bookingDayCb[i].checked = true;
  }
}

function unCheckAllDayCb() {
  for (let i = 0; i < bookingDayCb.length; i++) {
    bookingDayCb[i].checked = false;
  }
}

function updateSelectedDays() {
  for (let i = 0; i < bookingDayCb.length - 1; i++) {
    bookingDayCb[i].checked === true
      ? selectedDays.add(i + 1)
      : selectedDays.delete(i + 1);
  }
}

function checkAllDays() {
  allDaysCb.checked === true ? checkAllDayCb() : unCheckAllDayCb();
  updateSelectedDays();
  if (selectedDays.size > 0 && selectedHour.size === 2) {
    enableFieldSelected();
  } else {
    disableFieldSelected();
  }
}

allDaysCb.addEventListener('change', checkAllDays);

for (let i = 0; i < bookingDayCb.length - 1; i++) {
  bookingDayCb[i].addEventListener('change', function check() {
    updateSelectedDays();
    allDaysCb.checked = selectedDays.size === 7 && bookingDayCb[i].checked;
    if (selectedDays.size > 0 && selectedHour.size === 2) {
      enableFieldSelected();
    } else {
      disableFieldSelected();
    }
  });
}
