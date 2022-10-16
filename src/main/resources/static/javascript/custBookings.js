const bookingWindow = document.getElementById("bookingWindow");

const viewButton = document.getElementById("view");
viewButton.addEventListener("click", function(e) {
    bookingWindow.setAttribute("data","/html/custViewBookings.html")
});

const editButton = document.getElementById("edit");
editButton.addEventListener("click", function(e) {
    bookingWindow.setAttribute("data","/html/custEditBookings.html")
});

const bookingButton = document.getElementById("booking");
bookingButton.addEventListener("click", function(e) {
    bookingWindow.setAttribute("data","/html/custMakeBookings.html")
});