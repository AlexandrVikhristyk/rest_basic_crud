(function () {
	'use strict'
	let content = document.querySelectorAll(".container span");
	let theme = document.getElementById("theme");

	let data = {};

	for (let i = 0; i < content.length; i++) {
		content[i].addEventListener("click", function() {
			content[i].id = data.theme_id = `${content[i].id}`;
		});
	}

	theme.onclick = async function() {

		let response = await fetch(`http://localhost:8080/theme`, {
	        method: 'POST',
	        body: JSON.stringify(data),
	        headers: {
	          'Content-Type': 'application/json'
	        }
	    });

	  	let json = await response.json();
	} 
	
})();