package fcul.pco.eurosplit.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import fcul.pco.eurosplit.domain.Expense;
import fcul.pco.eurosplit.main.ApplicationConfiguration;

/**
 * @author: Saul Silva e Joao Paiva
 */
public class ExpenseCatalog {
	
	/**
	 * Este metodo tem como objectivo
	 * guarda por cada linha um utilizador num determinado ficheiro 
	 * no seguinte formato "id-idSplit-valorDespesa-[email#nome,...]-data";
	 * @param Expenses - Eh um dicionario de despesas
	 * com chave de numero unico e value de instancia de user.
	 */
	public static void save(Map<Integer, Expense> expenses) throws IOException {
		FileWriter writeToFile = new FileWriter(new File(ApplicationConfiguration.ROOT_DIRECTORY_Expenses));
		int i = 0;
		for (Expense expense : expenses.values()) {
			writeToFile.write(i + "-"+expense.toString() + "\n");
			i++;
		}
		writeToFile.close();  
	} 

	/**
	 * Objectivo de este metodo é ler um ficheiro e colocar numa instancia
	 * de Map para ser usada.
	 * @return: Devolve um dicionário com uma key = Integer
	 * e value = Expense
	 * @throws ParseException 
	 */
	
	@SuppressWarnings("null")
	public static Map<Integer, Expense> load() throws FileNotFoundException, ParseException {
		Map<Integer, Expense> mapExpenseFile = new HashMap<>();
		@SuppressWarnings("resource")
		Scanner inputFromFile = new Scanner(new File(ApplicationConfiguration.ROOT_DIRECTORY_Expenses));
		int i = 0;
		while (inputFromFile.hasNextLine()) {
			String linha = inputFromFile.nextLine();
		
			Expense expense = fcul.pco.eurosplit.domain.Expense.getInstance();
			expense = expense.fromString(linha);
			mapExpenseFile.put(i, expense);
			i++;
		}
		return mapExpenseFile;

	}

}
