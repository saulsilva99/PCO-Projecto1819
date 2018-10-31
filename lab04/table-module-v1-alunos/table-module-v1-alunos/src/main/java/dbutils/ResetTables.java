package dbutils;

import java.io.IOException;
import java.sql.SQLException;

import dataaccess.Persistence;
import dataaccess.PersistenceException;
import facade.startup.SaleSys;

public class ResetTables {

	public void resetCSSDerbyDB() throws IOException, SQLException, PersistenceException {
		try (Persistence persistence = new dataaccess.Persistence(SaleSys.DB_CONNECTION_STRING + ";create=false", 
				"SaleSys", "")) {
			RunSQLScript.runScript (persistence.getConnection(), "data/scripts/resetTables-Derby.sql");
		}
	}
	
	public static void main(String[] args) throws PersistenceException, IOException, SQLException {
		new ResetTables().resetCSSDerbyDB();
	}

}
