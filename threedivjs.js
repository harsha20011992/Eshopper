var isTextBoxClicked = false;
window.onload = function() {
  alert("Test");
  
  
  document.addEventListener('click', function(e) {
    /*var target = e.target
        var text = target.textContent;*/
		document.getElementById("clickHandlerSecondDiv").textContent="Some other element is clicked";
		isTextBoxClicked = false;
}, false);
  

  document.getElementById("ListItems").addEventListener("click", function(e) {
	// e.target is the clicked element!
	// If it was a list item
	if(e.target && e.target.nodeName == "LI") {
		// List item found!  Output the ID!
		//alert("ListItemFound");
		swapnodes(e.target);
		//alert("e.target.innerHTML is " + e.target.innerHTML);
	}
});  



document.getElementById("second").addEventListener("click", function(e) {
	// e.target is the clicked element!
	// If it was a list item]
	
	if(e.target && e.target.nodeName == "INPUT") {
		// List item found!  Output the ID!
		//alert("INPUTFound");
		  document.getElementById("clickHandlerSecondDiv").textContent="You have clicked TextBox";
		//alert("e.target.innerHTML is " + e.target.innerHTML);
		isTextBoxClicked = true;
		e.stopPropagation();
		
	}
	else{
	//alert("In Second div");
	if(isTextBoxClicked){
	document.getElementById("clickHandlerSecondDiv").textContent="You have clicked DIV after TextBox"
	}else
	{
	document.getElementById("clickHandlerSecondDiv").textContent="You have clicked only DIV"
	
	}
	e.stopPropagation();
	isTextBoxClicked = false;
	}
});  
}; 

 function addItem(){
		var input = document.getElementById("listInputValue");
		if(input.value == ""){
		alert("Enter valid value");
		}
		else{
        var li = document.createElement("LI");  
        
        li.innerHTML = input.value;
        input.value = "";

        document.getElementById("ListItems").appendChild(li);
		}
    }

function swapnodes(liElement){
//alert("InsideSwap");
if(liElement.parentNode.lastChild.isSameNode(liElement)){
var liTempfirstNode = liElement.parentNode.children[0];
liElement.parentNode.replaceChild(liElement,liElement.parentNode.childNodes[0]);
var appendChild = liElement.parentNode.appendChild(liTempfirstNode);
}
else{
 var nextElement =   liElement.nextSibling;
liElement.parentNode.insertBefore(nextElement,liElement);
//alert("InsideSwapNormal");
}
} 	

  
   function UpdateFlyingObj(e){
            var mouseX = Math.round (event.clientX);
            var mouseY = Math.round (event.clientY);
            var flyingObj = document.getElementById("MouseDiv");
			flyingObj.textContent = "mouseX " + mouseX + "   mouseY: " + mouseY;
            flyingObj.style.left = mouseX + "px";
            flyingObj.style.top = mouseY + "px";
        }
		
