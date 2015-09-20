import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.logging.*;
import java.util.*;

public class MySQLAccess {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;
  private static final CustomLoggerProperties MySQLAccessLoggerProperties = new CustomLoggerProperties();
  private static Logger MySQLAccessLogger;

static{
initializeLoggerParams();
}
private static void initializeLoggerParams(){

boolean isValidLogger = MySQLAccessLoggerProperties.setLoggerProperties("MysQLAccessLogger","./webapps/helloworld/Logs/MySQLAccess.%u.%g.txt");
	if(isValidLogger){
	MySQLAccessLogger = MySQLAccessLoggerProperties.getLogger();
	}

 }
 
  
  
  public JSONObject InsertQuery( String user_name,String password,String email,String mobileNumber,String DOB) throws Exception {
  JSONObject resultObj=new JSONObject(); 
    try {
	
	
	Class.forName("com.mysql.jdbc.Driver");
			connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/Handicrafts","root","root");
                        statement=connect.createStatement();
						
	//String sql=("insert into (Name,Email,Mobile_number,DOB) values( "'" ++ "'","'" +  + "'","'" +  + "'","'" +  +"'")");
	String sql=("insert into User_Details (Name,Email,Mobile_number,DOB) values('"+user_name+"','"+email+"','"+mobileNumber+"','"+DOB+"')");
                        MySQLAccessLogger.info(sql);
			String User_id_from_Db = null;
			statement.executeUpdate(sql);
                        //String sql=("select " + columns_needed +   " from " + table_name +  " where " + criteria );
                        resultSet=statement.executeQuery("select * from User_Details where Email="+ "'" + email + "'");
						while(resultSet.next())
                        {
						    MySQLAccessLogger.info("User_details_table1: " + resultSet.getString(1)+" User_details_table2: "+resultSet.getString(2));
							User_id_from_Db = resultSet.getString(1);
							resultObj.put("Username",resultSet.getString(2));
                        }
						
		
		sql=("insert into User_Login (User_Id,Password,Email,User_Type_Id) values('"+User_id_from_Db+"','"+password+"','"+email+"',1)");
		statement.executeUpdate(sql);
                        resultSet=statement.executeQuery("select * from User_Login where Email="+ "'"+email + "'");
						
                        while(resultSet.next())
                        {
							//result = resultSet.getString(1) + resultSet.getString(2) + "inside InsertQuery";
                            MySQLAccessLogger.info("User_Login_table1: " + resultSet.getString(1)+"User_Login_table2: "+resultSet.getString(2));
							
                        }
						close();
		}
		catch(Exception ex)
		{
			MySQLAccessLogger.info(ex.toString());
		      resultObj.put("Exception",ex.toString());
			  

		}
	
	return resultObj;
	
	}
      // This will load the MySQL driver, each DB has its own driver
    //  Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      //connect = DriverManager
        //  .getConnection("jdbc:mysql://localhost:3306/Handicrafts?"
            //  + "user=root&password=root");

      // Statements allow to issue SQL queries to the database
      //statement = connect.createStatement();
      // Result set get the result of the SQL query
      /*resultSet = statement
          .executeQuery("select * from Handicrafts.User_Details");
      writeResultSet(resultSet);*/

      // PreparedStatements can use variables and are more efficient
      //preparedStatement = connect
        //  .prepareStatement("insert into  Handicrafts.User_Details values (1, harsha, ?, ?, ? , ?)");
      // "myuser, webpage, datum, summery, User_Details from Handicrafts.User_Details");
      // Parameters start with 1
      //preparedStatement.setString(1, user_name1);
      //preparedStatement.setString(2, user_name2);
      //preparedStatement.setString(3, user_name3);
      //preparedStatement.setString(4, user_name4);
      //preparedStatement.setString(5, user_name5);
      //preparedStatement.executeUpdate();

