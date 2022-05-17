// Form Validation
var pwd = document.getElementById('pwd');
var eye = document.getElementById('eye');

eye.addEventListener('click', togglePass);

function togglePass() {

	eye.classList.toggle('active');

	(pwd.type == 'password') ? pwd.type = 'text' : pwd.type = 'password';
}


function checkStuff() {
	var username = document.form1.username;
	var password = document.form1.password;
	var msg = document.getElementById('msg');

	if (username.value == "") {
		msg.style.display = 'block';
		msg.innerHTML = "Vui lòng nhập username";
		username.focus();
		return false;
	} else {
		msg.innerHTML = "";
	}

	if (password.value == "") {
		msg.innerHTML = "Vui lòng nhập mật khẩu";
		password.focus();
		return false;
	} else {
		msg.innerHTML = "";
	}
}