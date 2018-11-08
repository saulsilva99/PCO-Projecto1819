package fcul.pco.eurosplit;

import fcul.pco.eurosplit.domain.*;

import java.text.ParseException;
import java.util.Date;
public class Test {

	public static void main(String[] args) throws ParseException {
		User u = new User("saul", "email");
		String oi = "coias@40@saul@email@DateThu Nov 08 22:46:56 GMT 2018\r\n";
		Date myDate = new Date();
		Expense e = new Expense("coias", 40, u, myDate);
		
		//e.fromString(oi);
		String a = e.toString();
		System.out.println(e.fromString(a));
	}

}