      //preparedStatement = connect
        //  .prepareStatement("SELECT * from Handicrafts.User_Details");
      //resultSet = preparedStatement.executeQuery();
      //writeResultSet(resultSet);

      // Remove again the insert comment
     /* preparedStatement = connect
      .prepareStatement("delete from Handicrafts.User_Details where myuser= ? ; ");
      preparedStatement.setString(1, "Test");
      preparedStatement.executeUpdate();*/
      
      //resultSet = statement
      //.executeQuery("select * from Handicrafts.User_Details");
      //writeMetaData(resultSet);
      
    //} catch (Exception e) {
      ///throw e;
    //} finally {
     // close();
  //  }

  

  public ResultSet SelectQuery(String table_name,String columns_needed,String criteria)
	{

						 String sql;
						 if(criteria==null)
						 {
						 sql=("select " + columns_needed +   " from " + table_name);
						 }
						 else{
						 sql=("select " + columns_needed +   " from " + table_name +  " where " + criteria );
						 }
						 JSONObject obj=new JSONObject();
				
                 try
		{
			                    
                        
						
						 MySQLAccessLogger.info("Query to be executed: " + sql);
                       
						Class.forName("com.mysql.jdbc.Driver");
						connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/Handicrafts","root","root");
                        statement=connect.createStatement();
			
                        resultSet=statement.executeQuery(sql);
						MySQLAccessLogger.info("Query executed: " + sql);						
						if(!resultSet.first()){
						MySQLAccessLogger.info("Query executed: " +sql+ " Result is: Empty row");
						return null;
						}
						else
						{
						//resultSetToJSONArrayLeftJoing(resultSet,"email",null);
						//return (resultSetToJSONArray(resultSet));
                        return (resultSet);
						/*while(resultSet.next())
                        {
                         Id=resultSet.getString(1);
						 password = resultSet.getString(2);
						 Email = resultSet.getString(3);
                        }*/
						}
  //obj.put("Id",Id);
  //obj.put("password",password);
 //jsonText = obj.toString();                       
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			//jsonText = ex.toString() + sql;
			MySQLAccessLogger.info("Exception" + ex.toString());
			return null;		// have to write code to throw exception to the calling class. so empty row and exception are differentiated errorss

		}
		
	}
  
  
  public ResultSet JOINQuery(String sql)
	{

						 //String sql = "select category.*,subcategory.subcategoryname from category LEFT JOIN subcategory ON(category.category_id = subcategory.category_id) ORDER BY category_id";
						  //System.out.println("Query to be executed: " + sql);
						 MySQLAccessLogger.info("Query to be executed: " + sql);
						 /*if(criteria==null)
						 {
						 sql=("select " + columns_needed +   " from " + table_name);
						 }
						 else{
						 sql=("select " + columns_needed +   " from " + table_name +  " where " + criteria );
						 }
						 JSONObject obj=new JSONObject();*/
				
                 try
		{
			                    
                        
						
						  MySQLAccessLogger.info("Query to be executed before connection " + sql);
                       
						Class.forName("com.mysql.jdbc.Driver");
						connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/Handicrafts","root","root");
                        statement=connect.createStatement();
			
                        resultSet=statement.executeQuery(sql);
						MySQLAccessLogger.info("Query executed after connection: " + resultSet.toString());						
						if(resultSet==null || !resultSet.first()){
						MySQLAccessLogger.info("Query executed: " +sql+ " Result is: Empty row");
						return null;
						}
						else
						{
						MySQLAccessLogger.info("Test else");
						//System.out.println(resultSetToJSONArrayLeftJoing(resultSet).toString());
						return (resultSet);     
							}
							}
		catch(Exception ex)
		{
			//System.out.println(ex);
			//jsonText = ex.toString() + sql;
			System.out.println("Exception" + ex.toString());
			return null;		// have to write code to throw exception to the calling class. so empty row and exception are differentiated errorss

		}
		
	}
	
  
  
  
  
  
  private void writeMetaData(ResultSet resultSet) throws SQLException {
    //   Now get some metadata from the database
    // Result set get the result of the SQL query
    
    System.out.println("The columns in the table are: ");
    
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }

