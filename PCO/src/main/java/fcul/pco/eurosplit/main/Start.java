package fcul.pco.eurosplit.main;

import fcul.pco.eurosplit.domain.*;
import fcul.pco.eurosplit.main.Interp_;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Start {
	private static UserCatalog userCatalog;
	private static ExpenseCatalog expenseCatalog;
	private static Interp_ interp;

	public static void main(String[] args) throws ParseException, IOException {

		run();
	}

	private static void run() throws IOException {
		//deleteCatalogs(); <-- apagado para test
		Scanner input = new Scanner(System.in);
		initialize();
		interp = new Interp_(input);
		String command = "";
		do {
			command = interp.nextToken();
			interp.execute(command, input);
		} while (!command.equals("quit"));
	}

	public static UserCatalog getUserCatalog() {
		return userCatalog;
	}

	public static void initialize() throws IOException {
		userCatalog = new UserCatalog();
		try {
			userCatalog.load(); // vai buscar o que tenho escrito no ficheiro
		} catch (IOException i) {
			System.out.println("Ficheiro não foi encontrado, ups.");
		}
	}

}
