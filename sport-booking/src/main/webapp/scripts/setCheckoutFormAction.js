let bookingForm = document.getElementById('bookingForm');
let stripeBtn = document.getElementById('checkoutStripe');
let transferBtn = document.getElementById('checkoutTransfer');

stripeBtn.addEventListener('click', () => {
    bookingForm.action = "./create-checkout-session";
    bookingForm.submit();
});

transferBtn.addEventListener('click', () => {
    bookingForm.action = "./success";
    bookingForm.submit();
});