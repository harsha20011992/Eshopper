// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.logging.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;


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

		String userIDKey = new String("userID");
		PrintWriter out = response.getWriter();
		MySQLAccess dao = new MySQLAccess();
		JSONArray DR_CategoryJSONArray = new JSONArray();
		JSONArray DR_ProductsJSONArray = new JSONArray();
		JSONObject ResultJSONObject = new JSONObject();

		DR_CategoryJSONArray = dao.SelectQuery("Category", "*", null); //getting category json
		ShopPageLdrSrvltLgger.info(DR_CategoryJSONArray.toString());
		if (DR_CategoryJSONArray != null) {

			ResultJSONObject.put("category", DR_CategoryJSONArray);

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

		DR_ProductsJSONArray = dao.SelectQuery("products", "*", null); //getting category json
		ShopPageLdrSrvltLgger.info(DR_ProductsJSONArray.toString());
		if (DR_ProductsJSONArray != null) {
			ResultJSONObject.put("products", DR_ProductsJSONArray);
		} else {
			ResultJSONObject.put("products", null);
		}
		
		out.print(ResultJSONObject.toString());
	}
}