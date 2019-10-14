(function () {
	'use strict'
	let formaRegistration = document.getElementById("formaRegistration");
  let formaLogin = document.getElementById("formaLogin");
	let errorRegistration = document.querySelector(".error-registration");
  let errorLogin = document.querySelector(".error-login");
  let forma = document.getElementsByClassName("forma");

  for(let i = 0; i < forma.length; i++) {
    forma[i].addEventListener("submit", fetchRequest);
  }

  async function fetchRequest(e) {
    let even = e || window.e;

    let target = even.target;

    if(e.target.name === "formaRegistration") {
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

        let json = await response.json();
      
      } else {
        errorRegistration.style.display = "block";
        errorRegistration.innerHTML = "Паролі не співпадають!";
        e.preventDefault();
      } 
      return;

    } else if(e.target.name === "formaLogin") {
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

    


// 	formaRegistration.onsubmit = async (e) => {
//   if(e.target.elements.password.value === e.target.elements.confirmPassword.value) {
//     let data = {
//     email: e.target.elements.login.value,
//     password: e.target.elements.password.value,
//     confirmPasword: e.target.elements.confirmPassword.value
//   };
  
//     e.preventDefault();
//     error.style.display = "none";

//     let response = await fetch(`http://localhost:8080/user`, {
//       method: 'POST',
//       body: JSON.stringify(data),
//       headers: {
//       'Content-Type': 'application/json'
//     }
//     });

//     formaRegistration.reset();

//     let json = await response.json();
  
//   } else {
//     error.style.display = "block";
//     error.innerHTML = "Паролі не співпадають!";
//     e.preventDefault();
//   } 
// };

// formaLogin.onsubmit = async (e) => {
  
//   let data = {
//     email: e.target.elements.login.value,
//     password: e.target.elements.password.value
//   };

//   console.log(e.target.elements.login.value + " " + e.target.elements.password.value);
  
//     e.preventDefault();

//     let response = await fetch(`http://localhost:8080/login`, {
//       method: 'POST',
//       body: JSON.stringify(data),
//       headers: {
//       'Content-Type': 'application/json'
//     }
//     });

//     formaLogin.reset();

//     let json = await response.json();
// };

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

// let regularEmail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/; 
// let regularPassword = /^(?=(.*[a-z]){1,})(?=(.*[\d]){1,})(?=(.*[\W]){1,})(?!.*\s).{7,30}$/;

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
    errorRegistration.innerHTML = "Email or Password not valid!";
  } else {
    errorRegistration.innerHTML = "Email or Password correct)";
  }
  return false;
} 
})();