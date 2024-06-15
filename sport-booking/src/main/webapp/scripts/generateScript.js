import {dayOfWeek, generateDateCheckbox} from "./utils.js";


for (let i = 0; i < dayOfWeek.length; i++) {
  document.getElementById('dateInWeek').innerHTML += generateDateCheckbox(i);
}
