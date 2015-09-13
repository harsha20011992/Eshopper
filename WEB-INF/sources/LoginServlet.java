// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.logging.*;
import org.json.simple.JSONObject;


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
	  JSONObject DBResultJSONObjUserName =new JSONObject();
	  DBResultJSONObjUserName = dao.SelectQuery("User_login","*","email='" + request.getParameter("first_name") + "' and password='" + request.getParameter("password")  + "'" );
	  String DBResultStrUserName = (String) DBResultJSONObjUserName.get("Email");
	  if(DBResultStrUserName!=null){
			HttpSession session = request.getSession();
			if (session.isNew()){
				session.setAttribute(userIDKey, DBResultStrUserName);
				loginServletLogger.info("Session Object is created: " + userIDKey + ": "+ DBResultStrUserName);
				} 
			loginServletLogger.info("Logged in User Name" + DBResultStrUserName);
			}
	  else{
			DBResultStrUserName =(String) DBResultJSONObjUserName.get("Error");
			loginServletLogger.info("Logging in Problem: " + DBResultStrUserName);
		}
	  out.println(DBResultStrUserName);

				
				
  }
}