import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.logging.*;
import org.json.simple.JSONObject;

// Extend HttpServlet class
public class SignUpServlet extends HttpServlet {
 
private static final Logger signUpServletLogger = Logger.getLogger("SignUpServletLogger");;
private static FileHandler signUpServletfileHandler;
 
static{
initializeLoggerParams();
}

private static void initializeLoggerParams(){

try{
 signUpServletfileHandler = new FileHandler("./webapps/helloworld/Logs/SignupServlet.%u.%g.txt",1024 * 1024, 10, true);
 signUpServletfileHandler.setFormatter(new LoggerFormatter());
 signUpServletLogger.addHandler(signUpServletfileHandler);
}catch(IOException ex){
	//throw new IOException("test");
}

 }
 
 
  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
  signUpServletLogger.info("inside signup servlet");
      // Set response content type
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
	  
      String userIDKey = new String("userID");
	  JSONObject DBResultJSONObjUserName = new JSONObject();
	  String user_name = request.getParameter("sign_up_user_name");
				String password =request.getParameter("sign_up_password");
				String email = request.getParameter("sign_up_email");
				String MobileNum = request.getParameter("sign_up_Phone Number");
				String DOB = request.getParameter("sign_up_DOB");
				
				try{
					MySQLAccess DbObj = new MySQLAccess();
				
					DBResultJSONObjUserName = DbObj.InsertQuery(user_name,password,email,MobileNum,DOB);
					String DBResultStrUserName = (String) DBResultJSONObjUserName.get("Username");
					if(DBResultStrUserName!=null){
						HttpSession session = request.getSession();
						if (session.isNew()){
							session.setAttribute(userIDKey, DBResultStrUserName);
							signUpServletLogger.info("Session Object is created: " + userIDKey + ": "+ DBResultStrUserName);
							} 
						signUpServletLogger.info("Signed up  User Name" + DBResultStrUserName);
						}
					else{
						DBResultStrUserName =(String) DBResultJSONObjUserName.get("Exception");
						signUpServletLogger.info("Logging in Problem: " + DBResultStrUserName);
						}
					out.println(DBResultStrUserName); 
					}catch(Exception e){
					signUpServletLogger.info("Logging in Problem: " + e.toString());
						//out.println(e.toString());
						}
					}
				}