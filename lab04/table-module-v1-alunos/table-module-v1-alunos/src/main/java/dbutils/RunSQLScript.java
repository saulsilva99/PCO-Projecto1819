package dbutils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RunSQLScript {

	/**
	 * An utility class should not have public constructors 
	 */
	private RunSQLScript() {
	}
	
	public static void runScript (Connection connection, String scriptFilename) throws IOException, SQLException {
		try (BufferedReader br = new BufferedReader(new FileReader(scriptFilename))) {
		    String command;
		    int i = 1;
		    while ((command = br.readLine()) != null) {
		        System.out.println(i + ": " + command);
		        i++;
		    	try (Statement statement = connection.createStatement()) {
		    		statement.execute(command);
		    	}
		    }
		}
	}
	
}
