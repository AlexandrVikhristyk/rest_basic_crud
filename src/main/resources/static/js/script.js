(function () {
	'use strict'
	let formaRegistration = document.getElementById("formaRegistration");
	let error = document.querySelector(".error-registration");

	formaRegistration.onsubmit = async (e) => {
  if(e.target.elements.password.value === e.target.elements.confirmPassword.value) {
    let data = {
    email: e.target.elements.login.value,
    password: e.target.elements.password.value,
    confirmPasword: e.target.elements.confirmPassword.value
  };
  
    e.preventDefault();
    error.style.display = "none";

    let response = await fetch(`http://localhost:8080/user`, {
      method: 'POST',
      body: JSON.stringify(data),
      headers: {
      'Content-Type': 'application/json'
    }
    });

    formaRegistration.reset();

    let json = await response.json();
  
  } else {
    error.style.display = "block";
    error.innerHTML = "Паролі не співпадають!";
    e.preventDefault();
  } 
};

formaLogin.onsubmit = async (e) => {
  
  let data = {
    email: e.target.elements.login.value,
    password: e.target.elements.password.value
  };

  console.log(e.target.elements.login.value + " " + e.target.elements.password.value);
  
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
};



















let items = document.getElementsByClassName("enter");

for(let i = 0; i < items.length; i++) {
  items[i].addEventListener("click", addFlip);
}

function addFlip(e) {
  let even = e || window.e;

  let target = even.target;

  if( !target.closest(".item.flip")) {
    target.closest(".item").classList.add("flip");
    return;
  }

  target.closest(".item").classList.remove("flip");
  
}
})();