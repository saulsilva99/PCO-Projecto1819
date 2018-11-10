package fcul.pco.eurosplit;

import fcul.pco.eurosplit.domain.*;
import java.time.LocalDateTime;

import java.text.ParseException;
public class Test {

	public static void main(String[] args) throws ParseException {
		User u = new User("saul", "email");
		String oi = "coias@40@saul@email@DateThu Nov 08 22:46:56 GMT 2018\r\n";
		Date dt = new Date(2017,02,30,14,2);
		Expense e = new Expense("a",2,u,dt);
	}

}
