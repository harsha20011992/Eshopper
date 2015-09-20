import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.sql.ResultSet;

public class MySQLInvoker {
  public static void main(String[] args) throws Exception {
    MySQLAccess dao = new MySQLAccess();
    //dao.InsertQuery("1","6","2","3","4");
	JSONObject resultJsonObject = new JSONObject();
	JSONArray resultJsonArray = new JSONArray();
	ResultSet DBResultSet;
	String category = "kids";
	DBResultSet = dao.JOINQuery("select category.CategoryName,products.ProductName,products.prize from category left join products ON(category.category_id = products.category_id) where category.categoryName = '" +category + "'");
	
	//dao.resultSetToJSONArray(DBResultSet);
	//DBResultSet = dao.SelectQuery("products", "*", null);
	dao.resultSetToJSONArrayLeftJoing(DBResultSet,"productname",null);
	//resultJsonArray=  dao.SelectQuery("User_login","*","email='" + "harsha@zoho.com" + "' and password='" + "harsha"  + "'" );
	//resultJsonObject=  dao.JOINQuery("select products.ProductName,products.prize from category left join products ON(category.category_id = products.category_id) where category.categoryName = 'Kids'","productname",null);
	
	//String test = testObj.toString();
	//System.out.println(test);
  }

} 
