const passToggle = document.getElementById("passToggle");
const reenterPassToggle = document.getElementById('reenterPassToggle');
const password = document.getElementById('password');
const reenterPass = document.getElementById('reenterPass');
const FName = document.getElementById('FName');
const LName = document.getElementById('LName');
const email = document.getElementById('email');
const promoEmails = document.getElementById('promoEmails');
const submit = document.getElementById('signUp');

passToggle.addEventListener('click', function(e) {
    const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
    password.setAttribute('type', type);
    this.classList.toggle('bi-eye');
});

reenterPassToggle.addEventListener('click', function(e) {
    const type = reenterPass.getAttribute('type') === 'password' ? 'text' : 'password';
    reenterPass.setAttribute('type', type);
    this.classList.toggle('bi-eye');
});

submit.addEventListener('click', function(e) {
    errorOne.innerHTML = "";
    errorTwo.innerHTML = "";
    errorThree.innerHTML = "";
    if (password.value == reenterPass.value && (FName.value != "" && LName != "" && email != "" && password != "")) {
        location.replace("/signUp?accountDetails=" + FName.value + "," + LName.value + "," + email.value + "," + password.value + "," + promoEmails.value);
    }
    else {
        if (password.value != reenterPass.value) {
            errorOne.innerHTML = "Passwords do not match";
            password.value = "";
            reenterPass.value = "";
        }
        if (FName.value != "" || LName != "" || email != "" || password != "") {
            errorTwo.innerHTML("One or more values are invalid");
        }
    }
});

if (failed == "False") {
    location.replace("/logIn?logInDetails=");
    alert("Account created!");
}
else {
    if (emailError == "" && passwordError == "" && existsError == "") {
    }
    else {
        if (passwordError != "") {
            document.getElementById('errorOne').innerHTML = 'Password must be between 8 and 20 characters, and contain one uppercase letter; one lowercase letter; one number; and one symbol';
        }
        else if (emailError != "") {
            document.getElementById('errorTwo').innerHTML = 'Email is in an invalid format';
        }
        else if (existsError != "") {
            document.getElementById('errorThree').innerHTML = 'An account with that email already exists';
        }
    }
}

