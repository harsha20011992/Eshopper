// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.logging.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.sql.ResultSet;


// Extend HttpServlet class
public class ShopPageLoaderServlet extends HttpServlet {

	private static final CustomLoggerProperties ShopPageLoaderSrvltLP = new CustomLoggerProperties();
	private static Logger ShopPageLdrSrvltLgger;

	static {
		initializeLoggerParams();
	}

	private static void initializeLoggerParams() {

		boolean isValidLogger = ShopPageLoaderSrvltLP.setLoggerProperties("ShopPageLdrSrvltLgger", "./webapps/helloworld/Logs/ShopPageLdrSrvltLgger.%u.%g.txt");
		if (isValidLogger) {
			ShopPageLdrSrvltLgger = ShopPageLoaderSrvltLP.getLogger();
		}

	}

	public void doPost(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {
		// Set response content type
		ShopPageLdrSrvltLgger.info("inside doPost Example");
		response.setContentType("text/html");

		//String userIDKey = new String("userID");
		PrintWriter out = response.getWriter();
		MySQLAccess dao = new MySQLAccess();
		JSONObject DR_CategoryJSONObject = new JSONObject();
		JSONObject DR_ProductsJSONObject = new JSONObject();
		JSONObject ResultJSONObject = new JSONObject();

		//DR_CategoryJSONObject = dao.JOINQuery("select category.*,subcategory.subcategoryname from category LEFT JOIN subcategory ON(category.category_id = subcategory.category_id) ORDER BY category_id","categoryname","subcategoryname"); //getting category json
		ResultSet DBCategoriesResultSet = dao.JOINQuery("select category.*,subcategory.subcategoryname from category LEFT JOIN subcategory ON(category.category_id = subcategory.category_id) ORDER BY category_id"); //getting category json
		try{
	DR_CategoryJSONObject = dao.resultSetToJSONArrayLeftJoing(DBCategoriesResultSet,"categoryname","subcategoryname");
}catch(Exception ex){
ShopPageLdrSrvltLgger.info(ex.toString());
}
		
		ShopPageLdrSrvltLgger.info(DR_CategoryJSONObject.toString());
		if (DR_CategoryJSONObject != null) {

			ResultJSONObject.put("category", DR_CategoryJSONObject);

			/*  JSONObject DR_CategoryJSONObject = new JSONObject();
            for (int i = 0; i < itemList.size(); i++) {
            JSONObject itemObj = (JSONObject)itemList.get(i);
            itemId=(String) itemObj.get("ItemId");
            System.out.println(itemId);
            }*/

			//ResultJSONObject.put("category1",DR_CategoryJSONArray);

		} else {
			ResultJSONObject.put("category", null);
		}
		ResultSet DBProductsResultSet = dao.SelectQuery("products", "*", null); //getting category json
try{
	DR_ProductsJSONObject = dao.resultSetToJSONArrayLeftJoing(DBProductsResultSet,"productname",null);
}catch(Exception ex){
ShopPageLdrSrvltLgger.info(ex.toString());
}
	ShopPageLdrSrvltLgger.info(DR_ProductsJSONObject.toString());
		if (DR_ProductsJSONObject != null) {
			ResultJSONObject.put("products", DR_ProductsJSONObject);
		} else {
			ResultJSONObject.put("products", null);
		}
		
		out.print(ResultJSONObject.toString());
	}
	
	 
	public void doGet(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {
		// Set response content type
		ShopPageLdrSrvltLgger.info("inside doGET Example");
		response.setContentType("text/html");

		//String userIDKey = new String("userID");
		PrintWriter out = response.getWriter();
		MySQLAccess dao = new MySQLAccess();
		//JSONObject DR_CategoryJSONObject = new JSONObject();
		JSONObject DR_CategoryProdJSONObject = new JSONObject();
		JSONObject ResultJSONObject = new JSONObject();
		String category = request.getParameter("category");
        ResultSet DBCategoryProdResultSet = dao.JOINQuery("select category.CategoryName,products.ProductName,products.prize from category left join products ON(category.category_id = products.category_id) where category.categoryName = '" +category + "'"); //getting category json
		try{
		DR_CategoryProdJSONObject = dao.resultSetToJSONArrayLeftJoing(DBCategoryProdResultSet,"productname",null);
		}catch(Exception ex){
			ShopPageLdrSrvltLgger.info(ex.toString());
			}
		
		ShopPageLdrSrvltLgger.info(DR_CategoryProdJSONObject.toString());
		if (DR_CategoryProdJSONObject != null) {
			ResultJSONObject.put("products", DR_CategoryProdJSONObject);
		} else {
			ResultJSONObject.put("products", null);
		}
		
		out.print(ResultJSONObject.toString());
	}
	
}