package fcul.pco.eurosplit.main;

import fcul.pco.eurosplit.domain.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Start {
	private static UserCatalog userCatalog;
	private static ExpenseCatalog expenseCatalog;

	public static void main(String[] args) throws ParseException, IOException {
		
		ApplicationConfiguration app = new ApplicationConfiguration();
		Scanner sc = new Scanner(System.in);
		System.out.print("Eurosplit > ");
		String command = sc.nextLine();
		Interp_ interp_ = new Interp_(sc);
		interp_.execute(command, sc);
	}

	public static UserCatalog getUserCatalog() {
		return userCatalog;
	}

}
