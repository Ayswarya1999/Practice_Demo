import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2 {
	
	static Logger logger = LogManager.getLogger(Log4j2.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Logging");
		logger.info("This is it");

	}

}
