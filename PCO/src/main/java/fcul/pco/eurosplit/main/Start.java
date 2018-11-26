package fcul.pco.eurosplit.main;

import fcul.pco.eurosplit.domain.*;

import java.io.IOException;
import java.text.ParseException;

public class Start {
	private static UserCatalog userCatalog;
	// private static ExpenseCatalog expenseCatalog;

	public static void main(String[] args) throws ParseException, IOException {

		/*
		 * 
		 * User u1 = new User("saul", "fcmarina@fcul.pt"); User u2 = new User("julieta",
		 * "fcsaul@fcul.pt"); User u3 = new User("roberto", "fc432423@fcul.pt");
		 * fcul.pco.eurosplit.domain.UserCatalog userCatalog = new
		 * fcul.pco.eurosplit.domain.UserCatalog();
		 * 
		 * userCatalog.addUser(u1); userCatalog.addUser(u2); userCatalog.addUser(u3);
		 * 
		 * userCatalog.save();
		 * 
		 * // Segunda parte
		 */
		fcul.pco.eurosplit.domain.UserCatalog userCatalog = new fcul.pco.eurosplit.domain.UserCatalog();
		userCatalog.load();
		System.out.println(userCatalog.hasUserWithId("fcsaul@fcul.pt"));
		userCatalog.getUserById("fcsaul@fcul.pt");
		userCatalog.getUserById("ola");

	}

	public static UserCatalog getUserCatalog() {
		return userCatalog;
	}

}
