function getAppDetails(){

	var userNameValue = getCookie("username");
	console.log("username retreived frim cookie is" + userNameValue);
	if(userNameValue != ""){
		var indexUserNameElement = document.getElementById("indexUserNameElement");  
		indexUserNameElement.innerHTML = "Hi " + "<b>" + userNameValue + "</b>";
		indexUserNameElement.className = "inactiveLink";
		}
	}	
	
window.onload = getAppDetails();