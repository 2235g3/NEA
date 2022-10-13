const pass = document.getElementById('pass');
const passToggle = document.getElementById('passToggle');

passToggle.addEventListener('click',function(e) {
    const type = pass.getAttribute('type') === 'password' ? 'text' : 'password';
    pass.setAttribute('type',type);
    this.classList.toggle('bi-eye');
});