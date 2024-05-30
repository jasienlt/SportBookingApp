const dayOfWeek = [
  'Mon',
  'Tue',
  'Wed',
  'Thurs',
  'Fri',
  'Sat',
  'Sun',
  'All Days',
];

const startBookingTime = 5;
const endBookingTime = 24;

const numsOfField = 24;

function convertToTime(i) {
  let ans = '';
  let hour = '';
  let min = '';
  if (i % 10 === 0) {
    hour += i / 10 > 9 ? i / 10 : '0' + i / 10;
    min += '00';
  } else {
    hour += i / 10 > 10 ? i / 10 - 0.5 : '0' + (i / 10 - 0.5);
    min += (i % 10) * 6;
  }

  return hour + ':' + min;
}

function generateHourBtn(time) {
  return `<button class="btn btn-primary mx-1 my-1 hourBtn" type="button" value="${time}">${convertToTime(
    time
  )}</button>`;
}

function generateDateCheckbox(date) {
  return `<div class="form-check form-check-inline checkbox">
            <input class="form-check-input bookingDayCb" type="checkbox" id="day-${date}" value="${date}">
            <label class="form-check-label" for="day-${date}">
              ${dayOfWeek[date]}
            </label>
          </div>`;
}

function generateField(fieldNum) {
  return `<div class="button-overlay mx-2 my-3">
            <img class="fieldImg" src="resource/image/field.png" alt="field"/>
            <button class="btn btn-light btn-sm fieldBtn" type="button" disabled>${
              fieldNum + 1
            }</button>
            <small>50k/h</small>
          </div>`;
}
