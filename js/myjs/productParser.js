function productParser(JSONShopDtlsProducts,isappend,H2category){
console.log("hello");
//product_Names = JSONShopDtlsProducts[0]["productname"];
product_Names = Object.keys(JSONShopDtlsProducts);
console.log("product_Names");

var FeaturesparentDiv = document.getElementById("featuresParentDiv");
var FirstH2Element = FeaturesparentDiv.getElementsByClassName("title text-center")[0];
console.log(FirstH2Element)
FirstH2Element.innerHTML= H2category;
var PaginationId = document.getElementById("FeaturespaginationID");
if(isappend == false){

var deleteDivs = FeaturesparentDiv.getElementsByClassName("col-sm-4");
console.log(deleteDivs);
for(var k=deleteDivs.length-1; k >= 0; k--){
console.log("length: " + deleteDivs.length);
console.log("removed child: "+ k + + " " + FeaturesparentDiv.removeChild(deleteDivs[k]));
}
}
//for(var i=0; i< JSONShopDtlsProducts.length; i++ ){

for(var i=0; i< product_Names.length; i++ ){


var FeaturesdivElement = document.createElement("DIV"); 
FeaturesdivElement.className = "col-sm-4";
FeaturesdivElement.addEventListener("click", function(e){
			productDetails(e)
			
			});

var ImageWrapperdivElement = document.createElement("DIV");
ImageWrapperdivElement.className = "product-image-wrapper";


var SingleProductdivElement = document.createElement("DIV"); 
SingleProductdivElement.className = "single-products";


var ProductInfodivElement = document.createElement("DIV"); 
ProductInfodivElement.className = "productinfo text-center";

var ImageElement = document.createElement("IMG");
ImageElement.setAttribute("src", "images/shop/product12.jpg");
ImageElement.setAttribute("alt", "");

var H2Element = document.createElement("H2");
//H2Element.innerHTML = JSONShopDtlsProducts[i]["prize"];
H2Element.innerHTML = JSONShopDtlsProducts[product_Names[i]]["prize"];

var PElement = document.createElement("P");
//PElement.innerHTML = JSONShopDtlsProducts[i]["productname"];
PElement.innerHTML = product_Names[i];


var AElement = document.createElement("A"); 
AElement.className = "btn btn-default add-to-cart";
AElement.setAttribute("href", "#");

var iCartElement = document.createElement("I"); 
iCartElement.className = "fa fa-shopping-cart";

var SpanElement = document.createElement("SPAN"); 
SpanElement.innerHTML="Add to cart";

AElement.appendChild(iCartElement);
AElement.appendChild(SpanElement);


ProductInfodivElement.appendChild(ImageElement);
ProductInfodivElement.appendChild(H2Element);
ProductInfodivElement.appendChild(PElement);
ProductInfodivElement.appendChild(AElement);



var ProductOverLaydivElement = document.createElement("DIV"); 
ProductOverLaydivElement.className = "product-overlay";

var OverLayContentdivElement = document.createElement("DIV"); 
OverLayContentdivElement.className = "overlay-content";


var H2Element = document.createElement("H2");
H2Element.innerHTML = JSONShopDtlsProducts[product_Names[i]]["prize"];

var PElement = document.createElement("P");
PElement.innerHTML = product_Names[i];

var AElement = document.createElement("A"); 
AElement.className = "btn btn-default add-to-cart";
AElement.setAttribute("href", "#");

var iCartElement = document.createElement("I"); 
iCartElement.className = "fa fa-shopping-cart";

var SpanElement = document.createElement("SPAN"); 
SpanElement.innerHTML="Add to cart";

AElement.appendChild(iCartElement);
AElement.appendChild(SpanElement);



OverLayContentdivElement.appendChild(H2Element);
OverLayContentdivElement.appendChild(PElement);
OverLayContentdivElement.appendChild(AElement);



ProductOverLaydivElement.appendChild(OverLayContentdivElement);


SingleProductdivElement.appendChild(ProductInfodivElement);
SingleProductdivElement.appendChild(ProductOverLaydivElement);

//choosedivElement start
var chooseDivElement = document.createElement("DIV"); 
chooseDivElement.className = "choose";

var chooseULList = document.createElement("UL");
chooseULList.className = "nav nav-pills nav-justified";


var chooseLIElement = document.createElement("LI");
 
var chooseAElement = document.createElement("A");
chooseAElement.setAttribute("href", "");

var wishListIelement = document.createElement("I"); 
wishListIelement.className = "fa fa-plus-square";


var chooseSpanElement = document.createElement("SPAN"); 
chooseSpanElement.innerHTML="Add to wishlist";

chooseAElement.appendChild(wishListIelement);
chooseAElement.appendChild(chooseSpanElement);

chooseLIElement.appendChild(chooseAElement);

<!-- ****************************-->
var chooseLI2Element = document.createElement("LI");
 
var chooseA2Element = document.createElement("A");
chooseA2Element.setAttribute("href", "");

var wishListI2element = document.createElement("I"); 
wishListI2element.className = "fa fa-plus-square";


var chooseSpan2Element = document.createElement("SPAN"); 
chooseSpan2Element.innerHTML="Add to compare";

chooseA2Element.appendChild(wishListI2element);
chooseA2Element.appendChild(chooseSpan2Element);

chooseLI2Element.appendChild(chooseA2Element);


<!-- ****************************-->


chooseULList.appendChild(chooseLIElement);
chooseULList.appendChild(chooseLI2Element);

chooseDivElement.appendChild(chooseULList);

ImageWrapperdivElement.appendChild(SingleProductdivElement);
ImageWrapperdivElement.appendChild(chooseDivElement);

FeaturesdivElement.appendChild(ImageWrapperdivElement);

FeaturesparentDiv.insertBefore(FeaturesdivElement, PaginationId);			


}

console.log(product_Names);
}

function productDetails(e){
console.log("Tag h2: " +  e.target.getElementsByTagName("H2")[0].innerHTML);
console.log("Tag P: " +  e.target.getElementsByTagName("P")[0].innerHTML);
var price = e.target.getElementsByTagName("H2")[0].innerHTML;
var productname = e.target.getElementsByTagName("P")[0].innerHTML;
showProductDetails(productname,price);
}