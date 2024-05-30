const fieldsBtn = document.getElementsByClassName('fieldBtn');

function enableFieldSelected() {
  for (let i = 0; i < fieldsBtn.length; i++) {
    fieldsBtn[i].disabled = false;
  }
}

function disableFieldSelected() {
  for (let i = 0; i < fieldsBtn.length; i++) {
    fieldsBtn[i].disabled = true;
    fieldsBtn[i].style.backgroundColor = 'white';
  }
}

for (let i = 0; i < fieldsBtn.length; i++) {
  fieldsBtn[i].addEventListener('click', function onClick() {
    fieldsBtn[i].style.backgroundColor =
      fieldsBtn[i].style.backgroundColor === 'salmon' ? 'white' : 'salmon';
  });
}
