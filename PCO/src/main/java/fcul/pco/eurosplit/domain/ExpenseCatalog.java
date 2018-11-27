package fcul.pco.eurosplit.domain;

import java.io.IOException;

public class ExpenseCatalog {
	
	public static void initialize() throws IOException{
		fcul.pco.eurosplit.domain.UserCatalog userCatalogs= new UserCatalog();
		try {
			userCatalogs.load();
		}
		catch(IOException i) {
			System.out.println("Ficheiro não foi encontrado, ups.");
		}		
	}
}
