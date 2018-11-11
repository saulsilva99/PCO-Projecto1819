package fcul.pco.eurosplit;

import fcul.pco.eurosplit.domain.*;

import java.io.IOException;
import java.text.ParseException;

import javax.swing.plaf.synth.SynthSeparatorUI;
public class Test {

	public static void main(String[] args) throws ParseException, IOException {
		/*
		 Primeira Parte
		
		User u1 = new User("saul", "fcmarina@fcul.pt");
		User u2 = new User("julieta", "fcsaul@fcul.pt");
		User u3 = new User("roberto", "fc432423@fcul.pt");
		fcul.pco.eurosplit.domain.UserCatalog userCatalog = new fcul.pco.eurosplit.domain.UserCatalog();

		userCatalog.addUser(u1);
		userCatalog.addUser(u2);
		userCatalog.addUser(u3);
		
		userCatalog.save();
		 */
		 // Segunda parte
		
		fcul.pco.eurosplit.domain.UserCatalog userCatalog = new fcul.pco.eurosplit.domain.UserCatalog();
		userCatalog.load();
		//System.out.println(userCatalog.getUserById("fc432423@fcul.pt"));
		//System.out.println(userCatalog.getUsers().toString());
		System.out.println(userCatalog.hasUserWithId("fcsaul@fcul.pt"));
		System.out.println(userCatalog.hasUserWithId("asda"));
		String test = "asdas asdas";
		System.out.println(test.split(" ")[1]);
		
		
	}

}
