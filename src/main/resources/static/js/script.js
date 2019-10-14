(function () {
	'use strict'
	let formaRegistration = document.getElementById("formaRegistration");
  let formaLogin = document.getElementById("formaLogin");
	let errorRegistration = document.querySelector(".error-registration");
  let errorLogin = document.querySelector(".error-login");
  let validErrorRegistration = document.querySelector(".valid-error-registration");
  let forma = document.getElementsByClassName("forma");
  let items = document.getElementsByClassName("enter");

  for(let i = 0; i < forma.length; i++) {
    forma[i].addEventListener("submit", fetchRequest);
  }

  async function fetchRequest(e) {
    let even = e || window.e;

    let target = even.target;

    switch(e.target.name) {
      case 'formaRegistration': 
        validate(e.target.elements.login.value, e.target.elements.password.value);
        if(e.target.elements.password.value === e.target.elements.confirmPassword.value) {
          let data = {
          email: e.target.elements.login.value,
          password: e.target.elements.password.value,
          confirmPasword: e.target.elements.confirmPassword.value
        };
        
          e.preventDefault();
          errorRegistration.style.display = "none";

          let response = await fetch(`http://localhost:8080/user`, {
            method: 'POST',
            body: JSON.stringify(data),
            headers: {
            'Content-Type': 'application/json'
          }
          });

          formaRegistration.reset();
        
        } else {
          errorRegistration.innerHTML = "Паролі не співпадають!";
          e.preventDefault();
        } 

      break;

      case 'formaLogin': 
        let data = {
          email: e.target.elements.login.value,
          password: e.target.elements.password.value
        };
        
          e.preventDefault();

          let response = await fetch(`http://localhost:8080/login`, {
            method: 'POST',
            body: JSON.stringify(data),
            headers: {
            'Content-Type': 'application/json'
          }
          });

          formaLogin.reset();

          let json = await response.json();
      }
    }

function validateEmail(email) {
  let regularEmail = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return regularEmail.test(email);
}

function validatePassword(password) {
  let regularPassword = /^(?=(.*[a-z]){1,})(?=(.*[\d]){1,})(?=(.*[\W]){1,})(?!.*\s).{7,30}$/;
  return regularPassword.test(password);
}

function validate(email, password) {
  if(validateEmail(email) && validatePassword(password)) {
    validErrorLogin.style.display = "block";
  } else {
    validErrorRegistration.innerHTML = "Email or Password not valid!";
  }
  return false;
} 
})();