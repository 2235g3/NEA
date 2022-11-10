const pass = document.getElementById('pass');
const passToggle = document.getElementById('passToggle');
const email = document.getElementById('email');
const submit = document.getElementById('submit');
const errorOne = document.getElementById('errorOne');

passToggle.addEventListener('click',function(e) {
    const type = pass.getAttribute('type') === 'password' ? 'text' : 'password';
    pass.setAttribute('type',type);
    this.classList.toggle('bi-eye');
});

submit.addEventListener('click',function(e) {
    errorOne.innerHTML = "";
    if (email.value != "" && pass.value != "") {
        location.replace("/logIn?logInDetails=" + email.value + "," + pass.value);
    }
    else {
        errorOne.innerHTML = "One or more entries are blank!";
    }
});

if (error == "Incorrect") {
    errorOne.innerHTML = "Email or password is wrong";
}
if (loggedIn != "False" && accountType == "1") {
    location.replace("/custAccount");
}

