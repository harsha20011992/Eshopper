function getAppDetails(){

	var userNameValue = getCookie("username");
	if(userNameValue != ""){
		var shopUserNameElement = document.getElementById("shopUserNameElement");  
		shopUserNameElement.innerHTML = "Hi " + "<b>" + userNameValue + "</b>";
		shopUserNameElement.className = "inactiveLink";
		}
	loadCategory();	
	}	
	
function loadCategory(){
console.log("Inside loadCategory()");
	var AJAXRequestObject;
	if (window.XMLHttpRequest){// If the browser if IE7+[or]Firefox[or]Chrome[or]Opera[or]Safari
		AJAXRequestObject=new XMLHttpRequest();
		}
	else{//If browser is IE6, IE5
		AJAXRequestObject=new ActiveXObject("Microsoft.XMLHTTP");
		}
	AJAXRequestObject.onreadystatechange=function(){
		if (AJAXRequestObject.status==200 && AJAXRequestObject.readyState == 4 ){
			var ShopDtlsFromServer = AJAXRequestObject.responseText.trim();
			console.log("ShopDtlsFromServer returned is:" + ShopDtlsFromServer);
			if(ShopDtlsFromServer != ""){
			console.log(ShopDtlsFromServer);
			var JSONShopDtls = JSON.parse(ShopDtlsFromServer);
			// category loading starts here
			categoryParser(JSONShopDtls.category,"subcategoryname");
			productParser(JSONShopDtls.products,true,"Features Items");	
				}
				else{
				/*	var loginErrorText = document.getElementById("loginErrorText");
						loginErrorText.innerHTML = "User name/Password is incorrect" ;
						loginErrorText.className = "loginErrorText";*/
				}
			}
		}
		
	AJAXRequestObject.open("POST", "ShopPageLoaderServlet", true)
	AJAXRequestObject.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
	AJAXRequestObject.send()
}		

function loadCategoryWithCriteria(category){
console.log("Inside loadCategoryWithCriteria()");
	var AJAXRequestObject;
	if (window.XMLHttpRequest){// If the browser if IE7+[or]Firefox[or]Chrome[or]Opera[or]Safari
		AJAXRequestObject=new XMLHttpRequest();
		}
	else{//If browser is IE6, IE5
		AJAXRequestObject=new ActiveXObject("Microsoft.XMLHTTP");
		}
	AJAXRequestObject.onreadystatechange=function(){
		if (AJAXRequestObject.status==200 && AJAXRequestObject.readyState == 4 ){
			var ShopDtlsFromServer = AJAXRequestObject.responseText.trim();
			console.log("ShopDtlsFromServer returned is:" + ShopDtlsFromServer);
			if(ShopDtlsFromServer != ""){
			console.log(ShopDtlsFromServer);
			var JSONShopDtls = JSON.parse(ShopDtlsFromServer);
			// category loading starts here
			//categoryParser(JSONShopDtls.category,"subcategoryname");
			productParser(JSONShopDtls.products,false,category);	
				}
				else{
				/*	var loginErrorText = document.getElementById("loginErrorText");
						loginErrorText.innerHTML = "User name/Password is incorrect" ;
						loginErrorText.className = "loginErrorText";*/
				}
			}
		}
	
	AJAXRequestObject.open("GET", "ShopPageLoaderServlet?category=" + category, true)
	AJAXRequestObject.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
	AJAXRequestObject.send()
}

function showProductDetails(productname,price){
setCookie("productname",productname,3);
setCookie("price",price,3);
window.location="product-details.html";
}
	
window.onload = getAppDetails();