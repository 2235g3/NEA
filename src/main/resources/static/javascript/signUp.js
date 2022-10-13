const passToggle = document.getElementById("passToggle");
const reenterPassToggle = document.getElementById('reenterPassToggle');
const password = document.getElementById('password');
const reenterPass = document.getElementById('reenterPass');

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