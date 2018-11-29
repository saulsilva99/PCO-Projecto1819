package fcul.pco.eurosplit.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import fcul.pco.eurosplit.domain.Expense;

public class ExpenseCatalog {
	/*
	 * Este metodo guarda para um ficheiro "expenses.dat" 
	 * as despesas que existem no dicionário em Integer
	 * com o formato:
	 * "emailDaPessoa-nomeDaPessoa"
	 */
	public static void save(Map<Integer, Expense> expenses) throws IOException {
		
	}
	public static Map<Integer, Expense> load() throws FileNotFoundException {
		//Falta acabar esta função.
		return null;
	}
	
}