  private void writeResultSet(ResultSet resultSet) throws SQLException {
    // ResultSet is initially before the first data set
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
      String user_id = resultSet.getString("User_id");
      String name = resultSet.getString("Name");
      String email = resultSet.getString("Email");
      Date date = resultSet.getDate("Mobile_number");
      String DOB = resultSet.getString("DOB");
      System.out.println("User: " + user_id);
      System.out.println("Website: " + name);
      System.out.println("Summery: " + email);
      System.out.println("Date: " + date);
      System.out.println("Comment: " + DOB);
    }
  }

  public static JSONArray resultSetToJSONArray(ResultSet resultSet)
            throws Exception {
        JSONArray resultJsonArray = new JSONArray();
		resultSet.beforeFirst();
		//int row_count = 0;
        while (resultSet.next()) {
			//row_count++;
            int total_columns = resultSet.getMetaData().getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i = 0; i < total_columns; i++) {
                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                        .toLowerCase(), resultSet.getObject(i + 1));
						}
						//System.out.println("JSON OBJECT " + obj.toString());
						resultJsonArray.add(obj);
                System.out.println("JSONArray from DB is: \n" + resultJsonArray.toString());
            
        }
		
		
        return resultJsonArray;
    }
	
 public static JSONObject resultSetToJSONArrayLeftJoing(ResultSet resultSet,String ResultJSONObjectKey,String ResultJSONObjectArrayList)
            throws Exception {
        
		resultSet.beforeFirst();
		//int row_count = 0;
		      MySQLAccessLogger.info("Inside resultSetToJSONArrayLeftJoing method");
			  JSONObject TempJSONObj = new JSONObject();
			  //TempJSONObj.put("category_id","");
        while (resultSet.next()) {
			//row_count++;
			//JSONArray resultJsonArray = new JSONArray();
            int total_columns = resultSet.getMetaData().getColumnCount();
            MySQLAccessLogger.info("Iterating resultset");
						
			if(TempJSONObj.get(resultSet.getString(ResultJSONObjectKey)) != null){
			
				//System.out.println("*****************************************" +((ArrayList)((JSONObject)((JSONArray)TempJSONObj.get(resultSet.getString(ResultJSONObjectKey))).get(0)).get("subcategoryname")).toString());
				
				((ArrayList)((JSONObject)(TempJSONObj.get(resultSet.getObject(ResultJSONObjectKey)))).get(ResultJSONObjectArrayList)).add(resultSet.getObject(ResultJSONObjectArrayList));
			}
			else{
			JSONObject ResultJSONArrayJSONObj = new JSONObject();
            for (int i = 0; i < total_columns; i++) {
			MySQLAccessLogger.info("CategoryName VALUE: " + !(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase().equals(ResultJSONObjectKey)));
			if(ResultJSONObjectArrayList != null && (resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase()).equals(ResultJSONObjectArrayList)){
			ArrayList ResultArrayList = new ArrayList();
			ResultArrayList.add(resultSet.getObject(i + 1));
			ResultJSONArrayJSONObj.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), ResultArrayList);
			}
			else if(!(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase().equals(ResultJSONObjectKey))){
			ResultJSONArrayJSONObj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                        .toLowerCase(), resultSet.getObject(i + 1));
						
			}					
						}
						//resultJsonArray.add(ResultJSONArrayJSONObj);	
						TempJSONObj.put(resultSet.getObject(ResultJSONObjectKey),ResultJSONArrayJSONObj);
						}
						//System.out.println("JSON OBJECT " + obj.toString());
						
						
            
        }
		
		MySQLAccessLogger.info("JSONArray from DB is: \n" + TempJSONObj.toString());
        return TempJSONObj;
    }
	
  // You need to close the resultSet
  private void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }

}