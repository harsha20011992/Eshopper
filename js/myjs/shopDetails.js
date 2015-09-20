function getProductDetails(){

	var userNameValue = getCookie("username");
	console.log("username retreived from cookie is" + userNameValue);
	if(userNameValue != ""){
		var indexUserNameElement = document.getElementById("indexUserNameElement");  
		indexUserNameElement.innerHTML = "Hi " + "<b>" + userNameValue + "</b>";
		indexUserNameElement.className = "inactiveLink";
		}
		var productNameParentElement = document.getElementById("productinformation");
		
	var productNameValue = getCookie("productname");
	console.log("Productname retreived frim cookie is" + productNameValue);
	if(productNameValue != ""){
		var productName	= productNameParentElement.getElementsByTagName("H2")[0];
		productName.innerHTML = productNameValue;
		}

	var productPriceValue = getCookie("price");
	console.log("Product price retreived frim cookie is" + productPriceValue);
	if(productPriceValue != ""){
		
		var productprice	= document.getElementById("productprice");
		productprice.innerHTML = "RS." + productPriceValue;
		}
	
	}	


window.onload = getProductDetails();