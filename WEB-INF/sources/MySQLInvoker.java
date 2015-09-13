import org.json.simple.JSONObject;
public class MySQLInvoker {
  public static void main(String[] args) throws Exception {
    MySQLAccess dao = new MySQLAccess();
    //dao.InsertQuery("1","6","2","3","4");
	JSONObject testObj=new JSONObject();
	testObj = dao.SelectQuery("User_login","*","2");
	String test = testObj.toString();
	System.out.println(test);
  }

} 
