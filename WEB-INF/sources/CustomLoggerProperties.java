import java.util.logging.*;
import java.io.*;
class CustomLoggerProperties{

private Logger customLogger;
private FileHandler customLoggerfileHandler;

Logger getLogger(){
return customLogger;
}


boolean setLoggerProperties(String loggerName,String filePath){
	boolean isLoggerSet=false;
	try{
	
	customLogger = Logger.getLogger(loggerName);
	customLoggerfileHandler = new FileHandler(filePath,1024 * 1024, 10, true);
	customLoggerfileHandler.setFormatter(new LoggerFormatter());
	customLogger.addHandler(customLoggerfileHandler);
	isLoggerSet = true;
	return isLoggerSet;
	}
	catch(IOException ex){
		isLoggerSet = false;
		return isLoggerSet;
		}
}


} 