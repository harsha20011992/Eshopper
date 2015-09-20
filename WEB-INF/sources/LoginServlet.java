// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.logging.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.sql.ResultSet;


// Extend HttpServlet class
public class LoginServlet extends HttpServlet {
 
//private static final Logger loginServletLogger = Logger.getLogger("LoginServletLogger");;
//private static FileHandler loginServletfileHandler;

private static final CustomLoggerProperties loginServletLoggerProperties = new CustomLoggerProperties();
private static Logger loginServletLogger;

static{
initializeLoggerParams();
}

private static void initializeLoggerParams(){

/*try{
 loginServletfileHandler = new FileHandler("./webapps/helloworld/Logs/LoginServlet.%u.%g.txt",1024 * 1024, 10, true);
 loginServletfileHandler.setFormatter(new LoggerFormatter());
 loginServletLogger.addHandler(loginServletfileHandler);
}catch(IOException ex){
	//throw new IOException("DBResultStrUserName");
}*/

boolean isValidLogger = loginServletLoggerProperties.setLoggerProperties("LoginServletLogger","./webapps/helloworld/Logs/LoginServlet.%u.%g.txt");
	if(isValidLogger){
	loginServletLogger = loginServletLoggerProperties.getLogger();
	}

 }
 
 
 
 
 
 
  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      // Set response content type
	  loginServletLogger.info("inside doPost Example");
	  response.setContentType("text/html");
	  
	  String userIDKey = new String("userID");
	  PrintWriter out = response.getWriter();	  
	  MySQLAccess dao = new MySQLAccess();
	  JSONArray DR_UserNameJSONArray = new JSONArray();
	  //DR_UserNameJSONArray = dao.SelectQuery("User_login","email","email='" + request.getParameter("first_name") + "' and password='" + request.getParameter("password")  + "'" );
	  ResultSet DBResultSet = dao.SelectQuery("User_login","email","email='" + request.getParameter("first_name") + "' and password='" + request.getParameter("password")  + "'" );
	  try{
	  DR_UserNameJSONArray = dao.resultSetToJSONArray(DBResultSet);
	  
	  }catch(Exception ex){
	  loginServletLogger.info(ex.toString());
	  }
	  if(DR_UserNameJSONArray!=null){
	  JSONObject DR_UserNameJSONObject = new JSONObject();
	  DR_UserNameJSONObject  = (JSONObject) DR_UserNameJSONArray.get(0);
	  String DR_UserName = (String) DR_UserNameJSONObject.get("email");
	  out.println(DR_UserName);
	  /*String itemId;
            for (int i = 0; i < itemList.size(); i++) {
            JSONObject itemObj = (JSONObject)itemList.get(i);
            itemId=(String) itemObj.get("ItemId");
            System.out.println(itemId);
            }*/
	  }
	  else {
	  out.println("USER_NOT_REGISTERED");
	  }
	  
	  //String DBResultStrUserName =" test";
	  
	 /*  for(int i=0; i<DR_UserNameJSONArray.size(); i++)
        {
            DR_UserNameJSONObject  = (JSONObject) DR_UserNameJSONArray.get(i);
            loginServletLogger.info("p",p);


        }*/
	  
	 /* String DBResultStrUserName = (String) DR_UserNameJSONArray.get("Email");
	  if(DBResultStrUserName!=null){
			HttpSession session = request.getSession();
			if (session.isNew()){
				session.setAttribute(userIDKey, DBResultStrUserName);
				loginServletLogger.info("Session Object is created: " + userIDKey + ": "+ DBResultStrUserName);
				} 
			loginServletLogger.info("Logged in User Name" + DBResultStrUserName);
			}
	  else{
			DBResultStrUserName =(String) DR_UserNameJSONArray.get("Error");
			loginServletLogger.info("Logging in Problem: " + DBResultStrUserName);
		}*/
	  

				
				
  }
}