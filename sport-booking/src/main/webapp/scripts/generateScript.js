for (let i = startBookingTime * 10; i <= endBookingTime * 10; i += 5) {
  document.getElementById('hourGroupBtn').innerHTML += generateHourBtn(i);
}

for (let i = 0; i < dayOfWeek.length; i++) {
  document.getElementById('dateInWeek').innerHTML += generateDateCheckbox(i);
}

for (let i = 0; i < numsOfField; i++) {
  document.getElementById('fields').innerHTML += generateField(i);
}
