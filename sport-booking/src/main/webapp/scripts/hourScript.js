import { selectedDays } from './dayScripts.js';

const hourBtn = document.getElementsByClassName('hourBtn');
export let selectedHour = new Set();
let tmp = new Set();

function setColor(hourBtn) {
  hourBtn.style.backgroundColor =
    hourBtn.style.backgroundColor == 'salmon' ? '#0d6efd' : 'salmon';
  hourBtn.style.borderColor = 'white';
}

function selectRange(start, end) {
  for (let i = start + 1; i < end; i++) {
    setColor(hourBtn[i]);
  }
}

function setDefaultExcept(except) {
  for (let i = 0; i < hourBtn.length; i++) {
    hourBtn[i].style.backgroundColor = i == except ? 'salmon' : '#0d6efd';
    hourBtn[i].style.borderColor = 'white';
  }
}

for (let i = 0; i < hourBtn.length; i++) {
  hourBtn[i].addEventListener('click', function onClick() {
    setColor(hourBtn[i]);
    if (!tmp.has(i)) tmp.add(i);
    if (tmp.size == 2) {
      selectedHour = tmp;
      selectRange(Math.min(...tmp), Math.max(...tmp));
      if (selectedDays.size != 0) {
        enableFieldSelected();
      }
      tmp = new Set();
    } else {
      selectedHour = new Set();
      setDefaultExcept(i);
      disableFieldSelected();
    }
  });
}
