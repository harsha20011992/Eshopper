function actionLogin(){
	console.log("Inside actionLogin()");
	var AJAXRequestObject;
	if (window.XMLHttpRequest){// If the browser if IE7+[or]Firefox[or]Chrome[or]Opera[or]Safari
		AJAXRequestObject=new XMLHttpRequest();
		}
	else{//If browser is IE6, IE5
		AJAXRequestObject=new ActiveXObject("Microsoft.XMLHTTP");
		}
	AJAXRequestObject.onreadystatechange=function(){
		if (AJAXRequestObject.status==200 && AJAXRequestObject.readyState == 4 ){
			var userNameFromServer = AJAXRequestObject.responseText.trim();
			console.log("userNameFromServer returned is:" + userNameFromServer);
			if(userNameFromServer != "USER_NOT_REGISTERED"){
				setCookie("username",userNameFromServer,1);
				window.location="index.html";
				}
				else{
					var loginErrorText = document.getElementById("loginErrorText");
						loginErrorText.innerHTML = "User name/Password is incorrect" ;
						loginErrorText.className = "loginErrorText";
				}
			}
		}
	var localUsername = document.getElementById("ElemIdUsername");
	var localPassword = document.getElementById("ElemIdPassword");
	var postParameters="first_name=" + localUsername.value + "&password=" + localPassword.value ;
	AJAXRequestObject.open("POST", "LoginServlet", true)
	AJAXRequestObject.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
	AJAXRequestObject.send(postParameters)
	}	

	
function actionSignUp(){

console.log("Inside actionSignUp()");
	var AJAXRequestObject;
	if (window.XMLHttpRequest){// If the browser if IE7+[or]Firefox[or]Chrome[or]Opera[or]Safari
		AJAXRequestObject=new XMLHttpRequest();
		}
	else{//If browser is IE6, IE5
		AJAXRequestObject=new ActiveXObject("Microsoft.XMLHTTP");
		}
	AJAXRequestObject.onreadystatechange=function(){
		if (AJAXRequestObject.status==200 && AJAXRequestObject.readyState == 4 ){
		//alert(AJAXRequestObject.responseText);	
			var userNameFromServer = AJAXRequestObject.responseText.trim();
			console.log("userNameFromServer returned is:" + userNameFromServer);
			alert(userNameFromServer);
			if(userNameFromServer != "EmailAlreadyExists"){ //Need to write server logic in future to send this error code (EmailAlreadyExists)
				setCookie("username",userNameFromServer,2);
				window.location="index.html";
				}
				else{
				//once the error code  'EmailAlreadyExists' comes from server , need to strengthen this error display part
					/*var loginErrorText = document.getElementById("loginErrorText");
						loginErrorText.innerHTML = "User name/Password is incorrect" ;
						loginErrorText.className = "loginErrorText";*/
				}
			}
		}
var SignUpUsername = document.getElementById("SignUpIdUsername");
var SignUpEmail = document.getElementById("SignUpIdEmail");
var SignUpPassword = document.getElementById("SignUpIdPassword");
var SignUpDOB = document.getElementById("SignUpIdDOB");
var SignUpMobileNum = document.getElementById("SignUpIdMobileNum");

var postParameters="sign_up_user_name=" + SignUpUsername.value + "&sign_up_email=" + SignUpIdEmail.value + "&sign_up_password=" + SignUpIdPassword.value + "&sign_up_DOB=" + SignUpIdDOB.value + "&sign_up_Phone Number=" + SignUpIdMobileNum.value;

AJAXRequestObject.open("POST", "SignUpServlet", true)
AJAXRequestObject.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
AJAXRequestObject.send(postParameters)

}	
	
	
function getLoginAppDetails(){
	var userNameValue = getCookie("username");
	if(userNameValue != ""){
		window.location="index.html";
		}
	}		
	
window.onload = getLoginAppDetails();	