function categoryParser(JSONShopDtlsCategory,JSONsubcategoryname){


          // var Category = String((Object.keys(JSONShopDtls)[0]));
			//var Category_Names = Object.keys(JSONShopDtls[Category]);
			Category_Names = Object.keys(JSONShopDtlsCategory);
			console.log("category name received: " + Category_Names);
			//console.log("First category name: " + JSONShopDtls[Category][Category_Names[0]]);
			
			//var CategoryProps = Object.keys(JSONShopDtls[Category][Category_Names[0]]);
			var CategoryProps = Object.keys(JSONShopDtlsCategory[Category_Names[0]]);
			//console.log("Prop names: " + Object.keys(JSONShopDtls[Category][CategoryProps]));
			//console.log("Subcategory names: " + JSONShopDtls[Category][Category_Names[0]].subcategoryname);
			
			console.log("Subcategory names: " + JSONShopDtlsCategory[Category_Names[0]][JSONsubcategoryname]);
			//var lengthofwomenes = Object.keys(JSONShopDtls.category.womens)[0];
			//alert(lengthofwomenes);
			
			/*var shopCategoryElement = document.getElementById("shopCategory");  
		shopCategoryElement.innerHTML = JSONShopDtls.category[1].categoryname;
		shopUserNameElement.className = "inactiveLink";*/
		
			var parentDiv = document.getElementById("accordian")
			//console.log(parentDiv.innerHTML);
				
			//console.log(childDiv.innerHTML);
			//childDiv.innerHTML = "Test";
			var isSubCategory=false;
			for(var i=0;i<Category_Names.length;i++){
			
			var jsonCategory = Category_Names[i];
			var subcategory = JSONShopDtlsCategory[Category_Names[i]][JSONsubcategoryname];
			console.log(subcategory);
			if(subcategory != ""){
			isSubCategory = true;
			}
			
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
			AElement.setAttribute("href", "#" + Category_Names[i]);
			
			

		
			var SpanElement = document.createElement("SPAN"); 
			SpanElement.className = "categoryName";
			SpanElement.addEventListener("click", function(e){
			CategoryCriteria(e)
			
			});
			
			SpanElement.innerHTML=jsonCategory;
			
			AElement.appendChild(SpanElement);
			
			H4Element.appendChild(AElement);
				
			childDivElement.appendChild(H4Element);
			
			divElement.appendChild(childDivElement);
			
			parentDiv.appendChild(divElement);
			console.log(parentDiv);
			
			
			
			
			if(isSubCategory == true){
				var spanPlusElement = document.createElement("SPAN"); 
				spanPlusElement.className = "badge pull-right";
				
				var iPlusElement = document.createElement("I"); 
				iPlusElement.className = "fa fa-plus";
				
				spanPlusElement.appendChild(iPlusElement);
				AElement.insertBefore(spanPlusElement, AElement.children[0]);
				
				var SubCategorydivElement = document.createElement("DIV"); 
			    SubCategorydivElement.className = "panel-collapse collapse";
				SubCategorydivElement.id =  Category_Names[i] ;
				
				var SubCategorypanelBody = document.createElement("DIV"); 
				SubCategorypanelBody.className = "panel-body"
				
				var SubCategoryULList = document.createElement("UL");
				
				
				for(var j=0; j<subcategory.length ; j++){
				console.log("sucategory: "  + subcategory[j]);
				var SubCategoryLIElement = document.createElement("LI"); 
				
				var SubCategoryAElement = document.createElement("A");

				SubCategoryAElement.innerHTML = subcategory[j];
				
				SubCategoryLIElement.appendChild(SubCategoryAElement);
				
				SubCategoryULList.appendChild(SubCategoryLIElement);
				
				
				
				}
				
				SubCategorypanelBody.appendChild(SubCategoryULList);
				SubCategorydivElement.appendChild(SubCategorypanelBody);
				
				divElement.appendChild(SubCategorydivElement);
				
			}
			
			
			
			
			
								
			
			
			
			
			
			
			
			
			
			
			/*var jsonCategory = JSONShopDtls.category[i].categoryname;
			
			console.log("i: "+ i + "value: " + jsonCategory);
			var childDiv = parentDiv.getElementsByClassName("panel-title")[i].children[0].getElementsByClassName("categoryName")[0];
			console.log("i: "+ i + "value: " + jsonCategory + childDiv.innerHTML);
			childDiv.innerHTML = "test";*/
			isSubCategory=false;
			}
			//category loading ends here
			




}

function CategoryCriteria(e) {
    console.log("E: " + e.target.innerHTML);
	loadCategoryWithCriteria(e.target.innerHTML);
}
	
