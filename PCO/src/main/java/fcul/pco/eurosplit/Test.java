package fcul.pco.eurosplit;

import fcul.pco.eurosplit.domain.*;

import java.io.IOException;
import java.text.ParseException;
public class Test {

	public static void main(String[] args) throws ParseException, IOException {
		User u1 = new User("saul", "fcmarina@fcul.pt");
		User u2 = new User("Mariana", "fcsaul@fcul.pt");
		Date dt = new Date(2017,02,30,14,2);
		Expense e = new Expense("a",2,u1,dt);
		
		fcul.pco.eurosplit.domain.UserCatalog uc = new UserCatalog();
		uc.addUser(u1);
		uc.addUser(u2);
		fcul.pco.eurosplit.persistence.UserCatalog ucp;
		ucp = new fcul.pco.eurosplit.persistence.UserCatalog();
		ucp.save(uc.getUsers());
		ucp.load();
		 
	}

}
