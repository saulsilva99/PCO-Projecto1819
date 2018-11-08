package fcul.pco.eurosplit;

import fcul.pco.eurosplit.domain.*;
 
public class Test {

	public static void main(String[] args) {
		User u = new User("saul", "email@email");
		String user = u.toString();
		u.fromString(user);
	}

}
