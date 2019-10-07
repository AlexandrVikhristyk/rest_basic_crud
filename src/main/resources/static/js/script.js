(function () {
	'use strict'
	let formaRegistration = document.getElementById("formaRegistration");
	let error = document.querySelector(".error");

	formaRegistration.onsubmit = async (e) => {
	let data = {
		login: e.target.elements.login.value,
		password: e.target.elements.password.value,
    confirmPasword: e.target.elements.confirmPassword.value
	};

    e.preventDefault();

    chekingPassword(e.target.elements.password.value, e.target.elements.confirmPassword.value);        

    let response = await fetch(`http://localhost:8080/login`, {
      method: 'POST',
      body: JSON.stringify(data),
      headers: {
	    'Content-Type': 'application/json'
	  }
    });

    formaRegistration.reset();

    let json = await response.json();
  };

  function chekingPassword(password, confirmPassword) {
    if(password === confirmPassword) {
        alert("Дані успішно відправлені)");
        error.style.display = "none";
    } else {
        error.style.display = "block";
        error.innerHTML = "Паролі не співпадають!";
    }
  }
})();