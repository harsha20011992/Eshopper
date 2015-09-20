function setCookie(CookieKey,CookieValue,expiryTimeInMinutes){
	var currentTime = new Date(); 
	console.log("current Date:" + currentTime.toGMTString());
	var expireTime = currentTime.getTime() +(1000*60*expiryTimeInMinutes); //milis * seconds * minutes
	currentTime.setTime(expireTime);
	console.log("Expiry Time:" + currentTime.toGMTString());
	document.cookie = CookieKey+'='+CookieValue+';expires='+currentTime.toGMTString()+';path=/';
	console.log("Cookie is set for key: " + CookieKey); 
}


function getCookie(cookieKey) {
    var localCookieKey="";
	var localCookieValue="";
	
	if (document.cookie && document.cookie.indexOf(cookieKey+"=") != -1) {
		var cookieArray = document.cookie.split(';');
    for(var i=0; i<cookieArray.length; i++) {
        //var singleCookie = cookieArray[i];
		console.log("cookiename: " + cookieArray[i].split('=')[0] + " cookievalue: " + cookieArray[i].split('=')[1]);
		 
		 localCookieKey = cookieArray[i].split('=')[0].trim();
		 //console.log("cookieKey: " +  cookieKey + " LocalCookieKey: " + localCookieKey + " Locale compare value " + localCookieKey.localeCompare(cookieKey));
		 if(localCookieKey.localeCompare(cookieKey) == 0){
                  localCookieValue = cookieArray[i].split('=')[1];
				  console.log("cookie found: Key is : " + localCookieKey + " and Value is : " + localCookieValue);
				  return localCookieValue;
				  }
                  
				  
       
    }
	console.log("Global value: Key is : " + localCookieKey + " and Value is : " + localCookieValue);
    return localCookieValue;
    } else {
	 console.log("Cookie disabled or not found: Key is : " + localCookieKey + " and Value is : " + localCookieValue);
       return localCookieValue;
		}
    
}