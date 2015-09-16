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
			alert(ShopDtlsFromServer);
			var JSONShopDtls = JSON.parse(ShopDtlsFromServer);
			alert(JSONShopDtls.category[1].categoryname);
			
			/*var shopCategoryElement = document.getElementById("shopCategory");  
		shopCategoryElement.innerHTML = JSONShopDtls.category[1].categoryname;
		shopUserNameElement.className = "inactiveLink";*/
		
			var parentDiv = document.getElementById("accordian")
			//console.log(parentDiv.innerHTML);
				
			//console.log(childDiv.innerHTML);
			//childDiv.innerHTML = "Test";
			for(var i=0;i<JSONShopDtls.category.length;i++){
			
			var jsonCategory = JSONShopDtls.category[i].categoryname;
			
			var divElement = document.createElement("DIV"); 
			divElement.className = "panel panel-default";
			
			var childDivElement = document.createElement("DIV"); 
			childDivElement.className = "panel-heading";
			
			
			var H4Element = document.createElement("H4"); 
			H4Element.className = "panel-title";
			
			
			var AElement = document.createElement("A"); 
			AElement.className = "categoryA";
			AElement.setAttribute("data-toggle", "collapse");
			AElement.setAttribute("data-parent", "#accordian");
			AElement.setAttribute("href", "#sportswear");
			
			var SpanElement = document.createElement("SPAN"); 
			SpanElement.className = "categoryName";
			
			SpanElement.innerHTML=jsonCategory;
			
			AElement.appendChild(SpanElement);
			
			H4Element.appendChild(AElement);
				
			childDivElement.appendChild(H4Element);
			
			divElement.appendChild(childDivElement);
			
			parentDiv.appendChild(divElement);
			console.log(parentDiv);
			
			
			/*var jsonCategory = JSONShopDtls.category[i].categoryname;
			
			console.log("i: "+ i + "value: " + jsonCategory);
			var childDiv = parentDiv.getElementsByClassName("panel-title")[i].children[0].getElementsByClassName("categoryName")[0];
			console.log("i: "+ i + "value: " + jsonCategory + childDiv.innerHTML);
			childDiv.innerHTML = "test";*/
			}
			
			
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
	
window.onload = getAppDetails();