package fcul.pco.eurosplit.main;

import fcul.pco.eurosplit.domain.*;
import fcul.pco.eurosplit.main.Interp_;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
/**
 * @author: Saul Silva e Joao Paiva
 */
public class Start {
	private static UserCatalog userCatalog;
	private static ExpenseCatalog expenseCatalog;
	private static SplitCatalog splitCatalog; // prof nao falou de isto
	private static Interp_ interp;

	public static void main(String[] args) throws ParseException, IOException {
		run();

	}

	private static void run() throws IOException {
		// deleteCatalogs(); <-- apagado para test
		Scanner input = new Scanner(System.in);
		initialize(); 
		interp = new Interp_(input);
		String command = ""; 
		do {
			command = interp.nextToken();
			interp.execute(command, input);
		} while (!command.equals("quit"));
	}
	
	/**
	 * @return Instancia de ExpenseCatalog
	 */
	public static ExpenseCatalog getExpenseCatalog() {
		return expenseCatalog;
	}
	
	/**
	 * @return Instancia de UserCatalog
	 */
	public static UserCatalog getUserCatalog() {
		return userCatalog;
	}

	/**
	 * @return Instancia de SplitCatalog
	 */
	public static SplitCatalog getSplitCatalog() {
		return splitCatalog;
	}

	/**
	 * Metodo para inicializar os valores que estao nos ficheiros para
	 * as instancias dos Catalogos.
	 * @throws IOException
	 */
	public static void initialize() throws IOException {
		userCatalog = new UserCatalog();
		splitCatalog = SplitCatalog.getInstance();
		expenseCatalog = new ExpenseCatalog();
		
		try {
			userCatalog.load(); // vai buscar o que tenho escrito no ficheiro
		} catch (IOException i) {
			System.out.println("Ficheiro não foi encontrado, ups.");
		}
		try {
			splitCatalog.load(); // vai buscar o que tenho escrito no ficheiro
		} catch (IOException i) {
			System.out.println("Ficheiro não foi encontrado, ups.");
		}
		try {
			expenseCatalog.load(); // vai buscar o que tenho escrito no ficheiro
		}catch (IOException | ParseException i) {
			System.out.println("Ficheiro não foi encontrado, ups.");
		}

	}
}
